package com.fpl.core.request.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fpl.core.request.IRequestMailSender;
import com.fpl.domain.Domain;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.mail.MailHandler;
import com.fpl.mail.MailHandlerFactory;
import com.fpl.mail.MailInfo;
import com.fpl.mail.MailType;
import com.fpl.parallel.ParallelTaskExecutionType;
import com.fpl.parallel.ParallelTaskExecutor;
import com.fpl.parallel.ParallelTaskExecutorFactory;
import com.fpl.parallel.Task;
import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.persistence.domain.IDomainDAO;
import com.fpl.persistence.financialplanner.IFinancialPlannerDAO;
import com.fpl.profile.customer.Customer;
import com.fpl.request.Request;
import com.fpl.request.RequestStatusReference;
import com.fpl.util.DateUtil;

public class RequestCreationMailSender implements IRequestMailSender<Request> {
	
	private IFinancialPlannerDAO fplDao;
	private IDomainDAO domainDAO;
	private ICustomerDAO customerDAO;
	
	@Override
	public void sendMail(final Request request) {
		final Domain domain = domainDAO.get(request.getDomainId());
		final Customer customer = customerDAO.get(request.getCustomerId());
		final String name = customer.getPersonalData().getName();
		final String date = DateUtil.getFormattedDate(request.getCreatedDate());
		final Set<RequestStatusReference> requestReferences = request.getRequestReferenceList();
		final List<Task<Void>> taskList = new ArrayList<Task<Void>>();
		final MailHandler mailHandler = MailHandlerFactory.getMailSender(MailType.SSL);
		int count = 0;
		for(final RequestStatusReference reference : requestReferences) {
			
			if(reference.isSendToFP())
			{
			final MailInfo mailInfo = getMailInfo(reference.getFinPlannerId(), date, domain.getName(), name);
			final Task<Void> task = new Task<Void>("RequesCreationMailSendingTask-"+count) {
				@Override
				protected Void execute() {
					mailHandler.sendMailWithDefaultUser(mailInfo);
					return null;
				}
			};
			count = count+1;
			taskList.add(task);
			}
		}
		final ParallelTaskExecutor executor = ParallelTaskExecutorFactory.getInstance().getParallelTaskExecutor(ParallelTaskExecutionType.SIMPLE_EXECUTOR);
		executor.executeTask(taskList);
	}
	
	public MailInfo getMailInfo(final Long fplId, final String createdDate, final String domainName, final String name) {
		final FinancialPlanner financialPlanner = fplDao.get(fplId);
		final String emailId = financialPlanner.getPersonalData().getEmail();
		final MailInfo mailInfo = new MailInfo();
		mailInfo.setToAddress(emailId);
		mailInfo.setSubject("New Request Information");
		final StringBuilder builder = new StringBuilder();
		builder.append("Request Type: ").append(domainName).append("\n");
		builder.append("Request Created Date: ").append(createdDate).append("\n");
		builder.append("Customer Name: ").append(name).append("\n");
		mailInfo.setMessage(builder.toString());
		return mailInfo;
	}

	/**
	 * @param fplDao the fplDao to set
	 */
	public void setFplDao(final IFinancialPlannerDAO fplDao) {
		this.fplDao = fplDao;
	}

	/**
	 * @param domainDAO the domainDAO to set
	 */
	public void setDomainDAO(final IDomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}

	/**
	 * @param customerDAO the customerDAO to set
	 */
	public void setCustomerDAO(final ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
}

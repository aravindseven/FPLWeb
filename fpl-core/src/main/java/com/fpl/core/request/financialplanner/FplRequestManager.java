package com.fpl.core.request.financialplanner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fpl.common.AbstractTransformer;
import com.fpl.core.request.RequestActivityDTO;
import com.fpl.core.request.RespondRequestPV;
import com.fpl.core.request.customer.CustomerDashboardPV;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.persistence.financialplanner.IFinancialPlannerDAO;
import com.fpl.persistence.request.IRequestDAO;
import com.fpl.persistence.request.IRequestStatusRefQueryDAO;
import com.fpl.persistence.status.IStatusDAO;
import com.fpl.profile.customer.Customer;
import com.fpl.request.Request;
import com.fpl.request.RequestFplConfig;
import com.fpl.request.RequestStatusReference;
import com.fpl.status.Status;
import com.fpl.util.CollectionUtil;
import com.fpl.util.CollectionUtil.IPredicate;

public class FplRequestManager implements IFPLRequestManager {
	
	private IStatusDAO statusDAO;
	private IRequestDAO requestDAO;
	private IRequestStatusRefQueryDAO requestStatusRefQueryDAO;
	private ICustomerDAO customerDAO;
	private IFinancialPlannerDAO financialPlannerDAO; 
	private AbstractTransformer<RespondRequestPV, RequestStatusReference> transformer;
	
	@Override
	public void respondRequest(final RespondRequestPV respondRequest) {
		final RequestStatusReference requestStatusRef = transformer.transform(respondRequest);
		final Request request = requestDAO.get(requestStatusRef.getRequestId());
		request.getRequestReferenceList().add(requestStatusRef);
		final Set<RequestFplConfig> fplConfigs = request.getRequestFplConfigList();
		RequestFplConfig requestFplConfig = CollectionUtil.find(fplConfigs, new IPredicate<RequestFplConfig>() {
			@Override
			public boolean evaluate(final RequestFplConfig requestFplConfig) {
				return requestFplConfig.getFinPlannerId().equals(requestStatusRef.getFinPlannerId());
			}
		});
		if(requestFplConfig==null)
		{
			requestFplConfig=new RequestFplConfig();
		}
		requestFplConfig.setStatusId(requestStatusRef.getStatusId());
		requestFplConfig.setDate(new Timestamp(System.currentTimeMillis()));
		request.setCurrentStatusId(requestStatusRef.getStatusId());
		request.setUpdatedDate(new Date());
		requestDAO.update(request, true);
		//TODO: add the mail sending to customer
	}

	@Override
	public Collection<RequestActivityDTO> getRespondedFPL(final Long requestId) {
		final Collection<RequestActivityDTO> finalList = new ArrayList<RequestActivityDTO>();
		final Collection<RequestActivityDTO> respondedFPLList =  requestStatusRefQueryDAO.getRespondedFPL(requestId);
		for(final RequestActivityDTO requestActivity : respondedFPLList) {
			final Status status = statusDAO.get(Long.valueOf(requestActivity.getCurrentStatus()));
			requestActivity.setCurrentStatus(status.getDisDescription());
		}
//		final Collection<RequestActivityDTO> custRespondedList = CollectionUtil.select(respondedFPLList, new IPredicate<RequestActivityDTO>() {
//			@Override
//			public boolean evaluate(final RequestActivityDTO activityDTO) {
//				return activityDTO.getCurrentStatus().equalsIgnoreCase("ST_07");
//			}
//		});
//		finalList.addAll(custRespondedList);
//		final Collection<RequestActivityDTO> custNotRespondedList = CollectionUtil.selectRejected(respondedFPLList, new IPredicate<RequestActivityDTO>() {
//			@Override
//			public boolean evaluate(final RequestActivityDTO activityDTO) {
//				return activityDTO.getCurrentStatus().equalsIgnoreCase("ST_07");
//			}
//		});
//		Collections.sort((List<RequestActivityDTO>)custNotRespondedList, new Comparator<RequestActivityDTO>() {
//			@Override
//			public int compare(final RequestActivityDTO dto, final RequestActivityDTO anotherDto) {
//				return dto.getStatus().compareTo(anotherDto.getStatus());
//			}
//		});
//		finalList.addAll(custNotRespondedList);
		return respondedFPLList;
	}
	
	
	
	@Override
	public List<FPLDashboardPV> fetchFPLDashboardDetails(Long loginId) {
		
		List<FPLDashboardPV> dashboardPVs=new ArrayList<FPLDashboardPV>();
		FinancialPlanner planner=financialPlannerDAO.getFinancialPlannerByLoginId(loginId);
		
		Collection<FPLDashboardPV> fplDashboardPVs=requestStatusRefQueryDAO.getRequestDashboardCustomerDetails(planner.getId());
		
		dashboardPVs.addAll(fplDashboardPVs);
		
		return dashboardPVs;
		
	}

	public List<CustomerDashboardPV> fetchCustomerDashboardDetails(final Long loginId)
	{
		List<CustomerDashboardPV> customerDashboardPVs=new ArrayList<CustomerDashboardPV>();
		Customer customer=customerDAO.getCustomerLoginId(loginId);
		Collection<Request> requestList=requestDAO.getActiveRequestByCustomerId(customer.getId());
		
		if(requestList!=null)
		{
			for(Request request:requestList)
			{
			
				
				CustomerDashboardPV customerDashboardPV=new CustomerDashboardPV();
				customerDashboardPV.setRequestId(request.getId().toString());
				customerDashboardPV.setType(request.getDomain().getName());
				customerDashboardPV.setCreationDate(request.getCreatedDate());
				customerDashboardPV.setStatus(request.getStatus().getDisDescription());
				
				final List<RequestActivityDTO> respondedFPLList = (List<RequestActivityDTO>)requestStatusRefQueryDAO.getRequestDashboardDetails(request.getId());
				
				customerDashboardPV.setRequestFPLList(respondedFPLList);
				customerDashboardPV.setSecondRequestFPLList(respondedFPLList);
				customerDashboardPVs.add(customerDashboardPV);
			}	
		}	
		
		return customerDashboardPVs;
	}
	
	/**
	 * @param transformer the transformer to set
	 */
	public void setTransformer(final AbstractTransformer<RespondRequestPV, RequestStatusReference> transformer) {
		this.transformer = transformer;
	}

	/**
	 * @param requestDAO the requestDAO to set
	 */
	public void setRequestDAO(final IRequestDAO requestDAO) {
		this.requestDAO = requestDAO;
	}

	/**
	 * @param statusDAO the statusDAO to set
	 */
	public void setStatusDAO(final IStatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}
	
	/**
	 * @param requestStatusRefQueryDAO the requestStatusRefQueryDAO to set
	 */
	public void setRequestStatusRefQueryDAO(final IRequestStatusRefQueryDAO requestStatusRefQueryDAO) {
		this.requestStatusRefQueryDAO = requestStatusRefQueryDAO;
	}

	public void setCustomerDAO(ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public void setFinancialPlannerDAO(IFinancialPlannerDAO financialPlannerDAO) {
		this.financialPlannerDAO = financialPlannerDAO;
	}

	
	
	
}

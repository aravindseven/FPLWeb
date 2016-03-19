package com.fpl.core.request.customer;

import com.fpl.common.AbstractTransformer;
import com.fpl.core.request.IRequestMailSender;
import com.fpl.persistence.request.IRequestDAO;
import com.fpl.request.Request;

public class RequestCreator implements IRequestCreator {
	
	private IRequestDAO requestDAO;
	private AbstractTransformer<NewRequestPV, Request> transformer;
	private IRequestMailSender<Request> mailSender;
	
	@Override
	public NewRequestPV saveRequest(final NewRequestPV requestPV) {
		final Request request = transformer.transform(requestPV);
		requestDAO.saveOrUpdate(request, false);
		requestPV.setRequestId(request.getId().toString());
		requestPV.setRequestIdText("REQ"+String.format("%010d", Integer.parseInt(requestPV.getRequestId())));
		requestPV.setCreationDate(request.getCreatedDate());
		
		if(requestPV.isSendToFpl()) {
			mailSender.sendMail(request);
		}
		return requestPV;
	}

	/**
	 * @param requestDAO the requestDAO to set
	 */
	public void setRequestDAO(final IRequestDAO requestDAO) {
		this.requestDAO = requestDAO;
	}

	/**
	 * @param transformer the transformer to set
	 */
	public void setTransformer(
			final AbstractTransformer<NewRequestPV, Request> transformer) {
		this.transformer = transformer;
	}

	/**
	 * @param mailSender the mailSender to set
	 */
	public void setMailSender(final IRequestMailSender<Request> mailSender) {
		this.mailSender = mailSender;
	}
	
}

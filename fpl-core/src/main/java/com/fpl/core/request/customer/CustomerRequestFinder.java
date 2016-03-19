package com.fpl.core.request.customer;

import java.util.Collection;

import com.fpl.common.AbstractTransformer;
import com.fpl.core.request.IRequestFinder;
import com.fpl.core.request.RequestListPV;
import com.fpl.core.request.SearchRequestParam;
import com.fpl.core.request.support.AbstractRequestFinder;
import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.profile.customer.Customer;
import com.fpl.request.Request;
import com.fpl.util.DateUtil;

public class CustomerRequestFinder extends AbstractRequestFinder implements IRequestFinder {
	
	private ICustomerDAO customerDAO;
	
	@Override
	public Collection<RequestListPV> getRequestList(final Long loginId) {
		final Customer customer = customerDAO.getCustomerLoginId(loginId);
		final Collection<Request> requestList = requestDAO.getRequestByCustomerId(customer.getId());
		return new AbstractTransformer<Request, RequestListPV>() {
			@Override
			public RequestListPV transform(final Request request) {
				final RequestListPV requestListPV = new RequestListPV();
				requestListPV.setId(request.getId()+"");
				requestListPV.setCreatedDate(request.getCreatedDate());
				requestListPV.setCurrentStatus(request.getStatus().getDescription());
				requestListPV.setStatusDisDesc(request.getStatus().getDisDescription());
				requestListPV.setDescription(request.getDescription());
				requestListPV.setDomainName(request.getDomain().getName());
				requestListPV.setDomainId(request.getDomain().getId().toString());
				requestListPV.setLocation(request.getLocation());
				requestListPV.setUpdatedDate(DateUtil.getFormattedDate(request.getUpdatedDate()));
				return requestListPV;
			}
		}.transform(requestList);
	}
	
	@Override
	public Collection<RequestListPV> getRequestList(final SearchRequestParam searchRequest) {
		final Customer customer = customerDAO.getCustomerLoginId(Long.valueOf(searchRequest.getId()));
		searchRequest.setId(""+customer.getId());
		final Collection<Request> requestList = requestDAO.getRequestList(searchRequest);
		return new AbstractTransformer<Request, RequestListPV>() {
			@Override
			public RequestListPV transform(final Request request) {
				final RequestListPV requestListPV = new RequestListPV();
				requestListPV.setId(request.getId()+"");
				requestListPV.setCreatedDate(request.getCreatedDate());
				requestListPV.setCurrentStatus(request.getStatus().getDescription());
				requestListPV.setStatusDisDesc(request.getStatus().getDisDescription());
				requestListPV.setDescription(request.getDescription());
				requestListPV.setDomainName(request.getDomain().getName());
				requestListPV.setLocation(request.getLocation());
				requestListPV.setUpdatedDate(DateUtil.getFormattedDate(request.getUpdatedDate()));
				return requestListPV;
			}
		}.transform(requestList);
	}
	
	/**
	 * @param customerDAO the customerDAO to set
	 */
	public void setCustomerDAO(final ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
}

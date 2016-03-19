package com.fpl.core.request.customer;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fpl.common.AbstractTransformer;
import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.persistence.request.IRequestDAO;
import com.fpl.persistence.status.IStatusDAO;
import com.fpl.profile.customer.Customer;
import com.fpl.request.Request;
import com.fpl.request.RequestFplConfig;
import com.fpl.request.RequestStatusReference;
import com.fpl.util.StringUtil;

public class RequestTransformer extends AbstractTransformer<NewRequestPV, Request> {
	
	private IStatusDAO statusDAO;
	private ICustomerDAO customerDAO;
	private IRequestDAO requestDAO;
	
	@Override
	public Request transform(final NewRequestPV newRequest) {
		
	    Request request = new Request();
	    Long requestId=0l;
	    
	    if(StringUtil.isNotEmpty(newRequest.getRequestId()))
	    {
	    	try {
				requestId=Long.parseLong(newRequest.getRequestId());
			} catch (NumberFormatException e) {
				// DO nothing
			}
	 	}
	    
	    if(requestId>0)
	    {
	    	request=requestDAO.get(requestId);

	    	request.setDomainId(Long.valueOf(newRequest.getType()));
	    	request.setPostalCode(newRequest.getPostalCode());
	    	request.setLocation(newRequest.getLocation());
	    	request.setDescription(newRequest.getDescription());
	    	request.setKeyword(newRequest.getKeyword());
	    	request.setUpdatedDate(new Date());
	    	Long currentStatusId= statusDAO.getStatusByDisc("ST_04").getId();
	    	
	    	if(newRequest.isSendToFpl()) {
	    		currentStatusId = statusDAO.getStatusByDisc("ST_00").getId();
	    	
	    	} 
	    	Set<RequestStatusReference> newStatusSet=getRequestStatusReference(newRequest, currentStatusId);
	    	if(request.getRequestReferenceList()!=null && newStatusSet!=null && !newStatusSet.isEmpty())
	    	{
	    		request.getRequestReferenceList().addAll(newStatusSet);
	    	}else if(newStatusSet!=null && !newStatusSet.isEmpty())
	    	{
	    		request.setRequestReferenceList(newStatusSet);
	    	}
	    	
	    	Set<RequestFplConfig> newFplSet=getRequestFplConfig(newRequest, currentStatusId);
	    	if(request.getRequestFplConfigList()!=null && newFplSet!=null && !newFplSet.isEmpty())
	    	{
	    		request.getRequestFplConfigList().addAll(newFplSet);
	    	}else if(newFplSet!=null && !newFplSet.isEmpty())
	    	{
	    		request.setRequestFplConfigList(newFplSet);
	    	}
	    	
	    }else
	    {
	    	request.setDomainId(Long.valueOf(newRequest.getType()));
	    	request.setPostalCode(newRequest.getPostalCode());
	    	request.setLocation(newRequest.getLocation());
	    	request.setCreatedDate(new Date());
	    	request.setUpdatedDate(new Date());
	    	if(newRequest.isSendToFpl()) {
	    		final Long currentStatusId = statusDAO.getStatusByDisc("ST_00").getId();
	    		request.setCurrentStatusId(currentStatusId);
	    	} else {
	    		final Long currentStatusId = statusDAO.getStatusByDisc("ST_04").getId();
	    		request.setCurrentStatusId(currentStatusId);
	    	}
	    	request.setDescription(newRequest.getDescription());
	    	request.setKeyword(newRequest.getKeyword());
	    	final Customer customer = customerDAO.getCustomerLoginId(Long.valueOf(newRequest.getLoginId()));
	    	request.setCustomerId(customer.getId());
	    	if(StringUtil.isNotEmpty(newRequest.getFollowUp())) {
	    		request.setNotification(Long.valueOf("0"));
	    	} else {
	    		request.setNotification(Long.valueOf("1"));
	    	}
	    	request.setRequestReferenceList(getRequestStatusReference(newRequest, request.getCurrentStatusId()));
	    	request.setRequestFplConfigList(getRequestFplConfig(newRequest, request.getCurrentStatusId()));
	    }
		return request;
	}
	
	private Set<RequestStatusReference>  getRequestStatusReference(final NewRequestPV newRequest, final Long statusId) {
		Set<RequestStatusReference> requestStatusReferences = null;
		final Collection<String> fplList = newRequest.getFplList();
		if(fplList != null && !fplList.isEmpty()) {
			requestStatusReferences = new HashSet<RequestStatusReference>();
			final Collection<RequestStatusReference> references = new AbstractTransformer<String, RequestStatusReference>() {
				@Override
				public RequestStatusReference transform(final String id) {
					final RequestStatusReference reference = new RequestStatusReference();
					reference.setDate(new Date());
					reference.setFinPlannerId(Long.valueOf(id));
					reference.setStatusId(statusId);
					reference.setNote("Requested send to financial planner ");
					reference.setSendToFP(true);
					return reference;
				}
			}.transform(fplList);
			requestStatusReferences.addAll(references);
		}
		return requestStatusReferences;
	}
	
	private Set<RequestFplConfig>  getRequestFplConfig(final NewRequestPV newRequest, final Long statusId) {
		Set<RequestFplConfig> requestFplConfigs = null;
		final Collection<String> fplList = newRequest.getFplList();
		if(fplList != null && !fplList.isEmpty()) {
			requestFplConfigs = new HashSet<RequestFplConfig>();
			final Collection<RequestFplConfig> configs = new AbstractTransformer<String, RequestFplConfig>() {
				@Override
				public RequestFplConfig transform(final String id) {
					final RequestFplConfig requestFplConfig = new RequestFplConfig();
					requestFplConfig.setDate(new Timestamp(System.currentTimeMillis()));
					requestFplConfig.setFinPlannerId(Long.valueOf(id));
					requestFplConfig.setStatusId(statusId);
					return requestFplConfig;
				}
			}.transform(fplList);
			requestFplConfigs.addAll(configs);
		}
		return requestFplConfigs;
	}
	
	/**
	 * @param statusDAO the statusDAO to set
	 */
	public void setStatusDAO(final IStatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}

	/**
	 * @param customerDAO the customerDAO to set
	 */
	public void setCustomerDAO(final ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public void setRequestDAO(IRequestDAO requestDAO) {
		this.requestDAO = requestDAO;
	}
	
	
}

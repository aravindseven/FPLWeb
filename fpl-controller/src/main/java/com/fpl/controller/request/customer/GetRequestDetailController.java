package com.fpl.controller.request.customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.financialplanner.PartialUserView;
import com.fpl.core.profile.customer.ICustomerFinder;
import com.fpl.core.request.IRequestFinder;
import com.fpl.core.request.RequestActivityDTO;
import com.fpl.core.request.RequestListPV;
import com.fpl.core.request.customer.NewRequestPV;
import com.fpl.core.request.financialplanner.IFPLRequestManager;
import com.fpl.core.request.financialplanner.IRequestFPLFinder;
import com.fpl.domain.Domain;
import com.fpl.persistence.domain.IDomainDAO;
import com.fpl.profile.customer.Customer;
import com.fpl.util.JsonUtil;

public class GetRequestDetailController extends AjaxBaseController {
	
	private IRequestFinder requestFinder;
	private ICustomerFinder customerFinder;
	private IRequestFPLFinder fplFinder;
	private IFPLRequestManager requestManager; 
	private IDomainDAO domainDAO;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final String requestId = request.getParameter("requestId");
		System.out.println("requestId: "+ requestId);
		final RequestListPV fplRequest =  requestFinder.getRequestInfo(Long.valueOf(requestId));
		
		
		Customer customerPV = customerFinder.getCustomerId(Long.parseLong(fplRequest.getCustomerId()));

		final NewRequestPV newRequest =new NewRequestPV();
		newRequest.setType(fplRequest.getDomainId());
		newRequest.setTypeDescription(fplRequest.getDomainName());
		newRequest.setRequestId(fplRequest.getId());
		newRequest.setCreationDate(fplRequest.getCreatedDate());
		newRequest.setDescription(fplRequest.getDescription());
		newRequest.setKeyword(fplRequest.getKeyword());
		newRequest.setPostalCode(fplRequest.getPostalCode());
		newRequest.setLocation(fplRequest.getLocation());
		if((newRequest.getLatitude()==null || newRequest.getLatitude()==0 || newRequest.getLongitude()==null || newRequest.getLongitude()==0)  && (customerPV!=null && customerPV.getPersonalData()!=null))
		{
			newRequest.setLongitude(customerPV.getPersonalData().getAddress().getLongitude());
			newRequest.setLatitude(customerPV.getPersonalData().getAddress().getLatitude());
		}
		
		final List<PartialUserView> partialFPLViews =new ArrayList<PartialUserView>();
		partialFPLViews.addAll(fplFinder.getFPListByLocation(newRequest));
		
		
		Collection<RequestActivityDTO> activityDTOs=requestManager.getRespondedFPL(Long.parseLong(fplRequest.getId()));
		
		if(activityDTOs!=null && !activityDTOs.isEmpty())
		{
			for(RequestActivityDTO activityDTO:activityDTOs)
			{
				PartialUserView partialUserView=new PartialUserView();
				partialUserView.setId(activityDTO.getFplId());
				if(partialFPLViews.contains(partialUserView))
				{
					partialFPLViews.get(partialFPLViews.indexOf(partialUserView)).setRequestSent(1);
					
				}else
				{
					
					final PartialUserView partialFPLView = new PartialUserView();
					partialFPLView.setId(activityDTO.getFplId());
					partialFPLView.setFirstName(activityDTO.getFpName());
					partialFPLView.setLocation(activityDTO.getLocation());
					partialFPLView.setRequestSent(1);
					partialFPLView.setOnline(true);
					partialFPLViews.add(partialFPLView);

				}
			}
		}
		
		final Map jsonMap = new HashMap();
	
		final Collection<Domain> domains =  domainDAO.getAllEntities();
		jsonMap.put("DOMAIN", domains);
		jsonMap.put("request", newRequest);
		jsonMap.put("respList",partialFPLViews);
		jsonMap.put("requestFPLList",activityDTOs);
		
		resMap.put("res", JsonUtil.toJsonString(jsonMap));
		return new ModelAndView("fplajax_domain", resMap);
	}

	/**
	 * @param requestFinder the requestFinder to set
	 */
	public void setRequestFinder(final IRequestFinder requestFinder) {
		this.requestFinder = requestFinder;
	}

	

	public void setCustomerFinder(ICustomerFinder customerFinder) {
		this.customerFinder = customerFinder;
	}

	public void setFplFinder(IRequestFPLFinder fplFinder) {
		this.fplFinder = fplFinder;
	}

	public void setRequestManager(IFPLRequestManager requestManager) {
		this.requestManager = requestManager;
	}
	
	/**
	 * @param domainDAO the domainDAO to set
	 */
	public void setDomainDAO(final IDomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}	
	
}

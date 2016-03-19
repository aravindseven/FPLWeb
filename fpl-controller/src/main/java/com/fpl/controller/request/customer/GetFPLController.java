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
import com.fpl.controller.support.UserSessionKey;
import com.fpl.core.financialplanner.PartialUserView;
import com.fpl.core.request.RequestActivityDTO;
import com.fpl.core.request.customer.NewRequestPV;
import com.fpl.core.request.financialplanner.IFPLRequestManager;
import com.fpl.core.request.financialplanner.IRequestFPLFinder;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.UserType;
import com.fpl.profile.customer.CustomerPV;
import com.fpl.services.profile.IProfileFetcher;
import com.fpl.util.JsonUtil;

public class GetFPLController extends AjaxBaseController {
	
	private IRequestFPLFinder fplFinder;
	private Map<String, IProfileFetcher> fetcherMap;
	private IFPLRequestManager requestManager; 
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = (UserLoginInfo) get(request,USER_LOGIN_KEY);
		if(loginInfo.getUserType().equals(UserType.CUST_CORPORATE) || loginInfo.getUserType().equals(UserType.CUST_INDIVIDUAL)) {

			final IProfileFetcher  fetcher = fetcherMap.get(loginInfo.getUserType().getUser());
			CustomerPV customerPV = fetcher.getProfilePageView(loginInfo.getLoginCredentialid());
			final String editRequest = request.getParameter("EDIT");
			final String requestJson = request.getParameter("requestJson");
			System.out.println(requestJson);
			final NewRequestPV newRequest = JsonUtil.convertPojo(requestJson, NewRequestPV.class);
			put(request, UserSessionKey.NEW_REQUEST_PV, newRequest);
			if((newRequest.getLatitude()==null || newRequest.getLatitude()==0 || newRequest.getLongitude()==null || newRequest.getLongitude()==0)  && (customerPV!=null && customerPV.getAddressPV()!=null))
			{
				newRequest.setLongitude(customerPV.getAddressPV().getLongitude());
				newRequest.setLatitude(customerPV.getAddressPV().getLatitude());
			}
			
			final Collection<PartialUserView> partialFPLViewList =  fplFinder.getFPListByLocation(newRequest);
			
			List<PartialUserView> partialFPLViews=new ArrayList<PartialUserView>();
			partialFPLViews.addAll(partialFPLViewList);
			if(editRequest!=null && "EDIT".equalsIgnoreCase(editRequest))
			{
				Collection<RequestActivityDTO> activityDTOs=requestManager.getRespondedFPL(Long.parseLong(newRequest.getRequestId()));
				
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

			}
			
			resMap.put("res", JsonUtil.toJsonString(partialFPLViews));
		}
		return new ModelAndView("fplajax_GetFPLList", resMap);
	}
	
	/**
	 * @param fplFinder the fplFinder to set
	 */
	public void setFplFinder(final IRequestFPLFinder fplFinder) {
		this.fplFinder = fplFinder;
	}

	public void setFetcherMap(Map<String, IProfileFetcher> fetcherMap) {
		this.fetcherMap = fetcherMap;
	}

	public void setRequestManager(IFPLRequestManager requestManager) {
		this.requestManager = requestManager;
	}

	
	
	
}
package com.fpl.controller.admin.advertisement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.subsciption.advertisement.AdvSubscriptionListPV;
import com.fpl.core.subsciption.advertisement.IAdSubscriptionManager;
import com.fpl.util.JsonUtil;

public class GetAdSubscriptionController extends AjaxBaseController {
	
	@Autowired
	private IAdSubscriptionManager subscriptionManager; 
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final Collection<AdvSubscriptionListPV> subscriptionListPVs = subscriptionManager.getAllAdSubscription();
		resMap.put("res", JsonUtil.convertJSONSingleStr(subscriptionListPVs));
		return new ModelAndView("fplajax_GetAdvertisementType",resMap);
	}
}

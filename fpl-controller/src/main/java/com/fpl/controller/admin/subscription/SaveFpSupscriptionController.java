package com.fpl.controller.admin.subscription;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.subsciption.fpl.FPSubscriptionPersistPV;
import com.fpl.core.subsciption.fpl.IFplSubscriptionManager;
import com.fpl.util.DateUtil;
import com.fpl.util.JsonUtil;

public class SaveFpSupscriptionController extends AjaxBaseController {
	
	@Autowired
	private IFplSubscriptionManager manager;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final Map<String, String> output = new HashMap<String, String>();
		try {
			final String advTypeJson = request.getParameter("FPSubJson");
			System.out.println(advTypeJson);
			final FPSubscriptionPersistPV param = JsonUtil.convertPojo(advTypeJson, FPSubscriptionPersistPV.class);
			final Date date =null; 
					//manager.persist(param);
			output.put("status", "Success");
			output.put("reason","Details Saved Successfully !!");
			output.put("lastUpdatedDate", DateUtil.getFormattedDate(date));
		} catch (final Exception e) {
			e.printStackTrace();
			output.put("status", "failure");
			output.put("reason",e.getMessage());
		}
		resMap.put("res", JsonUtil.toJsonString(output));
		return new ModelAndView("fplajax_SaveAdvertisementType", resMap);
	}

}

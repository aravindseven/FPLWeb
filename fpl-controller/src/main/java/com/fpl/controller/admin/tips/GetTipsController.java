package com.fpl.controller.admin.tips;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.communication.ICommunicationManager;
import com.fpl.core.subsciption.fpl.IFplSubscriptionManager;
import com.fpl.core.tips.AdminTips;
import com.fpl.core.tips.ITipsManager;
import com.fpl.persistence.tips.ITipsDAO;
import com.fpl.util.JsonUtil;

public class GetTipsController extends AjaxBaseController {
	
	@Autowired
	@Qualifier("fpl.communication.communicationManager")
	private ICommunicationManager communicationManager;
	/*private ITipsManager communicationManager;*/
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final Map<String, String> output = new HashMap<String, String>();
		final AdminTips admin_tips = new AdminTips();
		final Map map = new HashMap();
			try {
				Object stats = communicationManager.GetTips();
				System.out.println(stats);
				map.put("Status", "Success");
				map.put("Result", stats);
			} catch (final Exception e) {
				map.put("Status", "Failure");
				map.put("Result", e.getMessage());
				e.printStackTrace();
			}
			resMap.put("res", JsonUtil.toJsonString(map));
			return new ModelAndView("fplajax_domain", resMap);
	}
	
	/**
	 * @param policyPersister the policyPersister to set
	 */
	public void setCommunicationManager(final ICommunicationManager communicationManager) {
		this.communicationManager = communicationManager;
	}


}
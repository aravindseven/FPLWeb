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
import com.fpl.core.tips.AdminTips;
import com.fpl.core.tips.ITipsManager;
import com.fpl.util.JsonUtil;

public class DeleteTipsController extends AjaxBaseController {
	
	@Autowired
	@Qualifier("fpl.communication.communicationManager")
	private ICommunicationManager communicationManager;
	
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final Map<String, String> output = new HashMap<String, String>();
		System.out.println("called delete");
			//here have to check with user id... whether this user have admin role or not
			final AdminTips admin_tips = new AdminTips();
			String tip_name  = request.getParameter("tip_name");
			final Map map = new HashMap();
			try {
				Boolean stats = communicationManager.deletetips(tip_name);
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
	public void setTipsmanager(final ICommunicationManager communicationManager) {
		this.communicationManager = communicationManager;
	}


}
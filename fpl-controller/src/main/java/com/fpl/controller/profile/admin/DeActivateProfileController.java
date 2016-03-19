package com.fpl.controller.profile.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.profile.admin.IAdminProfileManager;
import com.fpl.util.JsonUtil;

public class DeActivateProfileController extends AjaxBaseController {
	
	private IAdminProfileManager profileManager;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final FPLServiceResponse serviceResponse = new FPLServiceResponse();
		final Map<String, String> resMap =  new HashMap<String, String>();
		final String email = request.getParameter("submittedEmail");
		System.out.println(email);
		try {
			profileManager.deActivateProfile(email);
			serviceResponse.setReason("Successfully de activated");
			serviceResponse.setStatus("success");
		} catch (final Exception e) {
			e.printStackTrace();
			serviceResponse.setStatus("failure");
			serviceResponse.setReason(e.getMessage());
		}
		resMap.put("res", JsonUtil.toJsonString(serviceResponse));
		return new ModelAndView("fplajax_login", resMap);
	}

	/**
	 * @param profileManager the profileManager to set
	 */
	public void setProfileManager(final IAdminProfileManager profileManager) {
		this.profileManager = profileManager;
	};
}

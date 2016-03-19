package com.fpl.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.mobile.device.Device;
import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.entity.LoginSupport;
import com.fpl.persistence.login.ILoginSupportDAO;
import com.fpl.util.JsonUtil;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

public class FPLLogoutController extends AjaxBaseController {
	
	private ILoginSupportDAO loginSupportDAO;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		System.out.println("devicetype"+request.getHeader("User-Agent"));
		UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
		ReadableUserAgent agent = parser.parse(request.getHeader("User-Agent"));
		final LoginSupport loginSupport = loginSupportDAO.getByLoginCredentialId(loginInfo.getLoginCredentialid());
		loginSupport.setOnline(Integer.valueOf(1));
		if(agent.getTypeName().equalsIgnoreCase("Browser")){
			loginSupport.setBrowser_Active("false");
		}else if(agent.getTypeName().equalsIgnoreCase("Library")){
			loginSupport.setMobile_Active("false");
		}
		loginSupport.setSessionid(null);
		loginSupportDAO.update(loginSupport, true);
		clearAll(request);
		request.getSession().invalidate();
		final FPLServiceResponse serviceResponse = new FPLServiceResponse();
		serviceResponse.setStatus("success");
		serviceResponse.setReason("User Logged out");
		resMap.put("res", JsonUtil.toJsonString(serviceResponse));
		return new ModelAndView("fplajax_login", resMap);
	}
	 
	/**
	 * @param loginSupportDAO the loginSupportDAO to set
	 */
	public void setLoginSupportDAO(final ILoginSupportDAO loginSupportDAO) {
		this.loginSupportDAO = loginSupportDAO;
	}
}

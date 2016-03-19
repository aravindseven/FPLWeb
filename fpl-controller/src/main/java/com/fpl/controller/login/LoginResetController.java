package com.fpl.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.login.entity.LoginSupport;
import com.fpl.persistence.login.ILoginSupportDAO;
import com.fpl.util.JsonUtil;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

public class LoginResetController extends AjaxBaseController {
	private ILoginSupportDAO loginSupportDAO;
	@Override
	public ModelAndView executeAjaxCall(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		final Map<String, String> resMap =  new HashMap<String, String>();
		System.out.println("called reset Controller");
		System.out.println("id"+request.getParameter("id"));
		String email=request.getParameter("id");
		String email1=Encryption.decrypt(email, CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		System.out.println("sessionid"+email1);
			UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
			ReadableUserAgent agent = parser.parse(request.getHeader("User-Agent"));
			final LoginSupport loginSupport = loginSupportDAO.getByLoginCredentialId(Long.parseLong(email1));
			loginSupport.setOnline(Integer.valueOf(1));
			 remove(loginSupport.getSessionid(),USER_LOGIN_KEY);
			if(agent.getTypeName().equalsIgnoreCase("Browser")){
				loginSupport.setBrowser_Active("false"); 
			}else if(agent.getTypeName().equalsIgnoreCase("Library")){
				loginSupport.setMobile_Active("false");
			}
			loginSupport.setSessionid(null);
			loginSupportDAO.update(loginSupport, true);
			//clearAll(request);
			request.getSession().invalidate();
			final FPLServiceResponse serviceResponse = new FPLServiceResponse();
			serviceResponse.setStatus("success");
			serviceResponse.setReason("User Logged out");
			resMap.put("res", JsonUtil.toJsonString(serviceResponse));
			//return new ModelAndView("fplajax_login", resMap);		
			ModelAndView model = new ModelAndView(FPLPageName.INDEX.getPageName());
			
			return  model;
	}
	/**
	 * @param loginSupportDAO the loginSupportDAO to set
	 */
	public void setLoginSupportDAO(final ILoginSupportDAO loginSupportDAO) {
		this.loginSupportDAO = loginSupportDAO;
	}
}

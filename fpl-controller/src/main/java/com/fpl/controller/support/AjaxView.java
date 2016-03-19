package com.fpl.controller.support;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.AbstractView;

import com.fpl.login.UserLoginInfo;

/**
 * @author Yaazhsoft
 *
 */
public class AjaxView extends AbstractView {
	
	private static final Log log = LogFactory.getLog(AjaxView.class);
	
	@Override
	protected void renderMergedOutputModel(final Map<String, Object> map, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		log.info("Resolving ajax request view -" + map);
		response.setContentType("text/plain; charset=UTF-8");
		String res = (String) map.get("res");
		if(res !=null){
			System.out.println("called ajax"+res);
			response.getOutputStream().write(res.toString().getBytes());	
		} else {
			res = "__XXERRORXX__:"+ map.get("err");	
			response.getOutputStream().write(res.toString().getBytes());	
		}
		final UserLoginInfo loginInfo = (UserLoginInfo) request.getSession().getAttribute("UserLoginInfo");
		if(loginInfo != null) {
			request.setAttribute("UserLoginInfo", loginInfo);
		}
	}
}

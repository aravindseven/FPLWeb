package com.fpl.controller.communication;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.communication.ICommunicationManager;
import com.fpl.login.UserLoginInfo;
import com.fpl.util.JsonUtil;

public class GetReadstatusController extends AjaxBaseController {
	
	@Autowired
	@Qualifier("fpl.communication.communicationManager")
	private ICommunicationManager communicationManager;
	
@Override
public ModelAndView executeAjaxCall(HttpServletRequest request, HttpServletResponse response) {
final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
final Map<String, String> resMap =  new HashMap<String, String>();
final Map map = new HashMap();
/*try {
	String readstatus=request.getParameter("readstatususer");
	//String stats = communicationManager.getReadstatus(readstatus);
	System.out.println("getreadstatus"+readstatus+stats);
	map.put("Status", "Success");
	map.put("Result", stats);
} catch (final Exception e) {
	map.put("Status", "Failure");
	map.put("Result", e.getMessage());
	e.printStackTrace();
}*/
resMap.put("res", JsonUtil.toJsonString(map));
return new ModelAndView("fplajax_domain", resMap);
}
} 

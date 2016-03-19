package com.fpl.controller.admin.subscription;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.FPLAbstractController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.core.subsciption.fpl.FPSubscriptionListPV;
import com.fpl.core.subsciption.fpl.IFplSubscriptionManager;
import com.fpl.login.UserLoginInfo;

public class PayNowController extends FPLAbstractController {

	@Autowired
	private IFplSubscriptionManager manager;

	
	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response) {
		final ModelAndView model = new ModelAndView(FPLPageName.SUBSCRIBE.getPageName());
		final UserLoginInfo loginInfo = (UserLoginInfo) get(request,USER_LOGIN_KEY);
		String fplSubId=request.getParameter("fplSubId");
		String token=request.getParameter("token");
		String payerId=request.getParameter("PayerID");
		String action=request.getParameter("ACTION");
		
		if(action!=null && "CHECKOUT".equalsIgnoreCase(action))
		{
			FPSubscriptionListPV fpSubscriptionListPV= manager.getFplSubscriptionById(Long.parseLong(fplSubId));
			fpSubscriptionListPV.setTransactionPayerId(payerId);
			fpSubscriptionListPV=manager.getPayPalCheckoutRef(fpSubscriptionListPV);
			manager.persistTransactionDetails(fpSubscriptionListPV);
			request.setAttribute("PAY_SUCCESS", "SHOW");
		}if(action!=null && "EWAYCHECKOUT".equalsIgnoreCase(action))
		{
			FPSubscriptionListPV fpSubscriptionListPV= manager.getFplSubscriptionById(Long.parseLong(fplSubId));
			fpSubscriptionListPV=manager.getEWayCheckoutRef(fpSubscriptionListPV);
			manager.persistTransactionDetails(fpSubscriptionListPV);
			request.setAttribute("PAY_SUCCESS", "SHOW");
		}
		else
		{
			FPSubscriptionListPV fpSubscriptionListPV= manager.getFplSubscriptionById(Long.parseLong(fplSubId));
			manager.cancelTransaction(fpSubscriptionListPV);
		}
		return model;
	}
}

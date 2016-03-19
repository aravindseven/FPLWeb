package com.fpl.controller.admin.subscription;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.FPLException;
import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.errormsg.FPLCommonErrorMessage;
import com.fpl.persistence.subscription.IFplSubscriptionDAO;
import com.fpl.subscription.FplSubscription;
import com.fpl.util.JsonUtil;
import com.fpl.util.NumberUtil;

public class DeleteFPSubscriptionController extends AjaxBaseController {
	
	@Autowired
	@Qualifier("fpl.subscrption.FplSubscriptionDAO")
	private IFplSubscriptionDAO subscriptionDAO; 
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final FPLServiceResponse fplServiceResponse = new FPLServiceResponse();
		final Map<String, String> resMap =  new HashMap<String, String>();
		final String id = request.getParameter("advId");
		try {
			if(!NumberUtil.isValidNumber(id)) {
				throw new FPLException(FPLCommonErrorMessage.INVALID, "Id");
			}
			final FplSubscription subscription = subscriptionDAO.get(Long.valueOf(id));
			if(subscription == null) {
				throw new FPLException(FPLCommonErrorMessage.NO_RECORD_FOUND);
			}
			subscriptionDAO.delete(subscription, true);
			fplServiceResponse.setStatus("Success");
			fplServiceResponse.setReason("Details Deleted Successfully !!");
		} catch (final Exception e) {
			fplServiceResponse.setStatus("failure");
			fplServiceResponse.setReason(e.getMessage());
			e.printStackTrace();
		}
		resMap.put("res", JsonUtil.convertJSONSingleStr(fplServiceResponse));
		return new ModelAndView("fplajax_GetAdvertisementType",resMap);
	}
}

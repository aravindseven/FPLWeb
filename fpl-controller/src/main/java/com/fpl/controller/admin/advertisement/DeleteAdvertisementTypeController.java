package com.fpl.controller.admin.advertisement;

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
import com.fpl.persistence.subscription.IAdvertisementTypeDAO;
import com.fpl.subscription.AdvertisementType;
import com.fpl.util.JsonUtil;
import com.fpl.util.NumberUtil;

public class DeleteAdvertisementTypeController extends AjaxBaseController {
	
	@Autowired
	@Qualifier("fpl.subscrption.AdvertisementTypeDAO")
	private IAdvertisementTypeDAO advertisementTypeDAO; 
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final FPLServiceResponse fplServiceResponse = new FPLServiceResponse();
		final Map<String, String> resMap =  new HashMap<String, String>();
		final String id = request.getParameter("advId");
		try {
			if(!NumberUtil.isValidNumber(id)) {
				throw new FPLException(FPLCommonErrorMessage.INVALID, "Id");
			}
			final AdvertisementType type = advertisementTypeDAO.get(Long.valueOf(id));
			if(type == null) {
				throw new FPLException(FPLCommonErrorMessage.NO_RECORD_FOUND);
			}
			advertisementTypeDAO.delete(type, true);
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

package com.fpl.controller.profile.customer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.login.LoginCredentialServices;
import com.fpl.login.UserLoginInfo;
import com.fpl.profile.ProfileState;
import com.fpl.profile.ProfileStatus;
import com.fpl.profile.address.AddressPV;
import com.fpl.profile.customer.CustomerPV;
import com.fpl.profile.personal.PersonalDataPV;
import com.fpl.services.profile.ICustomerRegisterService;
import com.fpl.util.JsonUtil;

public class CustomerAjaxProfileCreationController extends AjaxBaseController {
	
	private ICustomerRegisterService registerService;
	
	private LoginCredentialServices loginServices;
	
	@Override       
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		
		String uploadImage=request.getParameter("uploadImage");
		Boolean uploadImageFlag=new Boolean(uploadImage);
		if(uploadImageFlag)
		{
			final UserLoginInfo loginInfo = (UserLoginInfo) get(request,USER_LOGIN_KEY);
			request.setAttribute("EMAIL", loginInfo.getEmail());
			loginServices.uploadProfileImage(request);
		}else
		{
		
		final String customerDataJson = request.getParameter("customerJson");
		System.out.println(customerDataJson);
		final CustomerPV customerPV = JsonUtil.convertPojo(customerDataJson, CustomerPV.class);
		
		final String primaryPersonalDataJson = request.getParameter("primaryPersonalJson");
		System.out.println(primaryPersonalDataJson);
		final PersonalDataPV primaryPersonalData = JsonUtil.convertPojo(primaryPersonalDataJson, PersonalDataPV.class);
		customerPV.setPrimaryPersonalData(primaryPersonalData);
		final String seconPersonalDataJson = request.getParameter("secondaryPersonalJson");
		System.out.println(seconPersonalDataJson);
		final PersonalDataPV seconPersonalData = JsonUtil.convertPojo(seconPersonalDataJson, PersonalDataPV.class);
		customerPV.setSecondaryPersonalData(seconPersonalData);
		final String addressPVJson = request.getParameter("addressJson");
		System.out.println(addressPVJson);
		final AddressPV addressPV = JsonUtil.convertPojo(addressPVJson, AddressPV.class);
		final String permanentAddressJson = request.getParameter("permanentAddressJson");
		System.out.println(permanentAddressJson);
		final AddressPV permanentAddressPV = JsonUtil.convertPojo(permanentAddressJson, AddressPV.class);
		final String alternativeemailid = request.getParameter("alternativeemailid");
		System.out.println(permanentAddressJson);
		customerPV.setAddressPV(addressPV);
		customerPV.setPermanentAddressPV(permanentAddressPV);
		customerPV.setAlternativeemailid(alternativeemailid);
		customerPV.setState(ProfileState.DEACTIVE);
		customerPV.setStatus(ProfileStatus.PENDING);
		final FPLServiceResponse serviceResponse = registerService.insertCustomer(customerPV);
		resMap.put("res", JsonUtil.toJsonString(serviceResponse));
		}
		return new ModelAndView("fplajax_customerProfileCreation", resMap);
	}

	/**
	 * @param registerService the registerService to set
	 */
	public void setRegisterService(final ICustomerRegisterService registerService) {
		this.registerService = registerService;
	}

	public void setLoginServices(LoginCredentialServices loginServices) {
		this.loginServices = loginServices;
	}
	
	
	
}

package com.fpl.controller.policy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.controller.support.FPLAbstractController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.controller.support.RequestToPageViewTransfer;
import com.fpl.core.policy.IPolicyPersister;
import com.fpl.domain.Domain;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.UserType;
import com.fpl.persistence.domain.IDomainDAO;
import com.fpl.policy.NewPolicyParam2;
import com.fpl.util.JsonUtil;

public class NewPolicyCreationController2 extends AjaxBaseController {
	
	private IPolicyPersister policyPersister;
	private IDomainDAO domainDAO;
	
	/**
	 * @param policyPersister the policyPersister to set
	 */
	public void setPolicyPersister(final IPolicyPersister policyPersister) {
		this.policyPersister = policyPersister;
	}

	/**
	 * @param domainDAO the domainDAO to set
	 */
	public void setDomainDAO(final IDomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}

	@Override
	public ModelAndView executeAjaxCall(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		final Map<String, String> resMap =  new HashMap<String, String>();
		final Map map = new HashMap();
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		
		//final NewPolicyParam2 policyParam  = new NewPolicyParam2(); //RequestToPageViewTransfer.getPageView(NewPolicyParam2.class, request,"form");
		
		String s1=request.getParameter("policyREId");
		String s2=request.getParameter( "policyREDate").split("T")[0].substring(1);
		String s3=request.getParameter("policyFname");
		String s4=request.getParameter("policyREQNUMBER");
		String s23=request.getParameter("policyTYPE");
		String s5=request.getParameter("policyNUMBER");
		String s6=request.getParameter("policyINSURANCECOMPANY");
		
		String s7=request.getParameter("policyDESC");
		String s8=request.getParameter("policyDATE").split("T")[0].substring(1);
		
		String s9=request.getParameter("policyDURATION");
		
	    String s10=request.getParameter("policyAMOUNT");
		String s11=request.getParameter("policyPREMAMOUNT");
		String s12=request.getParameter("policyRENEWAL_DATE").split("T")[0].substring(1);
		
		String s13=request.getParameter("policy_nomineefname");
		String s14=request.getParameter("policy_nomineelname");
		String s15=request.getParameter("policy_nomineeIDprof");
		String s16=request.getParameter("policy_nomineemobile");
		
		String s17=request.getParameter("policy_nomineerelation");
		String s18=request.getParameter("policyCONFIDENTIALITY_PRE");
		
		String s19=request.getParameter("policyAdvancealert");
		String s20=request.getParameter("policyAlertsch");
		String s21=request.getParameter("policySchedule_Days");
		String s22=request.getParameter("policyComment");
		System.out.println(s1+s2+s3+s4+s5+s5+s6+s7+s8+s9+s10+s11+s12+s13+s14+s15+s16+s17+s18+s19+s20+s21+s22+s23);
		final NewPolicyParam2 policyParam  = new NewPolicyParam2(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23);
		
		policyParam.setLoginId(loginInfo.getLoginCredentialid()+"");
		if(loginInfo.getUserType().equals(UserType.CUST_CORPORATE) || loginInfo.getUserType().equals(UserType.CUST_INDIVIDUAL)) {
			policyParam.setStatus("ST_10");
		} else {
			policyParam.setStatus("ST_11");
		}
		try {
			policyPersister.persistPolicy(policyParam);
			map.put("status", "success");
		} catch (final Exception e) {
			e.printStackTrace();
			final Collection<Domain> domains = domainDAO.getAllEntities();
			map.put("status", "success");
			map.put("domains", "domains");
		//	model.addObject("DomainList", domains);
		//	model.addObject(ERROR_MESSAGE, e.getMessage());
		}
		resMap.put("res", JsonUtil.toJsonString(map));
		return  new ModelAndView("fplajax_domain", resMap);
	}
}

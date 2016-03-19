package com.fpl.core.login;

import javax.servlet.http.HttpServletRequest;

import com.fpl.FPLException;
import com.fpl.core.profile.IpersonalDAO;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.errormsg.FPLCommonErrorMessage;
import com.fpl.login.FPLLoginOutput;
import com.fpl.login.ILoginInputParam;
import com.fpl.login.ILoginRecoveryInput;
import com.fpl.login.IUserRecoverInput;
import com.fpl.login.LoginRecordTO;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.entity.LoginCredential;
import com.fpl.login.entity.LoginSupport;
import com.fpl.persistence.login.ILoginCredentialDAO;
import com.fpl.persistence.login.ILoginSupportDAO;
import com.fpl.profile.personal.PersonalData;
import com.fpl.util.NumberUtil;

public class LoginProcessorImpl implements ILoginProcessor {

	private IpersonalDAO personalDAO;
	private ILoginCredentialDAO loginCredentialDAO;
	//private IloginRecordDAO loginRecordDAO;
	private ILoginSupportDAO loginSupportDAO;
	private ILoginValidator loginValidator;
	private ILoginProcessorSupport loginProcessorSupport;
	
	private LoginRecordTO loginRecordTO;
	
	@Override
	public UserLoginInfo getLoginInfo(final ILoginInputParam loginParam,FPLLoginOutput loginoutput) { 
		final UserLoginInfo loginInfo = loginCredentialDAO.getLoginInfo(loginParam);
		
		System.out.println("getstatus");
		System.out.println("");
		
		loginValidator.validteCredential(loginInfo, loginParam,loginoutput);
		final LoginSupport loginSupport = loginSupportDAO.getByLoginCredentialId(loginInfo.getLoginCredentialid());
		final PersonalData personaldata=personalDAO.getuserdata(loginInfo.getEmail());
		final boolean isVerified = (loginSupport.getSecEmailVerified() != null && loginSupport.getSecEmailVerified().intValue() == 0);
		loginInfo.setSecEmailVerified(isVerified);
		loginInfo.setProfileUrl(loginSupport.getImageUrl());
		loginSupport.setOnline(Integer.valueOf(1));
		loginSupport.setSessionid(loginParam.getSessionId());
		if(personaldata!=null){
		loginInfo.setFirstname(personaldata.getName());
		loginInfo.setLastname(personaldata.getLastNameOrRNumber());
		}
		if(loginSupport.getStatus()!=null){
			loginInfo.setStatus(loginSupport.getStatus());
		}
		if(loginParam.getDeviceType().equals("Browser")){
			//loginSupport.setBrowser_Active("true");
		}else if(loginParam.getDeviceType().equals("Library")){
			//loginSupport.setMobile_Active("true");
		}
		loginSupportDAO.update(loginSupport, true);
		return loginInfo;
	}
	   

	@Override
	public void recoverPassword(final IUserRecoverInput loginRecovery) {
		/*if(!NumberUtil.isValidNumber(loginRecovery.getMobileNumber())) {
			throw new FPLException(FPLCommonErrorMessage.INVALID, "Mobile Number");
		}*/
		final LoginCredential loginCredential = loginCredentialDAO.getLoginCredential(loginRecovery);
		loginProcessorSupport.processRecoverPassword(loginCredential);
	}
	
	@Override
	public void recoverPassword( final IUserRecoverInput loginRecovery, final HttpServletRequest request) {
		/*if(!NumberUtil.isValidNumber(loginRecovery.getMobileNumber())) {
			throw new FPLException(FPLCommonErrorMessage.INVALID, "Mobile Number");
		}*/
		
		System.out.println(""+loginRecovery.getFirstname()+loginRecovery.getLastname()+loginRecovery.getAltEmail()+loginRecovery.getSecretQuestion());
		final String email = Encryption.encrypt(loginRecovery.getAltEmail(), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		String pd=personalDAO.getpasswordinfo(loginRecovery.getFirstname(), loginRecovery.getLastname(),email,loginRecovery.getSecretQuestion());
		final LoginCredential loginCredential = loginCredentialDAO.getLoginCredential(loginRecovery);
		if((pd!=null)&&(pd.equalsIgnoreCase(email))){
			loginProcessorSupport.processRecoverPassword(loginCredential, request);
			
			if(null!=loginCredential){
			final LoginSupport loginSupport = loginSupportDAO.getByLoginCredentialId(loginCredential.getId());
			loginSupport.setNon_consecutive_on("true");
			loginSupportDAO.update(loginSupport, true);
			}
		}else{
			throw new FPLException(FPLCommonErrorMessage.INVALID, "Invalid details");
		}
		/*LoginCredential loginCredential=new LoginCredential();
		loginCredential.setAlternateMailId("nandajobseeker446@gmail.com");
		loginCredential.setMailId("nandajobseeker446@gmail.com");
		loginCredential.setId(Long.valueOf("6"));*/
	}
	
	@Override
	public void recoverUserName(final IUserRecoverInput loginRecovery, final HttpServletRequest request) {
		/*if(!NumberUtil.isValidNumber(loginRecovery.getMobileNumber())) {
			throw new FPLException(FPLCommonErrorMessage.INVALID, "Mobile Number");
		}*/
		final String email=Encryption.encrypt(loginRecovery.getAltEmail(), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());

		System.out.println(""+loginRecovery.getFirstname()+loginRecovery.getLastname());
		String pd=personalDAO.getinfo(loginRecovery.getFirstname(), loginRecovery.getLastname(),email,loginRecovery.getSecretQuestion());
		final LoginCredential loginCredential = loginCredentialDAO.getLoginCredentialbyAltemail(loginRecovery);
		System.out.println("personaldata"+pd);
		System.out.println("logincrediantail"+email);
		if((pd!=null)&&(pd.equalsIgnoreCase(email))){
			loginProcessorSupport.processRecoverUserName(loginCredential, request);
			
			if(null!=loginCredential){
			final LoginSupport loginSupport = loginSupportDAO.getByLoginCredentialId(loginCredential.getId());
			loginSupport.setNon_consecutive_on("true");
			loginSupportDAO.update(loginSupport, true);
			}
		}else{
			throw new FPLException(FPLCommonErrorMessage.INVALID, "incorrect details");
		}
		/*LoginCredential loginCredential=new LoginCredential();
		loginCredential.setAlternateMailId("nandajobseeker446@gmail.com");
		loginCredential.setMailId("nandajobseeker446@gmail.com");*/
		
	}
	
	@Override
	public boolean isValidOTP(final String otp, final String date) {
		System.out.println("isvalidotp2"+otp);

		return loginProcessorSupport.isValidOTP(otp, date);
	}
	
	@Override
	public void SendSecEmailVerfication(final Long loginId) {
		final LoginCredential loginCredential = loginCredentialDAO.get(loginId);
		loginProcessorSupport.SendSecEmailVerfication(loginCredential);
	}
	
	@Override
	public void SendSecEmailVerfication(final Long loginId, final HttpServletRequest request) {
		final LoginCredential loginCredential = loginCredentialDAO.get(loginId);
		loginProcessorSupport.SendSecEmailVerfication(loginCredential, request);
	}
	
	/**
	 * @param loginCredentialDAO the loginCredentialDAO to set
	 */
	public final void setLoginCredentialDAO(final ILoginCredentialDAO loginCredentialDAO) {
		this.loginCredentialDAO = loginCredentialDAO;
	}
	/**
	 * @param loginCredentialDAO the loginCredentialDAO to set
	 */
	/*public final void setLoginRecordDAO(final IloginRecordDAO loginRecordDAO) {
		this.loginRecordDAO = loginRecordDAO;
	}*/
	/**
	 * @param loginValidator the loginValidator to set
	 */
	public final void setLoginValidator(final ILoginValidator loginValidator) {
		this.loginValidator = loginValidator;
	}

	/**
	 * @param loginProcessorSupport the loginProcessorSupport to set
	 */
	public void setLoginProcessorSupport(
			final ILoginProcessorSupport loginProcessorSupport) {
		this.loginProcessorSupport = loginProcessorSupport;
	}

	/**
	 * @param loginSupportDAO the loginSupportDAO to set
	 */
	public void setLoginSupportDAO(final ILoginSupportDAO loginSupportDAO) {
		this.loginSupportDAO = loginSupportDAO;
	}

	
	/**
	 * @param loginSupportDAO the loginSupportDAO to set
	 */
	/*public void setLoginRecord(final LoginRecord alLoginRecord) {
		this.alLoginRecord = alLoginRecord;
	}*/
	public  void setloginrecord(final LoginRecordTO loginrecord) {
		this.loginRecordTO = loginrecord;
	}
	public  void setPersonalDAO(final IpersonalDAO PersonalDAO) {
		this.personalDAO = PersonalDAO;
	}
	@Override
	public boolean Loginlog(LoginRecordTO lr) {
		// TODO Auto-generated method stub
		
		/*System.out.println("loginprocessIMPL");
		
		alLoginRecord.setSessionId(lr.getSessionId());
		alLoginRecord.setLoginresult(lr.getLoginresult());
		alLoginRecord.setLogincrediantialid(lr.getLogincrediantialid());
		
		alLoginRecord.setUserId(lr.getUserId());
		alLoginRecord.setDevicetype(lr.getDevicetype());
		alLoginRecord.setDeviceid(lr.getDeviceid());
		
		alLoginRecord.setChatstatus(lr.getChatstatus());
		alLoginRecord.setIpaddress(lr.getIpaddress());
		alLoginRecord.setLogindate(lr.getLogindate());
		alLoginRecord.setLogoutdate(lr.getLogoutdate());
		alLoginRecord.setResultreason(lr.getResultreason());
		
		loginRecordDAO.save(alLoginRecord, true);*/
		return false;
	}


	@Override
	public void recoverUserName(IUserRecoverInput loginRecovery) {
		// TODO Auto-generated method stub
		
	}


	


	


	
}

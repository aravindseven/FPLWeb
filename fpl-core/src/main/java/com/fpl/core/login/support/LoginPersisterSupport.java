package com.fpl.core.login.support;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.fpl.FPLException;
import com.fpl.core.login.ILoginPersisterSupport;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.errormsg.FPLCommonErrorMessage;
import com.fpl.login.LoginRegisterParam;
import com.fpl.login.entity.LoginCredential;
import com.fpl.login.entity.LoginRecovery;
import com.fpl.login.entity.SecretQuestion;
import com.fpl.login.entity.User;
import com.fpl.mail.MailHandler;
import com.fpl.mail.MailHandlerFactory;
import com.fpl.mail.MailInfo;
import com.fpl.mail.MailType;
import com.fpl.persistence.login.ILoginRecoveryDAO;
import com.fpl.persistence.login.ISecretQuestionDAO;
import com.fpl.persistence.login.IUserDAO;
import com.fpl.profile.personal.PersonalData;
import com.fpl.util.NumberUtil;

public class LoginPersisterSupport implements ILoginPersisterSupport {

	private ISecretQuestionDAO secretQuestionDAO;
	private IUserDAO userDAO;
	private ILoginRecoveryDAO loginRecoveryDAO;
	                         
	@Override
	public LoginCredential getLoginCredential(final LoginRegisterParam loginRegister) {
		final LoginCredential loginCredential = new LoginCredential();
		loginCredential.setMailId(loginRegister.getPrimaryEmailId());
		loginCredential.setDate(new Date());
		loginCredential.setCountry(loginRegister.getCountry());
		loginCredential.setAlternateMailId(loginRegister.getAlternativemailId());
		if(!StringUtils.isEmpty(loginRegister.getMobilenumber())){
		loginCredential.setMobileNumber(Long.valueOf(loginRegister.getMobilenumber()));
		}
		loginCredential.setPassword(loginRegister.getPassword());
		loginCredential.setSecretAnswer(loginRegister.getSecretAnswer());
		final SecretQuestion secretQuestion = secretQuestionDAO.getSecretQuestion(loginRegister.getSecretQuestion());
		loginCredential.setSecretQuestionId(secretQuestion.getId());
		final User user = userDAO.getUser(loginRegister.getUserType());
		loginCredential.setUserTypeId(user.getId());
		return loginCredential;
	}
	@Override
	public PersonalData getpersonaldata(LoginRegisterParam loginRegister) {
		// TODO Auto-generated method stub
		final PersonalData pdt=new PersonalData();
		pdt.setLastNameOrRNumber(loginRegister.getLastname());
		pdt.setName(loginRegister.getFirstname());
		pdt.setEmail(loginRegister.getPrimaryEmailId());
		return pdt;
	}
	@Override
	public void sendRegistration(final LoginCredential loginCredential) {
		final Integer otp = NumberUtil.getRandomNumber();
		final LoginRecovery loginRecovery = getLoginRecovery(loginCredential.getId(), otp);
		loginRecoveryDAO.saveOrUpdate(loginRecovery, true);
		final MailInfo mailInfo = new MailInfo();
		mailInfo.setToAddress(loginCredential.getMailId());
		mailInfo.setSubject("FPL - Your account registration request");
		final String encryptedOtp = Encryption.encrypt(String.valueOf(otp), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		String encodedValue = null;
		try {
			encodedValue = URLEncoder.encode(encryptedOtp, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new FPLException(FPLCommonErrorMessage.TECHNICAL_PROBLEM);
		}
		final StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Please use the link below to activate your account.\n \n");
		messageBuilder.append("http://localhost:8080/FPLWeb/userActivate.do?uoid="+encodedValue);
		messageBuilder.append("\n \n Thank you, \n The FPL Team");
		mailInfo.setMessage(messageBuilder.toString());
		final MailHandler mailHandler = MailHandlerFactory.getMailSender(MailType.SSL);
		mailHandler.sendMailWithDefaultUser(mailInfo);
	}
	
	@Override
	public void sendRegistration(final LoginCredential loginCredential, final HttpServletRequest request) {
		final Integer otp = NumberUtil.getRandomNumber();
		final LoginRecovery loginRecovery = getLoginRecovery(loginCredential.getId(), otp);
		loginRecoveryDAO.saveOrUpdate(loginRecovery, true);
		final MailInfo mailInfo = new MailInfo();
		mailInfo.setToAddress(loginCredential.getMailId());
		mailInfo.setSubject("FPL - Your account registration request");
		final String encryptedOtp = Encryption.encrypt(String.valueOf(otp), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		String encodedValue = null;
		try {
			encodedValue = URLEncoder.encode(encryptedOtp, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new FPLException(FPLCommonErrorMessage.TECHNICAL_PROBLEM);
		}
		final StringBuilder messageBuilder = new StringBuilder();
		/* Construct Activation URL*/
		final String url = (ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("SEVER_URL"))+ "/FPLWeb/userActivate.do?uoid=" + encodedValue;
		/* Construct Subject Line */
		String isoCode = ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("DEFAULT_COUNTRY");
		String refNum = isoCode+ System.currentTimeMillis();
		mailInfo.setSubject("FPL-Online communication: Reference # " + refNum);
		
		/* Format the mail before being sent out */
		mailInfo.setMessage(activationEmailTemplate(messageBuilder, refNum, url, loginCredential.getPassword(), request));
		
		mailInfo.setMessage(messageBuilder.toString());
		final MailHandler mailHandler = MailHandlerFactory.getMailSender(MailType.SSL);
		mailHandler.sendMailWithDefaultUser(mailInfo);
	}
	
	private String activationEmailTemplate(StringBuilder messageBuilder, String refNum, String url, String password, HttpServletRequest request){		
		messageBuilder.append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("MAIL_TOP"));
		messageBuilder.append("<img src='").append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("SEVER_URL")).append("/FPLWeb/WebContent/support/images/fpl.png' style='width:118px;height:88px' align ='right'><br>");
		messageBuilder.append("<p>Date & Time:");
		messageBuilder.append(new java.util.Date().toGMTString());
		messageBuilder.append("</p><p>FPL-Online Communication: Reference # ");		
		messageBuilder.append(refNum).append("</p></div>");
		messageBuilder.append("<div style='height: 330px; padding:5px; border-bottom: 2px solid red;'><p><h4>Dear FPL Online User,</h4>We have received an account activation request. In case this is not initiated by you, please write to our account admin.<br><br> In order to activate, please follow the secure link: <a href='");
		messageBuilder.append(url).append("'>Activate</a> <br>Link validity: 48 hours from issuance <br><br>In case this link is expired, you may again request for activation via our website.");
		//messageBuilder.append("<br><br>Kinldy use this password for login: "+password);
		messageBuilder.append("<br>We recommend to change this password at first login via Profile page");
		messageBuilder.append("<br><br> Sincerely,<br><br>Admin<br><br>Website : <a href='www.fpl-online.com'>www.fpl-online.com</a><br>Email   : <a href='account.admin.sg@fpl-online.com'>account.admin.sg@fpl-online.com</a><br></p></div>");		
		messageBuilder.append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("MAIL_FOOTER"));
		return messageBuilder.toString();
	}
	
	@Override
	public LoginRecovery getLoginRecovery(final String otp) {
		if(!NumberUtil.isValidNumber(otp)) {
			throw new FPLException(FPLCommonErrorMessage.INVALID, "INVALID OTP");
		}
		
		return loginRecoveryDAO.getLoginRecovery(Integer.valueOf(otp));
	}
	
	@Override
	public void deleteLoginRecovery(final LoginRecovery loginRecovery) {
		loginRecoveryDAO.delete(loginRecovery, true);
	}
	@Override
	public void UpdateLoginRecovery(LoginRecovery loginRecovery) {
		// TODO Auto-generated method stub
		loginRecoveryDAO.update(loginRecovery, true);
	}
	private LoginRecovery getLoginRecovery(final Long loginCredentialId, final Integer otp) {
		LoginRecovery loginRecovery;
		loginRecovery=loginRecoveryDAO.getLoginRecovery(loginCredentialId);
		if(loginRecovery==null || loginRecovery.getId()==0)
		{
			loginRecovery= new LoginRecovery();
		}
		loginRecovery.setLoginCredentialId(loginCredentialId);
		loginRecovery.setOtp(otp);
		loginRecovery.setOtpDate(new Date());
		return loginRecovery;
	}

	/**
	 * @param secretQuestionDAO the secretQuestionDAO to set
	 */
	public void setSecretQuestionDAO(final ISecretQuestionDAO secretQuestionDAO) {
		this.secretQuestionDAO = secretQuestionDAO;
	}
	
	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(final IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * @param loginRecoveryDAO the loginRecoveryDAO to set
	 */
	public void setLoginRecoveryDAO(final ILoginRecoveryDAO loginRecoveryDAO) {
		this.loginRecoveryDAO = loginRecoveryDAO;
	}
	

	
}

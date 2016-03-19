package com.fpl.core.login.support;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.fpl.FPLException;
import com.fpl.core.login.ILoginProcessorSupport;
import com.fpl.core.login.LoginErrorMessage;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.errormsg.FPLCommonErrorMessage;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.entity.LoginCredential;
import com.fpl.login.entity.LoginRecovery;
import com.fpl.mail.MailHandler;
import com.fpl.mail.MailHandlerFactory;
import com.fpl.mail.MailInfo;
import com.fpl.mail.MailType;
import com.fpl.persistence.login.ILoginRecoveryDAO;
import com.fpl.util.DateUtil;
import com.fpl.util.NumberUtil;

public class LoginRecoverySupport implements ILoginProcessorSupport {
	
	private ILoginRecoveryDAO loginRecoveryDAO;
	
	@Override
	public void processRecoverPassword(final LoginCredential loginCredential) {
		if(loginCredential == null) {
			throw new FPLException(LoginErrorMessage.INVALID_RECOVERY_DATA);
		}
		final MailInfo mailInfo = new MailInfo();
		mailInfo.setToAddress(loginCredential.getMailId());
		mailInfo.setSubject("FPL - Your password recovery request");
		final StringBuilder builder = new StringBuilder();
		builder.append("{0} is your One-Time Password (OTP) to log in to your FPL. ");
		builder.append("It is valid for one day only. \n \n Requested {1} \n \n " );
		builder.append("Please use the below link to reset the password  \n \n ");
		builder.append("http://localhost:8181/FPLWeb/passwordResetRequest.do?uoid={2}&pmi={3} \n \n ");
		builder.append("Thank you, \n The FPL Team" );
		mailInfo.setMessage(builder.toString());
		processLoginRecovery(loginCredential, mailInfo);
	}
	
	@Override
	public void processLoginReset(UserLoginInfo info) {
		// TODO Auto-generated method stub
		final String email = Encryption.encrypt(info.getLoginCredentialid().toString(), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		String encodedEmail = null;
		Date dt=new Date();
		try {
			
			encodedEmail = URLEncoder.encode(email, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new FPLException(FPLCommonErrorMessage.TECHNICAL_PROBLEM);
		}
		
		final MailInfo mailInfo = new MailInfo();
		mailInfo.setToAddress(info.getEmail());
		String isoCode = ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("DEFAULT_COUNTRY");
		String refNum = isoCode+ System.currentTimeMillis();
		mailInfo.setSubject("FPL-Online communication: Reference # " + refNum);
		final StringBuilder builder = new StringBuilder();
		mailInfo.setMessage(activationloginrest(builder, refNum, encodedEmail, dt ));
		mailInfo.setMessage(builder.toString());
		//processLoginRecovery(loginCredential, mailInfo);
		final MailHandler mailHandler = MailHandlerFactory.getMailSender(MailType.SSL);
		mailHandler.sendMailWithDefaultUser(mailInfo);
	}
	private String activationloginrest(StringBuilder builder, String refNum, String encodedEmail,Date date) {
		// TODO Auto-generated method stub
		
		builder.append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("MAIL_TOP"));
		builder.append("<img src='").append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("SEVER_URL")).append("/FPLWeb/WebContent/support/images/fpl.png' style='width:118px;height:88px' align ='right'><br>");
		builder.append("<p>Date & Time:");
		builder.append(new java.util.Date().toGMTString());
		builder.append("</p><p>FPL-Online Communication: Reference # ");		
		builder.append(refNum).append("</p></div>");
		//messageBuilder.append("<div style='height: 330px; padding:5px; border-bottom: 2px solid red;'><p><h4>Dear FPL Online User,</h4>Please use the User name below to enter the network " +request.getScheme() +  "://" + request.getServerName() + ":" + request.getServerPort() + "/FPLWeb/ ");
		//messageBuilder.append("<br><br>Kinldy use this password for login: "+password);
		builder.append("<div style='height: 330px; padding:5px; border-bottom: 2px solid red;'><p><h4>Dear FPL Online User,</h4>We detect a new login for your account,if it is you  Please use the below link to reset the login ");
		/*messageBuilder.append("It is valid for one day only. \n \n Requested {1} \n \n " );
		messageBuilder.append("Please use the below link to reset the password  \n \n ");*/
		builder.append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("SEVER_URL")).append("/FPLWeb/resetlogin.do?id="+encodedEmail);
		builder.append("<br><br> Sincerely,<br><br>Admin<br><br>Website : <a href='www.fpl-online.com'>www.fpl-online.com</a><br>Email   : <a href='account.admin.sg@fpl-online.com'>account.admin.sg@fpl-online.com</a><br></p></div>");		
		builder.append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("MAIL_FOOTER"));
		return builder.toString();
	}
	
	
	@Override
	public void processRecoverPassword(final LoginCredential loginCredential , final HttpServletRequest request) {
		if(loginCredential == null) {
			throw new FPLException(LoginErrorMessage.INVALID_RECOVERY_DATA);
		}
		final Integer otp1 = NumberUtil.getRandomNumber();
		final LoginRecovery loginRecovery1 = getLoginRecovery(loginCredential.getId(), otp1);
		final Date date = loginRecovery1.getOtpDate();
		final String encryptedOtp1 = Encryption.encrypt(String.valueOf(otp1), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		final String email = Encryption.encrypt(loginCredential.getMailId(), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		String encodedOtp = null;
		String encodedEmail = null;
		try {
			encodedOtp = URLEncoder.encode(encryptedOtp1, "UTF-8");
			encodedEmail = URLEncoder.encode(email, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new FPLException(FPLCommonErrorMessage.TECHNICAL_PROBLEM);
		}
		
		final MailInfo mailInfo = new MailInfo();
		mailInfo.setToAddress(loginCredential.getMailId());
		String isoCode = ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("DEFAULT_COUNTRY");
		String refNum = isoCode+ System.currentTimeMillis();
		mailInfo.setSubject("FPL-Online communication: Reference # " + refNum);
		final StringBuilder builder = new StringBuilder();
		mailInfo.setMessage(activationPasswordTemplate(builder, refNum,  request, encodedOtp , encodedEmail, date,otp1 ));
		mailInfo.setMessage(builder.toString());
		//processLoginRecovery(loginCredential, mailInfo);
		loginRecoveryDAO.save(loginRecovery1, true);
		final MailHandler mailHandler = MailHandlerFactory.getMailSender(MailType.SSL);
		mailHandler.sendMailWithDefaultUser(mailInfo);
	}
	
	private String activationPasswordTemplate(StringBuilder builder, String refNum, HttpServletRequest request ,String encodedOtp ,String encodedEmail,Date date,Integer otp) {
		// TODO Auto-generated method stub
		
		builder.append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("MAIL_TOP"));
		builder.append("<img src='").append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("SEVER_URL")).append("/FPLWeb/WebContent/support/images/fpl.png' style='width:118px;height:88px' align ='right'><br>");
		builder.append("<p>Date & Time:");
		builder.append(new java.util.Date().toGMTString());
		builder.append("</p><p>FPL-Online Communication: Reference # ");		
		builder.append(refNum).append("</p></div>");
		//messageBuilder.append("<div style='height: 330px; padding:5px; border-bottom: 2px solid red;'><p><h4>Dear FPL Online User,</h4>Please use the User name below to enter the network " +request.getScheme() +  "://" + request.getServerName() + ":" + request.getServerPort() + "/FPLWeb/ ");
		//messageBuilder.append("<br><br>Kinldy use this password for login: "+password);
		builder.append("<div style='height: 330px; padding:5px; border-bottom: 2px solid red;'><p><h4>Dear FPL Online User,</h4> "+otp+" is your One-Time Password (OTP) to log in to your FPL. It is valid for one day only.  Requested"+date.toString()+"<br><br> please follow the secure link: <a href=' ");
		/*"+encodedOtp+" is your One-Time Password (OTP) to log in to your FPL. It is valid for one day only.  Requested"+date.toString()+"
		 * messageBuilder.append("It is valid for one day only. \n \n Requested {1} \n \n " );
		messageBuilder.append("Please use the below link to reset the password  \n \n ");*/
		//builder.append("<div style='height: 330px; padding:5px; border-bottom: 2px solid red;'><p><h4>Dear FPL Online User,</h4>We have received an account activation request. In case this is not initiated by you, please write to our account admin.<br><br> In order to activate, please follow the secure link: <a href='");
		builder.append(request.getScheme() +  "://" + request.getServerName() + ":" + request.getServerPort() + "/FPLWeb/passwordResetRequest.do?uoid="+encodedOtp+"&pmi="+encodedEmail).append("'>Reset the password</a>");
		
		//builder.append(request.getScheme() +  "://" + request.getServerName() + ":" + request.getServerPort() + "/FPLWeb/passwordResetRequest.do?uoid="+encodedOtp+"&pmi="+encodedEmail);
		builder.append("<br><br> Sincerely,<br><br>Admin<br><br>Website : <a href='www.fpl-online.com'>www.fpl-online.com</a><br>Email   : <a href='account.admin.sg@fpl-online.com'>account.admin.sg@fpl-online.com</a><br></p></div>");		
		builder.append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("MAIL_FOOTER"));
		return builder.toString();
	}

	@Override
	public void processRecoverUserName(final LoginCredential loginCredential) {
		if(loginCredential == null) {
			throw new FPLException(LoginErrorMessage.INVALID_RECOVERY_DATA);
		}
		final String[] emailArray = loginCredential.getMailId().split("@");
		final String partialEmail = emailArray[0].substring(0,2);
		final MailInfo mailInfo = new MailInfo();
		mailInfo.setToAddress(loginCredential.getAlternateMailId());
		mailInfo.setSubject("FPL - Your User name recovery request");
		final StringBuilder builder = new StringBuilder();
		builder.append("Please use the User name below to enter the network http://localhost:8181/FPLWeb/ ");
		builder.append("\n \n Email: ").append(partialEmail).append("******@");
		builder.append(emailArray[1]);
		builder.append("\n \n Thank you, \n The FPL Team");
		mailInfo.setMessage(builder.toString());
		final MailHandler mailHandler = MailHandlerFactory.getMailSender(MailType.SSL);
		mailHandler.sendMailWithDefaultUser(mailInfo);
	}
	
	@Override
	public void processRecoverUserName(final LoginCredential loginCredential, final HttpServletRequest request) {
		if(loginCredential == null) {
			throw new FPLException(LoginErrorMessage.INVALID_RECOVERY_DATA);
		}
		final MailInfo mailInfo = new MailInfo();
		mailInfo.setToAddress(loginCredential.getAlternateMailId());//
		String isoCode = ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("DEFAULT_COUNTRY");
		String refNum = isoCode+ System.currentTimeMillis();
		mailInfo.setSubject("FPL-Online communication: Reference # " + refNum);
		final StringBuilder builder = new StringBuilder();
		mailInfo.setMessage(activationEmailTemplate(builder, refNum,  loginCredential.getPassword(), request, loginCredential));
		mailInfo.setMessage(builder.toString());
		final MailHandler mailHandler = MailHandlerFactory.getMailSender(MailType.SSL);
		mailHandler.sendMailWithDefaultUser(mailInfo);
	}
	private String activationEmailTemplate(StringBuilder messageBuilder, String refNum,  String password, HttpServletRequest request,LoginCredential loginCredential){		
		String[] emailArray =loginCredential.getMailId().split("@");
		final String partialEmail = emailArray[0].substring(0,2);
		messageBuilder.append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("MAIL_TOP"));
		messageBuilder.append("<img src='").append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("SEVER_URL")).append("/FPLWeb/WebContent/support/images/fpl.png' style='width:118px;height:88px' align ='right'><br>");
		messageBuilder.append("<p>Date & Time:");
		messageBuilder.append(new java.util.Date().toGMTString());
		messageBuilder.append("</p><p>FPL-Online Communication: Reference # ");		
		messageBuilder.append(refNum).append("</p></div>");
		messageBuilder.append("<div style='height: 330px; padding:5px; border-bottom: 2px solid red;'><p><h4>Dear FPL Online User,</h4>Please use the User name below to enter the network " +request.getScheme() +  "://" + request.getServerName() + ":" + request.getServerPort() + "/FPLWeb/ ");
		//messageBuilder.append("<br><br>Kinldy use this password for login: "+password);
		messageBuilder.append("\n \n Email: ").append(partialEmail).append("******@");
		messageBuilder.append(emailArray[1]);
		messageBuilder.append("<br><br> Sincerely,<br><br>Admin<br><br>Website : <a href='www.fpl-online.com'>www.fpl-online.com</a><br>Email   : <a href='account.admin.sg@fpl-online.com'>account.admin.sg@fpl-online.com</a><br></p></div>");		
		messageBuilder.append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("MAIL_FOOTER"));
		return messageBuilder.toString();
	}
	@Override
	public boolean isValidOTP(final String otp, final String date) {
		System.out.println("isvalidotp3"+otp);

		 LoginRecovery recovery = loginRecoveryDAO.getLoginRecovery(Integer.valueOf(otp));
	       if(recovery!=null){
		         if((recovery.getExpired()!=null)&&(recovery.getExpired().equalsIgnoreCase("expired"))){
		        	 System.out.println("isvalidotp3 inside not null"+otp);
			          recovery=null;
		          }else if((recovery.getOtpDate()!=null)&&(DateUtil.hoursDifference(new Date(), recovery.getOtpDate())>47))
			            {
		        	            recovery=null;
			           }
		                      //  System.out.println("isvalidotp3"+recovery.getOtpDate());
	         }
		return recovery != null;
	}
	
	@Override
	public void SendSecEmailVerfication(final LoginCredential loginCredential) {
		final Integer otp = NumberUtil.getRandomNumber();
		final LoginRecovery loginRecovery = getLoginRecovery(loginCredential.getId(), otp);
		final String encryptedOtp = Encryption.encrypt(String.valueOf(otp), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		final String email = Encryption.encrypt(loginCredential.getAlternateMailId(), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		String encodedOtp = null;
		String encodedEmail = null;
		try {
			encodedOtp = URLEncoder.encode(encryptedOtp, "UTF-8");
			encodedEmail = URLEncoder.encode(email, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new FPLException(FPLCommonErrorMessage.TECHNICAL_PROBLEM);
		}
		final MailInfo mailInfo = new MailInfo();
		mailInfo.setToAddress(loginCredential.getAlternateMailId());
		mailInfo.setSubject("FPL - Your Secondary Email verification request");
		final StringBuilder builder = new StringBuilder();
		builder.append("Please use the below link to verify the email  \n \n ");
		builder.append("http://localhost:8181/FPLWeb/verifySecEmail.do?uoid=");
		builder.append(encodedOtp).append("&pmi=");
		builder.append(encodedEmail).append("\n \n ");
		builder.append("Thank you, \n The FPL Team" );
		mailInfo.setMessage(builder.toString());
		loginRecoveryDAO.save(loginRecovery, true);
		final MailHandler mailHandler = MailHandlerFactory.getMailSender(MailType.SSL);
		mailHandler.sendMailWithDefaultUser(mailInfo);
	}
	
	@Override
	public void SendSecEmailVerfication(final LoginCredential loginCredential, final HttpServletRequest request) {
		final Integer otp = NumberUtil.getRandomNumber();
		final LoginRecovery loginRecovery = getLoginRecovery(loginCredential.getId(), otp);
		final String encryptedOtp = Encryption.encrypt(String.valueOf(otp), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		final String email = Encryption.encrypt(loginCredential.getAlternateMailId(), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		String encodedOtp = null;
		String encodedEmail = null;
		try {
			encodedOtp = URLEncoder.encode(encryptedOtp, "UTF-8");
			encodedEmail = URLEncoder.encode(email, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new FPLException(FPLCommonErrorMessage.TECHNICAL_PROBLEM);
		}
		final MailInfo mailInfo = new MailInfo();
		mailInfo.setToAddress(loginCredential.getAlternateMailId());
		mailInfo.setSubject("FPL - Your Secondary Email verification request");
		final StringBuilder builder = new StringBuilder();
		builder.append("Please use the below link to verify the email  \n \n ");
		builder.append(request.getScheme() +  "://" + request.getServerName() + ":" + request.getServerPort() + "/FPLWeb/verifySecEmail.do?uoid=");
		builder.append(encodedOtp).append("&pmi=");
		builder.append(encodedEmail).append("\n \n ");
		builder.append("Thank you, \n The FPL Team" );
		mailInfo.setMessage(builder.toString());
		loginRecoveryDAO.save(loginRecovery, true);
		final MailHandler mailHandler = MailHandlerFactory.getMailSender(MailType.SSL);
		mailHandler.sendMailWithDefaultUser(mailInfo);
	}
	
	private void processLoginRecovery(final LoginCredential loginCredential, final MailInfo mailInfo) {
		final Integer otp = NumberUtil.getRandomNumber();
		final LoginRecovery loginRecovery = getLoginRecovery(loginCredential.getId(), otp);
		final Date date = loginRecovery.getOtpDate();
		final String encryptedOtp = Encryption.encrypt(String.valueOf(otp), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		final String email = Encryption.encrypt(loginCredential.getMailId(), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		String encodedOtp = null;
		String encodedEmail = null;
		try {
			encodedOtp = URLEncoder.encode(encryptedOtp, "UTF-8");
			encodedEmail = URLEncoder.encode(email, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new FPLException(FPLCommonErrorMessage.TECHNICAL_PROBLEM);
		}
		final String[] param = new String[] {""+encodedOtp, date.toString(), ""+encodedOtp, ""+encodedEmail};
		System.out.println("String msg while mail"+mailInfo.getMessage());
		final String message = MessageFormat.format(mailInfo.getMessage(), param);
		System.out.println("String msg while mail"+message);
		mailInfo.setMessage(message);
		loginRecoveryDAO.save(loginRecovery, true);
		final MailHandler mailHandler = MailHandlerFactory.getMailSender(MailType.SSL);
		mailHandler.sendMailWithDefaultUser(mailInfo);
	}
	
	private LoginRecovery getLoginRecovery(final Long loginCredentialId, final Integer otp) {
		final LoginRecovery loginRecovery  = new LoginRecovery();
		loginRecovery.setLoginCredentialId(loginCredentialId);
		loginRecovery.setOtp(otp);
		loginRecovery.setOtpDate(new Date());
		return loginRecovery;
	}
	
	/**
	 * @param loginRecoveryDAO the loginRecoveryDAO to set
	 */
	public void setLoginRecoveryDAO(final ILoginRecoveryDAO loginRecoveryDAO) {
		this.loginRecoveryDAO = loginRecoveryDAO;
	}

	
}

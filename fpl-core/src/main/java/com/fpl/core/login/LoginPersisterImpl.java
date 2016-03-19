package com.fpl.core.login;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang.StringUtils;

import com.fpl.FPLException;
import com.fpl.common.IValidator;
import com.fpl.login.LoginRegisterParam;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.entity.LoginCredential;
import com.fpl.login.entity.LoginRecovery;
import com.fpl.login.entity.LoginSupport;
import com.fpl.mail.MailHandler;
import com.fpl.mail.MailHandlerFactory;
import com.fpl.mail.MailInfo;
import com.fpl.mail.MailType;
import com.fpl.persistence.login.ILoginCredentialDAO;
import com.fpl.persistence.login.ILoginSupportDAO;
import com.fpl.persistence.recovery.personal.PersonalDAO;
import com.fpl.profile.personal.PersonalData;
import com.fpl.util.DateUtil;
import com.fpl.util.StringUtil;

public class LoginPersisterImpl implements ILoginPersister {
	
	private PersonalDAO personaldao;
	private ILoginCredentialDAO loginCredentialDAO;
	private ILoginSupportDAO loginSupportDAO;
	private IValidator<LoginRegisterParam> validator;
	private ILoginPersisterSupport persisterSupport;
	
	@Override
	public void registerLogin(final LoginRegisterParam loginRegister) {
		validator.validate(loginRegister);
		final LoginCredential credential = loginCredentialDAO.getLoginCredentialByMail(loginRegister.getPrimaryEmailId());
		if(credential != null) {
			throw new FPLException(LoginErrorMessage.USER_ALREADY_EXIST);
		}
		final LoginCredential loginCredential = persisterSupport.getLoginCredential(loginRegister);
		final Long loginCredentialId = (Long) loginCredentialDAO.save(loginCredential, true);
		final LoginSupport loginSupport = getLoginSupport(null, loginCredential.getPassword());
		loginSupport.setLoginCredentialId(loginCredentialId);
		loginSupportDAO.save(loginSupport, true);
		persisterSupport.sendRegistration(loginCredential);
	}
	
	private boolean isActiveLinkExpired(LoginCredential credential)
	{
		
		LoginSupport loginSupport =loginSupportDAO.getByLoginCredentialId(credential.getId());
		if(loginSupport!=null && loginSupport.getActive()!=null && loginSupport.getActive()==1)
		{
			if(DateUtil.hoursDifference(new Date(), loginSupport.getExpireDate())>47)
			{
			  return true;	
			}
		}
	  return false;	
	}
	public void registerLogin(final LoginRegisterParam loginRegister, HttpServletRequest request) {
		System.out.println(loginRegister.getPrimaryEmailId());
		validator.validate(loginRegister);
		final LoginCredential credential = loginCredentialDAO.getLoginCredentialByMail(loginRegister.getPrimaryEmailId());
		final LoginCredential loginCredential = persisterSupport.getLoginCredential(loginRegister);
		final PersonalData psdta= persisterSupport.getpersonaldata(loginRegister);
		if(credential != null) {
			if(!isActiveLinkExpired(credential))
			{
				throw new FPLException(LoginErrorMessage.USER_ALREADY_EXIST);
			}	
		     //personaldao.update(psdta,true);
			loginCredential.setId(credential.getId());
			loginCredentialDAO.saveOrUpdate(loginCredential, true);
			final LoginSupport loginSupport = getLoginSupport(null, loginCredential.getPassword());
			LoginSupport lSupport =loginSupportDAO.getByLoginCredentialId(credential.getId());
			loginSupport.setId(lSupport.getId());
			loginSupport.setLoginCredentialId(credential.getId());
			loginSupportDAO.saveOrUpdate(loginSupport, true);
		}else
		{
		
			//personaldao.save(psdta, true);
		final Long loginCredentialId = (Long) loginCredentialDAO.save(loginCredential, true);
		final LoginSupport loginSupport = getLoginSupport(null, loginCredential.getPassword());
		loginSupport.setLoginCredentialId(loginCredentialId);
		loginSupportDAO.save(loginSupport, true);
		}
		
		persisterSupport.sendRegistration(loginCredential, request);
	}

	@Override
	public void activateUser(final String otp) {
		final LoginRecovery recovery = persisterSupport.getLoginRecovery(otp);
		if(recovery == null) {
			throw new FPLException(LoginErrorMessage.OTP_EXPIRED,"user activate link");
		}
		final LoginSupport loginSupport = loginSupportDAO.getByLoginCredentialId(recovery.getLoginCredentialId());
		loginSupport.setActive(Integer.valueOf(0));
		loginSupportDAO.update(loginSupport, true);
		persisterSupport.deleteLoginRecovery(recovery);
	}
	
	@Override
	public void activateUser(final String otp, final String password){
		final LoginRecovery recovery = persisterSupport.getLoginRecovery(otp);
		if(recovery == null) {
			throw new FPLException(LoginErrorMessage.OTP_EXPIRED,"user activate link");
		}
		System.out.println("crediantailid from recovery"+recovery.getLoginCredentialId());
		final LoginSupport loginSupport = loginSupportDAO.getByLoginCredentialId(recovery.getLoginCredentialId());
		System.out.println("crediantailid from loginSupport"+loginSupport.getLoginCredentialId());
		final LoginCredential credential = loginCredentialDAO.get(loginSupport.getLoginCredentialId());
		resetPassword(otp, password);
		activateUser(otp);
	}
	
	@Override
	public void resetPassword(final String email, final String password) {
		 LoginRecovery recovery = persisterSupport.getLoginRecovery(email);
		if(recovery!=null){
			if((recovery.getExpired()!=null)&&(recovery.getExpired().equalsIgnoreCase("expired"))){
				recovery=null;
			}
			
		}
		if(recovery == null) {
			throw new FPLException(LoginErrorMessage.OTP_EXPIRED,"password reset link");
		}
		final LoginSupport support = loginSupportDAO.getByLoginCredentialId(recovery.getLoginCredentialId());
		if(password.equals(support.getPasswordOne()) || password.equals(support.getPasswordTwo()) || password.equals(support.getPasswordThree())) {
			throw new FPLException(LoginErrorMessage.PASSWORD_SAME);
		}
		final LoginCredential credential = loginCredentialDAO.get(support.getLoginCredentialId());
		if(credential.getMailId().equals(password)) {
			throw new FPLException(LoginErrorMessage.USERNAME_PASSWORD_DIFFE);
		}
		if(password.length() < 8) {
			throw new FPLException(LoginErrorMessage.PASSWORD_SMALL);
		}
		credential.setPassword(password);
		recovery.setExpired("expired");
		getLoginSupport(support, password);
		loginCredentialDAO.update(credential, true);
		loginSupportDAO.update(support, true);
		AlertPasswordChange(credential.getMailId());
		persisterSupport.UpdateLoginRecovery(recovery);
		//persisterSupport.deleteLoginRecovery(recovery);
	}
	
	private LoginSupport getLoginSupport(LoginSupport loginSupport, final String passsword) {
		if(loginSupport == null) {
			final Integer defalutValue = Integer.valueOf(0);
			loginSupport = new LoginSupport();
			loginSupport.setActive(Integer.valueOf(1));
			loginSupport.setAttempt(defalutValue);
			loginSupport.setLocked(defalutValue);
			loginSupport.setPasswordOne(passsword);
			loginSupport.setSecEmailVerified(Integer.valueOf(1));
			loginSupport.setExpireDate(DateUtil.getAddedDate(new Date(), 2));
		} else {
			if(StringUtil.isEmpty(loginSupport.getPasswordOne())) {
				loginSupport.setPasswordOne(passsword);
			} else if(StringUtil.isEmpty(loginSupport.getPasswordTwo())) {
				loginSupport.setPasswordTwo(passsword);
			} else if(StringUtil.isEmpty(loginSupport.getPasswordThree())) {
				loginSupport.setPasswordThree(passsword);
			} 
		}
		return loginSupport;
	}
	
	@Override
	public void changePassword(final String email, final String password) {
		final LoginCredential credential = loginCredentialDAO.getLoginCredentialByMail(email);
		final LoginSupport support = loginSupportDAO.getByLoginCredentialId(credential.getId());
		if(credential.getMailId().equals(password)) {
			throw new FPLException(LoginErrorMessage.USERNAME_PASSWORD_DIFFE);
		}
		if(password.length() < 8) {
			throw new FPLException(LoginErrorMessage.PASSWORD_SMALL);
		}
		if(password.equals(support.getPasswordOne()) || password.equals(support.getPasswordTwo()) || password.equals(support.getPasswordThree())) {
			throw new FPLException(LoginErrorMessage.PASSWORD_SAME);
		}
		credential.setPassword(password);
		getLoginSupport(support, password);
		loginCredentialDAO.update(credential, true);
		loginSupportDAO.update(support, true);
		AlertPasswordChange(email);
	}
	
	
	public void AlertPasswordChange(final String email) {
		 
		/*final MailInfo mailInfo = new MailInfo();
		mailInfo.setToAddress(email);//
		mailInfo.setSubject("FPL - Your password has been changed");
		final StringBuilder builder = new StringBuilder();
		builder.append("\n \n Thank you, \n The FPL Team");
		mailInfo.setMessage(builder.toString());*/
		
		final MailInfo mailInfo = new MailInfo();
		mailInfo.setToAddress(email);//
		String isoCode = ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("DEFAULT_COUNTRY");
		String refNum = isoCode+ System.currentTimeMillis();
		mailInfo.setSubject("FPL-Online communication: Reference # " + refNum);
		final StringBuilder builder = new StringBuilder();
		mailInfo.setMessage(activationEmailTemplate(builder, refNum ));
		mailInfo.setMessage(builder.toString());
		final MailHandler mailHandler = MailHandlerFactory.getMailSender(MailType.SSL);
		mailHandler.sendMailWithDefaultUser(mailInfo);
	
	}
	
private String activationEmailTemplate(StringBuilder messageBuilder, String refNum){		
	
	messageBuilder.append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("MAIL_TOP"));
	messageBuilder.append("<img src='").append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("SEVER_URL")).append("/FPLWeb/WebContent/support/images/fpl.png' style='width:118px;height:88px' align ='right'><br>");
	messageBuilder.append("<p>Date & Time:");
	messageBuilder.append(new java.util.Date().toGMTString());
	messageBuilder.append("</p><p>FPL-Online Communication: Reference # ");		
	messageBuilder.append(refNum).append("</p></div>");
	messageBuilder.append("<div style='height: 330px; padding:5px; border-bottom: 2px solid red;'><p><h4>Dear FPL Online User,</h4>Your password has been changed ");
	//messageBuilder.append("<br><br>Kinldy use this password for login: "+password);
	messageBuilder.append("<br><br> Sincerely,<br><br>Admin<br><br>Website : <a href='www.fpl-online.com'>www.fpl-online.com</a><br>Email   : <a href='account.admin.sg@fpl-online.com'>account.admin.sg@fpl-online.com</a><br></p></div>");		
	messageBuilder.append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("MAIL_FOOTER"));
	return messageBuilder.toString();
}
	
	
	@Override
	public void updateLoginDetail(final LoginRegisterParam loginRegister) {
		final LoginCredential credential = loginCredentialDAO.getLoginCredentialByMail(loginRegister.getPrimaryEmailId());
		loginRegister.setPassword(credential.getPassword());
		validator.validate(loginRegister);
		//credential.setAlternateMailId(loginRegister.getAlternateMailId());
		if(!StringUtils.isEmpty(loginRegister.getMobilenumber())){
			credential.setMobileNumber(Long.valueOf(loginRegister.getMobilenumber()));
		}
		credential.setSecretAnswer(loginRegister.getSecretAnswer());
		loginCredentialDAO.update(credential, true);
		if(!loginRegister.getAlternativemailId().equals(credential.getAlternateMailId())) {
			final LoginSupport loginSupport = loginSupportDAO.getByLoginCredentialId(credential.getId());
			loginSupport.setSecEmailVerified(1);
			loginSupportDAO.update(loginSupport, true);
		}
	}
	
	@Override
	public void verifySecondaryEmail(final String otp) {
		final LoginRecovery recovery = persisterSupport.getLoginRecovery(otp);
		if(recovery == null) {
			throw new FPLException(LoginErrorMessage.OTP_EXPIRED,"Email verification link");
		}
		final LoginSupport loginSupport = loginSupportDAO.getByLoginCredentialId(recovery.getLoginCredentialId());
		loginSupport.setSecEmailVerified(Integer.valueOf(0));
		loginSupportDAO.update(loginSupport, true);
		persisterSupport.deleteLoginRecovery(recovery);
	}
	
	@Override
	public void updateStatus(String status, UserLoginInfo info) {
		// TODO Auto-generated method stub
		if(info!=null) {
			final LoginSupport loginSupport = loginSupportDAO.getByLoginCredentialId(info.getLoginCredentialid());
			loginSupport.setStatus(status);
			loginSupportDAO.update(loginSupport, true);
		}
	}
	
	
	@Override
	public void uploadProfileImage(HttpServletRequest request) {
		try {
			
			
			String email=(String)request.getAttribute("EMAIL");
			
			if(email!=null && !"".equalsIgnoreCase(email))
			{
				final LoginCredential credential = loginCredentialDAO.getLoginCredentialByMail(email);
				if(credential!=null)
				{
					final LoginSupport loginSupport = loginSupportDAO.getByLoginCredentialId(credential.getId());
					if(loginSupport!=null)
					{
						readProfileImage(request,credential.getId());
//						FileOutputStream fout= new FileOutputStream ("C:/Output Files/img.jpg");
//			              int byt;
//			             // while ((byt=stream.read()) != -1)
//			              {
//			                  fout.write(b);
//			              }
//			              fout.close();
						loginSupport.setImageUrl(credential.getId()+"profile_img.jpg");
						
						loginSupportDAO.update(loginSupport, true);
					}	
				}
			
						             
		     }
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
	}
	
	
	
	private void readProfileImage(HttpServletRequest request,long ImageId) throws Exception
	{
		
		FileOutputStream fout= new FileOutputStream ("C:/Software/apache-tomcat-7.0.63/wtpwebapps/Images/"+ImageId+"profile_img.jpg");
	    //ByteArrayOutputStream os = new ByteArrayOutputStream();

		//boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload();
		// Parse the request
		FileItemIterator iter = upload.getItemIterator(request);
		while (iter.hasNext()) {
			FileItemStream item = iter.next();
	     String name = item.getFieldName();
	     InputStream stream = item.openStream();
	     
	     float imageQuality = 0.3f;

			//Create the buffered image
			BufferedImage bufferedImage = ImageIO.read(stream);
			
			BufferedImage resizedImage = new BufferedImage(68, 68,  BufferedImage.TYPE_INT_RGB);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(bufferedImage, 0, 0, 68, 68, null);
			g.dispose();

			//Get image writers
			Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("jpg");

			if (!imageWriters.hasNext())
				throw new IllegalStateException("Writers Not Found!!");

			ImageWriter imageWriter = (ImageWriter) imageWriters.next();
			ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(fout);
			imageWriter.setOutput(imageOutputStream);

			ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

			//Set the compress quality metrics
			imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			imageWriteParam.setCompressionQuality(imageQuality);

			//Created image
			imageWriter.write(null, new IIOImage(resizedImage, null, null), imageWriteParam);
			
			// close all streams
			stream.close();
			fout.close();
			imageOutputStream.close();
			imageWriter.dispose();
			
//	     if (item.isFormField()) {
//	               System.out.println("Form field " + name + " with value " + Streams.asString(stream) + " detected.");
//	     } else {
//	    	 
//	    	 	byte[] buffer = new byte[1024];
//	    	
//	    	    int line = 0;
//	    	    // read bytes from stream, and store them in buffer
//	    	    while ((line = stream.read(buffer)) != -1) {
//	    	        // Writes bytes from byte array (buffer) into output stream.
//	    	        os.write(buffer, 0, line);
//	    	    }
//	    	    stream.close();
//	    	    os.flush();
//	    	    os.close();
//	     }	    	    
		}          
     // return os.toByteArray();
	}

	/**
	 * @param loginCredentialDAO the loginCredentialDAO to set
	 */
	public void setLoginCredentialDAO(final ILoginCredentialDAO loginCredentialDAO) {
		this.loginCredentialDAO = loginCredentialDAO;
	}

	public void setPersonalDAO(final PersonalDAO PersonalDAO) {
		this.personaldao = PersonalDAO;
	}
	/**
	 * @param loginSupportDAO the loginSupportDAO to set
	 */
	public void setLoginSupportDAO(final ILoginSupportDAO loginSupportDAO) {
		this.loginSupportDAO = loginSupportDAO;
	}

	/**
	 * @param validator the validator to set
	 */
	public void setValidator(final IValidator<LoginRegisterParam> validator) {
		this.validator = validator;
	}

	/**
	 * @param persisterSupport the persisterSupport to set
	 */
	public void setPersisterSupport(final ILoginPersisterSupport persisterSupport) {
		this.persisterSupport = persisterSupport;
	}

	
}

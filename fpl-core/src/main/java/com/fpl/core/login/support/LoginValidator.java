package com.fpl.core.login.support;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Set;

import com.fpl.FPLException;
import com.fpl.core.login.ILoginPersister;
import com.fpl.core.login.ILoginProcessor;
import com.fpl.core.login.ILoginProcessorSupport;
import com.fpl.core.login.ILoginValidator;
import com.fpl.core.login.LoginErrorMessage;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.errormsg.FPLCommonErrorMessage;
import com.fpl.login.FPLLoginOutput;
import com.fpl.login.ILoginInputParam;
import com.fpl.login.LoginRecordTO;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.entity.LoginSupport;
import com.fpl.persistence.login.ILoginAuditDAO;
import com.fpl.persistence.login.ILoginSupportDAO;

import javassist.compiler.ast.Symbol;


public class LoginValidator implements ILoginValidator {

	private ILoginSupportDAO loginSupportDAO;
	private ILoginAuditDAO loginAuditDAO; 
	private ILoginProcessorSupport loginProcessorSupport;
	@Override
	public void validteCredential(final UserLoginInfo loginInfo, final ILoginInputParam loginParam,FPLLoginOutput loginOutput) {
		System.out.println("inside validator");
		if(loginInfo == null) {
			
			throw new FPLException(LoginErrorMessage.INVALID_CREDENTIAL);
		}
		
		final LoginSupport loginSupport = loginSupportDAO.getByLoginCredentialId(loginInfo.getLoginCredentialid());
		System.out.println("inside validatesession"+loginParam.getSessionId());
		
		if(loginSupport.getActive().intValue() == 1) {
			throw new FPLException(LoginErrorMessage.NOT_ACTIVATED);
		}
		
		if((null!=loginSupport.getNon_consecutive_on())&&(loginSupport.getNon_consecutive_on().equalsIgnoreCase("true"))){
			//nonconsecutive attempts 
			System.out.println("inside nonconsecutive");
			if((loginSupport.getLocked() != null) && (loginSupport.getLocked().intValue() == 1)) {
				   throw new FPLException(LoginErrorMessage.LOCKED);
				  }
				  
				  System.out.println("["+loginInfo.getPassword()+"]");
				  System.out.println("["+loginParam.getPassword()+"]");
				  if(!loginInfo.getPassword().equals(loginParam.getPassword())) {
				   final Integer oldAttempt = loginSupport.getAttempt();
				   final int newAttempt = oldAttempt == null ? 1 : (oldAttempt.intValue() + 1);
				   loginSupport.setAttempt(newAttempt);
				   if(newAttempt == 3) {
				    loginSupport.setLocked(1);
				   }
				   loginSupportDAO.update(loginSupport, true);
				   throw new FPLException(LoginErrorMessage.INVALID_CREDENTIAL);
				  } else if((loginSupport.getAttempt() != null) && (loginSupport.getAttempt().intValue() > 0)) {
				   loginSupport.setAttempt(0);
				   loginSupport.setNon_consecutive_on("false");
				   loginSupportDAO.update(loginSupport, true);
				  }
				 
			
		}else{		//consecutive attempts,locking on sessions for that user
			System.out.println("inside consecutive");
		HashMap<String, Integer>sess=loginAuditDAO.getSessionlog(loginInfo.getLoginCredentialid(),loginParam.getSessionId());
		
        String lockstatus=null;
        Integer attemptsatus=null;
        
        Set mapSet = (Set) sess.entrySet();
		Iterator mapIterator = mapSet.iterator();
		System.out.println("Display the key/value of HashMap.");
		while (mapIterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) mapIterator.next();
			// getKey Method of HashMap access a key of map
			lockstatus= (String) mapEntry.getKey();
			//getValue method returns corresponding key's value
			attemptsatus =  (Integer) mapEntry.getValue();
			System.out.println("Key : " + lockstatus + "= Value : " + attemptsatus);
		}
		if(null!=lockstatus && !lockstatus.isEmpty()){
			System.out.println("inside if"+loginParam.getSessionId());
			
		    if(lockstatus.equalsIgnoreCase("true")){
		    	 throw new FPLException(LoginErrorMessage.LOCKED);
		        
		      } 
		}
		System.out.println("["+loginInfo.getPassword()+"]");
		System.out.println("["+loginParam.getPassword()+"]");
		
		if(!loginInfo.getPassword().equals(loginParam.getPassword())) {
			final Integer oldAttempt =attemptsatus;//loginSupport.getAttempt();
			final int newAttempt = oldAttempt == null ? 1 : (oldAttempt.intValue() + 1);
			loginSupport.setAttempt(newAttempt);
			if(newAttempt == 2) {
				//loginSupport.setLocked(1);
				System.out.println("inside  sessionadd ");
				loginAuditDAO.sessionlog(loginInfo.getLoginCredentialid(), loginParam.getSessionId());
			}
			loginAuditDAO.sessionlog(loginInfo.getLoginCredentialid(), loginParam.getSessionId(),newAttempt);
			//loginSupportDAO.update(loginSupport, true);
			throw new FPLException(LoginErrorMessage.INVALID_CREDENTIAL);
		} else  {//if((attemptsatus != null) && (attemptsatus.intValue() > 0))
			System.out.println("inside success");
			//loginSupport.setAttempt(0);
			int attempt=0;
			loginAuditDAO.sessionlog(loginInfo.getLoginCredentialid(), loginParam.getSessionId(),attempt);
			if((null!=loginSupport.getBrowser_Active())&&(loginSupport.getBrowser_Active().equals("true")&&loginParam.getDeviceType().equals("Browser"))){
				loginProcessorSupport.processLoginReset(loginInfo);//recoverLogin(loginInfo);
				
				//generating link
				final String email = Encryption.encrypt(loginInfo.getLoginCredentialid().toString(), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
				String encodedEmail = null;
				Date dt=new Date();
				try {
					
					encodedEmail = URLEncoder.encode(email, "UTF-8");
				} catch (final UnsupportedEncodingException e) {
					throw new FPLException(FPLCommonErrorMessage.TECHNICAL_PROBLEM);
				}
				final StringBuilder builder = new StringBuilder();
				builder.append(ResourceBundle.getBundle("com.fpl.mail.config.mailconfig").getString("SEVER_URL")).append("/FPLWeb/resetlogin.do?id="+encodedEmail);
				loginOutput.setLink(builder.toString());
				throw new FPLException(LoginErrorMessage.AlREADY_LOGGEDIN);
			}else if((null!=loginSupport.getMobile_Active())&&(loginSupport.getMobile_Active().equals("true"))&&loginParam.getDeviceType().equals("Library")){
				throw new FPLException(LoginErrorMessage.AlREADY_LOGGEDIN);
			}
			//loginSupportDAO.update(loginSupport, true);
		}
	     }
		
	}
	/**
	 * @param loginSupportDAO the loginSupportDAO to set
	 */
	public final void setLoginSupportDAO(final ILoginSupportDAO loginSupportDAO) {
		this.loginSupportDAO = loginSupportDAO;
	}
	/**
	 * @param loginAuditDAO the loginAuditDAO to set
	 */
	public final void setLoginAuditDAO(final ILoginAuditDAO loginAuditDAO) {
		this.loginAuditDAO = loginAuditDAO;
	}
	/**
	 * @param loginProcessorSupport the loginProcessorSupport to set
	 */
	public void setLoginProcessorSupport(
			final ILoginProcessorSupport loginProcessorSupport) {
		this.loginProcessorSupport = loginProcessorSupport;
	}

}

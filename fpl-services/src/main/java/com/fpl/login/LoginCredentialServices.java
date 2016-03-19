package com.fpl.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fpl.FPLException;
import com.fpl.common.FPLServiceResponse;
import com.fpl.core.login.ILoginPersister;
import com.fpl.core.login.ILoginProcessor;
import com.fpl.core.login.LoginErrorMessage;
import com.fpl.errormsg.FPLCommonErrorMessage;
import com.fpl.util.StringUtil;

public class LoginCredentialServices {
	
	private ILoginProcessor loginProcessor;
	private ILoginPersister loginPersister;
	
	public Map<String, Object> getLoginInfo(final ILoginInputParam loginParam) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final FPLLoginOutput loginOutput = new FPLLoginOutput();
		loginOutput.setUser(loginParam.getPrimaryEmailId());
		try {
			final UserLoginInfo userLoginInfo = loginProcessor.getLoginInfo(loginParam,loginOutput);
			map.put("UserLoginInfo", userLoginInfo);
			loginOutput.setSuccess(true);
			
		} catch (final Exception e) {
			e.printStackTrace();
			loginOutput.setSuccess(false);
			loginOutput.setErrorMsg(e.getMessage());
		}
		map.put("FPLLoginOutput", loginOutput);
		
		return map;
	}
	
	public FPLServiceResponse recoverPassword(final IUserRecoverInput loginRecovery) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			loginProcessor.recoverPassword(loginRecovery);
			response.setReason("Thanks for Contacting FPL. Your Password has been emailed to your primary email");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
	
	public FPLServiceResponse recoverPassword(final IUserRecoverInput loginRecovery, HttpServletRequest request) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			loginProcessor.recoverPassword(loginRecovery, request);
			response.setReason("Thanks for Contacting FPL. Your Password has been emailed to your primary email");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
	
	public FPLServiceResponse recoverUserName(final IUserRecoverInput loginRecovery) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			loginProcessor.recoverUserName(loginRecovery);
			response.setReason("Thanks for Contacting FPL. Your Primary Email details has been emailed to your alternate email");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
	
	public FPLServiceResponse recoverUserName(final IUserRecoverInput loginRecovery, HttpServletRequest request) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			loginProcessor.recoverUserName(loginRecovery, request);
			response.setReason("Thanks for Contacting FPL. Your Primary Email details has been emailed to your alternate email");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
	
	public FPLServiceResponse UpdateStatus(final String status, final UserLoginInfo inf) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			loginPersister.updateStatus(status, inf);
			response.setReason("Your Status has been updated");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
	public FPLServiceResponse registerLogin(final LoginRegisterParam loginRegister) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			loginPersister.registerLogin(loginRegister);
			response.setReason("Thanks for Registering with FPL. Your details has been emailed to your Primary Email");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
	
	public FPLServiceResponse registerLogin(final LoginRegisterParam loginRegister, HttpServletRequest request) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			loginPersister.registerLogin(loginRegister, request);
			response.setReason("Thanks for Registering with FPL. Your details has been emailed to your Primary Email");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
	
	public FPLServiceResponse activateUser(final String otp) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			loginPersister.activateUser(otp);
			response.setReason("Thanks for Registering with FPL. Your account has been activated");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
	
	public FPLServiceResponse activateUser(final String otp, final String password) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			loginPersister.activateUser(otp, password);
			response.setReason("Thanks for Registering with FPL. Your account has been activated");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	} 
	
	public FPLServiceResponse verifySecondaryEmail(final String otp) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			loginPersister.verifySecondaryEmail(otp);
			response.setReason("Thanks for Registering with FPL. Your account has been activated");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
	
	public FPLServiceResponse resetPassword(final PasswordResetParam resetParam) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			if(StringUtil.isEmpty(resetParam.getNewPassword())) {
				throw new FPLException(FPLCommonErrorMessage.PLEASE_ENTER, "Password");
			}
			if(StringUtil.isEmpty(resetParam.getConformPassword())) {
				throw new FPLException(FPLCommonErrorMessage.PLEASE_ENTER, "Confirm Password");
			}
			if(!resetParam.getConformPassword().equals(resetParam.getNewPassword())) {
				throw new FPLException(LoginErrorMessage.TWO_PASSWORD_DIFFER);
			}
			loginPersister.resetPassword(resetParam.getOtp(), resetParam.getNewPassword());
			response.setReason("Your password has been changed !");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
	
	public FPLServiceResponse changePassword(final PasswordResetParam resetParam) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			loginPersister.changePassword(resetParam.getEmail(), resetParam.getNewPassword());
			response.setReason("Your password has been changed !");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
	
	public FPLServiceResponse updateLoginDetail(final LoginRegisterParam loginRegister) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			loginPersister.updateLoginDetail(loginRegister);
			response.setReason("Updated Profile information");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
	
	public boolean isValidOTP(final String otp, final String date) {
		System.out.println("isvalidotp1"+otp);
		return loginProcessor.isValidOTP(otp, date);
	}
	
	/**
	 * @param loginProcessor the loginProcessor to set
	 */
	public void setLoginProcessor(final ILoginProcessor loginProcessor) {
		this.loginProcessor = loginProcessor;
	}

	public FPLServiceResponse SendSecEmailVerfication(final Long loginId) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			loginProcessor.SendSecEmailVerfication(loginId);
			response.setReason("Email sent!");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
	
	public FPLServiceResponse SendSecEmailVerfication(final Long loginId, HttpServletRequest request) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			loginProcessor.SendSecEmailVerfication(loginId, request);
			response.setReason("Email sent!");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
	
	
	public FPLServiceResponse uploadProfileImage(HttpServletRequest request) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			loginPersister.uploadProfileImage(request);
			response.setReason("Thanks for Registering with FPL. Your details has been emailed to your Primary Email");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
	
	/**
	 * @param loginPersister the loginPersister to set
	 */
	public void setLoginPersister(final ILoginPersister loginPersister) {
		this.loginPersister = loginPersister;
	}

	public boolean Loginlog(LoginRecordTO lr) {
		// TODO Auto-generated method stub
		try {
			final boolean loglogin = loginProcessor.Loginlog(lr);
			System.out.println("entered into login services");
			
		} catch (final Exception e) {
			e.printStackTrace();
			
		}
		
		return true;
	}
}

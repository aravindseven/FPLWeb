package com.fpl.login;

import com.fpl.core.login.ILoginProcessor;
import com.fpl.factory.BeanUtil;
import com.fpl.login.config.LoginBean;


public class LoginManagerTest {

	public static void main(final String[] args) {
		final LoginInputParam param = new LoginInputParam();
		param.setPrimaryEmailId("murali.p@yaazhsoft.com");
		final ILoginProcessor manager = BeanUtil.getInstance(LoginBean.LOGIN_PROCESSOR).getBean(ILoginProcessor.class);
		final LoginInfo loginInfo = manager.getLoginInfo(param);
		System.out.println(loginInfo);
	}
	
	/*public static void main(final String[] args) {
		final ILoginCredentialDAO dao = BeanUtil.getInstance(new IBeanInfo() {
			@Override
			public String getBeanId() {
				return "fpl.login.loginCredentialDAO";
			}
			@Override
			public BeanConfigLocationType getBeanConfig() {
				return BeanConfigLocationType.LOGIN;
			}
		}).getBean(ILoginCredentialDAO.class);
		
		final LoginCredential credential = new LoginCredential(); 
		//credential.setMailId("ENC-ahORuMe6nGXUvmEPPy5bEga6A259jHhD");
		credential.setMailId("murali.p@yaazhsoft.com");
		dao.save(credential,true);
	}*/
}

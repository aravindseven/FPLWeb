/*package com.fpl.persistence.login;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.login.ILoginInputParam;
import com.fpl.login.ILoginRecoveryInput;
import com.fpl.login.LoginRecordTO;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.entity.LoginCredential;
import com.fpl.login.entity.LoginRecord;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.util.StringUtil;

public class LoginRecordsDAO extends HibernateDAOSupport<com.fpl.login.entity.LoginRecord> implements IloginRecordDAO {
private static final String ENTITY_CLASS_NAME = "com.fpl.login.entity.LoginRecord";
	
	@Override
	protected Class<LoginRecord> getEntityClass() {
		return (Class<LoginRecord>) ClassInstantiator.loadClass(ENTITY_CLASS_NAME);
	}
	@Override
	public boolean LoginRecord(LoginRecordTO loginInputParam) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Serializable save(final LoginRecord entity, final boolean flushMode) {
		System.out.println("save called");
		return super.save(entity, flushMode);
	}

	@Override
	public void update(final LoginRecord entity, final boolean flushMode) {
		super.update(entity, flushMode);
	}

	@Override
	public void delete(final LoginRecord entity, final boolean flushMode) {
		super.delete(entity, flushMode);
	}
	@Override
	public LoginRecord getStatus(final LoginRecordTO loginInputParam) {
		// TODO Auto-generated method stub
		System.out.println("getstatus");
		return getHibernateTemplate().execute(new HibernateCallback<LoginRecord, LoginRecord>("getLoginCredential for recovery") {
			@Override
			public LoginRecord doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				if(!StringUtil.isEmpty(loginInputParam.getUserId())) {
		            criteria.add(Restrictions.eq("userId",loginInputParam.getUserId()));
		            criteria.add(Restrictions.eq("devicetype",loginInputParam.getDevicetype()));
		            criteria.add(Restrictions.eq("loginresult","true"));
		            criteria.add(Restrictions.eq("logoutdate","null"));
				}
				//criteria.add(Restrictions.eq("secretAnswer",loginRecovery.getSecretQuestion()));
				//criteria.add(Restrictions.eq("alternateMailId",loginRecovery.getAltEmail()));
				//criteria.add(Restrictions.eq("mobileNumber",Long.valueOf(loginRecovery.getMobileNumber())));
				return (LoginRecord) criteria.uniqueResult();
			}
		});
	
	}*/
//}

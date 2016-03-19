package com.fpl.persistence.login;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;

import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.login.ILoginInputParam;
import com.fpl.login.ILoginRecoveryInput;
import com.fpl.login.IUserRecoverInput;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.UserType;
import com.fpl.login.entity.LoginCredential;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.util.StringUtil;

public class LoginCredentialDAO extends HibernateDAOSupport<LoginCredential> implements ILoginCredentialDAO {

	private static final String ENTITY_CLASS_NAME = "com.fpl.login.entity.LoginCredential";
	
	@Override
	protected Class<LoginCredential> getEntityClass() {
		return (Class<LoginCredential>) ClassInstantiator.loadClass(ENTITY_CLASS_NAME);
	}
	
	@Override
	public UserLoginInfo getLoginInfo(final ILoginInputParam loginInputParam) {
		return getHibernateTemplate().execute(new HibernateCallback<LoginCredential, UserLoginInfo>("getLoginInfo") {
			@Override
			public UserLoginInfo doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
                
				final ProjectionList projections = Projections.projectionList(); 
                projections.add(Projections.property("id"));
                projections.add(Projections.property("mailId"));
                projections.add(Projections.property("alternateMailId"));
                projections.add(Projections.property("mobileNumber"));
                projections.add(Projections.property("country"));
                projections.add(Projections.property("password"));
                projections.add(Projections.property("secretAnswer"));
                
				criteria.createAlias("user", "ut");
                projections.add(Projections.property("ut.userType"));
                projections.add(Projections.property("ut.id"));
                criteria.setProjection(projections);
                
                criteria.add(Restrictions.eq("mailId",loginInputParam.getPrimaryEmailId()));
				criteria.add(Restrictions.eq("country",loginInputParam.getCountry()));
				
				criteria.setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(final Object[] objArray, final String[] arg1) {
                    	int index = 0;
                        final UserLoginInfo loginInfo = new UserLoginInfo();
                        loginInfo.setLoginCredentialid((Long) objArray[index++]);
                        loginInfo.setEmail(StringUtil.getNotNullValue(objArray[index++]));
                        loginInfo.setAlternateMailId(StringUtil.getNotNullValue(objArray[index++]));
                        loginInfo.setMobileNumber(StringUtil.getNotNullValue(objArray[index++]));
                        loginInfo.setCountry(StringUtil.getNotNullValue(objArray[index++]));
                        loginInfo.setPassword(StringUtil.getNotNullValue(objArray[index++]));
                        loginInfo.setSecreatAnswer(StringUtil.getNotNullValue(objArray[index++]));
                        loginInfo.setUserType(UserType.valueOf(StringUtil.getNotNullValue(objArray[index++]).toUpperCase()));
                        loginInfo.setUserTypeId((Long) objArray[index++]);
                        return loginInfo;
                    }
                    
                    @Override
                    public List transformList(final List list) {
                        return list;
                    }
                });
				return (UserLoginInfo) criteria.uniqueResult();
			}
		});
	}

	@Override
	public LoginCredential getLoginCredential(final IUserRecoverInput loginRecovery) {
		return getHibernateTemplate().execute(new HibernateCallback<LoginCredential, LoginCredential>("getLoginCredential for recovery") {
			@Override
			public LoginCredential doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				if(!StringUtil.isEmpty(loginRecovery.getAltEmail())) {
					final String email = Encryption.encrypt(loginRecovery.getAltEmail(), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());

					criteria.add(Restrictions.eq("mailId",email));
				}
				criteria.add(Restrictions.eq("secretAnswer",loginRecovery.getSecretQuestion()));
				
				return (LoginCredential) criteria.uniqueResult();
			}
		});
	}
	@Override
	public LoginCredential getLoginCredentialbyAltemail(final IUserRecoverInput loginRecovery) {
		final String email = Encryption.encrypt(loginRecovery.getAltEmail(), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());

		return getHibernateTemplate().execute(new HibernateCallback<LoginCredential, LoginCredential>("getLoginCredential for recovery") {
			@Override
			public LoginCredential doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				if(!StringUtil.isEmpty(loginRecovery.getAltEmail())) {

					criteria.add(Restrictions.eq("alternateMailId",email));
				}
				criteria.add(Restrictions.eq("secretAnswer",loginRecovery.getSecretQuestion()));
				
				return (LoginCredential) criteria.uniqueResult();
			}
		});
	}

	@Override
	public LoginCredential getLoginCredentialByMail(final String userName) {
		final String email = Encryption.encrypt(userName, CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		return getHibernateTemplate().execute(new HibernateCallback<LoginCredential, LoginCredential>("isUserNameExist") {
			@Override
			public LoginCredential doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				criteria.add(Restrictions.eq("mailId",email));
				final LoginCredential loginCredential = (LoginCredential) criteria.uniqueResult();
				return loginCredential;
			}
		});
	}

	@Override
	public Serializable save(final LoginCredential entity, final boolean flushMode) {
		return super.save(entity, flushMode);
	}

	@Override
	public void update(final LoginCredential entity, final boolean flushMode) {
		super.update(entity, flushMode);
	}
	@Override
	public void saveOrUpdate(final LoginCredential entity, final boolean flushMode) {
		super.update(entity, flushMode);
	}
	
	@Override
	public void delete(final LoginCredential entity, final boolean flushMode) {
		super.delete(entity, flushMode);
	}
}

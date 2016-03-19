package com.fpl.persistence.recovery.personal;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fpl.core.profile.IpersonalDAO;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.profile.personal.PersonalData;

public class PersonalDAO extends HibernateDAOSupport<PersonalData> implements IpersonalDAO{
	private static final String ENTITY_CLASS_NAME = "com.fpl.profile.personal.PersonalData";
	
//	private JdbcTemplate jdbcTemplate;
	@Override
	protected Class<? super PersonalData> getEntityClass() {
		// TODO Auto-generated method stub
		return (Class<PersonalData>) ClassInstantiator.loadClass(ENTITY_CLASS_NAME);
	}
	@Override
	public String getinfo(final String fname, final String lname,final String email,final String secans) {
		// TODO Auto-generated method stub
		/*final StringBuilder query = new StringBuilder();
		query.append(" select lc_id, ut_descrption, lc_mail_id, lc_alternate_maill_id, ls_online ");
		query.append("   from fpl_ma_login_credential lc, fpl_ma_login_support ls, fpl_ma_user_type ut ");
		query.append("  where lc.lc_id = ls.ls_lc_id ");
		query.append("   and lc.lc_ut_id = ut.ut_id ");
		query.append("   and ut_descrption != ? ");
		
		return jdbcTemplate.query(query.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(final PreparedStatement statement) throws SQLException {
				int index = 1;
				statement.setString(index++, UserType.FP_ADMIN.getUser());
				if (searchParam.isOnline() != null) {
					final int online = searchParam.isOnline().booleanValue() ? 0 : 1;
					statement.setInt(index++, online);
				}
				if (searchParam.getUserTypeCollection() != null && !searchParam.getUserTypeCollection().isEmpty()) {
					for (final UserType userType : searchParam.getUserTypeCollection()) {
						statement.setString(index++, userType.getUser());
					}
				}
			}
		}*/
		/*Query query = session.createSQLQuery(
				"select s.stock_code from stock s where s.stock_code = :stockCode")
				.setParameter("stockCode", "7277");
				List result = query.list();*/
		return getHibernateTemplate().execute(new HibernateCallback<String, String>("isUserNameExist") {
			
			@Override
			public String doInHibernate(final Session session) {
				String hql = "select LC_ALTERNATE_MAILL_ID from fpl_ma_login_credential as lc join fpl_ma_personal_data as pd on pd.PD_EMAIL = lc.LC_MAIL_ID && pd.PD_NAME = ? && pd.PD_LNAME_RNUMBER=? where lc.LC_ALTERNATE_MAILL_ID=? && lc.LC_QUESTION_ANSWER=?";
				Query query = session.createSQLQuery(hql)
								.setParameter(0,fname)
				                .setString(1,lname)
				                .setString(2,email)
				                .setString(3,secans);
						String result = (String) query.uniqueResult();
				
				return result;
			}
		});
		
	}
	@Override
	public PersonalData getuserdata(final String email) {
		// TODO Auto-generated method stub
		final String email1 = Encryption.encrypt(email, CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		return getHibernateTemplate().execute(new HibernateCallback<PersonalData, PersonalData>("geting personaldata by email") {
			@Override
			public PersonalData doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				criteria.add(Restrictions.eq("email",email1));
				final PersonalData PersonalData = (PersonalData) criteria.uniqueResult();
				return PersonalData;
			}
		});
	}
	@Override
	public String getpasswordinfo(final String fname, final String lname,final String email,final String secans) {
		// TODO Auto-generated method stub
		
		return getHibernateTemplate().execute(new HibernateCallback<String, String>("isUserNameExist") {
			
			@Override
			public String doInHibernate(final Session session) {

				String hql = "select LC_MAIL_ID from fpl_ma_login_credential as lc join fpl_ma_personal_data as pd on pd.PD_EMAIL = lc.LC_MAIL_ID && pd.PD_NAME = ? && pd.PD_LNAME_RNUMBER=? where lc.LC_MAIL_ID=? && lc.LC_QUESTION_ANSWER=?";
				Query query = session.createSQLQuery(hql)
						.setParameter(0,fname)
		                .setString(1,lname)
		                .setString(2,email)
		                .setString(3,secans);
						String result = (String) query.uniqueResult();
				
				return result;
			}
		});
		
	}

	
	@Override
	public Serializable save(final PersonalData entity, final boolean flushMode) {
		return super.save(entity, flushMode);
	}

	@Override
	public void update(final PersonalData entity, final boolean flushMode) {
		super.update(entity, flushMode);
	}

	
	@Override
	public void delete(final PersonalData entity, final boolean flushMode) {
		super.delete(entity, flushMode);
	}
	
	
}

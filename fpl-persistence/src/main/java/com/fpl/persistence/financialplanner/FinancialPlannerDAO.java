package com.fpl.persistence.financialplanner;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.profile.admin.SearchProfilePV;
import com.fpl.util.StringUtil;

public class FinancialPlannerDAO extends HibernateDAOSupport<FinancialPlanner>  implements IFinancialPlannerDAO {

	private static final String ENTITY_CLASS_NAME = "com.fpl.financialplanner.FinancialPlanner";
	
	@Override
	protected Class<FinancialPlanner> getEntityClass() {
		return (Class<FinancialPlanner>) ClassInstantiator.loadClass(ENTITY_CLASS_NAME);
	}

	@Override
	public FinancialPlanner getFinancialPlannerByLoginId(final Long loginId) {
		return getHibernateTemplate().execute(new HibernateCallback<FinancialPlanner, FinancialPlanner>("getFinancialPlannerByLoginId") {
			@Override
			public FinancialPlanner doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
                criteria.add(Restrictions.eq("loginCredentialId", loginId));
				return (FinancialPlanner) criteria.uniqueResult();
			}
		});
	}
	
	public Long getMaxId()
	{
		return getHibernateTemplate().execute(new HibernateCallback<Long, Long>("getMaxId") {
			@Override
			public Long doInHibernate(final Session session) {
				
				final Criteria criteria = session.createCriteria(getEntityClass());
				 criteria.setProjection(Projections.max("id"));
					Long maxId=(Long)criteria.uniqueResult();
					if(maxId==null)
					{
						maxId=0L;
					}
					return maxId;
			}
		});
		
	}

	@Override
	public Collection<FinancialPlanner> getFinancialPlannerList(final SearchProfilePV searchProfile) {
		return getHibernateTemplate().execute(new HibernateCallback<FinancialPlanner, Collection<FinancialPlanner>>("getFinancialPlannerList") {
			@Override
			public Collection<FinancialPlanner> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				criteria.createAlias("personalData", "pd");
				if(StringUtil.isNotEmpty(searchProfile.getName())) {
					criteria.add(Restrictions.like("pd.name", "%"+searchProfile.getName()+"%"));
				}
				if(StringUtil.isNotEmpty(searchProfile.getEmail())) {
					criteria.add(Restrictions.eq("pd.email", searchProfile.getEmail()));
				}
				if(StringUtil.isNotEmpty(searchProfile.getMobileNum())) {
					criteria.add(Restrictions.eq("pd.mobileNumber", searchProfile.getMobileNum()));
				}
				criteria.addOrder(Order.asc("pd.name"));
				return criteria.list();
			}
		});
	}
//<<<<<<< HEAD
	public Collection<FinancialPlanner> getFinancialPlannerList() {
		return getHibernateTemplate().execute(new HibernateCallback<FinancialPlanner, Collection<FinancialPlanner>>("getFinancialPlannerList") {
			@Override
			public Collection<FinancialPlanner> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				criteria.createAlias("personalData", "pd");
				criteria.addOrder(Order.asc("pd.name"));
				return criteria.list();
			}
		});
	}
//=======

//>>>>>>> refs/remotes/origin/master
}

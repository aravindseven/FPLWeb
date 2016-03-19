package com.fpl.persistence.subscription;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.subscription.FplSubscription;
import com.fpl.subscription.SubscriptionStatusType;

public class FplSubscriptionDAO extends HibernateDAOSupport<FplSubscription> implements IFplSubscriptionDAO {
    
    @Override
    protected Class<FplSubscription> getEntityClass() {
    	return FplSubscription.class;
    }

    @Override
	public Collection<FplSubscription> getAllEntities() {
		return getHibernateTemplate().execute(new HibernateCallback<FplSubscription, Collection<FplSubscription>>("getAllEntities") {
			@Override
			public Collection<FplSubscription> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				return criteria.list();
			}
		});
	}
    
    
    public FplSubscription getActiveOrInProgressFPLEntities(final long fplId)
    {
    	return getHibernateTemplate().execute(new HibernateCallback<FplSubscription, FplSubscription>("getFPLEntities") {
			@Override
			public FplSubscription doInHibernate(final Session session) {
				
				final Criteria criteria = session.createCriteria(getEntityClass());
				 criteria.add(Restrictions.eq("fplId",fplId));
				 criteria.add(Restrictions.in("activityFlag", new String[]{SubscriptionStatusType.AC.name(),SubscriptionStatusType.IP.name(),SubscriptionStatusType.CI.name()}));
						 
//						 .or(Restrictions.eq("activityFlag",SubscriptionStatusType.AC.name())
//						 ,Restrictions.eq("activityFlag",SubscriptionStatusType.IP)));
				 
				return (FplSubscription)criteria.uniqueResult();
			}
		});
    }
    
    
   
	public Collection<FplSubscription> getFPLEntities(final long fplId)
    {
    	return getHibernateTemplate().execute(new HibernateCallback<FplSubscription, Collection<FplSubscription>>("getFPLEntities") {
			@Override
			public Collection<FplSubscription> doInHibernate(final Session session) {
				
				final Criteria criteria = session.createCriteria(getEntityClass());
				 criteria.add(Restrictions.eq("fplId",fplId));
				return criteria.list();
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
				return (Long)criteria.uniqueResult();
			}
		});
		
	}
}

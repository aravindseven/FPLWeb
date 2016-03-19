package com.fpl.persistence.customer;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.paging.support.IPagingTemplate;
import com.fpl.persistence.paging.CriteriaPagingTemplate;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.profile.admin.SearchProfilePV;
import com.fpl.profile.customer.Customer;
import com.fpl.util.StringUtil;

public class CustomerDAO extends HibernateDAOSupport<Customer> implements ICustomerDAO {

    private static final String ENTITY_CLASS_PATH = "com.fpl.profile.customer.Customer";
    
    @Override
    protected Class<Customer> getEntityClass() {
        return (Class<Customer>) ClassInstantiator.loadClass(ENTITY_CLASS_PATH);
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
	public Customer getCustomerLoginId(final Long loginId) {
		return getHibernateTemplate().execute(new HibernateCallback<Customer, Customer>("getCustomerLoginId") {
			@Override
			public Customer doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
                criteria.add(Restrictions.eq("loginCredentialId",loginId));
				return (Customer) criteria.uniqueResult();
			}
		});
	}

	@Override
	public Collection<Customer> getCustomerList(final SearchProfilePV searchProfile) {
		return getHibernateTemplate().execute(new HibernateCallback<Customer, Collection<Customer>>("getCustomerList") {
			@Override
			public Collection<Customer> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				criteria.createAlias("personalData", "pd");
				if(searchProfile != null && StringUtil.isNotEmpty(searchProfile.getName())) {
					criteria.add(Restrictions.like("pd.name", "%"+searchProfile.getName()+"%"));
				}
				if(searchProfile != null && StringUtil.isNotEmpty(searchProfile.getEmail())) {
					criteria.add(Restrictions.eq("pd.email", searchProfile.getEmail()));
				}
				if(searchProfile != null && StringUtil.isNotEmpty(searchProfile.getMobileNum())) {
					criteria.add(Restrictions.eq("pd.mobileNumber", searchProfile.getMobileNum()));
				}
				criteria.addOrder(Order.asc("pd.name"));
				return criteria.list();
			}
		});
	}
	
	
	public Collection<Customer> getCustomerList() {
		return getHibernateTemplate().execute(new HibernateCallback<Customer, Collection<Customer>>("getCustomerList") {
			@Override
			public Collection<Customer> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				criteria.createAlias("personalData", "pd");
				criteria.addOrder(Order.asc("pd.name"));
				return criteria.list();
			}
		});
	}

	@Override
	public IPagingTemplate getPagingTemplate(final SearchProfilePV searchProfile) {
		final CriteriaPagingTemplate pagingTemplate = new CriteriaPagingTemplate(getEntityClass());
		if(StringUtil.isNotEmpty(searchProfile.getName())) {
			pagingTemplate.addCriterion(Restrictions.like("pd.name", "%"+searchProfile.getName()+"%"));
		}
		if(StringUtil.isNotEmpty(searchProfile.getEmail())) {
			pagingTemplate.addCriterion(Restrictions.eq("pd.email", searchProfile.getEmail()));
		}
		if(StringUtil.isNotEmpty(searchProfile.getMobileNum())) {
			pagingTemplate.addCriterion(Restrictions.eq("pd.mobileNumber", searchProfile.getMobileNum()));
		}
		pagingTemplate.addOrder(Order.asc("pd.name"));
		return pagingTemplate;
	}
}



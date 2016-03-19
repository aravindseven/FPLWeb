package com.fpl.persistence.company;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.fpl.company.Company;
import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.profile.admin.SearchProfilePV;
import com.fpl.util.StringUtil;

public class CompanyDAO extends HibernateDAOSupport<Company> implements ICompanyDAO {

    private static final String ENTITY_CLASS_PATH = "com.fpl.company.Company";
    
    @Override
    protected Class<Company> getEntityClass() {
        return (Class<Company>) ClassInstantiator.loadClass(ENTITY_CLASS_PATH);
    }

	@Override
	public Company getCompanyByName(final String name) {
		return getHibernateTemplate().execute(new HibernateCallback<Company, Company>("getCompanyByName") {
			@Override
			public Company doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
                criteria.add(Restrictions.eq("name", name));
				return (Company) criteria.uniqueResult();
			}
		});
	}
	
	@Override
	public Company getCompanyByLocation(final String location) {
		return getHibernateTemplate().execute(new HibernateCallback<Company, Company>("getCompanyByLocation") {
			@Override
			public Company doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
                criteria.add(Restrictions.eq("incLocation", location));
				return (Company) criteria.uniqueResult();
			}
		});
	}
	
	@Override
	public Company getCompanyByNameLocation(final String name, final String location) {
		return getHibernateTemplate().execute(new HibernateCallback<Company, Company>("getCompanyByName") {
			@Override
			public Company doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
                criteria.add(Restrictions.eq("name", name));
                criteria.add(Restrictions.eq("incLocation", location));
				return (Company) criteria.uniqueResult();
			}
		});
	}
	
	@Override
	public Collection<Company> getCompanyList(final SearchProfilePV searchProfile) {
		return getHibernateTemplate().execute(new HibernateCallback<Company, Collection<Company>>("getCompanyList") {
			@Override
			public Collection<Company> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				if(StringUtil.isNotEmpty(searchProfile.getName())) {
					criteria.add(Restrictions.like("name", "%"+searchProfile.getName()+"%"));
				}
				if(StringUtil.isNotEmpty(searchProfile.getEmail())) {
					criteria.add(Restrictions.eq("email", searchProfile.getEmail()));
				}
				if(StringUtil.isNotEmpty(searchProfile.getMobileNum())) {
					criteria.add(Restrictions.or(Restrictions.eq("persoanNumber1", searchProfile.getMobileNum()), Restrictions.eq("persoanNumber2", searchProfile.getMobileNum())));
				}
				criteria.addOrder(Order.asc("name"));
				return criteria.list();
			}
		});
	}

	@Override
	public Collection<String> getCompanyList() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<String, Collection<String>>("getCompanyList") {
			@Override
			public Collection<String> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				criteria.setProjection(Projections.property("name"));
				criteria.addOrder(Order.asc("name"));
				return criteria.list();
			}
		});
		
	}
}

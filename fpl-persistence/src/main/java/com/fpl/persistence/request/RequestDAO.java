package com.fpl.persistence.request;

import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.fpl.core.request.SearchRequestParam;
import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.request.Request;
import com.fpl.util.DateUtil;
import com.fpl.util.StringUtil;

public class RequestDAO extends HibernateDAOSupport<Request> implements IRequestDAO {

	private static final String ENTITY_CLASS_NAME = "com.fpl.request.Request";
	
	@Override
	protected Class<Request> getEntityClass() {
		return (Class<Request>) ClassInstantiator.loadClass(ENTITY_CLASS_NAME);
	}

	
	
	@Override
	public Collection<Request> getActiveRequestByCustomerId(final Long customerId) {
		return getHibernateTemplate().execute(new HibernateCallback<Request, Collection<Request>>("getActiveRequestByCustomerId") {
			@Override
			public Collection<Request> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass(),"request");
				 criteria.setFetchMode("request.status", FetchMode.JOIN);
			     criteria.createAlias("request.status", "status");
				criteria.add(Restrictions.eq("customerId", customerId));
				criteria.add(Restrictions.ne("status.disDescription", "ST_03"));
				criteria.addOrder(Order.desc("updatedDate"));
				return criteria.list();
			}
		});
	}
	
	@Override
	public Collection<Request> getRequestByCustomerId(final Long customerId) {
		return getHibernateTemplate().execute(new HibernateCallback<Request, Collection<Request>>("getRequestByCustomerId") {
			@Override
			public Collection<Request> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				criteria.add(Restrictions.eq("customerId", customerId));
				criteria.addOrder(Order.asc("createdDate"));
				return criteria.list();
			}
		});
	}

	@Override
	public Collection<Request> getRequestList(final SearchRequestParam requestParam) {
		return getHibernateTemplate().execute(new HibernateCallback<Request, Collection<Request>>("getRequestByCustomerId") {
			@Override
			public Collection<Request> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				criteria.add(Restrictions.eq("customerId", Long.valueOf(requestParam.getId())));
				if(StringUtil.isNotEmpty(requestParam.getType()) && Long.valueOf(requestParam.getType())>0) {
					criteria.add(Restrictions.eq("domainId", Long.valueOf(requestParam.getType())));
				}
				if(StringUtil.isNotEmpty(requestParam.getStatus()) && Long.valueOf(requestParam.getStatus())>0) {
					criteria.add(Restrictions.eq("currentStatusId", Long.valueOf(requestParam.getStatus())));
				}
				if(StringUtil.isNotEmpty(requestParam.getFromDate())) {
					final Date fromDate = DateUtil.getFormattedDate(requestParam.getFromDate(), "dd/MM/yyyy");
					 Date toDate;
					if(StringUtil.isNotEmpty(requestParam.getToDate())) {
						toDate = DateUtil.getFormattedDate(requestParam.getToDate(), "dd/MM/yyyy");
					}else
					{
						toDate = new Date();
					}
					criteria.add(Restrictions.between("createdDate", fromDate, toDate));
				}
				if(StringUtil.isNotEmpty(requestParam.getRequestId()) && Long.valueOf(requestParam.getRequestId())>0) {
					criteria.add(Restrictions.eq("id", Long.valueOf(requestParam.getRequestId())));
				}
				criteria.addOrder(Order.asc("createdDate"));
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
					Long maxId=(Long)criteria.uniqueResult();
					if(maxId==null)
					{
						maxId=0L;
					}
					return maxId;
			}
		});
		
	}

}

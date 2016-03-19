package com.fpl.persistence.paging.support;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.AbstractHibernateConfig;

public class CriteriaPagingDAOSupport extends AbstractHibernateConfig {

	@SuppressWarnings("unchecked")
	public <EntityType,ReturnType> Collection<ReturnType> getPage(final Class<EntityType> entityClazz, final PagingCriteria pagingCriteria, final int pageNo, final int pageSize)
	{
		Collection<ReturnType> pageList = null;
		pageList = getHibernateTemplate().execute(new HibernateCallback<EntityType,Collection<ReturnType>>("Pagination getPage Using Criteria") {
			@Override
			public Collection<ReturnType> doInHibernate(final Session session) {
				final Criteria searchCriteria = session.createCriteria(entityClazz);
				if(pagingCriteria.getProjectionList().getLength() > 0) {
					searchCriteria.setProjection(pagingCriteria.getProjectionList());
				}
				for (final Criterion criterian : pagingCriteria.getCriterianList()) {
					searchCriteria.add(criterian);
				}
				for (final Order order : pagingCriteria.getOrderList()) {
					searchCriteria.addOrder(order);
				}
				if(pagingCriteria.getResultTransformer() != null) {
					searchCriteria.setResultTransformer(pagingCriteria.getResultTransformer());
				}
				searchCriteria.setFirstResult((pageSize * (pageNo - 1)));
				searchCriteria.setMaxResults(pageSize);
				final Collection<ReturnType> resultList = searchCriteria.list();
				for (final ReturnType result : resultList) {
					session.evict(result);
				}
				return resultList;
			}
		});
		return pageList;
	}

	public <EntityType> int getTotalRecords(final Class<EntityType> entityClazz, final PagingCriteria pagingCriteria) {
		Integer totalRecords;
		totalRecords = getHibernateTemplate().execute(new HibernateCallback<EntityType, Integer>("Pagination getTotalRecords Using Criteria") {

			@Override
			public Integer doInHibernate(final Session session) {
				final Criteria totalCountCriteria = session.createCriteria(entityClazz);
				totalCountCriteria.setProjection(Projections.rowCount());
				for (final Criterion criterian : pagingCriteria.getCriterianList()) {
					totalCountCriteria.add(criterian);
				}
				final Object object = totalCountCriteria.uniqueResult();
				Integer totalRecord = Integer.valueOf(0);
				if(object != null) {
					totalRecord = (Integer)object;
				}
				return totalRecord;
			}
		});
		return totalRecords;
	}
}



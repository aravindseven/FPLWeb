package com.fpl.persistence.paging;

import java.util.Collection;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.transform.ResultTransformer;

import com.fpl.factory.BeanUtil;
import com.fpl.paging.config.FPLPagingBean;
import com.fpl.paging.support.IPagingTemplate;
import com.fpl.persistence.paging.support.CriteriaPagingDAOSupport;
import com.fpl.persistence.paging.support.PagingCriteria;

@SuppressWarnings("serial")
public class CriteriaPagingTemplate implements IPagingTemplate {
    
    private final PagingCriteria pagingCriteria;
    private final Class<?> entityClass;
    
    public CriteriaPagingTemplate(final Class<?> entityClass) {
        this.entityClass = entityClass;
        pagingCriteria = new PagingCriteria(); 
    }
    
    public void addCriterion(final Criterion criterion) {
        pagingCriteria.addCriterion(criterion);
    }
    
    public void addProjection(final Projection projection) {
        pagingCriteria.addProjection(projection);
    }

    public void addOrder(final Order order) {
        pagingCriteria.addOrder(order);
    }
    
    /**
     * @param resultTransformer the resultTransformer to set
     */
    public void setResultTransformer(final ResultTransformer resultTransformer) {
        pagingCriteria.setResultTransformer(resultTransformer);
    }
    
    @Override
    public <PageResult> Collection<PageResult> getPageResult(final int pageNo, final int pageSize) {
    	final CriteriaPagingDAOSupport pagingDAOSupport =  BeanUtil.getInstance(FPLPagingBean.CRITERIA_PAGING).getBean(CriteriaPagingDAOSupport.class);
    	return pagingDAOSupport.getPage(entityClass, pagingCriteria, pageNo, pageSize);
    }

    @Override
	public int getTotalRecord() {
    	final CriteriaPagingDAOSupport pagingDAOSupport =  BeanUtil.getInstance(FPLPagingBean.CRITERIA_PAGING).getBean(CriteriaPagingDAOSupport.class);
        return pagingDAOSupport.getTotalRecords(entityClass, pagingCriteria);
    }
}



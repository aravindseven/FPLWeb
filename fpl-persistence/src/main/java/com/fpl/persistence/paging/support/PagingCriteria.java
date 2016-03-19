package com.fpl.persistence.paging.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.ResultTransformer;

@SuppressWarnings("serial")
public class PagingCriteria implements Serializable {
    
    private final List<Criterion> criterianList;
    private final List<Order> orderList;
    private final ProjectionList projectionList;
    private ResultTransformer resultTransformer;
    
    public PagingCriteria() {
        criterianList = new ArrayList<Criterion>();
        orderList = new ArrayList<Order>();
        projectionList = Projections.projectionList();
    }
    
    public void addCriterion(final Criterion criterion) {
        criterianList.add(criterion);
    }
    
    public void addProjection(final Projection projection) {
        this.projectionList.add(projection);
    }

    public void addOrder(final Order order) {
        this.orderList.add(order);
    }
    
    /**
     * @param resultTransformer the resultTransformer to set
     */
    public void setResultTransformer(final ResultTransformer resultTransformer) {
        this.resultTransformer = resultTransformer;
    }
    
    /**
     * @return the resultTransformer
     */
    protected ResultTransformer getResultTransformer() {
        return resultTransformer;
    }
    
    /**
     * @return the criterianList
     */
    protected List<Criterion> getCriterianList() {
        return criterianList;
    }
    
    /**
     * @return the orderList
     */
    protected List<Order> getOrderList() {
        return orderList;
    }
    
    /**
     * @return the projectionList
     */
    protected ProjectionList getProjectionList() {
        return projectionList;
    }
}



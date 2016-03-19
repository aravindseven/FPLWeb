package com.fpl.persistence.support;

import com.fpl.hibernate.HibernateTemplate;
import com.fpl.hibernate.HibernateTemplateFactory;

public abstract class AbstractHibernateConfig {

    private HibernateTemplateFactory templateFactory;

    /**
     * @param templateFactory the templateFactory to set
     */
    public void setTemplateFactory(final HibernateTemplateFactory templateFactory) {
        this.templateFactory = templateFactory;
    }

    /**
     * This class return a HibernateTemplate ready to be executed for the hibernate session factory defined
     * in this <tt> HibernateDAOSupport </tt> object.
     *   
     * @return HibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return templateFactory.getHibernateTemplate();
    }
}



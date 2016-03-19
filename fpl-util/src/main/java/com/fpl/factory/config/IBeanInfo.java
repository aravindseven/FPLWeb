package com.fpl.factory.config;

/**
 * <p> Central enum to provide module level dependencies spring xml for an application as well as bean id. 
 * Return an array of resource locations, referring to the XML bean definition files that this context 
 * should be built with and bean id. </p> 
 *  
 * @author Murali  
 */
public interface IBeanInfo {

    /**
     * Return <tt>BeanConfigLocationType</tt> enuman, which contain array of resource locations, 
     * referring to the XML bean definition files that this context should be built with. </p> 
     * 
     * @return - config location enum.
     */
    BeanConfigLocationType getBeanConfig();
    
    /**
     * Return the name of the bean to retrieve using bean util.
     * 
     * @return - the name of the bean to retrieve
     */
    String getBeanId();
}



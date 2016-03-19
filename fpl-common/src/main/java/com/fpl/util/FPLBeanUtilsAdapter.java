package com.fpl.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;

/**
 * Provides utility methods for populating JavaBeans properties via reflection.
 * 
 * @author Murali
 */
public final class FPLBeanUtilsAdapter {

    private FPLBeanUtilsAdapter() {}
    
    /**
     * <p>Copy property values from the origin bean to the destination bean for all cases where the property names 
     * are the same.  For each property, a conversion is attempted as necessary.  All combinations of standard 
     * JavaBeans and DynaBeans as origin and destination are supported.  Properties that exist in the origin bean, 
     * but do not exist in the destination bean (or are read-only in the destination bean) are
     * silently ignored.</p>
     *
     * @param inputBean Destination bean whose properties are modified
     * @param outputBean Origin bean whose properties are retrieved
     *
     * @exception IllegalAccessException if the caller does not have access to the property accessor method
     * 
     * @exception IllegalArgumentException if the <code>dest</code> or <code>orig</code> argument is null or 
     *            if the <code>dest</code> property type is different from the source type and the relevant
     *            converter has not been registered.
     *            
     * @exception InvocationTargetException if the property accessor method throws an exception
     */
    public static <InputBean, OutputBean> void copyProperties(final InputBean inputBean, final OutputBean outputBean) throws IllegalAccessException, InvocationTargetException 
    {
        final ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
        convertUtilsBean.register(false,true,Integer.valueOf(0));
        final BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean);
        beanUtilsBean.copyProperties(outputBean, inputBean);
    }
}



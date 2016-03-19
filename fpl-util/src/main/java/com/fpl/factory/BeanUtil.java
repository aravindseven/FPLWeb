package com.fpl.factory;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fpl.factory.config.BeanConfigLocationType;
import com.fpl.factory.config.IBeanInfo;

/**
 * <p> This BeanUtil is used for Dependency Injection (DI). All the dependencies to be configured in
 * <tt> BeanConfigLocationType </tt>, contains the array of XML bean definition files. </p> 
 * 
 * <p> Central class to provide dependency object using spring framework for an application. Intern this class is cached 
 *  the <tt> BeanUtil </tt> object based on the <tt> BeanConfigLocationType </tt>. For creating application object
 *  this class is used spring {@link ClassPathXmlApplicationContext}. ClassPathXmlApplicationContext, loading the definitions 
 *  from the given XML files and automatically refreshing the context. </p>
 *  
 *  <p> Standalone XML application context, taking the context definition files from the class path, interpreting plain paths as class path 
 *  resource names that include the package path (e.g. "com/persistence/config/spring_hibernate_config.xml"). Useful for test 
 *  harnesses as well as for application contexts embedded within JARs.</p>
 *  
 *  <p> Example call: <br>
 *  <code>
 *        IMessageManager messageManager = BeanUtil.getInstance(ExternalBeanInfoType.MESSAGE_MANAGER).getBean(IMessageManager.class);
 *  </code> <br> </p> 
 *  
 *  <p> <tt> BeanUtil </tt> is not allowed to create instance, instead of they provide one static method {@link #getInstance}
 *  using this method by passing <tt> BeanConfigLocationType </tt> client get the <tt> BeanUtil </tt> object.
 *  This strategy is followed because intern this class cached based on the <tt> BeanConfigLocationType </tt>, so that
 *  it will not allow to create object externally.
 *  </p>
 *  
 * @author Murali 
 */
public final class BeanUtil {
    
    private ApplicationContext applicationContext = null;
    private static final Map<BeanConfigLocationType, BeanUtil> beanCache = new Hashtable<BeanConfigLocationType, BeanUtil>();
    private final BeanConfigLocationType configLocationType;
    private String beanId;
    
    /**
     * Constructs an <code>BeanUtil</code> with the
     * specified {@link BeanConfigLocationType}.
     *
     * @param   configLocationType
     */
    private BeanUtil(final BeanConfigLocationType configLocationType) {
        this.configLocationType = configLocationType;
    }
    
    /**
     * This static method used to get the {@link BeanUtil} class object. <tt> BeanUtil </tt> is not 
     * allowed to create instance externally, instead of they provide this static method. Using this method by passing 
     * <tt> IBeanInfo </tt> client get the <tt> BeanUtil </tt> object.
     * 
     * @param 
     * 
     * @return BeanUtil
     */
    public static BeanUtil getInstance(final IBeanInfo beanInfo) {
        BeanUtil beanUtil = beanCache.get(beanInfo.getBeanConfig());
        if (beanUtil == null) {
            beanUtil = new BeanUtil(beanInfo.getBeanConfig());
            beanCache.put(beanInfo.getBeanConfig(), beanUtil);
        }
        beanUtil.beanId = beanInfo.getBeanId();
        return beanUtil;
    }
    
    /**
     * Used to get the Spring ApplicationContext Object. ApplicationContext is a  central interface to provide configuration 
     * for an application. 
     *  
     * @return ApplicationContext
     */
    private ApplicationContext getApplicationContext() {
    	if(configLocationType.equals(BeanConfigLocationType.DEFAULT_CONTEXT)) {
    		return DefaultApplicationContextProvider.getInstance().getApplicationContext();
    	} else if (applicationContext == null) {
            applicationContext = new ClassPathXmlApplicationContext(configLocationType.getConfigLocation());
        }
        return applicationContext;
    }
    
    /**
     * <p> Return an instance, which may be shared or independent, of the specified bean. Behaves the same as {@link #getBean(String)}, 
     * but provides a measure of type safety if the bean is not of the required type. This means that 
     * ClassCastException can't be thrown on casting the result correctly, as can happen with {@link #getBean(String)}. </p>
     * 
     * <p> Translates aliases back to the corresponding canonical bean name. Will ask the parent spring bean factory 
     * if the bean cannot be found in this factory instance. </p>
     * 
     * @param clazz - type the bean must match. Can be an interface or superclass of the actual class, or null for any match. 
     *                For example, if the value is Object.class, this method will succeed whatever the class of the returned instance.
     *                 
     * @return - an instance of the bean 
     * 
     * @throws RuntimeException - if there is no bean definition with the specified name or if the bean could 
     *                            not be obtained
     */
    public <BeanObject> BeanObject getBean(final Class<BeanObject> clazz) {
        return getApplicationContext().getBean(beanId,clazz);
    }
}



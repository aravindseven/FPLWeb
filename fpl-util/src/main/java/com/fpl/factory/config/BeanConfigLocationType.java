package com.fpl.factory.config;

import static com.fpl.factory.config.BeanConfigLocationType.SpringXmlLocation.LOGIN_XML;
import static com.fpl.factory.config.BeanConfigLocationType.SpringXmlLocation.PROFILE_XML;
import static com.fpl.factory.config.BeanConfigLocationType.SpringXmlLocation.SPRING_HIBERNATE_CONFIG;

/**
 * <p> Central enum to provide module level dependencies spring xml for an application. Return an array of 
 * resource locations, referring to the XML bean definition files that this context should be built with. </p> 
 *  
 * @author Murali
 */
public enum BeanConfigLocationType {
	
	DEFAULT_CONTEXT(),
	SPRING_HIBERNATE(SPRING_HIBERNATE_CONFIG.xmlPath),
	LOGIN(SPRING_HIBERNATE_CONFIG.xmlPath,LOGIN_XML.xmlPath),
	PROFILE(SPRING_HIBERNATE_CONFIG.xmlPath,PROFILE_XML.xmlPath);
	
    private String[] configLocation;

    /**
     * Constructs an <code>BeanConfigLocationType</code> with the array of resource locations
     * referring to the XML bean definition files.
     *
     * @param   list of module level dependencies spring xml paths.
     */
    private BeanConfigLocationType(final String... configLocation) {
        this.configLocation = configLocation;
    }

    /**
     * Return an array of resource locations, referring to the XML bean definition files that this context should be 
     * built with. 
     *  
     * @return list of module level dependencies spring xml paths.
     */
    public String[] getConfigLocation() {
        return configLocation;
    }

    public enum SpringXmlLocation {
    	
    	SPRING_HIBERNATE_CONFIG("com/fpl/hibernate/config/spring_hibernate_config.xml"),
    	LOGIN_XML("com/fpl/login/config/LoginBeans.xml"),
    	PROFILE_XML("com/fpl/profile/config/ProfileBean.xml");
    	
        private String xmlPath;
        private SpringXmlLocation(final String xmlPath) {
            this.xmlPath = xmlPath;
        }
        
    }
}



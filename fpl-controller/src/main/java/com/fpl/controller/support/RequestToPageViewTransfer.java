package com.fpl.controller.support;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fpl.util.StringUtil;

public class RequestToPageViewTransfer {

	public static <T> T getPageView(final Class<T> viewClass, final  HttpServletRequest request) {
		return getPageView(viewClass, request, null);
	}
    /**
     * This method transforms data from requestEvent to pageView
     * the only constrain is that pageView fields name should match controls in jsp
     * @param viewClass
     * @param requestEvent
     * @return Corresponding Object for given viewClass
     */
    public static <T> T getPageView(final Class<T> viewClass, final  HttpServletRequest request, final String id) {
        T viewClsObject = null;
        try {
            // Creates a new Object for the given view class
            viewClsObject = viewClass.newInstance();
            // Collects all Methods in view class
            //final Method[] viewClsMethods = viewClass.getDeclaredMethods();
            final Method[] viewClsMethods = viewClass.getMethods();
            final Object[] methodArgs = new Object[1];
            for (final Method method : viewClsMethods) {
                final String methodName = method.getName();
                // process only setters
                if (methodName.matches("set.+")) {
                    final StringBuilder fieldNameBuf = new StringBuilder(methodName);
                    // delete the prefix set
                    fieldNameBuf.delete(0, 3);
                    // change first character to lowercase
                    fieldNameBuf.replace(0, 1, fieldNameBuf.substring(0, 1).toLowerCase());
                    String param = null;
                    if(!StringUtil.isEmpty(id)) {
                    	param = id + fieldNameBuf.toString();
                    	param = param.toLowerCase();
                    } else {
                    	param = fieldNameBuf.toString();
                    }
                    final Object attribute = request.getParameter(param);
                    final Class<?>[] paramTypes = method.getParameterTypes();
                    if((paramTypes.length == 1) && (attribute !=null)){
                    	boolean isInvoke = false;
                        if(paramTypes[0] == List.class || paramTypes[0] == Collection.class){// then it is checkbox
                            if (attribute instanceof String[]) {// more than one is checked case
                                methodArgs[0] = Arrays.asList((String[]) attribute);
                            } else {// one check box or none is checked case
                                final String stringAttribute = (String) attribute;
                                methodArgs[0] = !((stringAttribute == null) && stringAttribute.isEmpty()) ? Arrays.asList(stringAttribute.trim()) : null;
                            }
                            isInvoke = true;
                        } else if(paramTypes[0] == String.class) {// then it is normal input field
                            final String stringAttribute = (String) attribute;
                            methodArgs[0] = !((stringAttribute == null) && stringAttribute.isEmpty()) ? stringAttribute.trim() : null;
                            isInvoke = true;
                        }
                        if (isInvoke && (methodArgs[0] != null)) {
                            // calls the setter method in ViewClassObject
                            // by passing values got from request event
                            method.invoke(viewClsObject, methodArgs);
                        }
                    }
                }
            } // end of for loop

        } catch (final InstantiationException e) {
            throw new RuntimeException(e.getMessage(),e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(),e);
        } catch (final IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage(),e);
        } catch (final InvocationTargetException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        System.out.println("passwordresetcontroller-reqst pag view"+viewClsObject);
        return viewClsObject;
    }
}

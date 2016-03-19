package com.fpl.factory.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Map;

import com.fpl.factory.config.ClassInstantiatorConfig;

/**
 * A class loader is an object that is responsible for loading classes. The class <tt>ClassInstantiator</tt> is
 * an final class, we can not extend are override class <tt>ClassInstantiator</tt> and the methods. Given the
 * <ahref="#name">binary name</a> of a class, a class loader should attempt to locate or generate data that
 * constitutes a definition for the class.
 * 
 * <p> Any class name provided as a {@link ClassInstantiatorType} parameter to methods in <tt>ClassInstantiator</tt> must be a
 * binary name.</p> 
 * 
 * <p> Examples of valid class names include:
 * <blockquote><pre>
 *      "com.beans.factory.TracciabilitaplichiCacheImpl"
 * </pre></blockquote>
 * </p>
 * @author  Murali
 */
@SuppressWarnings("unchecked")
public class ClassInstantiator {

    //in case of multi thread env, we can not use HashMap as a cache. so here we can use Hashtable. 
    private static final Map<ClassInstantiatorConfig, Object> singletonInstances = new Hashtable<ClassInstantiatorConfig, Object>();


    /**
     * If a system only needs one instance of a class, and that instance needs to be accessible in many different
     * parts of a system this method return single Object for all calls, this method returns the <code>Class</code>
     * object associated with the class or interface with the given string name.
     *
     * @param   binaryClassNameHolder   the fully qualified name of the desired class.
     * @return  the <code>Class</code> object for the class with the
     *          specified name.
     *          
     * @exception RuntimeException if the class cannot be located
     */
    public static <Type> Type createSingletonInstance(final ClassInstantiatorConfig binaryClassNameHolder) {
        Type instance = (Type) singletonInstances.get(binaryClassNameHolder);
        if(instance == null) {
            instance = (Type)createInstance(binaryClassNameHolder);
            singletonInstances.put(binaryClassNameHolder, instance);
        }
        return instance;
    }

    /**
     * If a system only needs one instance of a class, and that instance needs to be accessible in many different
     * parts of a system this method return single Object for all calls, this method returns the <code>Class</code>
     * object associated with the class or interface with the given string name.
     *
     * Returns the <code>Class</code> object associated with the class or interface with the given string name.
     * 
     * <p> This method intern Uses the constructor represented by this <code>Constructor</code> object to
     * create and initialize a new instance of the constructor's declaring class, with the specified
     * initialization parameters. Individual parameters are automatically unwrapped to match primitive formal
     * parameters, and both primitive and reference parameters are subject to method invocation conversions as
     * necessary. </P>
     * 
     * <p>If the required access and argument checks succeed and the instantiation will proceed, the constructor's
     * declaring class is initialized if it has not already been initialized.</P>
     *
     * <p>If the constructor completes normally, returns the newly created and initialized instance.
     * 
     * @param binaryClassNameHolder     the fully qualified name of the desired class.
     * @param paramValues   array of objects values to be passed as arguments to the constructor call
     * @param paramTypes    array of objects Types to be passed as arguments to the constructor call
     * @return              the <code>Class</code> object for the class with the specified name.
     * @exception           RuntimeException if the class cannot be located
     */
    public static <Type> Type createSingletonInstance(final ClassInstantiatorConfig binaryClassNameHolder, final Object[] paramValues,final Class<?> ... paramTypes)
    {
        Type instance = (Type) singletonInstances.get(binaryClassNameHolder);
        if(instance == null) {
            instance = (Type) createInstance(binaryClassNameHolder, paramValues, paramTypes);
            singletonInstances.put(binaryClassNameHolder, instance);
        }
        return instance;
    }
    
    /**
     * Returns the <code>Class</code> object associated with the class or
     * interface with the given string name.
     * 
     * <p> For example, the following code fragment returns the
     * runtime <code>Class</code> Object for the class named
     * <code>it.sella.condizioni.dao.impl.TrovaCondizioneDaoImpl</code>:
     *
     * <blockquote><pre>
     *   Class&nbsp;t&nbsp;= Class.forName("it.sella.condizioni.dao.impl.TrovaCondizioneDaoImpl").newInstance()
     * </pre></blockquote>
     *
     * @param      className the fully qualified name of the desired class.
     * @return     the <code>Class</code> object for the class with the
     *             specified name.
     * @exception  RuntimeException if the class cannot be located
     */
    public static <Type> Type createInstance(final ClassInstantiatorConfig binaryClassNameHolder) {
        try {
            return (Type)loadClass(binaryClassNameHolder.getQualifiedClassName()).newInstance();
        } catch(final IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch(final InstantiationException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Returns the <code>Class</code> object associated with the class or
     * interface with the given string name.
     * 
     * <p> This method intern Uses the constructor represented by this <code>Constructor</code> object to
     * create and initialize a new instance of the constructor's declaring class, with the specified
     * initialization parameters. Individual parameters are automatically unwrapped to match primitive formal
     * parameters, and both primitive and reference parameters are subject to method invocation conversions as
     * necessary. </P>
     * 
     * <p>If the required access and argument checks succeed and the instantiation will proceed, the constructor's
     * declaring class is initialized if it has not already been initialized.</P>
     *
     * <p>If the constructor completes normally, returns the newly created and initialized instance.
     * 
     * @param className     the fully qualified name of the desired class.
     * @param paramValues   array of objects values to be passed as arguments to the constructor call
     * @param paramTypes    array of objects Types to be passed as arguments to the constructor call
     * @return              the <code>Class</code> object for the class with the specified name.
     * @exception           RuntimeException if the class cannot be located
     */
    public static <Type> Type createInstance(final ClassInstantiatorConfig binaryClassNameHolder, final Object[] paramValues,final Class<?> ... paramTypes)
    {
        final Class<?> classInstance = loadClass(binaryClassNameHolder.getQualifiedClassName());

        final int paramTypesLength = paramTypes != null ? paramTypes.length : 0;
        final int paramValuesLength = paramValues != null ? paramValues.length : 0;
        final Object[] actualParamValues = new Object[paramTypesLength];
        if(paramValuesLength > paramTypesLength) {
            throw new RuntimeException("invalid arguments..");
        }
        final int paramValueLength = paramValues != null ? paramValues.length : 0;
        for(int i = 0; i <paramTypesLength ; i++) {
            if(i < paramValueLength) {
                actualParamValues[i] = paramValues[i];
            }
        }
        try {
            final Constructor<?> constructor = classInstance.getDeclaredConstructor(paramTypes);
            final Object classObject = constructor.newInstance(actualParamValues);
            return (Type)classObject;
        } catch (final InstantiationException e) {
            throw new RuntimeException(e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (final InvocationTargetException e) {
            throw new RuntimeException(e.getCause().getMessage(),e);
        } catch (final NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch(final IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the <code>Class</code> object associated with the class or interface with the given string name.
     *
     * <p> For example, the following code fragment returns the
     * runtime <code>Class</code> descriptor for the class named
     * <code>it.sella.condizioni.dao.impl.TrovaCondizioneDaoImpl</code>:
     *
     * <blockquote><pre>
     *   Class&nbsp;t&nbsp;= Class.forName("it.sella.condizioni.dao.impl.TrovaCondizioneDaoImpl")
     * </pre></blockquote>
     * <p>
     * A call to <tt>forName("X")</tt> causes the class named
     * <tt>X</tt> to be initialized.
     *
     * @param      className   the fully qualified name of the desired class.
     * @return     the <code>Class</code> object for the class with the
     *             specified name.
     * @exception RuntimeException if the class cannot be located
     */
    public static Class<?> loadClass(final String className) {
        try {
            return Class.forName(className);
        } catch(final ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }    
}



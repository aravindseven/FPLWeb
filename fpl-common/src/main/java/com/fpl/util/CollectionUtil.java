package com.fpl.util;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

/**
 * Provides utility methods and decorators for Collection instances. 
 * 
 * @author Murali
 */
@SuppressWarnings("unchecked")
public class CollectionUtil {

	public interface IClosure<Element> {
		void execute(Element input);
	}
    /**
     * <p> Defines a functor interface implemented by classes that do something. </p>
     * 
     * <p> A Closure represents a block of code which is executed from inside some block, function or iteration. It 
     * operates an input object </p>
     * 
     * @author Murali
     */
    public interface IPredicate<Element> {
        /**
         * Performs an action on the specified input object. 
         * 
         * @param element - the input to execute on
         */
        boolean evaluate(Element element);
    }

    /**
     * Returns the first ElementType for the input Collection based on the filter condition Criteria.
     * 
     * @param list Collection containing list of <tt>ElementType</tt>
     * @param filter Logic of filter condition Criteria
     * 
     * @return <tt>ElementType</tt>
     */
    public static <ElementType> ElementType find(final Collection<ElementType> list, final IPredicate<ElementType> predicate) {
        ElementType executionReturn = null;
        if((list != null) && !list.isEmpty()) {
            executionReturn = (ElementType)CollectionUtils.find(list, new Predicate() {
                @Override
                public boolean evaluate(final Object paramObject) {
                    return predicate.evaluate((ElementType)paramObject);
                }
            });
        }
        return executionReturn;
    }
    
    /**
     * Returns sub-set of Collection for the input Collection based on the filter condition Criteria.
     * If input Collection is null, then <tt>NullPointerException</tt> is thrown.
     * 
     * @param list Collection containing list of <tt>ElementType</tt>
     * @param filter Logic of filter condition Criteria
     * 
     * @return Collection of <tt> ElementType </tt>
     * 
     * @throws <tt>NullPointerException</tt> if this list contains a null element
     */
    public static <ElementType> Collection<ElementType> select(final Collection<ElementType> list, final IPredicate<ElementType> predicate) {
        final Collection<ElementType> filteredList = CollectionUtils.select(list, new Predicate() {
            @Override
            public boolean evaluate(final Object paramObject) {
                return predicate.evaluate((ElementType)paramObject);
            }
        });
        return filteredList;
    }
    
    /**
     * Selects all elements from inputCollection which don't match the given predicate into an output collection.
     * If the input predicate is null, the result is an empty list
     * 
     * @param list Collection containing list of <tt>ElementType</tt>
     * @param filter Logic of filter condition Criteria
     * 
     * @return Collection of <tt> ElementType </tt>
     * 
     * @throws <tt>NullPointerException</tt> if this list contains a null element
     */
    public static <ElementType> Collection<ElementType> selectRejected(final Collection<ElementType> list, final IPredicate<ElementType> predicate) {
        final Collection<ElementType> filteredList = CollectionUtils.selectRejected(list, new Predicate() {
            @Override
            public boolean evaluate(final Object paramObject) {
                return predicate.evaluate((ElementType)paramObject);
            }
        });
        return filteredList;
    }
    
    /**
     * Returns true when the input Collection is having the ElementType based on the filter condition Criteria.
     * 
     * @param list Collection containing list of <tt>ElementType</tt>
     * @param filter Logic of filter condition Criteria
     * 
     * @return <tt>boolean</tt>
     */
    public static <ElementType> boolean exists(final Collection<ElementType> list, final IPredicate<ElementType> predicate) {
        return CollectionUtils.exists(list, new Predicate() {
            @Override
            public boolean evaluate(final Object paramObject) {
                return predicate.evaluate((ElementType)paramObject);
            }
        });
    }
}



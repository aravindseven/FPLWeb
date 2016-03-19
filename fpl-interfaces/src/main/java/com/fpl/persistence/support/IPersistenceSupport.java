package com.fpl.persistence.support;

import java.io.Serializable;

/**
 * <p> The root interface in the <i>persistence hierarchy</i>. This is the central API class abstracting the notion of a 
 *     persistence service. Some IPersistenceSupport implementations have restrictions on the entity that they may contain.
 *     For example, attempting to use an ineligible entity or database connecting problem throws an unchecked exception,
 *     typically <tt> RuntimeException </tt>.</p> <br>
 * 
 * <p> The main function of the <tt>IPersistenceSupport</tt> is to offer,
 *      <li> create
 *      <li> read
 *      <li> update
 *      <li> delete <br> <br> 
 *    operations for instances of generic entity <code> Entity </code> classes. It is up to each persistence to determine its own 
 *    process for persistence related policy.</p>
 *
 * @author Murali     
 * 
 * @see <tt>IHibernateDAOSupport</tt> 
 */
public interface IPersistenceSupport<Entity> {

    /**
     * <p> Return the persistent instance of the entity class specified for this <tt> IPersistenceSupport </tt> with the 
     * given identifier, or null if there is no such persistent instance. If the instance, or a proxy for the
     * instance, is already associated with the session, return that instance or proxy (optional). The proxy
     * instance return is based on the <tt> IPersistenceSupport </tt> implementor.
     * </p> 
     * 
     * @param  an identifier
     * 
     * @return a persistent entity or null 
     */
    Entity get(final Serializable id);

    /**
     * <p> Persist the given transient entity, make an entity instance managed and persistent. First assigning a generated  
     * identifier, or using the current value of the identifier property if the <tt>assigned</tt> generator is used.(optional) </p>
     * 
     * <p> Specified entity object must be an instance of <code> Entity </code> specified for this <tt> IPersistenceSupport</tt>. </p>
     *  
     * @param entity -- entity a transient instance of <code> Entity </code> specified for this <tt> IPersistenceSupport</tt>
     * 
     * @param flushMode
     * 
     * @return identifier
     */
    Serializable save(final Entity entity, final boolean flushMode);

    /**
     * <p> Update the persistent instance with the identifier of the given detached instance. If there is a persistent 
     * instance with the same identifier, an exception is thrown(optional). </p>
     * 
     * <p> Specified entity object must be an instance of <code> Entity </code> specified for this <tt> IPersistenceSupport</tt>. </p>
     *
     * @param entity a detached instance containing updated state
     * 
     * @param flushMode
     */
    void update(final Entity entity, final boolean flushMode);

    /**
     * <p> Remove a persistent instance from the datastore. Remove the entity instance. The argument may be an instance 
     * associated with the receiving <tt>Session</tt> or a transient instance with an identifier associated with existing 
     * persistent state(optional). </p>
     * 
     * <p> Specified entity object must be an instance of <code> Entity </code> specified for this <tt> IPersistenceSupport</tt>. </p>
     *
     * @param entity the instance to be removed
     */
    void delete(final Entity entity, final boolean flushMode);
}



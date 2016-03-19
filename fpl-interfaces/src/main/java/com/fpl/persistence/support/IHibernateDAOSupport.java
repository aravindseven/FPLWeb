package com.fpl.persistence.support;

import java.io.Serializable;

import javax.persistence.PersistenceException;

/**
 * <p> The main runtime utility to access Hibernate features. The persistence pattern used this interface is 
 * <tt><a href="http://java.sun.com/blueprints/corej2eepatterns/Patterns/DataAccessObject.html">DAO Pattern</a></tt>.
 * The main purpose of this utility interface is to be implemented from Hibernate DAO class. </p>
 * 
 * <p> This interface extends {@link IPersistenceSupport} interface adding some methods specific to Hibernate 
 * implementations. This interface contains several easily usable methods to save, update, merge, delete or 
 * retrieve objects</p>
 * 
 * @author Murali 
 */
public interface IHibernateDAOSupport<Entity> extends IPersistenceSupport<Entity> {

    /**
     * <p> Return the persistent instance of the entity class specified for this <tt> IPersistenceSupport </tt> with the 
     * given identifier, or null if there is no such persistent instance. If the instance, or a proxy for the
     * instance, is already associated with the session, return that instance or proxy (optional). The proxy
     * instance return is based on the <tt> IPersistenceSupport </tt> implementor.</p> 
     * 
     * <p> This method returns the entity object by taking the id as input and evict the object based on the given 
     * input isEvict.</p>
     * 
     * @param id  an identifier
     * 
     * @return a persistent entity or null 
     * 
     * @throws PersistenceException - Wraps an HibernateException and Database related Exception. Indicates that an exception occurred during a persistence call. 
     */
    Entity get(final Serializable id, boolean isEvict);

    /**
     * Return the persistent instance of the given entity class with the given identifier, obtaining the specified 
     * lock mode, assuming the instance exists. 
     * 
     * @param id a valid identifier of an existing persistent instance of the class 
     * 
     * @return he persistent instance or proxy 
     * 
     * @throws PersistenceException - Wraps an HibernateException and Database related Exception. Indicates that an exception occurred during a persistence call. 
     */
    Entity load(final Serializable id);

    /**
     * Return the list of all persistent instances of the entity class specified for this <tt> HibernateDAOSupport </tt>.
     * If the instances, or proxies for the instances, are already associated with the session, return those instances 
     * or proxies.
     * 
     * @return List<Entity> the list of all entity results
     * 
     * @throws PersistenceException - Wraps an HibernateException and Database related Exception. Indicates that an exception occurred during a persistence call. 
     */
    //List<Entity> getAllEntities();

    /**
     * <p> This method insert/update single record based on the id in the entity object if flushMode is <b>true</b> after 
     * insertion or updation the entity will commit. else end of the Transection only it will insert or update in database.</p>
     * 
     * <p> Either <tt>save()</tt> or <tt>update()</tt> the given entity, depending upon the value of its identifier property. 
     * By default the instance is always saved. This behaviour may be adjusted by specifying an <tt>unsaved-value</tt> attribute 
     * of the identifier property mapping. This operation cascades to associated instances if the association is mapped 
     * with <tt>cascade="save-update"</tt>. Specified entity object must be an instance of entityClass specified for this 
     * IHibernateDAOSupport. </p>
     * 
     * @param entity - entity a transient or detached instance containing new or updated state
     * @param flushMode
     * 
     * @throws PersistenceException - Wraps an HibernateException and Database related Exception. Indicates that an exception occurred during a persistence call. 
     */
    void saveOrUpdate(final Entity entity, final boolean flushMode);

    /**
     * Remove this instance from the session cache. Changes to the instance will not be synchronized with the
     * database. This operation cascades to associated instances if the association is mapped with cascade="evict".
     *
     * @param a persistent instance 
     * 
     * @throws PersistenceException - Wraps an HibernateException and Database related Exception. Indicates that an exception occurred during a persistence call. 
     */
    void evict(final Entity entity);

    /**
     * <p> Force this session to flush. Must be called at the end of a unit of work, before committing the transaction 
     * and closing the session (depending on flush-mode, Transaction.commit()  calls this method).</p>
     *  
     * <p> Flushing is the process of synchronizing the underlying persistent store with persistable state 
     * held in memory. </p>
     * 
     * throws PersistenceException - Wraps an HibernateException and Database related Exception. Indicates that an exception occurred during a persistence call.
     */
    void flushSession();
}



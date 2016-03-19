package com.fpl.persistence.support;

import java.io.Serializable;

import javax.persistence.PersistenceException;

import org.hibernate.Session;

import com.fpl.hibernate.HibernateCallback;

/**
 * <p> The main runtime utility to access Hibernate features. This utility class helps and reduces efforts in 
 * creating Hibernate implementations for <code> IHibernateDAOSupport </code> interfaces.</p>
 * 
 * The persistency pattern used this class is 
 * <tt><a href="http://java.sun.com/blueprints/corej2eepatterns/Patterns/DataAccessObject.html">DAO Pattern</a></tt>
 * The main purpose of this utility class is to be called through delegation from Hibernate DAO implementations. </p>
 * 
 * <p> This class extends {@link IHibernateDAOSupport} interface adding some methods specific to Hibernate 
 * implementations. This class contains several easily usable methods to save, update, merge, delete or retrieve objects;
 * moreover it contains a method to get a <tt>HibernateTemplate</tt> to execute methods using <tt>HibernateSession</tt> 
 * object.</p>
 *
 * @author Murali
 * @param <Entity> -- Hibernate entity class
 */
public abstract class HibernateDAOSupport<Entity> extends AbstractHibernateConfig implements IHibernateDAOSupport<Entity> {

	protected abstract Class<? super Entity> getEntityClass();

	/**
	 * <p> Return the persistent instance of the entity class specified for this <tt> IPersistenceSupport </tt> with the 
	 * given identifier, or null if there is no such persistent instance. If the instance, or a proxy for the
	 * instance, is already associated with the session, return that instance or proxy (optional). The proxy
	 * instance return is based on the <tt> IPersistenceSupport </tt> implementor.</p> 
	 * 
	 * <p> This method returns the entity object by taking the id as input and evict the object with the session</p>
	 * 
	 * @param id  an identifier
	 * 
	 * @return a persistent entity or null 
	 */
	@Override
	public Entity get(final Serializable id) {
		return get(id, true);
	}

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
	@Override
	public Entity get(final Serializable id, final boolean isEvict) {
		final Entity entity = getHibernateTemplate().execute(new HibernateCallback<Entity,Entity>("get : " + id) {
			@Override
			public Entity doInHibernate(final Session session) {
				return (Entity) session.get(getEntityClass(), id);
			}
		});
		if(isEvict) {
			evict(entity);
		}
		return entity;
	}

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
	@Override
	public final Entity load(final Serializable id){
		return getHibernateTemplate().execute(new HibernateCallback<Entity,Entity>("load : " + id) {
			@Override
			public Entity doInHibernate(final Session session) {
				return (Entity) session.load(getEntityClass(), id);
			}
		});
	}

	/**
	 * <p> Persist the given transient entity, make an entity instance managed and persistent. First assigning a generated  
	 * identifier, or using the current value of the identifier property if the <tt>assigned</tt> generator is used. </p>
	 * 
	 * <p> Specified entity object must be an instance of <code> Entity </code> specified for this <tt> IPersistenceSupport</tt>. </p>
	 *  
	 * @param entity -- entity a transient instance of <code> Entity </code> specified for this <tt> IPersistenceSupport</tt>
	 * 
	 * @param flushMode
	 * 
	 * @return identifier
	 */
	@Override
	public Serializable save(final Entity entity,final boolean flushMode) {
		return getHibernateTemplate().execute(new HibernateCallback<Entity,Serializable>("save : " + entity) {
			@Override
			public Serializable doInHibernate(final Session session) {
				final Serializable id = session.save(entity);
				if(flushMode) {
					session.flush();
				}
				return id;
			}
		});
	}

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
	@Override
	public void saveOrUpdate(final Entity entity, final boolean flushMode) {
		getHibernateTemplate().execute(new HibernateCallback<Entity,Void>("saveOrUpdate : " + entity) {
			@Override
			public Void doInHibernate(final Session session) {
				session.saveOrUpdate(entity);
				if(flushMode) {
					session.flush();
				}
				return null;
			}
		});
	}

	/**
	 * <p> Update the persistent instance with the identifier of the given detached instance. If there is a persistent 
	 * instance with the same identifier, an exception is thrown(</p>
	 * 
	 * <p> Specified entity object must be an instance of <code> Entity </code> specified for this <tt> IPersistenceSupport</tt>. </p>
	 *
	 * @param entity a detached instance containing updated state
	 * 
	 * @param flushMode
	 */
	@Override
	public void update(final Entity entity, final boolean flushMode) {
		getHibernateTemplate().execute(new HibernateCallback<Entity,Void>("update : " + entity) {
			@Override
			public Void doInHibernate(final Session session) {
				session.update(entity);
				if(flushMode) {
					session.flush();
				}
				return null;
			}
		});
	}

	/**
	 * <p> Remove a persistent instance from the datastore. Remove the entity instance. The argument may be an instance 
	 * associated with the receiving <tt>Session</tt> or a transient instance with an identifier associated with existing 
	 * persistent state</p>
	 * 
	 * <p> Specified entity object must be an instance of <code> Entity </code> specified for this <tt> IPersistenceSupport</tt>. </p>
	 *
	 * @param entity the instance to be removed
	 */
	@Override
	public void delete(final Entity entity, final boolean flushMode) {
		getHibernateTemplate().execute(new HibernateCallback<Entity, Void>("delete : "+entity) {
			@Override
			public Void doInHibernate(final Session session) {
				session.delete(entity);
				if(flushMode) {
					session.flush();
				}
				return null;
			}
		});
	}

	/**
	 * <p> Force this session to flush. Must be called at the end of a unit of work, before committing the transaction 
	 * and closing the session (depending on flush-mode, Transaction.commit()  calls this method).</p>
	 *  
	 * <p> Flushing is the process of synchronizing the underlying persistent store with persistable state 
	 * held in memory. </p>
	 * 
	 * throws PersistenceException - Wraps an HibernateException and Database related Exception. Indicates that an exception occurred during a persistence call.
	 */
	@Override
	public void flushSession() {
		getHibernateTemplate().execute(new HibernateCallback<Entity, Void>("flush session"+ getEntityClass()) {
			@Override
			public Void doInHibernate(final Session session) {
				session.flush();
				return null;
			}
		});
	}

	/**
	 * Remove this instance from the session cache. Changes to the instance will not be synchronized with the
	 * database. This operation cascades to associated instances if the association is mapped with cascade="evict".
	 *
	 * @param a persistent instance 
	 * 
	 * @throws PersistenceException - Wraps an HibernateException and Database related Exception. Indicates that an exception occurred during a persistence call. 
	 */
	@Override
	public void evict(final Entity entity) {
		getHibernateTemplate().execute(new HibernateCallback<Entity, Void>("evict from session: "+entity) {
			@Override
			public Void doInHibernate(final Session session) {
				session.evict(entity);
				return null;
			}
		});
	}
}



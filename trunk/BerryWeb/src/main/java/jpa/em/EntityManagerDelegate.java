package jpa.em;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;

@ApplicationScoped
public class EntityManagerDelegate implements EntityManager {

	@Inject
	private SessionManagerStore entityManagerStore;

	public void persist(Object entity) {
	      entityManagerStore.get().save(entity);
	   }

	   public <T> T merge(T entity) {
		   entityManagerStore.get().saveOrUpdate(entity);
	      return entity;
	   }

	   public void remove(Object entity) {
	     // entityManagerStore.get().remove(entity);
	   }

	   public <T> T find(Class<T> entityClass, Object primaryKey) {
	      return  (T) entityManagerStore.get().load((Class)entityClass, (Serializable)primaryKey);
	   }

	   public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) {
		   return null;// entityManagerStore.get().find(entityClass, primaryKey, properties);
	   }

	   public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode) {
		   return null;// entityManagerStore.get().find(entityClass, primaryKey, lockMode);
	   }

	   public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties) {
		   return null;// entityManagerStore.get().find(entityClass, primaryKey, lockMode, properties);
	   }

	   public <T> T getReference(Class<T> entityClass, Object primaryKey) {
		   return null;// entityManagerStore.get().getReference(entityClass, primaryKey);
	   }

	   public void flush() {
	      entityManagerStore.get().flush();
	   }

	   public void setFlushMode(FlushModeType flushMode) {
	     // entityManagerStore.get().setFlushMode(flushMode);
	   }

	   public FlushModeType getFlushMode() {
		   return null;// entityManagerStore.get().getFlushMode();
	   }

	   public void lock(Object entity, LockModeType lockMode) {
	     // entityManagerStore.get().lock(entity, lockMode);
	   }

	   public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties) {
	      //entityManagerStore.get().lock(entity, lockMode, properties);
	   }

	   public void refresh(Object entity) {
	      entityManagerStore.get().refresh(entity);
	   }

	   public void refresh(Object entity, Map<String, Object> properties) {
	     // entityManagerStore.get().refresh(entity, properties);
	   }

	   public void refresh(Object entity, LockModeType lockMode) {
	    //  entityManagerStore.get().refresh(entity, lockMode);
	   }

	   public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties) {
	    //  entityManagerStore.get().refresh(entity, lockMode, properties);
	   }

	   public void clear() {
	      //entityManagerStore.get().clear();
	   }

	   public void detach(Object entity) {
	      //entityManagerStore.get().detach(entity);
	   }

	   public boolean contains(Object entity) {
		   return false;// entityManagerStore.get().contains(entity);
	   }

	   public LockModeType getLockMode(Object entity) {
		   return null;// entityManagerStore.get().getLockMode(entity);
	   }

	   public void setProperty(String propertyName, Object value) {
	      //entityManagerStore.get().setProperty(propertyName, value);
	   }

	   public Map<String, Object> getProperties() {
		   return null;// entityManagerStore.get().getProperties();
	   }

	   public Query createQuery(String qlString) {
		   return null;// entityManagerStore.get().createQuery(qlString);
	   }

	   public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
		   return null;// entityManagerStore.get().createQuery(criteriaQuery);
	   }

	   public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
		   return null;// entityManagerStore.get().createQuery(qlString, resultClass);
	   }

	   public Query createNamedQuery(String name) {
		   return null;// entityManagerStore.get().createNamedQuery(name);
	   }

	   public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
		   return null;// null;// entityManagerStore.get().createNamedQuery(name, resultClass);
	   }

	   public Query createNativeQuery(String sqlString) {
		   return null;// null;// entityManagerStore.get().createNativeQuery(sqlString);
	   }

	   

	   public Query createNativeQuery(String sqlString, String resultSetMapping) {
		   return null;// entityManagerStore.get().createNativeQuery(sqlString, resultSetMapping);
	   }

	   public void joinTransaction() {
	      //ntityManagerStore.get().joinTransaction();
	   }

	   public <T> T unwrap(Class<T> cls) {
		   return null;// entityManagerStore.get().unwrap(cls);
	   }

	   public Object getDelegate() {
		   return null;// entityManagerStore.get().getDelegate();
	   }

	   public void close() {
	      entityManagerStore.get().close();
	   }

	   public boolean isOpen() {
		   return false;// entityManagerStore.get().isOpen();
	   }

	   public EntityTransaction getTransaction() {
		   return null;// entityManagerStore.get().getTransaction();
	   }

	   public EntityManagerFactory getEntityManagerFactory() {
		   return null;// entityManagerStore.get().getEntityManagerFactory();
	   }

	   public CriteriaBuilder getCriteriaBuilder() {
		   return null;// entityManagerStore.get().getCriteriaBuilder();
	   }

	   public Metamodel getMetamodel() {
		   return null;// entityManagerStore.get().getMetamodel();
	   }

	   public Query createNativeQuery(String sqlString, Class resultClass) {
		   return null;// entityManagerStore.get().createNativeQuery(sqlString, resultClass);
	   }
	
}

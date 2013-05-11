package org.berrycloud.dao;

import javax.inject.Inject;


import org.berrycloud.core.tx.TransactionInfoStore;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public abstract class GenericDao<T> {

	private Class<T> type ;
	
	@Inject
	private TransactionInfoStore  transactionInfoStore;
	
	public void flush() throws HibernateException {
		transactionInfoStore.get().getSession().flush();
	}

	@SuppressWarnings("unchecked")
	public T get(long id) {
		return (T) transactionInfoStore.get().getSession().get(type, id);
	}

	@SuppressWarnings("unchecked")
	public T load(long id) {
		return (T) transactionInfoStore.get().getSession().load(type, id);
	}

	@SuppressWarnings("unchecked")
	public T merge(T obj) {
		return (T) transactionInfoStore.get().getSession().merge(obj);
	}

	
	public void refresh(T obj) {
		transactionInfoStore.get().getSession().refresh(obj);
	}

	public void save(T arg0) {
		transactionInfoStore.get().getSession().save(arg0);
	}

		
	public void saveOrUpdate(T arg0) {
		transactionInfoStore.get().getSession().saveOrUpdate(arg0);
	}

	protected final Session getSession(){
		return transactionInfoStore.get().getSession();
	}
	
	
	
	
}

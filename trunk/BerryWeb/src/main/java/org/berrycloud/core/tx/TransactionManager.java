package org.berrycloud.core.tx;

import java.lang.reflect.Method;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


import org.apache.log4j.Logger;
import org.berrycloud.core.NoRollbackException;

@ApplicationScoped
public class TransactionManager {

	@Inject
	private Logger log;
	
	@Inject
	private TransactionInfoStore transactionInfoStore;
	
	public TransactionInfo createIfNecessary(Method method, Object targetObject) {
		TransactionInfo tx = transactionInfoStore.get();
		if(tx == null){
			return transactionInfoStore.register();
		}
		tx = tx.noBeginNoCommit();
		if(method.isAnnotationPresent(Transactional.class)){
			Transactional ann =	method.getAnnotation(Transactional.class);
			if(ann.type()== TransactionAttributeType.REQUIRES_NEW){
				tx = transactionInfoStore.register();
			}
			
		} else if (targetObject.getClass().isAnnotationPresent(Transactional.class)){
			Transactional ann =	targetObject.getClass().getAnnotation(Transactional.class);
			if(ann.type()== TransactionAttributeType.REQUIRES_NEW){
				tx = transactionInfoStore.register();
			}
		}
		return tx;
	}

	public void beginTransaction(TransactionInfo txInfo) {
		if (txInfo.isAllowBegin()) {
			log.info(">>>>>START TRANSACTION<<<<<");
			txInfo.begin();
		}
	}

	public void commit(TransactionInfo txInfo) {
		if (txInfo.isAllowCommit()) {
			log.info(">>>>>>END transaction<<<<<<");
			txInfo.getTransaction().commit();
		}
	}

	public void release(TransactionInfo txInfo) {
		if(txInfo.isAllowBegin()){
			transactionInfoStore.unregister(txInfo);
		}
	}

	public void rollback(TransactionInfo txInfo, Exception e) throws Exception {
		if(!(e instanceof NoRollbackException)){
			txInfo.getTransaction().rollback();
		}
		throw e;
	}

}

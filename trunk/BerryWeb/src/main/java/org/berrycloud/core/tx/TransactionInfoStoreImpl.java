package org.berrycloud.core.tx;

import java.util.Stack;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.berrycloud.core.db.factory.SessionConfigurationFactory;
import org.hibernate.SessionFactory;

@ApplicationScoped
public class TransactionInfoStoreImpl implements TransactionInfoStore {
	
	@Inject
	private Logger log;
	
	private SessionFactory sessionFactory;
	
	
	@PostConstruct
	private void initTransactionInfoStore(){
		sessionFactory = SessionConfigurationFactory.sessionFactory;
	}
	
	private ThreadLocal<Stack<TransactionInfo>> txInfoStackThreadLocal = new ThreadLocal<>();
	
	@Override
	public TransactionInfo get() {
		 final Stack<TransactionInfo> txInfoStact = txInfoStackThreadLocal.get();
	    if (txInfoStact == null || txInfoStact.isEmpty()) {
	    	return null;
	      } else
	         return txInfoStact.peek();
	}

	@Override
	public TransactionInfo register() {
		Stack<TransactionInfo> txInfoStact = txInfoStackThreadLocal.get();
		if (txInfoStact == null) {
			txInfoStact = new Stack<>();
			txInfoStackThreadLocal.set(txInfoStact);
		}
		TransactionInfo txInfo = init();
		txInfoStact.push(txInfo);
		return txInfo;
	}

	@Override
	public void unregister(TransactionInfo info) {
		final Stack<TransactionInfo> txInfoStact = txInfoStackThreadLocal.get();
		if (txInfoStact == null || txInfoStact.isEmpty())
			throw new IllegalStateException(
					"Removing of entity manager failed. Your entity manager was not found.");

		if (txInfoStact.peek() != info)
			throw new IllegalStateException(
					"Removing of entity manager failed. Your entity manager was not found.");
		
		destroy(info);
		txInfoStact.pop();
	}

	
	private void destroy(TransactionInfo info) {
		info.getSession().close();
		
	}

	private TransactionInfo init() {
		TransactionInfo result = new TransactionInfo(sessionFactory.openSession());
		return result;
	}
}

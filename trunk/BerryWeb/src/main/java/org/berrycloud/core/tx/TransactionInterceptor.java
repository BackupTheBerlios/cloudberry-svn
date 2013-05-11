package org.berrycloud.core.tx;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

@Interceptor
@Transactional
public class TransactionInterceptor {
	
	@Inject
	private TransactionManager transactionManager;
	
	@Inject
	private Logger log;
	
	@AroundInvoke
	public Object runInTransaction(InvocationContext invocationContext)	throws Exception {
		 
	      Object result = null;
	      TransactionInfo txInfo = transactionManager.createIfNecessary(invocationContext.getMethod(),invocationContext.getTarget());
	      transactionManager.beginTransaction(txInfo);
	      try {
	         result = invocationContext.proceed();
	         transactionManager.commit(txInfo);
	      } catch (Exception e) {
	    	  transactionManager.rollback(txInfo,e);
	      } finally {
	    	  transactionManager.release(txInfo);
	      }
	      return result;
	}

}

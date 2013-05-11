package jpa.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import jpa.Transactional;
import jpa.em.SessionManagerStore;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Interceptor
@Transactional
public class TransactionInterceptor {

	@Inject
	private SessionManagerStore sessionManagerStore;
	
	@Inject
	private Logger log;
	
	@AroundInvoke
	public Object runInTransaction(InvocationContext invocationContext)
			throws Exception {

		  log.info("----------START transaction---------");
	      Session session = sessionManagerStore.register();

	      Object result = null;
	      Transaction tx = session.beginTransaction();
	      try {
	    	   	  
	         result = invocationContext.proceed();
	         tx.commit();
	      } catch (Exception e) {
	         try {
	            if (tx.isActive()) {
	                tx.rollback();
	                log.debug("Rolled back transaction");
	            }
	         } catch (HibernateException e1) {
	           // logger.warn("Rollback of transaction failed -> " + e1);
	         }
	         throw e;
	      } finally {
	         if (session != null) {
	        	 sessionManagerStore.unregister(session);
	        	 session.close();
	         }
	      }
	      log.info("----------END transaction---------");
	      return result;
	}

}

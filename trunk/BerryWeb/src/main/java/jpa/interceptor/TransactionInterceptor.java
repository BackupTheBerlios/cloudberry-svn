package jpa.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import jpa.Transactional;
import jpa.em.SessionManagerStore;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Interceptor
@Transactional
public class TransactionInterceptor {

	@Inject
	private SessionManagerStore sessionManagerStore;
	
	@AroundInvoke
	public Object runInTransaction(InvocationContext invocationContext)
			throws Exception {

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
	            //   logger.debug("Rolled back transaction");
	            }
	         } catch (HibernateException e1) {
	           // logger.warn("Rollback of transaction failed -> " + e1);
	         }
	         throw e;
	      } finally {
	         if (session != null) {
	        	 sessionManagerStore.unregister(session);
	         }
	      }
	      return result;
	}

}

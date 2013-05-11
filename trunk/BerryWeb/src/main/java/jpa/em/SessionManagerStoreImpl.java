package jpa.em;

import java.util.Stack;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import jpa.db.SessionConfigurationFactory;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@WebListener
@ApplicationScoped
public class SessionManagerStoreImpl implements SessionManagerStore, ServletContextListener{

	@Inject
	private Logger log;
	
	private static SessionFactory sessionFactory;
	
	private ThreadLocal<Stack<Session>> sessionStackThreadLocal = new ThreadLocal<>();
	
	@Override
	public Session get() {
		 final Stack<Session> sessionStack = sessionStackThreadLocal.get();
	      if (sessionStack == null || sessionStack.isEmpty()) {
	    	  log.debug("NO STACK");
	         return null;
	      } else
	         return sessionStack.peek();
	}

	@Override
	public Session register() {
		Stack<Session> sessionStack = sessionStackThreadLocal.get();
		if (sessionStack == null) {
			sessionStack = new Stack<>();
			sessionStackThreadLocal.set(sessionStack);
		}
		final Session session = sessionFactory.openSession();
		sessionStack.push(session);
		return session;
	}

	@Override
	public void unregister(Session session) {
		final Stack<Session> sessionStack = sessionStackThreadLocal.get();
	      if (sessionStack == null || sessionStack.isEmpty())
	         throw new IllegalStateException("Removing of entity manager failed. Your entity manager was not found.");

	      if (sessionStack.peek() != session)
	         throw new IllegalStateException("Removing of entity manager failed. Your entity manager was not found.");
	      sessionStack.pop();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			sessionFactory = new SessionConfigurationFactory().build();
		} catch (Exception ex ) {
			throw new Error(ex);
		} 
	}

	

}

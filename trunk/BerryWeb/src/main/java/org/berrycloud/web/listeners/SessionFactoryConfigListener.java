package org.berrycloud.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.berrycloud.core.db.factory.SessionConfigurationFactory;

@WebListener
public class SessionFactoryConfigListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		SessionConfigurationFactory factoty = new SessionConfigurationFactory();
		try {
			SessionConfigurationFactory.sessionFactory = factoty.build();
		} catch (Exception e) {
			throw new Error("UNABLE create SessionFactory", e);
		} 
		

	}

}

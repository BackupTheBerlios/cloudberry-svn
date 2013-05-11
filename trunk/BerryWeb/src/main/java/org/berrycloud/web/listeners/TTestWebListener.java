package org.berrycloud.web.listeners;



import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class TTestWebListener implements ServletContextListener {

	//@Inject
	//@Any
	private Event<ServletContext> servletContextEvent;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println(">>>>>>>>>>>>>>TTestWebListener" +  this + "\n\nservletContextEvent="+servletContextEvent);
//		servletContextEvent.select(new AnnotationLiteral<Initialized>() {
//		}).fire(sce.getServletContext());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println(">>>>>>>>>>>>>>TTestWebListener" +  this+ "\n\nservletContextEvent="+servletContextEvent);
//		servletContextEvent.select(new AnnotationLiteral<Destroyed>() {
//		}).fire(sce.getServletContext());
	}

}

package org.beatific.ddirori.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.beatific.ddirori.context.impl.XmlApplicationContext;

public class DDiroriListener implements ServletContextListener {

	private static final String ROOT_APPLICATION_CONTEXT = ApplicationContext.class.getName() + ".ROOT";
	
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		if(sc.getAttribute(ROOT_APPLICATION_CONTEXT) != null) {
			throw new IllegalStateException("Can't initialize duplicated application context.");
		}
		ApplicationContext context = createWebApplicationContext(sc);
		context.init();
	}

	protected ApplicationContext createWebApplicationContext(ServletContext sc){
		ApplicationContext context  = new XmlApplicationContext();
		sc.setAttribute(ROOT_APPLICATION_CONTEXT, context);
		return context;
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		ApplicationContext context = getWebApplicationContext(sc);
		context.destroy();
	}
	
	protected ApplicationContext getWebApplicationContext(ServletContext sc){
		return (ApplicationContext)sc.getAttribute(ROOT_APPLICATION_CONTEXT);
	}
}

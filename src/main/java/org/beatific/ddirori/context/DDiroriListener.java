package org.beatific.ddirori.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.beatific.ddirori.context.impl.XmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;


public class DDiroriListener implements ServletContextListener {

	private static final String ROOT_APPLICATION_CONTEXT = ApplicationContext.class.getName() + ".ROOT";
	public static final String SPRING_WEB_APPLICATION_CONTEXT_ATTRIBUTE = WebApplicationContext.class.getName() + ".ROOT";
	private static final String CONFIG_LOCATION = "configFileLocation";
	public static final String SPRING_CONFIG_LOCATION = "contextConfigLocation";
	private static final String BASE_PACKAGE = "basePackage";
	private static final String TIME_OUT = "timeout";
	
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		if(sc.getAttribute(ROOT_APPLICATION_CONTEXT) != null) {
			throw new IllegalStateException("Can't initialize duplicated application context.");
		}
		ApplicationContext context = createWebApplicationContext(sc);
		ApplicationContextUtils.setServletContext(sc);
		context.init();
	}

//	private org.springframework.context.ApplicationContext getSpringApplicationContext(ServletContext sc) {
//		
//		String springConfigLocation = sc.getInitParameter(SPRING_CONFIG_LOCATION);
//		if (springConfigLocation == null) return null;
//		Integer timeout = sc.getInitParameter(TIME_OUT) == null ? -1 : Integer.parseInt(sc.getInitParameter(SPRING_CONFIG_LOCATION));
//		Integer time = 0;
//		while(sc.getAttribute(SPRING_WEB_APPLICATION_CONTEXT_ATTRIBUTE) == null) {
//			try {
//				if(timeout > 0 && timeout <= time) return null;
//				Thread.sleep(1000);
//				time = time + 1000;
//			} catch (InterruptedException e) {
//				throw new SpringApplicationContextLoadException("Failed to load Spring ApplicationContext", e); 
//			}
//		}
//		return (org.springframework.context.ApplicationContext)sc.getAttribute(SPRING_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//	}
	
	protected ApplicationContext createWebApplicationContext(ServletContext sc){

		String basePackage = sc.getInitParameter(BASE_PACKAGE);
		if(basePackage == null) basePackage = "org.beatific.ddirori";
		XmlApplicationContext context  = new XmlApplicationContext(basePackage);
		
		String configLocationParam = sc.getInitParameter(CONFIG_LOCATION);
		if (configLocationParam != null) {
			context.setFilePath(configLocationParam);
		} else {
			context.setFilePath("ddirori-context.xml");
		}
		
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

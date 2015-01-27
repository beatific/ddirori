package org.beatific.ddirori.context;

import javax.servlet.ServletContext;

public class ApplicationContextUtils {

	private static ApplicationContext context;
	private static ServletContext sc;
	
	public static void setServletContext(ServletContext servletContext) {
		sc = servletContext;
	}
	
	public static void setApplicationContext(ApplicationContext currentContext) {
		context = currentContext;
	}
	
	public static ServletContext getServletContext() {
		return sc;
	}
	
	public static ApplicationContext getApplicationContext() {
		return context;
	}
}

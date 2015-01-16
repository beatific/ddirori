package org.beatific.ddirori.context;

public class ApplicationContextUtils {

	private static ApplicationContext context;
	
	public static void setApplicationContext(ApplicationContext currentContext) {
		context = currentContext;
	}
	public static ApplicationContext getApplicationContext() {
		return context;
	}
}

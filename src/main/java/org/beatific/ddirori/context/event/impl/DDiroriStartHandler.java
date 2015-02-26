package org.beatific.ddirori.context.event.impl;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beatific.ddirori.bean.BeanNotFoundException;
import org.beatific.ddirori.context.ApplicationContext;
import org.beatific.ddirori.context.annotation.DDirori;
import org.beatific.ddirori.context.event.AbstractEventHandler;
import org.beatific.ddirori.context.event.ApplicationContextNotFoundException;
import org.beatific.ddirori.context.event.BeanInjectionException;
import org.beatific.ddirori.context.event.annotation.EventListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * @author beatificho
 * 
 * spring refresh() is not supported by ddirori start handler
 */
@Component
@EventListener
public class DDiroriStartHandler extends AbstractEventHandler<DDiroriStartEvent> implements ApplicationListener<ContextStartedEvent> {

	private static boolean isStartDDirori = false;
	private static boolean isStartSpring = false;
	private static ApplicationContext ddiroriContext;
	private static org.springframework.context.ApplicationContext springContext;
	private final Log logger = LogFactory.getLog(getClass());

	private void inject(Object bean) {
		
		Class<?> clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			DDirori ddirori = (DDirori) field.getAnnotation(DDirori.class);
			String beanName = ddirori.name();

			logger.debug("beanName for Injection[" + beanName + "]");
			
			Object object = getBean(beanName);
			field.setAccessible(true);

			try {
				field.set(bean, object);
			} catch (Exception e) {
				throw new BeanInjectionException(beanName, e);
			}
		}
	}

	private Object getBean(String beanName) {

		if (springContext == null)
			throw new ApplicationContextNotFoundException(springContext);
		if (ddiroriContext == null)
			throw new ApplicationContextNotFoundException(ddiroriContext);

		Object bean = springContext.getBean(beanName);
		if (bean == null) {
			bean = ddiroriContext.getBean(beanName);
		}

		if (bean == null)
			throw new BeanNotFoundException("This Bean is not found["
					+ beanName + "]");

		return bean;
	}

	private void injectDependency() {
		
		Map<String, Object> springbeans = springContext
				.getBeansWithAnnotation(DDirori.class);
		
		Map<String, Object> ddiroribeans = ddiroriContext
				.getBeansWithAnnotation(DDirori.class);

		injectBeans(springbeans);
		injectBeans(ddiroribeans);
		
	}
	
	private void injectBeans(Map<String, Object> beans) {
		logger.debug("beans for Injection[" + beans + "]");
		
		for (Map.Entry<String, Object> entry : beans.entrySet())
			inject(entry.getValue());
	}
	
	public synchronized void handleMyEvent(DDiroriStartEvent event) {

		ddiroriContext = event.getApplicationContext();

		logger.debug("isStartSpring" + isStartSpring);
		
		if (isStartSpring) {
			injectDependency();
		}

		isStartDDirori = true;

	}

	public synchronized void onApplicationEvent(ContextStartedEvent paramE) {

		logger.debug("=====================================recive startEvent=======================================");
		springContext = paramE.getApplicationContext();

		logger.debug("isStartDDirori" + isStartDDirori);
		if (isStartDDirori) {
			injectDependency();
		}

		isStartSpring = true;
	}

}

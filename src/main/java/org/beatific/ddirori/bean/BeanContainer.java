package org.beatific.ddirori.bean;

public abstract class BeanContainer {
	
	protected abstract void registerBean(String beanName, Object bean);
	protected abstract void registerTemp(String tempName, Object temp);
	protected abstract Object getTemp(String tempName); 
	
	protected Object getObject(String objectName) {
		Object object = getBean(objectName);
		
		if(object == null) object = getTemp(objectName);
		
		if(object == null) throw new BeanNotFoundException("This bean is not found[" + objectName + "]");
		
		return object;
	}
	
	protected abstract void clearBean();
	protected abstract void clearTemp(); 
	
	protected void destory() {
		clearBean();
		clearTemp();
	}
	
	public abstract Object getBean(String beanName);
}

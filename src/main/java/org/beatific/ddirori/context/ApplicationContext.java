package org.beatific.ddirori.context;

import org.beatific.ddirori.bean.BeanContainer;

public abstract class ApplicationContext extends BeanContainer {

	public void init() {
		initContext();
		ApplicationContextUtils.setApplicationContext(this);
	}
	
	protected abstract void initContext();
	
	public void destroy() {
		ApplicationContextUtils.setApplicationContext(null);
		destoryContext();
	}
	
	protected abstract void destoryContext();
}

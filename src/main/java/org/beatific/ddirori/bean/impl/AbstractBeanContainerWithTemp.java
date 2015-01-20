package org.beatific.ddirori.bean.impl;

import java.util.HashMap;
import java.util.Map;

import org.beatific.ddirori.bean.BeanContainer;
import org.beatific.ddirori.bean.BeanCreationException;

public abstract class AbstractBeanContainerWithTemp extends BeanContainer {

	private final Map<String, Object> temp = new HashMap<String, Object>();
	
	protected synchronized void registerTemp(String tempName, Object temp) {
		if(this.temp.containsKey(tempName)) throw new BeanCreationException("Can't create duplicated Temp[" + tempName + "]");
		this.temp.put(tempName, temp);
	}
	
	protected synchronized Object getTemp(String tempName) {
		return this.temp.get(tempName);
	}
	
	protected synchronized void clearTemp() {
		this.temp.clear();
	}
}

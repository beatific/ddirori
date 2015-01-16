package org.beatific.ddirori.attribute;

import java.util.ArrayList;
import java.util.List;

public class RelationHolder {

	private final List<Object> holdedObjects = new ArrayList<Object>();
	
	public synchronized List<Object> getHoldedObjects() {
		return holdedObjects;
	}
	
	public synchronized void hold(Object object) {
		this.holdedObjects.add(object);
	}
	
	public synchronized void release() {
		this.holdedObjects.clear();
	}
			
}

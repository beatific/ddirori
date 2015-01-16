package org.beatific.ddirori.attribute;

public class RelationValueHolder {

	private String currentObject;
	
	public String getHoldedObject() {
		return currentObject;
	}
	
	public void hold(String objectName) {
		this.currentObject = objectName;
	}
			
}

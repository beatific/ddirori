package org.beatifc.ddirori.type;

public enum TagType {

	BEAN, TEMP, ATTRIBUTE;
	
	public static TagType valueOf(Type type) {
		return type.type();
	}
}

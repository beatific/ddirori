package org.beatific.ddirori.type;

import org.beatific.ddirori.bean.annotation.Action;

public enum TagType {

	BEAN, TEMP, ATTRIBUTE;
	
	public static TagType valueOf(Action action) {
		return action.type();
	}
}

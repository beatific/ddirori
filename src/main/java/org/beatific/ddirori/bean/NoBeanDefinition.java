package org.beatific.ddirori.bean;

import org.beatific.ddirori.type.TagType;

public class NoBeanDefinition extends BeanDefinition {

	public NoBeanDefinition(String tagName) {
		this.tagName = tagName;
		this.tagType = TagType.ATTRIBUTE;
		this.constructor = new NoBeanConstructor();
	}
	
}

package org.beatific.ddirori.bean;


public class DDiroriBeanDefinition extends BeanDefinition {

	public DDiroriBeanDefinition(org.beatific.ddirori.bean.annotation.Action annotation, Constructor constructor) {
		this.tagType = annotation.type();
		this.tagName = annotation.tag();
		this.constructor = constructor;
	}
}

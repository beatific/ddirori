package org.beatific.ddirori.context.impl;

import org.beatific.ddirori.bean.BeanDefinition;
import org.beatific.ddirori.bean.Constructor;
import org.beatific.ddirori.bean.annotation.Action;
import org.beatific.ddirori.type.TagType;

@Action(tag="test2", type=TagType.TEMP)
public class Test2Action implements Constructor<Test2Class>{

	public Test2Class create(BeanDefinition definition) {
		Test2Class test =  new Test2Class();
		test.setAttribute2((String)definition.attributes().get("attribute2"));
		return test;
	}

}

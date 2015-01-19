package org.beatific.ddirori.context.impl;

import java.util.List;
import java.util.Map;

import org.beatific.ddirori.bean.Constructor;
import org.beatific.ddirori.bean.annotation.Action;
import org.beatific.ddirori.meta.BeanDefinition;
import org.beatific.ddirori.type.TagType;

@Action(tag="test2", type=TagType.TEMP)
public class Test2Action implements Constructor<Test2Class>{

	public Test2Class create(BeanDefinition parent, List<BeanDefinition> children,
			Map<String, Object> attributes) {
		Test2Class test =  new Test2Class();
		test.setAttribute2((String)attributes.get("attribute2"));
		return test;
	}

}

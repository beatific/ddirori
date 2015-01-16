package org.beatific.ddirori.context.impl;

import java.util.List;
import java.util.Map;

import org.beatific.ddirori.bean.Constructor;
import org.beatific.ddirori.bean.annotation.Action;
import org.beatific.ddirori.meta.BeanDefinition;
import org.beatific.ddirori.type.TagType;

@Action(tag="ddirori", type=TagType.ATTRIBUTE)
public class DDiroriAction implements Constructor<TestClass>{

	public TestClass create(BeanDefinition parent, List<BeanDefinition> children,
			Map<String, Object> attributes) {
		return new TestClass();
	}

}

package org.beatific.ddirori.context.impl;

import java.util.List;
import java.util.Map;

import org.beatific.ddirori.bean.Constructor;
import org.beatific.ddirori.bean.annotation.Action;
import org.beatific.ddirori.meta.BeanDefinition;
import org.beatific.ddirori.type.TagType;

@Action(tag="test", type=TagType.BEAN)
public class TestAction implements Constructor<TestClass>{

	public TestClass create(BeanDefinition parent, List<BeanDefinition> children,
			Map<String, Object> attributes) {
		TestClass test =  new TestClass();
		test.setText((String)attributes.get("text"));
		return test;
	}

}

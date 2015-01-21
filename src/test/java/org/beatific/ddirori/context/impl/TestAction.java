package org.beatific.ddirori.context.impl;

import org.beatific.ddirori.bean.BeanDefinition;
import org.beatific.ddirori.bean.Constructor;
import org.beatific.ddirori.bean.annotation.Action;
import org.beatific.ddirori.type.TagType;

@Action(tag="test", type=TagType.BEAN)
public class TestAction implements Constructor<TestClass>{

	public TestClass create(BeanDefinition definition) {
		TestClass test =  new TestClass();
		test.setAttribute((String)definition.attributes().get("attribute"));
		test.setTest2((Test2Class)definition.attributes().get("test2"));
		return test;
	}

}

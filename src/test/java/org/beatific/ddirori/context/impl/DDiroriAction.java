package org.beatific.ddirori.context.impl;

import org.beatific.ddirori.bean.BeanDefinition;
import org.beatific.ddirori.bean.Constructor;

//@Action(tag="ddirori", type=TagType.ATTRIBUTE)
public class DDiroriAction implements Constructor<TestClass>{

	public TestClass create(BeanDefinition definition) {
		return new TestClass();
	}

}

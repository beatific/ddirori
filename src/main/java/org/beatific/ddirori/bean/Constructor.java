package org.beatific.ddirori.bean;

public interface Constructor<T> {

	public T create(BeanDefinition definition);
}

package org.beatific.ddirori.attribute.resolver;

public interface AttributeTypeResolver<T> {

	public T resolve(Object object);
}

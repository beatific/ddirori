package org.beatific.ddirori.attribute.caster;

import java.util.HashMap;
import java.util.Map;

import org.beatific.ddirori.attribute.TypeResolverCreationException;
import org.beatific.ddirori.attribute.annotation.ArgumentType;
import org.beatific.ddirori.attribute.resolver.AttributeTypeResolver;
import org.beatific.ddirori.utils.AnnotationUtils;

public class AttributeTypeCaster {

	private final Map<String, AttributeTypeResolver<?>> resolvers = new HashMap<String, AttributeTypeResolver<?>>();
	private final String basePackage;
	
	public AttributeTypeCaster(String basePackage) {
		this.basePackage = basePackage;
	}
	
	public Object cast(String type, Object object) {
		AttributeTypeResolver<?> resolver = resolvers.get(type);
		return resolver.resolve(object);
	}
	
	public void init() {
		for(Class<?> clazz : AnnotationUtils.findClassByAnnotation(basePackage, ArgumentType.class)) {
			ArgumentType annotation = clazz.getAnnotation(ArgumentType.class);
			try {
				resolvers.put(annotation.type(), (AttributeTypeResolver<?>)clazz.newInstance());
			} catch (InstantiationException e) {
				throw new TypeResolverCreationException("Creating AttributeTypeResolver is failed[" + clazz.getName() + "]");
			} catch (IllegalAccessException e) {
				throw new TypeResolverCreationException("Creating AttributeTypeResolver is failed[" + clazz.getName() + "]");
			}
		}
		
	}
}

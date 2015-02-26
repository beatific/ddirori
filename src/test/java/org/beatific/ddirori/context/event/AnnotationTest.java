package org.beatific.ddirori.context.event;

import java.lang.reflect.Field;

import org.beatific.ddirori.context.annotation.DDirori;
import org.junit.Test;

public class AnnotationTest {

	@Test
	public void testAnnotationFiled() {
		
		ClassWithDDiroriAnnotation ddirori = new ClassWithDDiroriAnnotation();
		Class<?> clazz = ddirori.getClass();

		for(Field field : clazz.getDeclaredFields()) {
			
			DDirori a = field.getAnnotation(DDirori.class);
			System.out.println(a);
		    if(field.isAnnotationPresent(DDirori.class)) {
		    	System.out.println(field.getType());
		    }
		}
	}
}

package org.beatific.ddirori.utils;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class AnnotationUtilsTests {

	@Test
	public void testFindClassByAnnotation() {
		List<Class<?>> list = AnnotationUtils.findClassByAnnotation("org.beatific", TestAnnotation.class);
		assertEquals(AnnotationUtilsTestObject.class, list.get(0));
	}
}

package org.beatific.ddirori.context.impl;

import static org.junit.Assert.assertEquals;

import org.beatific.ddirori.bean.BeanDefinitionNotFoundException;
import org.beatific.ddirori.meta.MetaInfo;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlApplicationContextTests {

	private XmlApplicationContext context;
	
	@Before
	public void setup() {
		context = new XmlApplicationContext("org.beatific", true);
		context.setFilePath("ddirori.xml");
	}
	
//	@Test
	public void testBuildDocument() {
		Document document = context.buildDocument();
		Element element = document.getDocumentElement();
		assertEquals("ddirori", element.getNodeName());
	}
	
//	@Test
	public void testBuildMetaInfo() {
		MetaInfo meta = context.buildMetaInfo();
		try {
			assertEquals("test", meta.getMeta("test").getBeanName());
			assertEquals("test2", meta.getMeta("test2").getBeanName());
		} catch (BeanDefinitionNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInit() {
		context.init();
		TestClass object = (TestClass)context.getBean("test");
		
		assertEquals("test1", object.getAttribute());
		assertEquals("test2",object.getTest2().getAttribute2());
	}
	
}

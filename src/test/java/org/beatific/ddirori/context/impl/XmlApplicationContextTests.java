package org.beatific.ddirori.context.impl;

import static org.junit.Assert.assertEquals;

import org.beatific.ddirori.meta.BeanDefinitionNotFoundException;
import org.beatific.ddirori.meta.MetaInfo;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlApplicationContextTests {

	private XmlApplicationContext context;
	
	@Before
	public void setup() {
		context = new XmlApplicationContext("org.beatific");
		context.setFilePath("ddirori.xml");
	}
	
	@Test
	public void testBuildDocument() {
		Document document = context.buildDocument();
		Element element = document.getDocumentElement();
		assertEquals("ddirori", element.getNodeName());
	}
	
	@Test
	public void testBuildMetaInfo() {
		MetaInfo meta = context.buildMetaInfo();
		try {
			assertEquals("ddirori", meta.getMeta("test").getParentElementDefinition().getBeanName());
		} catch (BeanDefinitionNotFoundException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void testInit() {
		context.init();
		TestClass object = (TestClass)context.getBean("test");
		System.out.println(object.getText());
	}
	
}

package org.beatific.ddirori.context.impl;

import org.beatific.ddirori.bean.BeanCreationException;
import org.beatific.ddirori.bean.Constructor;
import org.beatific.ddirori.bean.DDiroriBeanDefinition;
import org.beatific.ddirori.bean.annotation.Action;
import org.beatific.ddirori.context.ApplicationContext;
import org.beatific.ddirori.meta.DocumentReader;
import org.beatific.ddirori.meta.MetaInfo;
import org.beatific.ddirori.utils.AnnotationUtils;
import org.beatific.ddirori.xml.DocumentLoader;
import org.beatific.ddirori.xml.FileReader;
import org.beatific.ddirori.xml.XmlParseException;
import org.w3c.dom.Document;

public class XmlApplicationContext extends ApplicationContext {

	private boolean validation = false;
	private boolean namespaceAware = true;
	private String filePath = "ddiroricontext.xml";
	
	public XmlApplicationContext() {
	    super(null);	
	}
	
	public XmlApplicationContext(boolean isUseDDiroriExpression) {
	    super(null, isUseDDiroriExpression);	
	}
	
	public XmlApplicationContext(String basePackage) {
	    super(basePackage);	
	}
	
	public XmlApplicationContext(String basePackage, boolean isUseDDiroriExpression) {
	    super(basePackage, isUseDDiroriExpression);	
	}
	
	public void setValidation(boolean validation) {
		this.validation = validation;
	}

	public void setNamespaceAware(boolean namespaceAware) {
		this.namespaceAware = namespaceAware;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	protected MetaInfo buildMetaInfo() {
		
		Document doc = buildDocument();
		MetaInfo meta = initMeta();
		DocumentReader reader = new DocumentReader();
		reader.read(doc, meta);
		return meta;
	}
	
	protected MetaInfo initMeta() {
		MetaInfo meta = new MetaInfo();
		for(Class<?> constructor : AnnotationUtils.findClassByAnnotation(basePackage, Action.class)) {
			Action annotation = constructor.getAnnotation(Action.class);
			try {
				meta.setDefinition(annotation.tag(), new DDiroriBeanDefinition(annotation, (Constructor)constructor.newInstance()));
			} catch (InstantiationException e) {
				throw new BeanCreationException("Constructor is failed to execute[" + constructor.getName() + "]");
			} catch (IllegalAccessException e) {
				throw new BeanCreationException("Constructor is failed to execute[" + constructor.getName() + "]");
			}
		}
		return meta;
	}
	
	protected Document buildDocument() {
		DocumentLoader loader = new DocumentLoader();
		Document doc = null;
		
		try {
			doc = loader.loadDocument(FileReader.getInputStream(filePath), validation, namespaceAware);
		} catch (Exception e) {
			throw new XmlParseException("Parsing xml is failed[" + filePath + "]");
		}
		
		return doc;
	}

	
}

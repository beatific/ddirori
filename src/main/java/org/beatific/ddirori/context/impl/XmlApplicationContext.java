package org.beatific.ddirori.context.impl;

import org.beatific.ddirori.bean.BeanContainer;
import org.beatific.ddirori.bean.BeanCreationException;
import org.beatific.ddirori.bean.Constructor;
import org.beatific.ddirori.bean.annotation.Action;
import org.beatific.ddirori.context.ApplicationContext;
import org.beatific.ddirori.context.ContextInitializeException;
import org.beatific.ddirori.meta.BeanDefinition;
import org.beatific.ddirori.meta.BeanDefinitionNotFoundException;
import org.beatific.ddirori.meta.DocumentReader;
import org.beatific.ddirori.meta.MetaInfo;
import org.beatific.ddirori.utils.AnnotationUtils;
import org.beatific.ddirori.xml.DocumentLoader;
import org.beatific.ddirori.xml.FileReader;
import org.beatific.ddirori.xml.XmlParseException;
import org.w3c.dom.Document;

public class XmlApplicationContext extends BeanContainer implements ApplicationContext {

	private boolean validation;
	private boolean namespaceAware;
	private String filePath;
	
	public void initContext() {
		try {
			init();
		} catch (BeanDefinitionNotFoundException e) {
			throw new ContextInitializeException("This Context is failed to initialize.");
		}
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

	protected MetaInfo initMeta() {
		MetaInfo meta = new MetaInfo();
		for(Class<?> constructor : AnnotationUtils.findClassByAnnotation(Action.class)) {
			Action annotation = constructor.getAnnotation(Action.class);
			try {
				meta.setDefinition(annotation.tag(), new BeanDefinition(annotation, (Constructor)constructor.newInstance()));
			} catch (InstantiationException e) {
				throw new BeanCreationException("Constructor is failed to execute[" + constructor.getName() + "]");
			} catch (IllegalAccessException e) {
				throw new BeanCreationException("Constructor is failed to execute[" + constructor.getName() + "]");
			}
		}
		return meta;
	}
	
}
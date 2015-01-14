package org.beatifc.ddirori.context.impl;

import java.io.IOException;

import org.beatifc.ddirori.bean.BeanContainer;
import org.beatifc.ddirori.context.ApplicationContext;
import org.beatifc.ddirori.meta.MetaInfo;
import org.beatifc.ddirori.xml.DocumentLoader;
import org.beatifc.ddirori.xml.FileReader;

public class XmlApplicationContext extends BeanContainer implements ApplicationContext {

	private boolean validation;
	private boolean namespaceAware;
	private String filePath;
	

	void setValidation(boolean validation) {
		this.validation = validation;
	}

	void setNamespaceAware(boolean namespaceAware) {
		this.namespaceAware = namespaceAware;
	}

	void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	protected MetaInfo buildMetaInfo() {
		DocumentLoader loader = new DocumentLoader();
		
		try {
			loader.loadDocument(FileReader.getInputStream(filePath), validation, namespaceAware);
		} catch (IOException e) {
		} catch (Exception e) {
		}
		return null;
	}

	
}

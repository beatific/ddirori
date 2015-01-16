package org.beatific.ddirori.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beatific.ddirori.xml.handler.DefaultEntityResolver;
import org.beatific.ddirori.xml.handler.DefaultErrorHandler;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DocumentLoader {
	private static final String SCHEMA_LANGUAGE_ATTRIBUTE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	private static final String XSD_SCHEMA_LANGUAGE = "http://www.w3.org/2001/XMLSchema";
	private static final Log logger = LogFactory.getLog(DocumentLoader.class);
	
	private ErrorHandler errorHandler = new DefaultErrorHandler(logger);
	private EntityResolver entityResolver = new DefaultEntityResolver();

	void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	void setEntityResolver(EntityResolver entityResolver) {
		this.entityResolver = entityResolver;
	}

	public Document loadDocument(InputStream inputStream,
			boolean validation, boolean namespaceAware) throws ParserConfigurationException, SAXException, IOException {		
		InputSource inputSource = new InputSource(inputStream);
		
		DocumentBuilderFactory factory = createDocumentBuilderFactory(
				validation, namespaceAware);
		DocumentBuilder builder = createDocumentBuilder(factory);
		return builder.parse(inputSource);
	}

	protected DocumentBuilderFactory createDocumentBuilderFactory(
			boolean validation, boolean namespaceAware)
			throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(namespaceAware);
		factory.setValidating(validation);
		try {
			factory.setAttribute(SCHEMA_LANGUAGE_ATTRIBUTE, XSD_SCHEMA_LANGUAGE);
		} catch (IllegalArgumentException ex) {
			ParserConfigurationException pcex = new ParserConfigurationException("Failed to parse XML document!");

			pcex.initCause(ex);
			throw pcex;
		}

		return factory;
	}

	protected DocumentBuilder createDocumentBuilder(
			DocumentBuilderFactory factory) throws ParserConfigurationException {
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		docBuilder.setEntityResolver(entityResolver);
		docBuilder.setErrorHandler(errorHandler);
		return docBuilder;
	}
}

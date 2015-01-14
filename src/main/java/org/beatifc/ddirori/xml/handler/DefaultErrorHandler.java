package org.beatifc.ddirori.xml.handler;

import org.apache.commons.logging.Log;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DefaultErrorHandler implements ErrorHandler {

	private Log logger;
	
	public DefaultErrorHandler(Log logger) {
		this.logger = logger;
	}
	public void warning(SAXParseException exception) throws SAXException {
		logger.warn(exception);
	}

	public void error(SAXParseException exception) throws SAXException {
		throw exception;
		
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		throw exception;
		
	}

}

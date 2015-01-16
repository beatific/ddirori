package org.beatific.ddirori.xml;

public class XmlParseException extends RuntimeException {

	private static final long serialVersionUID = -4635846053608559614L;

	public XmlParseException() {
		super();
	}
	
	public XmlParseException(String m) {
		super(m);
	}
	
	public XmlParseException(Throwable t) {
		super(t);
	}
	
	public XmlParseException(String m, Throwable t) {
		super(m, t);
	}
}

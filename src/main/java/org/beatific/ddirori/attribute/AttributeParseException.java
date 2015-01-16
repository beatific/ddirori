package org.beatific.ddirori.attribute;

public class AttributeParseException extends RuntimeException {


	private static final long serialVersionUID = 2288078045074759656L;

	public AttributeParseException() {
        super();
    }
	
	public AttributeParseException(String s) {
        super(s);
    }
	
	public AttributeParseException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public AttributeParseException(Throwable cause) {
        super(cause);
    }
}

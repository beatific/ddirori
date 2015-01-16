package org.beatific.ddirori.meta;

public class DocumentParseException extends RuntimeException{

	private static final long serialVersionUID = -5664423152935307688L;

	public DocumentParseException() {
		super();
	}
	
	public DocumentParseException(String m) {
		super(m);
	}
	
	public DocumentParseException(Throwable t) {
		super(t);
	}
	
	public DocumentParseException(String m, Throwable t) {
		super(m, t);
	}
}

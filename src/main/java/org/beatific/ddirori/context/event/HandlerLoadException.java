package org.beatific.ddirori.context.event;

public class HandlerLoadException extends RuntimeException {

	private static final long serialVersionUID = -3115150728646772251L;

	public HandlerLoadException() {
        super();
    }
	
	public HandlerLoadException(String s) {
        super(s);
    }
	
	public HandlerLoadException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public HandlerLoadException(Throwable cause) {
        super(cause);
    }
}

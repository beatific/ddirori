package org.beatific.ddirori.context.event;

public class ApplicationContextNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2063881933068274367L;

	public ApplicationContextNotFoundException() {
		super();
	}
	
	public ApplicationContextNotFoundException(Object m) {
		super(new StringBuffer("This ApplicationContext is not found[").append(m.getClass().getName()).append("]").toString());
	}
	
	/**
	 * @param m spring or ddirori
	 */
	public ApplicationContextNotFoundException(String m) {
		super(new StringBuffer("This ApplicationContext is not found[").append(m).append("]").toString());
	}
	
	public ApplicationContextNotFoundException(Throwable t) {
		super(t);
	}
	
	public ApplicationContextNotFoundException(Object m, Throwable t) {
		super(new StringBuffer("This ApplicationContext is not found[").append(m.getClass().getName()).append("]").toString(), t);
	}
	
	/**
	 * @param m spring or ddirori
	 */
	public ApplicationContextNotFoundException(String m, Throwable t) {
		super(new StringBuffer("This ApplicationContext is not found[").append(m).append("]").toString(), t);
	}

}

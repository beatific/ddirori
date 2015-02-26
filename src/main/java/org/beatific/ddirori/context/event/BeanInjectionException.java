package org.beatific.ddirori.context.event;

public class BeanInjectionException extends RuntimeException {

	private static final long serialVersionUID = 2063881933068274367L;

	public BeanInjectionException() {
		super();
	}
	
	public BeanInjectionException(Object m) {
		super(new StringBuffer("Bean injection is failed[").append(m.getClass().getName()).append("]").toString());
	}
	
	public BeanInjectionException(String m) {
		super(new StringBuffer("Bean injection is failed[").append(m).append("]").toString());
	}
	
	public BeanInjectionException(Throwable t) {
		super(t);
	}
	
	public BeanInjectionException(Object m, Throwable t) {
		super(new StringBuffer("Bean injection is failed[").append(m.getClass().getName()).append("]").toString(), t);
	}
	
	public BeanInjectionException(String m, Throwable t) {
		super(new StringBuffer("Bean injection is failed[").append(m).append("]").toString(), t);
	}

}
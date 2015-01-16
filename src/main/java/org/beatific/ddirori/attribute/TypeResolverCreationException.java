package org.beatific.ddirori.attribute;

public class TypeResolverCreationException extends RuntimeException {
	
	private static final long serialVersionUID = 1781009137736631157L;

	public TypeResolverCreationException() {
		super();
	}
	
	public TypeResolverCreationException(String m) {
		super(m);
	}
	
	public TypeResolverCreationException(Throwable t) {
		super(t);
	}
	
	public TypeResolverCreationException(String m, Throwable t) {
		super(m, t);
	}
}

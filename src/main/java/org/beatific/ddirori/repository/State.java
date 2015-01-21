package org.beatific.ddirori.repository;

public class State extends Enum {
	public static final State TYPE_1 = new State("TYPE_1", 1);
	public static final State DIAMOND = new State("DIAMOND", 2);
	public static final State CLUB = new State("CLUB", 3);
	public static final State SPADE = new State("SPADE", 4);

	public State(String name, int ordinal) { super(name, ordinal);}

}

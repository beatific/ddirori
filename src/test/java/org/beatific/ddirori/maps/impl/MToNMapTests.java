package org.beatific.ddirori.maps.impl;

import org.junit.Test;

public class MToNMapTests {

	int hash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}
	
	@Test
	public void testHash() {
		System.out.println(1000000>>>12);
		System.out.println(Integer.MAX_VALUE>>30);
		System.out.println(Math.abs(Integer.MIN_VALUE));
		System.out.println(Math.log(Integer.MAX_VALUE + Math.abs(Integer.MIN_VALUE) + 2));
	}
}

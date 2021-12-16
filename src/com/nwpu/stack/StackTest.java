package com.nwpu.stack;


import org.junit.Test;

public class StackTest {

	@Test
	public void test() {

		IStack<Integer> s = new SimpleStack<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		
		System.out.println(s);
		s.pop();
		System.out.println(s);
	}

}

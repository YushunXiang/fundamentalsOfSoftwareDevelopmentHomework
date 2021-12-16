package com.nwpu.map;

import org.junit.Test;

public class MapTest {

	@Test
	public void test() {
		IMap<String, Integer> map = new SimpleMap<String, Integer>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		map.put("k", 4);
		System.out.println("添加前: " + map);
		map.put("b", 99);
		System.out.println("添加后: " + map);
		map.remove("a");
		System.out.println("删除后: " + map);
	}

}

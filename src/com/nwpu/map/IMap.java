package com.nwpu.map;

public interface IMap<K,V> {
	void put(K key, V val);
	V get(K key);
	void remove(K key);
	void set(K key, V val);
	

}

package com.nwpu.map;

import com.nwpu.map.exception.SimpleMapException;

import static java.lang.Math.abs;

/**
 * Desc:
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 *
 * @see SimpleMapException
 * @author Yushun Xiang
 * @date 2021/12/5 22:57
 */
public class SimpleMap<K, V> implements IMap<K, V> {
	private final int DEFAULT_SIZE = 10;
	private Node<K, V>[] tables = new Node[DEFAULT_SIZE];

	/**
	 * Put a key-value into the tables
	 * @param key key with which the specified value is to be associated
	 * @param val value to be associated with the specified key
	 * @author Yushun Xiang
	 * @date 2021/12/5 23:27
	 */
	@Override
	public void put(K key, V val) {
		if (key == null)
			throw new SimpleMapException("key is null exception");
		int index = key.hashCode();
		index %= tables.length;
		index = abs(index);
		Node<K, V> newNode = new Node<>(new Entry<>(key, val), null, null);
		if (tables[index] == null) {
			tables[index] = newNode;
		} else if(tables[index].next==null) { 
			if (key.equals(tables[index].data.key)) {
				tables[index].data.val = val;
				return;
			}

			// 老师我觉得put代码要加上以下两行，我认为原来老师写错了
			//--------------------------------------------------------
			tables[index].next = newNode;
			newNode.prev = tables[index];
			// --------------------------------------------------------
		} else {

			Node<K, V> tmp = tables[index];
			while (tmp.next!= null ) {// 最后一个节点
				if (key.equals(tmp.data.key)) {
					tmp.data.val = val;
					return;
				}
				tmp = tmp.next;
			}
			tmp.next = newNode;
			newNode.prev= tmp;
		}

	}

	/**
	 * Returns the value to which the specified key is mapped,
	 * or {@code null} if this map contains no mapping for the key.
	 *
	 * @param key the key
	 * @return {@link V} the value, or null if none
	 * @author Yushun Xiang
	 * @date 2021/12/5 23:31
	 */
	@Override
	public V get(K key) {
		if (key == null) {

			throw new SimpleMapException("Key is null exception.");
		}
		int index = key.hashCode();
		index %= tables.length;
		index = abs(index); // 防止 hashCode 溢出
		if (tables[index] == null) {

			throw new SimpleMapException("Key has no corresponding value.");
		} else {

			if (tables[index].data.key.equals(key)) {

				return tables[index].data.val;
			} else {
				Node<K, V> tmp = tables[index];
				while (tmp.next != null) {

					if (tmp.data.key.equals(key)) {

						return tmp.data.val;
					}
					tmp = tmp.next;
				}
				if (tmp.data.key.equals(key)) {

					return tmp.data.val;
				}
			}
		}
		return null;
	}

	@Override
	public void remove(K key) {
		if (key == null)
			throw new SimpleMapException("key is null exception");
		
		int index = key.hashCode();
		index %= tables.length;
		index = abs(index);
		if(tables[index]==null) {
			throw new SimpleMapException("key不存在");
		}
		if(tables[index].data.key.equals(key)) {
			Node<K,V>  head = tables[index];
			tables[index] = head.next;
			head = null;
		}else {
			Node<K,V> tmp = tables[index];
			while(tmp.next!=null) {
				if(tmp.data.key.equals(key)) {
					Node<K,V> front = tmp.prev;
					Node<K,V> back = tmp.next;
					front.next = back;
					back.prev = front;
					tmp =null;
					return;
				}
			}
		}
	}

	/**
	 * update the key-value
	 * @param key the key
	 * @param val the value
	 * @author Yushun Xiang
	 * @date 2021/12/5 23:36
	 */
	@Override
	public void set(K key, V val) {
		if (key == null) {

			throw new SimpleMapException("Key is null exception.");
		}
		int index = key.hashCode();
		index %= tables.length;
		index = abs(index);
		if (tables[index] == null) {

			throw new SimpleMapException("Key has no corresponding value.");
		} else {

			if (tables[index].data.key.equals(key)) {

				tables[index].data.val = val;
			} else {
				Node<K, V> tmp = tables[index];
				while (tmp.next != null) {

					if (tmp.data.key.equals(key)) {

						tmp.data.val = val;
					}
					tmp = tmp.next;
				}
				if (tmp.data.key.equals(key)) {

					tmp.data.val = val;
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < tables.length; i++) {
			if (tables[i] != null) {
				Node<K, V> tmp = tables[i];

				while (tmp != null) {
					sb.append("{");
					sb.append(tmp.data.key);
					sb.append(":");
					sb.append(tmp.data.val);
					sb.append("},");
					tmp = tmp.next;
				}
			}
		}

		sb.deleteCharAt(sb.length() - 1);

		return sb.toString();

	}

	private static class Node<K, V> {
		Entry<K, V> data;
		Node<K, V> prev;
		Node<K, V> next;

		public Node(Entry<K, V> data, Node<K, V> prev, Node<K, V> next) {
			super();
			this.data = data;
			this.prev = prev;
			this.next = next;
		}

	}

	private static class Entry<K, V> {
		K key;
		V val;

		public Entry(K key, V val) {
			super();
			this.key = key;
			this.val = val;
		}
	}

}

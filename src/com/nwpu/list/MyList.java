package com.nwpu.list;

public interface MyList<E> {
    void addFirst(E e);
    void addLast(E e);
    void add(E e);
    void add(int index, E e);

    void remove(int index);
    void remove(E e);

    void update(int index, E e);

    E get(int index);
}

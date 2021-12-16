package com.nwpu.list;


/**
 * Double-linked list collection implementation of the MyList interface.
 * @see MyList
 * @author Yushun Xiang
 */
public class MyListImp<E> implements MyList<E>{
    private Node<E> head;//头引用
    private Node<E> tail; //尾引用
    private int size;


    /**
     * Constructs an empty list.
     * @author Yushun Xiang
     */
    public MyListImp() {
        size = 0;
    }

    /**
     * Links e as first element.
     * @param e
     * @author Yushun Xiang
     */
    @Override
    public void addFirst(E e) {
        final Node<E> first = head;
        final Node<E> newNode = new Node<>(e, null, first);

        head = newNode;

        if(first==null) {

            tail = newNode;
        }else {

            first.prev = newNode;
        }
        size++;

    }

    /**
     * Links e as last elements.
     * @param e
     * @author Yushun Xiang
     */
    @Override
    public void addLast(E e) {
        final Node<E> last = tail;
        final Node<E> newNode = new Node<>(e, last,null);

        tail = newNode;

        if(last == null) {

            head = newNode;
        }else {

            last.next = newNode;
        }
        size++;

    }

    /**
     * Appends the specified element to the end of this list.
     * @param e element to be appended to this list
     * @author Yushun Xiang
     */
    @Override
    public void add(E e) {

        addLast(e);
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any
     * subsequent elements to the right (adds one to their indices).
     * @param index index at which the specified element is to be inserted
     * @param e element to be inserted
     * @author Yushun Xiang
     */
    @Override
    public void add(int index, E e) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }

        if (index == size) {
            addLast(e);
        } else {
            addBefore(e, node(index));
        }
    }

    /**
     * Inserts elements e before non-null Node succ.
     * @param e element to be inserted.
     * @param succ
     * @author Yushun Xiang
     */
    private void addBefore(E e, Node<E> succ) {
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<E>(e, pred, succ);
        succ.prev = newNode;
        if (pred == null) {
            head = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
    }


    /**
     * Returns the (non-null) Node at the specified element index.
     */
    private Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    @Override
    public void remove(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }

        unlink(node(index));
    }

    /**
     * Unlinks non-null node x
     * @param x
     * @author Yushun Xiang
     */
    private void unlink(Node<E> x) {
        final E element = x.data;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.data = null;
        size--;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param e element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
    @Override
    public void remove(E e) {
        if (e == null) {
            for (Node<E> x = head; x != null; x = x.next) {
                if (x.data == null) {
                    unlink(x);
                }
            }
        } else {
            for (Node<E> x = head; x != null; x = x.next) {
                if (e.equals(x.data)) {
                    unlink(x);
                }
            }
        }
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index index of the element to replace
     * @param e element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void update(int index, E e) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        Node<E> x = node(index);
        x.data = e;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public E get(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        return node(index).data;
    }

    /**
     * Desc: return a String to show list-information
     * @return {@link String}
     * @author Yushun Xiang
     * @date 2021/11/25 18:21
     */
    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < this.size - 1; i++) {
            s += String.valueOf(this.get(i).toString() + ", ");
        }
        s += String.valueOf(this.get(size - 1).toString() + "]");
        return s;
    }

    public static void main(String[] args) {
        MyListImp<Integer> list1 = new MyListImp<Integer>();
        list1.add(1);
        list1.add(3);
        list1.add(2);
        System.out.println(list1.toString());
        list1.update(2, 10);
        System.out.println(list1.toString());
    }

    private static class Node<E>{
        E data;
        Node<E> prev;
        Node<E> next;

        public Node(E data, Node<E> prev, Node<E> next) {
            super();
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}

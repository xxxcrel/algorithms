package solutions.fundamentals;

import java.util.Collection;
import java.util.NoSuchElementException;

public class LinkedList<T> {

    private Node<T> first;

    private Node<T> last;

    private int size = 0;

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public LinkedList(Collection<T> collection) {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // link to last by default
    public void add(T item) {
        Node<T> newNode = new Node<T>(item);
        if (size() == 0) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    /**
     * @throws NoSuchElementException
     */
    public T remove(int index) throws Exception {
        Node<T> pre = first;
        T oldData = null;
        if (size() == 0) {
            throw new NoSuchElementException();
        } else {
            checkElementIndex(index);
            while (--index > 0) {
                pre = pre.next;
            }
            oldData = pre.next.item;
            pre.next = pre.next.next;
        }
        return oldData;
    }

    /**
     * @throws IndexOutofBoundsException
     */
    public void checkElementIndex(int index) throws IndexOutOfBoundsException {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    public void addLast(T item) {
        add(item);
    }

    public void addFirst(T item) {
        Node<T> newNode = new Node<T>(item);
        if (size() == 0) {
            first = last = newNode;
        } else {
            newNode.next = first;
            first = newNode;
        }
        size++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> tmp = first;
        sb.append("[");
        while (tmp != last) {
            sb.append(tmp.item + ", ");
            tmp = tmp.next;
        }
        sb.append(tmp.item + "]");
        return sb.toString();
    }

    private class Node<T> {
        T item;
        Node<T> next;

        Node(T data) {
            item = data;
            next = null;
        }

        Node() {
            item = null;
            next = null;
        }
    }

    public static void main(String[] args) {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("This");
        ll.add("is");
        ll.add("a");
        ll.add("test");
        System.out.println(ll);
    }
}
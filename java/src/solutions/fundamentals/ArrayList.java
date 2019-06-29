package solutions.fundamentals;

import java.util.Iterator;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements Iterable<T>, Cloneable {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] element;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENT = {};
    private static final Object[] EMPTY_ELEMENT = {};
    private int size = 0;

    public ArrayList() {
        element = DEFAULTCAPACITY_EMPTY_ELEMENT;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0)
            element = new Object[initialCapacity];
        else if (initialCapacity == 0)
            element = EMPTY_ELEMENT;
        else
            throw new IllegalArgumentException();
    }

    public ArrayList(ArrayList<T> list) {
        for (T e : list)
            add(e);
    }

    public Object clone() {
        try {
            ArrayList<?> v = (ArrayList<?>) super.clone();
            v.element = Arrays.copyOf(element, size);
            return v;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (element == DEFAULTCAPACITY_EMPTY_ELEMENT)
            grow(Math.max(DEFAULT_CAPACITY, minCapacity));
        else
            grow(minCapacity);
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void grow(int minCapacity) {
        int oldCapacity = element.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        // minCapacity is usually close to size, so this is a win:
        element = Arrays.copyOf(element, newCapacity);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(T e) {
        ensureCapacityInternal(size + 1);
        element[size++] = e;
        return true;

    }

    @SuppressWarnings("unchecked")
    private T element(int index) {
        return (T) element[index];
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) throws ListUnderFlowException {
        rangeCheck(index);

        T oldValue = element(index);
        // number of element need to move
        int numMoved = (size - 1) - index;

        // if it's not remove from tail then it's necessary moved from index + 1 to
        // index position
        if (numMoved > 0)
            System.arraycopy(element, index + 1, element, index, numMoved);
        element[--size] = null;

        return (T) oldValue;
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object o : element) {
            sb.append("[" + o + "]");
        }
        return sb.substring(0);
    }

    public ListIterator<T> listIterator() {
        return new ListItr();
    }

    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        private int index = size;

        public boolean hasNext() {
            return true;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            return (T) element[index++];
        }
    }

    private class ListItr implements ListIterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            if (index != size)
                return true;
            else
                return false;
        }

        @Override
        public boolean hasPrevious() {
            if (index != size && index != 0)
                return true;
            else
                return false;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T previous() {
            if (hasPrevious())
                return (T) element[index - 1];
            else
                throw new NoSuchElementException();
        }

        @SuppressWarnings("unchecked")
        public T next() {
            if (hasNext())
                return (T) element[index++];
            else
                throw new NoSuchElementException();
        }

        @Override
        public int nextIndex() {
            return index + 1;
        }

        @Override
        public int previousIndex() {
            if (hasPrevious())
                return index - 1;
            else
                return 0;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T e) {
            throw new UnsupportedOperationException();
        }
    }

    public static class Tester {
        public static void main(String[] args) {
            // ArrayList<String> sList = new ArrayList<>(5);
            // for (String s : "wu xue cheng so cool".split(" "))
            // sList.add(s);
            // System.out.println(sList);
            ArrayList<Integer> iList = new ArrayList<>(10);
            for (int i = 1; i < 10; ++i)
                iList.add(i);
            System.out.println(iList);
            ArrayList<String> sList = new ArrayList<>();
            sList.add("wuxuecheng");
            sList.add("so");
            sList.add("cool");
            sList.add("very");
            for (String s : sList)
                System.out.println(s);
            Iterator<String> iter = sList.iterator();
            ListIterator<?> listIter = sList.listIterator();
            for (int i = 0; i < 4; ++i) {
                // print("iter.next()" + iter.next());
                System.out.println("listItr.nextIndex()" + listIter.nextIndex());
                System.out.println("listIter.next()" + listIter.next());
                System.out.println("listIter.previousIndex()" + listIter.previousIndex());
            }
            // for (String s : "wu xue cheng so cool".split(" "))
            // System.out.println(s);
        }
    }
}

class ListUnderFlowException extends Exception {
    private static final long serialVersionUID = -3387516993124229948L;

    public ListUnderFlowException() {
    }

    public ListUnderFlowException(String message) {
        super(message);
    }

    public ListUnderFlowException(String message, Throwable cause) {
        super(message, cause);
    }

    public ListUnderFlowException(Throwable cause) {
        super(cause);
    }
}
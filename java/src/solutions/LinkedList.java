package solutions;

import java.util.Collection;

public class LinkedList<T>
{

    private Node<T> top;

    public LinkedList()
    {
        top = new Node<T>();
    }

    public LinkedList(Collection<? extends T> c)
    {

    }

    public boolean add(T e)
    {
        Node<T> newNode = new Node<T>(e);
        newNode.next = top.next;
        top.next = newNode;
        return true;
    }

    public boolean add(int index, T e)
    {
        Node<T> previous = top;
        while (index-- > 0)
            previous = previous.next;
        Node<T> newNode = new Node<T>(e);
        newNode.next = previous.next;
        previous.next = newNode;
        return true;
    }

    public boolean remove(int index)
    {
        Node<T> previous = top;
        while (index-- > 0)
            previous = previous.next;
        previous.next = previous.next.next;
        return true;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        Node<T> temp = top.next;
        sb.append("[");
        while (temp.next != null)
        {
            sb.append(temp.data + "->");
            temp = temp.next;
        }
        sb.append(temp.data + "]");
        return sb.toString();
    }

    private class Node<T>
    {
        T data;
        Node<T> next;

        public Node(T data)
        {
            this.data = data;
            this.next = null;
        }

        public Node()
        {
            this.data = null;
            this.next = null;
        }

    }

    public static void main(String[] args)
    {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("wuxuecheng");
        ll.add("so cool");
        ll.add("yes");
        System.out.println(ll);
    }
}
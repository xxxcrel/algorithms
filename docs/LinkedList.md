链表
===========
`定义`:链表是一种递归的数据结构,它或者为`空`(null), 或者是一个指向一个`节点`(node)的应用(在C语言中使用指针,java则是`非直接实现`的链表),该节点含有一个泛型的元素和一个指向另一条链表的应用.

- 单链表
    * C语言实现
        > 程序API: [list.h](../c/list/list.h)
        ```C
        #ifndef LIST_H
        #define LIST_H
        #include <stdbool.h>
        typedef char Item; //模拟实现简单泛型概念

        struct Node;
        typedef struct Node *PtrToNode;
        typedef PtrToNode Position;
        typedef PtrToNode List;

        List makeEmpty(List list);
        bool isLast(Position pos, List list);
        bool isEmpty(List list);
        Position findPrevious(List list, Item x);
        Position find(List list, Item x);
        Item delete (List list, Item x);
        void insert(List list, Item x, Position pos);
        void insertToTail(List list, Item x);
        void insertToHead(List list, Item x);
        void printList(List list);
        void deleteList(List list);
        List reverse(List list);

        #endif
        ```
        > 实现例程: [SingleLinkedList.c](../c/list/SingleLinkedList.c)
        ```c
        #include "list.h"
        #include <stdio.h>
        #include <stdlib.h>
        #include <stdbool.h>
        //带头节点的单链表

        struct Node
        {
            Item element;
            PtrToNode next;
        };

        List makeEmpty(List list)
        {
            if (NULL != list)
                list->next = NULL;
            return list;
        }

        bool isEmpty(List list)
        {
            return list->next == NULL;
        }

        //list没有使用
        bool isLast(Position pos, List list)
        {
            return pos->next == NULL;
        }

        Position find(List list, Item x)
        {
            if (!isEmpty(list))
            {
                Position pos = list->next;
                while (pos && pos->element != x)
                    pos = pos->next;
                return pos;
            }
            return NULL;
        }
        //找到list中匹配x的结点并返回他的前驱结点位置指针, 否者返回最后一个结点位置;
        Position findPrevious(List list, Item x)
        {
            PtrToNode pre = list;
            while (pre->next && pre->next->element != x)
                pre = pre->next;
            return pre;
        }
        //删除表中匹配x的节点
        Item delete (List list, Item x)
        {
            PtrToNode pre, tmpNode;
            Item deleteItem;

            pre = findPrevious(list, x);
            if (!isLast(list, pre))
            {
                tmpNode = pre->next;
                deleteItem = pre->next->element;
                pre->next = pre->next->next;
                free(tmpNode);
            }
            return deleteItem;
        }
        //将x插入到list中pos位置后
        void insert(List list, Item x, Position pos)
        {
            Position newNode;
            newNode = (List)malloc(sizeof(struct Node));
            if (newNode == NULL)
                printf("No space for allocation!!");
            else
            {
                newNode->element = x;
                newNode->next = pos->next;
                pos->next = newNode;
            }
        }

        void insertToHead(List list, Item x)
        {
            //插入到带头结点链表的头部
            insert(list, x, list);
        }

        void insertToTail(List list, Item x)
        {
            Position last = list;
            while (last->next != NULL)
                last = last->next;
            insert(list, x, last);
        }

        void printList(List list)
        {
            PtrToNode iter = list->next;
            while (iter != NULL)
            {
                if (iter->next == NULL)
                    printf("%c\n", iter->element);
                else
                    printf("%c->", iter->element);
                iter = iter->next;
            }
        }
        //不管是否为带头结点的链表
        void deleteList(List list)
        {
            PtrToNode header = list;
            PtrToNode nextNode = list->next;
            while (header && nextNode)
            {
                free(header);
                header = nextNode;
                nextNode = nextNode->next;
            }
            free(header);
        }
        //在给定链表list中直接倒转(假定该表含有头结点)
        List reverse(List list)
        {
            PtrToNode first = list->next; //从第一个结点开始
            PtrToNode iter = first->next; //反向迭代器
            while (iter)
            {
                // printf("first = %c, iter = %c\n", first->element, iter->element);
                first->next = iter->next;
                iter->next = list->next;
                list->next = iter;
                iter = first->next;
                // printList(list);
            }
            return list;
        }
        ```

    * java语言实现
        > API
        ```Java
        int size();
        boolean isEmpty();
        void add(Item item);
        Item remove(int index);
        void addFirst(Item item);
        void addLast(Item item);
        String toString();//Override Object's toString()
        ```
        > [LinkedList.java](../java/src/solutions/fundamentals/LinkedList.java)
        ```Java
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

        }
        ```
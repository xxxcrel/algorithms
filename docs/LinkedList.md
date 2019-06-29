链表
===========
`定义`:链表是一种递归的数据结构,它或者为`空`(null), 或者是一个指向一个`节点`(node)的应用(在C语言中使用指针,java则是`非直接实现`的链表),该节点含有一个泛型的元素和一个指向另一条链表的应用.

- 单链表
    * C语言
        > API: [list.h](../c/list/list.h)
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
        > ### 实现: [SingleLinkedList.c](../c/list/SingleLinkedList.c)

    * Java
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
        > ### 实现：[SingleLinkedList.java](../java/src/solutions/fundamentals/SingleLinkedList.java)
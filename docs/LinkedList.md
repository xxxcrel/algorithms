链表
===========
`定义`:链表是一种递归的数据结构,它或者为`空`(null), 或者是一个指向一个`节点`(node)的应用(在C语言中使用指针,java实现则是`非直接实现`方式的链表),该节点含有一个泛型的元素和一个指向另一条链表的应用.

- 单链表(`本例中C语言实现采用Dummy Node技巧(即带头结点的链表), Java实现不采用`)
    * C语言
        > API: [list.h](../c/list/list.h)
        ```C
        #ifndef LIST_H
        #define LIST_H
        #include <stdbool.h>
        typedef int Item; //模拟实现简单泛型概念

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
        void deleteList(List list);

        #endif
        ```
        > Node结构
        ```C
        struct Node
        {
            Item element;
            PtrToNode next;
        };
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
        >Node类
        ```Java
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
        ```
        > ### 实现：[SingleLinkedList.java](../java/src/solutions/fundamentals/SingleLinkedList.java)
    * 相关练习(实践证明理论)
        1. 书中对应练习
            - 数据结构与算法分析(C语言版)
                - 编写一个非递归过程以O(N)时间反转单链表
                    ```C
                    //在给定链表list中直接倒转(假定该表含有头结点)
                    List reverse(List list)
                    {
                        PtrToNode top = list->next;//如不含头结点则:top = list;
                        PtrToNode last = top;//反转后的尾部
                        PtrToNode iter = top->next;//从第二个结点开始反转
                        while (iter)
                        {
                            last->next = iter->next;//尾部下降一格
                            iter->next = top;//迭代器当前结点上升一格
                            top = iter;//top保持最高位置
                            iter = last->next;//迭代器继续
                        }
                        list->next = top;//如不含头结点则:list = top;
                        return list;
                    }
                    ```
                - 给你一个链表L和另一个链表P, 它们包含以升序排列的整数. 操作printLots(L, P)将打印L中那些由P所指定的位置上的元素. 例如p = 1, 3, 4, 6, 那么, L中的第1,第3, 第4, 第6个元素被打印出来. 写出过程printLots(List, List). 你应该只使用基本的表操作.
                    ```C
                    //假定l和p均含有头结点且表中数据为升序的整数(以0开始计数的法则)
                    void printLots(List l, List p)
                    {
                        Position currentL = l->next;
                        Position iter = p->next;
                        int currentPosElement = 0;
                        //对p进行迭代
                        while (iter)
                        {
                            //p表中两个数据之间的差:如0-1, 1-3, 3-5...
                            for (int i = currentPosElement; i < iter->element; ++i)
                            {
                                currentL = currentL->next;
                                if (currentL == NULL)
                                    printf("OutOfBounds\n");
                            }
                            //打印l表中对应p表整数位置的元素
                            printf("%d ", currentL->element);
                            //记录表p的当前结点的整数数据
                            currentPosElement = iter->element;
                            iter = iter->next;
                        }
                        printf("\n");
                    }
                    ```

            - 算法4e(书中相关题目不符合我当前封装的List, 故此不作答)
                - 略
        2. LeetCode相关练习(初级)
            - 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。现有一个链表 -- head = [4,5,1,9]，它可以表示为:4->5->1->9  
                C:
                ```C
                /**
                    * Definition for singly-linked list.
                * struct ListNode {
                *     int val;
                *     struct ListNode *next;
                * };
                */
                void deleteNode(struct ListNode* node) {
                    struct ListNode *tmp = node->next;
                    node->val = node->next->val;
                    node->next = node->next->next;
                    free(tmp);
                }
                ```  
                Java:  
                ```Java
                /**
                * Definition for singly-linked list.
                * public class ListNode {
                *     int val;
                *     ListNode next;
                *     ListNode(int x) { val = x; }
                * }
                */
                class Solution {
                    public void deleteNode(ListNode node) {
                        node.val = node.next.val;
                        node.next = node.next.next;
                    }
                }
                ```
            - 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
            示例：
            给定一个链表: 1->2->3->4->5, 和 n = 2.
            当删除了倒数第二个节点后，链表变为 1->2->3->5.
            说明：
            给定的 n 保证是有效的。
            进阶：
            你能尝试使用一趟扫描实现吗？  
                C:  
                ```C
                /**
                 * Definition for singly-linked list.
                * struct ListNode {
                *     int val;
                *     struct ListNode *next;
                * };
                */
                //进阶答案:利用前后指针,last先走n步, pre再和last同走,当last到末尾时,pre正好是倒数第 n 个结点的前驱结点(这题很有趣, 还有处理边缘值如([1, 2], 2), ([1], 1))
                struct ListNode* removeNthFromEnd(struct ListNode* head, int n){
                    struct ListNode *pre = head;
                    struct ListNode *last = head;
                    for(int i = 0; i < n; ++i)
                        last = last->next;
                    //处理边缘情形
                    if(last == NULL)//说明删除正数第一个位
                    {
                        //只有一个结点
                        if(head->next == NULL)
                            head = NULL;
                        else
                            head = head->next;   
                    }
                    else
                    {
                        //正常情况  
                        for(; last->next; last = last->next, pre = pre->next)
                            ;
                        pre->next = pre->next->next;
                    }
                    return head;
                }
                ```  
                Java:  
                ```Java
                /**
                * Definition for singly-linked list.
                * public class ListNode {
                *     int val;
                *     ListNode next;
                *     ListNode(int x) { val = x; }
                * }
                */
                class Solution {
                    public ListNode removeNthFromEnd(ListNode head, int n) {
                        ListNode pre = head;
                        ListNode last = head;
                        for(int i = 0; i < n; ++i)
                            last = last.next;
                        if(last == null)
                        {
                            if(head.next == null)
                                head = null;
                            else
                                head = head.next;
                        }
                        else
                        {
                            while(last.next != null)
                            {
                                last = last.next;
                                pre = pre.next;
                            }
                            pre.next = pre.next.next;
                        }
                        return head;
                    }
                }
                ```
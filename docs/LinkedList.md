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
                        PtrToNode iter = list->next;//若无头结点则:iter = list;
                        PtrToNode nextIter;
                        PtrToNode newHead = NULL;
                        while(iter)
                        {
                            nextIter = iter->next;//nextIter保存下一个结点
                            iter->next = newHead;//iter当前所在结点暂时成为新头结点
                            newHead = iter;
                            iter = nextIter;//继续迭代反转
                        }
                        list->next = newHead;//无头结点则:list = newHead;
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
            ```C
            /**
            * c语言
            * Definition for singly-linked list.
            * struct ListNode {
            *     int val;
            *     struct ListNode *next;
            * }; 
            */  
            ```  
            ```Java
            /**
             * Java
             * Definition for singly-linked list.
             * public class ListNode {
             *     int val;
             *     ListNode next;
             *     ListNode(int x) { val = x; }
             * }
             */
            ```
            - 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。现有一个链表 -- head = [4,5,1,9]，它可以表示为:4->5->1->9  
                C:
                ```C
                
                void deleteNode(struct ListNode* node) {
                    struct ListNode *tmp = node->next;
                    node->val = node->next->val;
                    node->next = node->next->next;
                    free(tmp);
                }
                ```  
                Java:  
                ```Java
                
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
            - 反转一个单链表(递归方式)  
                C:  
                ```C

                struct ListNode* recursiveReverse(struct ListNode* newHead, struct ListNode* iter)
                {
                    if(iter == NULL)
                        return newHead;
                    struct ListNode* nextIter = iter->next;
                    iter->next = newHead;
                    newHead = iter;
                    return recursiveReverse(newHead, nextIter);
                    
                }
                struct ListNode* reverseList(struct ListNode* head)
                {
                    return recursiveReverse(NULL, head);
                }
                ```  
                Java:
                ```Java

                class Solution {
                    public ListNode reverseList(ListNode head) {
                        return recursiveReverse(null, head);
                    }
                    
                    public ListNode recursiveReverse(ListNode newHead, ListNode iter)
                    {
                        if(iter == null)
                            return newHead;
                        ListNode nextIter = iter.next;
                        iter.next = newHead;
                        newHead = iter;
                        return recursiveReverse(newHead, nextIter);
                    }
                }
                ```  
            - 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
                示例：
                输入：1->2->4, 1->3->4
                输出：1->1->2->3->4->4  
                C:  
                ```C  

                struct ListNode* mergeTwoLists(struct ListNode* l1, struct ListNode* l2){
                    struct ListNode *head = (struct ListNode*)malloc(sizeof(struct ListNode));
                    struct ListNode *iter = head;
                    while(l1 != NULL && l2 != NULL)
                    {
                        if(l1->val < l2->val)
                        {
                            iter = iter->next = l1;
                            l1 = l1->next;
                        }
                        else
                        {
                            iter = iter->next = l2;
                            l2 = l2->next;
                        }
                    }
                    iter->next = l1 == NULL ? l2 : l1;
                    return head->next;
                }
                ```  
                Java:  
                ```Java

                class Solution {
                    //边缘情形(null, null), (l1, null), (null, l2)
                    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
                        ListNode head = new ListNode(0);//先做一个Dummy Node
                        ListNode iter = head;//用迭代器来链接l1, l2
                        while(l1 != null && l2 != null)
                        {
                            if(l1.val < l2.val)
                            {
                                iter.next = l1;
                                iter = iter.next;
                                l1 = l1.next;
                            }
                            else
                            {
                                iter.next = l2;
                                iter = iter.next;
                                l2 = l2.next;
                            }
                        }
                        // iter.next = (l1 == null && l2 == null) ? null : l1 == null ? l2 : l1;
                        iter.next = l1 == null ? l2 : l1;//这里很有趣,正确处理三种边缘情形,比上面语句更加精炼
                        return head.next;
                    }
                }
                ```
            - 请判断一个链表是否为回文链表。
               示例 1:
               输入: 1->2
               输出: false
               示例 2:
               输入: 1->2->2->1
               输出: true
               进阶：
               你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？  
               C:
               ```C
               struct ListNode *reverse(struct ListNode *head)
               {
                   struct ListNode *iter = head, *newHead = NULL;
                   while (iter)
                   {
                       struct ListNode *nextIter = iter->next;
                       iter->next = newHead;
                       newHead = iter;
                       iter = nextIter;
                   }
                   return newHead;
               }
               bool isPalindrome(struct ListNode *head)
               {
                   struct ListNode *fast, *slow;
                   fast = slow = head;
                   if (fast == NULL || fast->next == NULL)
                       return true;
                   while (fast->next != NULL && fast->next->next != NULL)
                   {
                       slow = slow->next;
                       fast = fast->next->next;
                   }
                   slow->next = reverse(slow->next);
                   struct ListNode *secondHead = slow->next;
                   while (secondHead)
                   {
                       if (head->val != secondHead->val)
                           return false;
                       head = head->next;
                       secondHead = secondHead->next;
                   }
                   return true;
               }
               ```  
               Java:  
               ```Java

               class Solution {
                   public boolean isPalindrome(ListNode head) {
                       ListNode fast, slow;
                       fast = slow = head;
                       if(fast == null || fast.next == null)
                           return true;
                       while(fast.next != null && fast.next.next != null)
                       {
                           fast = fast.next.next;
                           slow = slow.next;
                       }
                       slow.next = reverse(slow.next);
                       ListNode secondHead = slow.next;
                       while(secondHead != null)
                       {
                           if(head.val != secondHead.val)
                               return false;
                           head = head.next;
                           secondHead = secondHead.next;
                       }
                       return true;
                   }

                   public ListNode reverse(ListNode head)
                   {
                       ListNode iter = head;
                       ListNode newHead = null;
                       while(iter != null)
                       {
                           ListNode nextIter = iter.next;
                           iter.next = newHead;
                           newHead = iter;
                           iter = nextIter;
                       }
                       return newHead;
                   }
               }
               ```

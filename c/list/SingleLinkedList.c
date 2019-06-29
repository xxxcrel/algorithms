#include "list.h"
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
//带右节点的单链表

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
            printf("%d\n", iter->element);
        else
            printf("%d->", iter->element);
        iter = iter->next;
    }
}
//不管是否为带头结点
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
//演示如何使用该例程
int main()
{
    // List list = (List)malloc(sizeof(struct Node));
    // if (list == NULL)
    //     printf("allocate failure\n");
    // list = makeEmpty(list);
    // Item is[10] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
    // for (int i = 0; i < 10; i++)
    //     insertToTail(list, is[i]);
    // insertToHead(list, 'H');
    // printList(list);
    // printf("delete 'd' from list\n");
    // delete (list, 'd');
    // printList(list);
    // deleteList(list);

    List l = (List)malloc(sizeof(struct Node));
    List p = (List)malloc(sizeof(struct Node));
    l = makeEmpty(l);
    p = makeEmpty(p);
    Item lData[6] = {3, 4, 5, 6, 7, 8};
    Item pData[3] = {1, 2, 5};
    for (int i = 0; i < 6; ++i)
        insertToTail(l, lData[i]);
    for (int i = 0; i < 3; ++i)
        insertToTail(p, pData[i]);
    printList(l);
    printList(p);
    printLots(l, p);
    return 0;
}
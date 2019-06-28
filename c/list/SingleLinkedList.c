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
            printf("%c\n", iter->element);
        else
            printf("%c->", iter->element);
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

//演示如何使用该例程
int main()
{
    List list = (List)malloc(sizeof(struct Node));
    if (list == NULL)
        printf("allocate failure\n");
    list = makeEmpty(list);
    Item is[10] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
    for (int i = 0; i < 10; i++)
        insertToTail(list, is[i]);
    insertToHead(list, 'H');
    printList(list);
    printf("delete 'd' from list\n");
    delete (list, 'd');
    printList(list);
    printf("reverse list\n");
    list = reverse(list);
    printList(list);
    deleteList(list);

    return 0;
}
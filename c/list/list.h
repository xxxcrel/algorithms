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
void printList(List list);
void deleteList(List list);

#endif
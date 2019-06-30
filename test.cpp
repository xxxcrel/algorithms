#include <vector>
#include <iostream>
#include <string>

int main(int argc, char *args[])
{
    std::vector<std::string> v = {"hello", "world"};
    v.push_back("hhh");
    for (std::string s : v)
        std::cout << s << std::endl;
    return 0;
}
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

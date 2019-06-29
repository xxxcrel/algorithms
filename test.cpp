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
#include <iostream>

using namespace std;

class apple{
    private:
    int count;
    public:
    apple(int value);
    int getCount();
    };

apple::apple(int value)
{
    count = value;
}

int apple::getCount()
{
    return count;
}

#include <iostream>

using namespace std;

class orange{
    private:
    int count;
    public:
    orange(int value);
    int getCount();
    };

orange::orange(int value)
{
    count = value;
}

int orange::getCount()
{
    return count;
}

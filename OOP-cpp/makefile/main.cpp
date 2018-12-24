#include <iostream>
#include "apple.h"
#include "orange.h"

using namespace std;

int main()
{
    apple myApple(1);
    orange myOrange(2);

    cout<<"Apple : "<< myApple.getCount() <<endl;
    cout<<"Orange : "<<myOrange.getCount() <<endl;
    return 0;
};

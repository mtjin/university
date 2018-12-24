#include <iostream>
#include <string>
#include "MyArrayList.h"

using namespace std;

int main()
{
    MyArrayList<int> myIntList;
    MyArrayList<double> doubleList(100);

    doubleList.addElement(1.4);
    doubleList.printIt();

    myIntList.addElement(123);
    for(int i = 0; i<35; i++){
        myIntList.addElement(i);
    }

    myIntList.printIt();
    myIntList.addElement(1);
    myIntList.printIt();

    return 0;
}
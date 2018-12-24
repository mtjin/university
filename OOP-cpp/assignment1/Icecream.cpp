#include "Icecream.h"
#include <string>
#include <iostream>

using namespace std;

IceCream::IceCream(bool iscone, int num, string name):dessertItem(name)
{
    setType(iscone);
    setNumberOfScoops(num);
    dessertItem::setName(name);
}
void IceCream::setNumberOfScoops(int num)
{
    numberOfScoops = num;
}
int IceCream::getNumberOfScoops()
{
    return numberOfScoops;
}
void IceCream::setType(bool iscone)
{
    isCone = iscone;
}
bool IceCream::getType()
{
    return isCone;
}
void IceCream::print()
{
    cout << dessertItem::getName() << endl;
    cout << getNumberOfScoops() << " scoops";
    if (getType())
    {
        cout << " + cone" << endl;
    }else{
        cout<<""<<endl;
    }
   
}
double IceCream::getCost()
{
    double coneprice = 0.5;
    if (getType())
    {
        return (getNumberOfScoops() * 1.00 + coneprice);
    }
    else
    {
        return getNumberOfScoops() * 1.00;
    }
}

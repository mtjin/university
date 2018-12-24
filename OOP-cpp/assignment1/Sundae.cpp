#include "Sundae.h"
#include <string>
#include <iostream>

using namespace std;

Sundae::Sundae(double cost, bool iscone, int num, string name):IceCream(iscone,num,name)
{
    costOfTooping = cost;
    IceCream::setType(iscone);
    IceCream::setNumberOfScoops(num);
    dessertItem::setName(name);
}

void Sundae::setCostOfTopping(double cost)
{
    costOfTooping = cost;
}
double Sundae::getCostOfTooping()
{
    return costOfTooping;
}
void Sundae::print()
{
    cout << dessertItem::getName() << endl;
    cout << IceCream::getNumberOfScoops() << " scoops" << endl;
    if (IceCream::getType())
    {
        cout << "+ cone" << endl;
    }
   
    cout<<"+" << getCostOfTooping() << " toppings" << endl;
}
double Sundae::getCost()
{
    if (IceCream::getType())
    {
        return getNumberOfScoops() * 1.00 + 0.5 + getCostOfTooping();
    }
    else
    {
        return getNumberOfScoops() * 1.00 + getCostOfTooping();
    }
}

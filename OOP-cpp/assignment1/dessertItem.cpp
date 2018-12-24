#include "dessertItem.h"
#include <string>
#include <iostream>

using namespace std;

dessertItem::dessertItem(string dessertName)
{
    setName(dessertName);
}

string dessertItem::getName()
{
    return dessertName;
}
void dessertItem::setName(string name)
{
    dessertName = name;
}

void dessertItem::print()
{
    cout << dessertName << endl;
}


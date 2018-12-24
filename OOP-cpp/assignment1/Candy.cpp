#include "Candy.h"
#include <string>
#include <iostream>

using namespace std;

Candy::Candy(double weight, double price, string name):dessertItem(name)
{
    setWeight(weight);
    setPrice(price);
    dessertItem::setName(name);
}

void Candy::setWeight(double weight)
{
    candyWeight = weight;
}
double Candy::getWeight()
{
    return candyWeight;
}
void Candy::setPrice(double price)
{
    pricePerPound = price;
}
double Candy::getPrice()
{
    return pricePerPound;
}
void Candy::print()
{
    cout << dessertItem::getName() << endl;
    cout << getWeight() << " pounds @" << getPrice() << " per pound" << endl;
}
double Candy::getCost()
{
    return candyWeight * pricePerPound;
}

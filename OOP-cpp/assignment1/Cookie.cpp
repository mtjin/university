#include "Cookie.h"
#include <string>
#include <iostream>

using namespace std;

Cookie::Cookie(int num, double price, string name):dessertItem(name)
{
    setNumberOfCookies(num);
    setPricePerDozen(price);
    dessertItem::setName(name);
}

void Cookie::setNumberOfCookies(int num)
{
    numberOfCookies = num;
}
double Cookie::getNumberOfCookies()
{
    return numberOfCookies;
}
void Cookie::setPricePerDozen(double price)
{
    pricePerDozen = price;
}
double Cookie::getPricePerDozen()
{
    return pricePerDozen;
}
void Cookie::print()
{
    cout << dessertItem::getName() << endl;
    cout << getNumberOfCookies() << " cookies @" << getPricePerDozen() << " per dozen" << endl;
}
double Cookie::getCost()
{
    return numberOfCookies/12 * pricePerDozen;
}

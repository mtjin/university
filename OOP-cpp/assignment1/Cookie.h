#ifndef COOKIE_H
#define COOKIE_H

#include "dessertItem.h"
#include <string>

using namespace std;

class Cookie: public dessertItem
{
    private:
        int numberOfCookies;
        double pricePerDozen;

    public:
        Cookie(int, double, string);

        void setNumberOfCookies(int);
        double getNumberOfCookies();
        void setPricePerDozen(double);
        double getPricePerDozen();
        void print();
        double getCost();
};

#endif
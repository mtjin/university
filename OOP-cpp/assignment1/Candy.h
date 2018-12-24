#ifndef CANDY_H
#define CANDY_H

#include "dessertItem.h"
#include <string>

using namespace std;

class Candy: public dessertItem
{
    private:
        double candyWeight, pricePerPound;

    public:
        Candy(double, double, string);
        void setWeight(double);
        double getWeight();
        void setPrice(double);
        double getPrice();
        void print();
        double getCost();
};

#endif
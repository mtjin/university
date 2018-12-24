#ifndef ICECREAM_H
#define ICECREAM_H

#include "dessertItem.h"
#include <string>

using namespace std;

class IceCream: public dessertItem{
    private:
        int numberOfScoops;
        bool isCone;

    public:
    IceCream(bool, int, string);
    void setNumberOfScoops(int);
    int getNumberOfScoops();
    void setType(bool);
    bool getType();
    void print();
    double getCost();

};

#endif
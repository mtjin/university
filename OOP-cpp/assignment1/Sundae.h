#ifndef SUNDAE_H
#define SUNDAE_H

#include "Icecream.h"
#include <string>

using namespace std;

class Sundae: public IceCream{
    private:
        double costOfTooping;

    public:
        Sundae(double, bool, int, string);

        void setCostOfTopping(double);
        double getCostOfTooping();
        void print();
        double getCost();
};

#endif
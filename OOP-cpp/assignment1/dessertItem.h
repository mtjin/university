#ifndef DESSERTITEM_H
#define DESSERTITEM_H

#include <string>
#include <iostream>

using namespace std;

class dessertItem{
    private:
        string dessertName;

    public:
        dessertItem(string);

        string getName();
        void setName(string);
        virtual double getCost() = 0;
        virtual void print();
};

#endif
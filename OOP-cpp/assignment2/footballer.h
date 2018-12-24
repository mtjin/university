#ifndef FOOTBALLER_H
#define FOOTBALLER_H

#include <iostream>
#include "person.h"

using namespace std;

class footballer: public person{
    public:
        footballer(string, int);
        void playFootball();
};

#endif
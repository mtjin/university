#ifndef MATHTEACHER_H
#define MATHTEACHER_H

#include <iostream>
#include "person.h"

using namespace std;

class mathTeacher: public person{
    public:
        mathTeacher(string, int);
        void teachMath();

};

#endif
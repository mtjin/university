#ifndef PERSON_H
#define PERSON_H

#include <iostream>

using namespace std;

class person{
    private:
        string profession;
        int age;

    public:
        person(string, int);
        void setProfession(string);
        void setAge(int);
        string getProfession();
        int getAge();
        void display();
        void walk();
        void talk();
};

#endif
#include "mathTeacher.h"
#include <string>
#include <iostream>

using namespace std;
mathTeacher::mathTeacher(string name, int age):person(name, age){
    person::setProfession(name);
    person::setAge(age);
}
void mathTeacher::teachMath(){
    cout<<"I can teach Maths"<<endl;
}
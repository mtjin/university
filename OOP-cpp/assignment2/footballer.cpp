#include "footballer.h"
#include <string>
#include <iostream>

using namespace std;

footballer::footballer(string name, int age):person(name, age){
    person::setProfession(name);
    person::setAge(age);
}
void footballer::playFootball(){
    cout<<"I can play Football"<<endl; 
}
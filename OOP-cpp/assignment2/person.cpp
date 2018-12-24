#include "person.h"
#include <string>
#include <iostream>

using namespace std;

person::person(string name, int age){
    profession = name;
    this->age = age;
    //cout<<"My profession is "+ getProfession()<<endl;
    //cout<<"My age is: "+ getAge()<<endl;
}
void person::setProfession(string name){
    profession = name;
}
void person::setAge(int age){
    this->age = age;
}
string person::getProfession(){
    return profession;
}
int person::getAge(){
    return this->age;
}
void person::display(){
    cout<<"My profession is "<< getProfession()<<"\nMy age is: "<<getAge()<<endl;
    //cout<<"My age is: "+ getAge()<<endl;
    person::walk();
    person::talk();
}
void person::walk(){
    cout<<"I can walk"<<endl;
}
void person::talk(){
    cout<<"I can talk"<<endl;
}
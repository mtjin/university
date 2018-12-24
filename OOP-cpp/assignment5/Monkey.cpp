#include <string>
#include <fstream>
#include <iostream>
#include <sstream>
#include <ctype.h>
#include <stdlib.h>
#include "animal.h"
using namespace std;

Monkey::Monkey(){
    wild = false;
    home ="";
    endangered = false;
    age =0;
}

    void Monkey::readInfo(){
        string notUse, names, colors, ages, wilds, homes, endangereds;
        fstream inFile;
        inFile.open("Monkey.csv");

        getline(inFile, notUse);
        getline(inFile, names, ','); 
        getline(inFile, colors, ',');
        getline(inFile, ages, ',');
        getline(inFile, wilds, ',');
        getline(inFile, homes, ','); 
        getline(inFile, endangereds, ','); 
        inFile.close();

        int tmpAges = atoi(ages.c_str());

        bool typeWilds;
        if(wilds =="TRUE"){
            typeWilds = true;
        }
        if(wilds == "FALSE"){
            typeWilds = false;
        }

        bool typeEndangereds;
        if(endangereds =="TRUE"){
            typeEndangereds = true;
        }
        if(endangereds == "FALSE"){
            typeEndangereds = false;
        }

        try{
            if(isdigit(tmpAges)){
                throw -1;
            }else{
                
            }
        }catch(int a){
            cout<<"Error"<<endl;
        }
        setName(names);
        setColor(colors);
        setAge(tmpAges);
        setWild(typeWilds);
        setHome(homes);
        setEndangered(typeEndangereds);
    }
    void Monkey::printInfo(){
        bool tempWild = getWild();
        bool tempEndangered = getEndangered();
    
        cout << "Monkey Information:\n";
        cout << "Name: " << getName() << endl;
        cout << "Color: " << getColor() << endl;
        cout << "Age: " << getAge() << endl;
        cout << "Wild: " << convertBool(tempWild) << endl;
        cout << "Home: " << getHome() << endl;
        cout << "Endangered: " << convertBool(tempEndangered) << endl;
    }
    string Monkey::convertBool(bool _value){
        if(_value){
            return "TRUE";
        }
        else{
            return "FALSE";
        }
    }
    void Monkey::changeEndangered(){
        if(endangered){
            setEndangered(false);
        }
        else{
            setEndangered(true);
        }
    }
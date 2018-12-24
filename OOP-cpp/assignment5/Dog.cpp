#include <string>
#include <fstream>
#include <iostream>
#include <sstream>
#include <ctype.h>
#include <stdlib.h>
#include <typeinfo>
#include "animal.h"
using namespace std;

Dog::Dog(){
    breed = "";
    age = 0;
    weight = 0;
}

void Dog::readInfo(){
    
        string notUse, names, breeds, ages, colors, weights;
        fstream inFile;
        inFile.open("Dog.csv");

        getline(inFile, notUse);
        getline(inFile, names, ','); 
        getline(inFile, breeds, ',');
        getline(inFile, ages, ',');
        getline(inFile, colors, ',');
        getline(inFile, weights, ','); 
        inFile.close();

        int tmpAges = atoi(ages.c_str());
        int tmpWeights = atoi(weights.c_str());
        
        try{
            if(isdigit(tmpAges)){
                throw -1;
            }else{
                
            }
        }catch(int a){
            cout<<"Error"<<endl;
        }

        try{
            if(isdigit(tmpWeights)){
                throw -1;
            }else{
                
            }
        }catch(int a){
            cout<<"Error"<<endl;
        }
        setName(names);
        setBreed(breeds);
        setAge(tmpAges);
        setColor(colors);
        setWeight(tmpWeights);
    }

    void Dog::printInfo(){
        try{
        cout<<"Dog Information:"<<endl;
        cout<<"Name: "<<getName()<<endl;
        cout<<"Breed: "<<getBreed()<<endl;
        cout<<"Age: "<<getAge()<<" (years)"<<endl;
        cout<<"Color: "<<getColor()<<endl;
        cout<<"Weight: "<<getWeight()<<" (pounds)"<<endl;
        }catch(exception e){
            cout<<"Error"<<endl;
        }
    }

    void Dog::subtractTen(){
        if(weight <= 10){
            cout<<"Error"<<endl;
        }
        else{
            weight = weight - 10;
        }
    }
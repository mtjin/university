#include <string>
#include <fstream>
#include <iostream>
#include <sstream>
#include <ctype.h>
#include <stdlib.h>
#include "animal.h"
using namespace std;
Horse::Horse(){
    maneColor ="";
    height = 0;
    age = 0;
}

    void Horse::readInfo(){
        string notUse, names, colors, maneColors, ages, heights;
        fstream inFile;
        inFile.open("Horse.csv");
        getline(inFile, notUse); 
        getline(inFile, names, ','); 
        getline(inFile, colors, ',');
        getline(inFile, maneColors, ',');
        getline(inFile, ages, ',');
        getline(inFile, heights, ','); 
        inFile.close();

        int tmpAges = atoi(ages.c_str());
        int tmpHeights = atoi(heights.c_str());
        
        try{
            if(isdigit(tmpAges)){
                throw -1;
            }else{
                
            }
        }catch(int a){
            cout<<"Error"<<endl;
        }

        try{
            if(isdigit(tmpHeights)){
                throw -1;
            }else{
                
            }
        }catch(int a){
            cout<<"Error"<<endl;
        }
        setName(names);
        setColor(colors);
        setManeColor(maneColors);
        setAge(tmpAges);
        setHeight(tmpHeights);
    }
    void Horse::printInfo(){
        cout << "Horse Information:\n";
        cout << "Name: " << getName() << endl;
        cout << "Color: " << getColor() << endl;
        cout << "Mane Color: " << getManeColor() << endl;
        cout << "Age: " << getAge() << " (years)" << endl;
        cout << "Height: " << getHeight() << " (hands)" << endl;

    }
    


#include <string>
#include <fstream>
#include <iostream>
#include <sstream>
#include <ctype.h>
#include <stdlib.h>
#include "animal.h"
using namespace std;
Lizard::Lizard(){
    habitat ="";
    isProtected = false;
    weight = 0;
}

    void Lizard::readInfo(){
        string notUse, names, colors, habitats, protecteds, weights;
        fstream inFile;
        inFile.open("Lizard.csv");

        getline(inFile, notUse);
        getline(inFile, names, ','); 
        getline(inFile, colors, ',');
        getline(inFile, habitats, ',');
        getline(inFile, protecteds, ',');
        getline(inFile, weights, ','); 
        inFile.close();
        
        int tmpWeights = atoi(weights.c_str());
        
        bool typeProtecteds;
        if(protecteds =="TRUE"){
            typeProtecteds = true;
        }
        if(protecteds == "FALSE"){
            typeProtecteds = false;
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
        setColor(colors);
        setHabitat(habitats);
        setProtected(typeProtecteds);
        setWeight(tmpWeights);
    }
    void Lizard::printInfo(){
        bool tempProtected = getProtected();
        cout << "Lizard information:\n";
        cout << "Name: " << getName() << endl;
        cout << "Color: " << getColor() << endl;
        cout << "Habitat: " << getHabitat() << endl;
        cout << "Protected: " << convertBool(tempProtected) << endl;
        cout << "Weight: " << getWeight() << " (ounces)" << endl;
    }
    string Lizard::convertBool(bool _value){
        if(_value){
            return "TRUE";
        }
        else{
            return "FALSE";
        }
    }
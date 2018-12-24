#include <string>
#include <fstream>
#include <iostream>
#include <sstream>
#include <ctype.h>
#include <stdlib.h>
#include "animal.h"
using namespace std;

Fish::Fish(){
    freshwater = false;
    habitat = "";
    predator = "";
}
    

    void Fish::readInfo(){
        string notUse, names, colors, freshWaters, habitats, predators;
        fstream inFile;
        inFile.open("Fish.csv");

        getline(inFile, notUse);
        getline(inFile, names, ','); 
        getline(inFile, colors, ',');
        getline(inFile, freshWaters, ',');
        getline(inFile, habitats, ',');
        getline(inFile, predators, ','); 
        inFile.close();

        bool typeWaters;
        if(freshWaters =="TRUE"){
            typeWaters = true;
        }
        if(freshWaters == "FALSE"){
            typeWaters = false;
        }

        bool typePredators;
        if(predators =="TRUE"){
            typePredators = true;
        }
        if(predators =="FALSE"){
            typePredators = false;
        }

        

        setName(names);
        setColor(colors);
        setFreshwater(typeWaters);
        setHabitat(habitats);
        setPredator(typePredators);
    }
    void Fish::printInfo(){
        cout << "Fish Information:\n";
        cout << "Name: " << getName() << endl;
        cout << "Color: " << getColor() << endl;
        cout << "Freshwater: " << convertBool(freshwater) << endl;
        cout << "Habitat: " << getHabitat() << endl;
        cout << "Predator: " << convertBool(predator) << endl;
    }
    string Fish::convertBool(bool _value){
        if (_value)
            return "TRUE";
        else
            return "FALSE";
    }



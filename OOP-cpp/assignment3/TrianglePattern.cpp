#include <iostream>
#include <string>
#include "TrianglePattern.h"

using namespace std;

TrianglePattern::TrianglePattern():ShapePattern(){
    this->height = 0;
}
TrianglePattern::TrianglePattern(int x){
    set_height(x);
}
void TrianglePattern::set_height(int x){
    this->height = x;
}
int TrianglePattern::patternHelper(int h){
    cout<<"The Right Triangle Pattern:(height = "<<this->height<<" )"<<endl;
    int tmp = h;
    for(int i= h; i>0; i--){
        
        for(int j=tmp; j>0; j--){
            cout<<ShapePattern::get_pattern();
        }
        tmp--;
        cout<<endl;
    }
    
    return 0;
}

void TrianglePattern::printPattern(){
    
    if(height>=0){
         patternHelper(height);
    }
    else{
        cout<<"Invaild Size!"<<endl;
    }
    cout<<endl;
}
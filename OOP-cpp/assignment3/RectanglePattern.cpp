#include <iostream>
#include <string>
#include "RectanglePattern.h"

using namespace std;

RectanglePattern::RectanglePattern():ShapePattern(){
    this->length = 0;
    this->width = 0;
}
RectanglePattern::RectanglePattern(int x, int y){
    set_length(x);
    set_width(y);
}
void RectanglePattern::set_length(int x){
    length = x;
}
void RectanglePattern::set_width(int y){
    width = y;
}
int RectanglePattern::patternHelper(int l, int w){
    cout<<"The Rectangle Pattern: (length = "<<this->length<<": width = "<<this->width<<" )"<<endl;
    for(int i = w; i>0; i--){
        int tmp = l;
        for(int j=0; j<tmp; j++){
            cout<<ShapePattern::get_pattern();
        }
        cout<<endl;
    }
    return 0;
    

}
void RectanglePattern::printPattern(){
    if(length>=0 && width>=0){
        patternHelper(length, width);
    }
    else{
        cout<<"Invaild Size!"<<endl;
    }
    cout<<endl;
}
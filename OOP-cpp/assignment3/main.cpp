#include <iostream>
#include <vector>
#include "ShapePattern.h"
#include "RectanglePattern.h"
#include "TrianglePattern.h"

using namespace std;

void pause_215(bool have_newline);
int main()
{
    const int SIZE = 4;
    vector<ShapePattern*> shapes(SIZE);
    RectanglePattern* R0 = new RectanglePattern;
    R0->set_length(12);
    R0->set_width(7);
    shapes[0] = R0;
    shapes[1] = new RectanglePattern(-7, 4);
    TrianglePattern* T0 = new TrianglePattern;
    T0->set_height(7);
    shapes[2] = T0;
    shapes[3] = new TrianglePattern(11);
    shapes[3]->set_pattern('0');

    for(int i = 0; i<SIZE; i++){
        shapes[i]->printPattern();
    }
    for(int i =0; i<SIZE; i++){
        delete shapes[i];
    }

    pause_215(false);
    return 0;
}

void pause_215(bool have_newline){
    if(have_newline){
        cin.ignore(256,'\n');
    }
    cout<<endl<<"Press ENTER to continue."<<endl;
    cin.ignore(256, '\n');
}
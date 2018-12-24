#include <iostream>
#include <string>
#include "ShapePattern.h"

using namespace std;

ShapePattern::ShapePattern(){
    set_pattern('*');
}
void ShapePattern::set_pattern(char c){
    pattern = c;
}
char ShapePattern::get_pattern() const{
    return pattern;
}



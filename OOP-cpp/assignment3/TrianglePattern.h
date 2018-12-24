#ifndef TRIANGLEPATTERN_H
#define TRIANGLEPATTERN_H

#include "ShapePattern.h"

class TrianglePattern : public ShapePattern
{
    public:
        TrianglePattern();

        TrianglePattern(int x);
    
        void set_height(int x);

        int patternHelper(int h);

        virtual void printPattern();

    private:
        int height; 
};
#endif
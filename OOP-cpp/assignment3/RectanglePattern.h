#ifndef RECTANGLEPATTERN_H
#define RECTANGLEPATTERN_H

#include "ShapePattern.h"

class RectanglePattern : public ShapePattern
{
    public:
        RectanglePattern();
        RectanglePattern(int x, int y);
        void set_length(int x);
        void set_width(int y);
        int patternHelper(int l, int w);
        virtual void printPattern();

    private:
        int length, width;
};
#endif
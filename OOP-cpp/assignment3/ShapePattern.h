#ifndef SHAPEPATTERN_H
#define SHAPEPATTERN_H

class ShapePattern{
    public:
        ShapePattern();
        void set_pattern(char c);
        char get_pattern() const;
        virtual void printPattern() = 0;

    private:
        char pattern;
};
#endif
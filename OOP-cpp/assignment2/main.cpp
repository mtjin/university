#include <iostream>
#include "person.h"
#include "mathTeacher.h"
#include "footballer.h"

using namespace std;

int main()
{
    mathTeacher teacher = mathTeacher("Teacher", 27);
    teacher.display();
    teacher.teachMath();

    footballer teacher2 = footballer("Footballer", 28);
    teacher2.display();
    teacher2.playFootball();

    return 0;
}
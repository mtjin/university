#include <stdio.h>

struct student{
	char* myname;
	int mynumber;
};
void swap(struct student *a, struct student *b){
	struct student tmp;
	tmp = *a;
	*a = *b;
	*b = tmp;
}

int main()
{
	struct student me;
	me.myname = "JSE";
	me.mynumber = 201404377;
	struct student teacher;
	teacher.myname = "JBK";
	teacher.mynumber = 0;
	printf("myname : %s\n", me.myname);
	printf("mynumber : %d\n", me.mynumber);
	printf("tname : %s\n", teacher.myname);
	printf("class : %d\n", teacher.mynumber);

	printf("[before] myname : %s, tname: %s\n", me.myname, teacher.myname);
	printf("[before] mynum : %d, tanum : %d\n", me.mynumber, teacher.mynumber);

	printf("swap Function call !!\n");
	swap(&me, &teacher);
	printf("[after] myname : %s, tname: %s\n", me.myname, teacher.myname);
	printf("[after] mynum : %d, tanum : %d\n", me.mynumber, teacher.mynumber);
}

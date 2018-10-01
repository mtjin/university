#ifndef __CQ_H__
#define __CQ_H__

#include <stdio.h>
#include <stdlib.h>

#include <unistd.h>

#define	BUFFER_SIZE 	10
#define	PRODUCE_ONCE	7

//define for text color
#define T_RED		"\x1b[31m"
#define T_GREEN		"\x1b[32m"
#define T_YELLOW	"\x1b[33m"
#define	T_DEFAULT	"\x1b[0m"



typedef struct{
	int 	buffer[BUFFER_SIZE];
	int 	front;
	int 	rear;
	int	size;
}CQ;
CQ cq;

void	initCQ();
int	insertItem(int );
int	takeItem();
int	isEmpty();
int	isFull();
#endif

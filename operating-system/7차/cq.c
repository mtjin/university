#include "cq.h"

void 	initCQ(){
	int i;
	
	cq.size = 0;
	cq.front = 0;
	cq.rear = 0;
	for(i = 0;i < BUFFER_SIZE;i++)
		cq.buffer[i] = -1;
}

int	insertItem(int item){
	if(isFull() == 0){
		cq.buffer[cq.front] = item;
		cq.front = (cq.front + 1) % BUFFER_SIZE;
		cq.size++;
		return 1;
	}
	return 0;
}

int	takeItem(){
	if(isEmpty() == 0){
		int item = cq.buffer[cq.rear];
		cq.rear = (cq.rear + 1) % BUFFER_SIZE;
		cq.size--;
		return item;
	}
	return -1;
}

int	isEmpty(){
	if(cq.size == 0){
		return 1;
	}
	return 0;
}

int	isFull(){
	if(cq.size == BUFFER_SIZE){
		return 1;
	}
	return 0;
}

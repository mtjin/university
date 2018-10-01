#ifndef __SEMAPHORE_H__
#define __SEMAPHORE_H__

#include <unistd.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include "cq.h"

#define PRODUCER_COUNT	5
#define CONSUMER_COUNT	2

#define PRODUCE_TIME	6
#define CONSUME_TIME	15

sem_t	consume_ok;
sem_t	produce_ok;
sem_t	buffer_lock;

int producerCount;
int consumerCount;

void init();
void* producer(void* );
void* consumer(void* );

#endif

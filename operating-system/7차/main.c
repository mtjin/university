#include "cq.h"
#include "semaphore.h"

int main(void){
	pthread_t producer_thread[PRODUCER_COUNT];
	int 	  producer_id[PRODUCER_COUNT];
	int 	  producer_state[PRODUCER_COUNT];

	pthread_t consumer_thread[CONSUMER_COUNT];
	int 	  consumer_id[CONSUMER_COUNT];
	int 	  consumer_state[CONSUMER_COUNT];

	int i;

	initCQ();
	init();

	for(i = 0; i < PRODUCER_COUNT ; i++){
		if(producer_id[i] = pthread_create(&producer_thread[i], NULL, producer, NULL)){
			printf("producer %d create error\n", i);
			exit(0);
		}
	}

	for(i = 0; i < CONSUMER_COUNT ; i++){
		if(consumer_id[i] = pthread_create(&consumer_thread[i], NULL, consumer, NULL)){
			printf("consumer %d create error\n", i);
			exit(0);
		}
	}
	
	for(i = 0; i < PRODUCER_COUNT ; i++)
		pthread_join(producer_thread[i], (void**)&producer_state[i]);
	
	for(i = 0; i < CONSUMER_COUNT ; i++)
		pthread_join(consumer_thread[i], (void**)&consumer_state[i]);

	sem_destroy(&buffer_lock);
	sem_destroy(&produce_ok);
	sem_destroy(&consume_ok);
}

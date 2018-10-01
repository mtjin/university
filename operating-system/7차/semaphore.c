#include "semaphore.h"

//선언된 세마포어 변수 이름: produce_ok, consume_ok, buffer_lock
void init(){
	producerCount = 0;
	consumerCount = 0;

	//소비는 생산이 될 때까진 못하므로 초기 세마포어 값은 0
	//이는 생산자가 세마포어 변수를 1증가시켜 소비할 수 있도록 하기 위함
	if(sem_init(&consume_ok, 0, 0) != 0){
		printf("%s[ERROR]Cannot init semaphore : consume_ok\n", T_RED);
		exit(0);
	}
	
	//생산은 동시에 최대 PRODUCE_ONCE만큼만 가능하도록 함
	if(sem_init(&produce_ok, 0, PRODUCE_ONCE) != 0){
		printf("%s[ERROR]Cannot init semaphore : produce_ok\n", T_RED);
		exit(0);
	}
	//생산자 또는 소비자가 생산 또는 소비를 하기위해 버퍼에 접근하는 것을 제한하는 세마포어변수
	if(sem_init(&buffer_lock, 0, 1) != 0){
		printf("%s[ERROR]Cannot init semaphore : buffer_lock\n", T_RED);
		exit(0);
	}
}

void *producer(void *args){
	int i;
	int producerID = ++producerCount;
	int item;

	for(i = 0; i < PRODUCE_TIME; i++){
		item = (random() % 100) + producerID;
		usleep(item);

	
		sem_wait(&produce_ok);//
		sem_wait(&buffer_lock);//
		//생산 세마포어 변수 produce_ok를 함수를 사용해 1 감소
		/*생산 하는 도중에 소비자나 다른 생산자가 버퍼에 접근하지 못하도록
	 	  버퍼 세마포어 변수 buffer_lock을 함수를 사용해 1 감소*/
		if(insertItem(item) == 0)
			printf("%s[PRODUCER_ERROR]insertItem Failed%s\n", T_RED, T_DEFAULT);
		else
			printf("%sproducer %d add CQ %d%s\n", T_YELLOW, producerID, item, T_DEFAULT);
		/*생산이 끝나면 소비자나 다른 생산자가 버퍼에 접근할 수 있도록
		  버퍼 세마포어 변수 buffer_lock을 함수를 사용해 1 증가*/
		/*소비자가 소비할 수 있도록
		소비 세마포어변수 consume_ok를 함수를 사용해 1 증가*/
	 	sem_post(&buffer_lock);
		sem_post(&consume_ok);//
		
	}
}

void *consumer(void *args){
	int i;
	int consumerID = ++consumerCount;
	int time;

	for(i = 0; i < CONSUME_TIME; i++){
		int item;
		time = (random() % 100) + consumerID;
		usleep(time);

		sem_wait(&consume_ok);//
		sem_wait(&buffer_lock);

		//소비 세마포어 변수 consume_ok를 함수를 사용해 1 감소
		/*생산자와 동일, 생산자나 다른 소비자가 버퍼에 접근하지 못하도록
		버퍼 세마포어 변수 buffer_lock을 함수를 사용해 1 감소*/
		if((item = takeItem()) == -1)
			printf("%s[CONSUMER_ERROR]takeItem Failed%s\n", T_RED, T_DEFAULT);	
		else
			printf("%sconsumer %d take item from CQ%d%s\n", T_GREEN, consumerID, item, T_DEFAULT);
		/*생산자와 동일, 생산자나 다른 소비자가 버퍼에 접근할 수 있도록
		버퍼 세마포어 변수 buffer_lock을 함수를 사용해 1 증가*/
		/*소비자가 버퍼 안의 물건을 하나 꺼내 소비했으므로
		생산 세마포어변수 produce_ok를 함수를 사용해 1 증가*/

		sem_post(&buffer_lock);
		sem_post(&produce_ok);
	}
}

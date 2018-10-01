//생산자-소비자 문제 : mutex를 통한 해결
//생산자 수 : 5, 소비자 수 2

#include <pthread.h>	//include for thread and mutex
#include <stdio.h>	//include for printf
#include <unistd.h>	//include for random
#include <stdlib.h>	//include for exit

#define Q_SIZE 10	//원형 큐의 크기
#define P_COUNT 6	//생산 횟수, 6 * 5 = 30개의 item 생산
#define C_COUNT 15	//소비 횟수, 15 * 2 = 30개의 item 소모


/*********mutex 관련 변수 상수로 초기화****************/
pthread_cond_t buffers = PTHREAD_COND_INITIALIZER; //생산 버퍼 조건변수
pthread_cond_t items = PTHREAD_COND_INITIALIZER;   //item 소비 조건변수

pthread_mutex_t buffer_lock = PTHREAD_MUTEX_INITIALIZER; //생산 mutex
/*********************초기화 끝************************/

/***************원형 큐와 관련 변수 선언***************/
int CQ[Q_SIZE];		//원형큐
int front = 0;		//원형 큐의 앞 index, 삽입 위치
int rear = 0;		//원형 큐의 뒤 index, 빼는 위치
int CQ_count = 0;	//현재 원형 큐의 크기
/*********************변수 선언 끝********************/
int pc=1;		//생산자 id변수
int cc=1;		//소비자 id변수

//getQ() : 버퍼에서 item 하나를 꺼내는 함수
int getQ(){
	int output = 99;
	//버퍼가 비어있다면 -1이 리턴됨
	if(CQ_count == 0 ){
		printf("CQ is empty\n");
		return -1;
	}
	else{
		output = CQ[rear];
		rear = (rear + 1) % 10;
		CQ_count--;
		return output;
	}
}

//addQ(int input) : 버퍼에 input을 하나 넣는 함수
int addQ(int input){
	//버퍼가 꽉 차 있다면 -1이 리턴됨
	if(CQ_count ==10){
		printf("CQ is full\n");
		return -1;
	}
	else{
		CQ[front] = input;
		front = (front + 1) % 10;
		CQ_count++;
		return 1;
	}
}

//producer(void *arg) : 생산자 thread
void *producer(void *arg){
	int i;
	int id;
	int input;

	id = pc++;
	//생산자는 생산 횟수(6)만큼 생산을 하고 버퍼에 넣는다.
	for(i = 0; i<P_COUNT; i++){
		//버퍼에 넣을 임의의 값 생성
		input = random()%100;
		usleep(input);
		printf("producer %d add Q %d\n",id, input);

		/***********************  코드 작성 부분  *************************/	
		/*  소비자가 버퍼에 접근하지 못하도록 임계영역으로 설정           */
		pthread_mutex_lock(&buffer_lock);

		/*  버퍼가 가득차면 대기(반드시 while문 사용, if문 사용하지 말 것)*/
		while(CQ_count ==10){
			pthread_cond_wait(&buffers , &buffer_lock);                }

		/* input을 버퍼에 넣는다 */
		addQ(input);  

		/*              버퍼 안에 item이 있음을 소비자에게 알림           */
		pthread_cond_signal(&items);

		/*  삽입이 끝나면 임계영역을 빠져 나가므로 mutex 락을 해제        */
		pthread_mutex_unlock(&buffer_lock );
		/******************************************************************/


	}
}

//consumer(void *arg) : 소비자 thread
void *consumer(void *arg){
	int i;
	int id;
	int output;
	id = cc++;

	for(i = 0; i<C_COUNT; i++){
		usleep(random()%100);

		/***********************  코드 작성 부분  *************************/

		/*  생산자가 버퍼에 접근하지 못하도록 임계영역으로 설정           */
		pthread_mutex_lock(&buffer_lock);		
		/*버퍼가 비어있으면 대기(반드시 while문 사용, if문 사용하지 말 것)*/
		while(CQ_count ==0){
			pthread_cond_wait(&items , &buffer_lock);    
		}
		/* item을 버퍼로부터 가져온다             */
		output=getQ();
		/*        버퍼 안에 item을 소비했다고 생산자에게 알림		  */
		pthread_cond_signal(&buffers);

		/*     소비가 끝나면 임계영역을 빠져 나가므로 mutex 락을 해제     */

		pthread_mutex_unlock(&buffer_lock);
		/******************************************************************/

		if(output != -1)
			printf("consumer %d get Q %d\n",id, output);
	}
}

int main(){
	pthread_t producer_thread[5];
	int producer_id[5];
	pthread_t consumer_thread[2];
	int consumer_id[2];

	int status;
	int i;
	//생산자 thread 생성
	for(i = 0; i < 5 ; i++)	{
		if((producer_id[i] = pthread_create(&producer_thread[i], NULL, producer, NULL)) < 0)
			exit(0);
	}
	//소비자 thread 생성
	for(i = 0; i < 2; i++){
		if((consumer_id[i] = pthread_create(&consumer_thread[i], NULL, consumer, NULL)) < 0)
			exit(0);
	}
	//생산자, 소비자 thread 실행
	pthread_join(producer_thread[0], (void **)&status);
	pthread_join(producer_thread[1], (void **)&status);
	pthread_join(producer_thread[2], (void **)&status);
	pthread_join(producer_thread[3], (void **)&status);
	pthread_join(producer_thread[4], (void **)&status);
	pthread_join(consumer_thread[0], (void **)&status);
	pthread_join(consumer_thread[1], (void **)&status);

	return 0;
}

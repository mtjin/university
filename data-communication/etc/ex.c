#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/stat.h>
#include <arpa/inet.h>

#define MAX_SIZE 300

int sndsock,rcvsock;
int clen;	
struct sockaddr_in s_addr,r_addr,r_info;


int L2_Sresult[4];//save parsing value  (option1)
int L2_Dresult[4];
int L3_Sresult[6];//(option2)
int L3_Dresult[6];
int dest_L2[4]; //(option3)
int dest_L3[6];

void setSocket(void);
void play1(void);//5
void play2(void);//5
void play3(void);//5
void *do_thread(void *);

struct L1 {
	int sequence;//2
	int key;//3
	int type;//4
	int sport;
	int dport;
	int length;
	char L1_data[MAX_SIZE];
};

struct L2 {
	int saddr[4];
	int daddr[4];
	int length;
	char L2_data[MAX_SIZE];
};

struct L3 {
	int saddr[6];
	int daddr[6];
	int length;
	char L3_data[MAX_SIZE];
};

void L1_send(char *input, int length);
void L2_send(char *input, int length);
void L3_send(char *data, int length);
void L4_send(char *data, int length);
char *L1_receive(int *);
char *L2_receive(int *);
char *L3_receive(int *);
char *L4_receive(int *);

void play1(){//5
	char s_tmp[20];
	char d_tmp[20];
	int L2_sadr[4];
	int L2_dadr[4];

	printf("input my L2 address :");
	scanf("%s", s_tmp);
	printf("input dest L2 address :");
	scanf("%s", d_tmp);

	int i = 0;
	char *sptr = strtok(s_tmp,"."); 

	while(sptr != NULL)
	{
		L2_sadr[i] = atoi(sptr);   
		L2_Sresult[i] = L2_sadr[i];//save parsing value
		i++;
		sptr = strtok(NULL,".");
	}
	i=0;
	char *dptr = strtok(d_tmp,"."); 
	while(dptr != NULL)
	{
		L2_dadr[i] = atoi(dptr); 
		L2_Dresult[i] = L2_dadr[i];// save parsing value
		i++;
		dptr = strtok(NULL,".");
	}
	i=0;//

}

void play2(){
	 char s_tmp[20];
	 char d_tmp[20];
	 int L3_sadr[6];//5
	 int L3_dadr[6];//5

	printf("input my L3 address :");
	scanf("%s", s_tmp);
	printf("input dest L3 address :");
	scanf("%s", d_tmp);
	int i = 0;
	char *sptr2 = strtok(s_tmp,"-"); 
	while(sptr2 != NULL)
        {
		L3_sadr[i] = strtol(sptr2,NULL,16);
		L3_Sresult[i] = L3_sadr[i];// save parsing value
                i++;
		sptr2 = strtok(NULL,"-");
	}
	i=0;
	char *dptr2 = strtok(d_tmp,"-"); 
	while(dptr2 != NULL)
	{
               L3_dadr[i] = strtol(dptr2,NULL,16);
	       L3_Dresult[i] = L3_dadr[i];// save parsing value
               i++;
	       dptr2 = strtok(NULL,"-");
	}



}
void play3(){//5
	char L2_tmp[20];
	char L3_tmp[20];
	 int i=0;
	 int dest_L2_addr[4];
	 int dest_L3_addr[6];
     	 
        
		 printf("Put the dest L2 address : ");
	          scanf("%s", L2_tmp);
	 	 printf("Put the dest L3 address : ");
	          scanf("%s", L3_tmp);
	          char *l2ptr = strtok(L2_tmp,".");//parsing
	         
		  while(l2ptr != NULL){//5
		 	 dest_L2_addr[i] = atoi(l2ptr); // char->int  (cast)
			 dest_L2[i] = dest_L2_addr[i];//  save parsing value
			 i++;
			 l2ptr = strtok(NULL,".");
		  }

		  i=0;
		   char *l3ptr = strtok(L3_tmp,"-");//parsing
		  
		  while(l3ptr != NULL){//5
			  dest_L3_addr[i] = strtol(l3ptr,NULL,16);// char->int (cast)
			  dest_L3[i] = dest_L3_addr[i];// save parsing value
			  i++;
			  l3ptr = strtok(NULL,"-");
		  }

}
int main (void)
{

	char input[MAX_SIZE];
	int length;
	int select_num;//5
	pthread_t tid;

	setSocket();
	pthread_create(&tid, NULL, do_thread, (void *)0);
	
	do{
		printf("1. set L2 address\n");//5
		printf("2. set L3 address\n");//5
		printf("3. Send message\n");//5
		scanf("%d", &select_num);//5
		switch(select_num){//5
			case 1:
				play1();
				break;
			
			case 2:
				play2();
				break;
			case 3:
			
				play3();;//5
				printf("Input message to send : ");
				scanf("%s",input);//3
				printf("\n");//
				length = strlen(input);
				printf("insert password_key:");//3
				L1_send(input, strlen(input));
				sleep(1);
				break;
			default:
				printf("not exist that menu!\n");
		}
	}while(strcmp(input,"exit"));
	close(sndsock);
}



void *do_thread(void *arg)
{

	char output[MAX_SIZE];
	int length;

	while (1) {
		strcpy(output, L1_receive(&length));		
		output[length] = '\0';
		
		if(length > 0) printf("Converted message = %s\n\n",output);		
		if(!strcmp(output,"exit")) exit(1);
	}
	
	close(rcvsock);

}

void setSocket()
{
	
	//Create Send Socket///////////////////////////////////////////////
	if((sndsock=socket(PF_INET,SOCK_DGRAM,IPPROTO_UDP)) < 0) 
	{
		perror("socket error : ");
		exit(1);
	}

	s_addr.sin_family = AF_INET;
	s_addr.sin_addr.s_addr=inet_addr("127.0.0.1");
	s_addr.sin_port = htons(7777);
	///////////////////////////////////////////////////////////////////

	//Create Receive Socket////////////////////////////////////////////
	if((rcvsock=socket(PF_INET,SOCK_DGRAM,IPPROTO_UDP)) < 0) 
	{
		perror("socket error : ");
		exit(1);
	}

	clen = sizeof(r_addr);
	memset(&r_addr,0,sizeof(r_addr));

	r_addr.sin_family = AF_INET;
	r_addr.sin_addr.s_addr=htonl(INADDR_ANY);
	r_addr.sin_port = htons(7777);

	if(bind(rcvsock,(struct sockaddr *)&r_addr, sizeof(r_addr)) < 0) 
	{
		perror("bind error : ");
		exit(1);
	}

	int optvalue = 1;
	int optlen = sizeof(optvalue);

	setsockopt(sndsock,SOL_SOCKET,SO_REUSEADDR,&optvalue,optlen);
	setsockopt(rcvsock,SOL_SOCKET,SO_REUSEADDR,&optvalue,optlen);
	///////////////////////////////////////////////////////////////////
}

void L1_send(char *input, int length)
{
	static int s_sq =0;//2
	struct L1 data;
	char temp[MAX_SIZE];
	int size = 0;
	int tmp_key;//3
	int tmp_type;//4
	data.sequence = s_sq; //2
	

	//gets(tmp_key);//3
	scanf("%d", &tmp_key);//3
	data.key = tmp_key;//3

	printf("Please input type number\n1)Convert lower to upper\n2)Convert upper to lower\n3)Reverse input string\n4)No conversion\n");//4
	scanf("%d", &tmp_type);//4
	data.type = tmp_type;


	data.sport = 51245;
	data.dport = 80;
	data.length = length;
	memset(data.L1_data, 0x00, MAX_SIZE);
	memcpy(data.L1_data, (void*)input, length);

	for(int i=0; i<length; i++){ //3
		data.L1_data[i] += data.key;//3
	}

	size = sizeof(struct L1) - sizeof(data.L1_data) + length;

   	memset(temp, 0x00, MAX_SIZE);  
   	memcpy(temp, (void *)&data, size);

	L2_send(temp, size);
	 printf("L1_send: sequence number : %d\n", s_sq++);//2
}


void L2_send(char *input, int length)
{
          
    
	struct L2 data;
	char temp[MAX_SIZE];
	int size = 0;
	
	data.length = length;

	memset(data.L2_data, 0x00, MAX_SIZE);
	memcpy(data.L2_data, (void*)input, length);


	for(int i=0; i<4; i++){   //5:  save header address value
		data.saddr[i] = L2_Sresult[i];
		data.daddr[i] = L2_Dresult[i];
	}

	size = sizeof(struct L2) - sizeof(data.L2_data) + length;

    	memset(temp, 0x00, MAX_SIZE);  
    	memcpy(temp, (void *)&data, size);

	L3_send(temp, size);
}

void L3_send(char *input, int length)
{
	struct L3 data;
	char temp[MAX_SIZE];
	int size = 0;
	
	data.length = length;

	memset(data.L3_data, 0x00, MAX_SIZE);
	memcpy(data.L3_data, (void *)input, length);
	
	for(int i=0; i<6; i++){
		data.saddr[i] = L3_Sresult[i];
		data.daddr[i] = L3_Dresult[i];
         }
	
	size = sizeof(struct L3) - sizeof(data.L3_data) + length;

	memset(temp, 0x00, MAX_SIZE); 
	memcpy(temp, (void *)&data, size); 

	L4_send(temp,size);
}

void L4_send(char *data, int length)
{

	char temp[MAX_SIZE];
	char bin[MAX_SIZE];//6
	int i =0;
	int b =0;
	while(data[i]!='\0'){
		for(int j=7; j>=0 ; j--){
			bin[b] = (((data[b])&1) ? '1' : '0');
			b++;
		}
		i++;
	}//6
	i=0;
	data[0]=5;
	int pre = 5;
	while(bin[++i]!='\0'){
		if(bin[i] == 0){
			data[i]= pre;
		}
		else if(bin[i] == 1){
			data[i] = -pre;
		}
	}
	pre = bin[i];
	//
	

	memset(temp, 0x00, MAX_SIZE);
	memcpy(temp, (void *)data, length);

	if(sendto (sndsock, temp, length, 0, (struct sockaddr *)&r_addr,sizeof(r_addr)) <= 0)
	{
		perror("send error : ");
		exit(1);
	}
}

char * L1_receive(int *length)
{
	static int r_sq = 0;//2
	struct L1 *data;
	data = (struct L1*)L2_receive(length);
	int i=0;//3
	char real_data[100];//3
	while(data->L1_data[i] != '\0'){//3
		real_data[i]= data->L1_data[i] - data->key;
		i++;
	}
	printf("Encrypted message(Question3): %s\n", data->L1_data);//3
	i = 0;//3

	switch(data->type){ //4
		case 1:
			while(data->L1_data[i] != '\0'){
				data->L1_data[i] = data->L1_data[i] -32;
				i++;
			}
			break;
		case 2:
			while(data->L1_data[i] != '\0'){
				data->L1_data[i] = data->L1_data[i] + 32;
				i++;
			}
			break;
		case 3:
			for(i=0 ; i<data->length/2; i++){
				char temp = data->L1_data[i];
				data->L1_data[i] =data->L1_data[data->length -1 - i];
				data->L1_data[data->length -1 -i]= temp;
			}
			break;
		case 4:
			break;
	}


	if(r_sq == data->sequence){ //2	
		printf("Delivered message : %s\n", real_data);//3
		printf("L1_receive : sequence number : %d\n", r_sq);
	}else{
		printf("data_s:%d, receive_sequence: %d \n", data->sequence, r_sq); 
	}
	r_sq++;
	*length = *length - sizeof(data->sport) - sizeof(data->length) - sizeof(data->dport);

	return (char *)data->L1_data;
}

char * L2_receive(int *length) 
{
	struct L2 *data;

	data = (struct L2*)L3_receive(length);
	
	*length = *length - sizeof(data->daddr) - sizeof(data->length) - sizeof(data->saddr);
	
	
	
	if(data->daddr[0] == dest_L2[0] && data->daddr[1] == dest_L2[1]	&&
			data->daddr[2] == dest_L2[2] && data->daddr[3] == dest_L2[3]){//5
	return (char *)data->L2_data;
	}
	else{
		perror("L2 address is different!\n");
		exit(1);
	}
}

char * L3_receive(int *length)
{
	struct L3 *data;
	
	data = (struct L3*)L4_receive(length);

	*length = *length - sizeof(data->daddr) - sizeof(data->length) - sizeof(data->saddr);
	
	if(data->daddr[0] == dest_L3[0] && data->daddr[1] == dest_L3[1] && data->daddr[2] == dest_L3[2] && data->daddr[3] == dest_L3[3] && data->daddr[4] == dest_L3[4] && data->daddr[5] == dest_L3[5]){//5
	return (char *)data->L3_data;
	}else{
		printf("L3 address is different!\n");
		exit(1);
	}
}

char *L4_receive(int *length)
{
	static char data[MAX_SIZE];
	int r_length = 0;
	char bin[MAX_SIZE];

	if((r_length = recvfrom(rcvsock, data, MAX_SIZE, 0,(struct sockaddr *)&r_info, &clen)) <= 0)
	{

		perror("read error : ");
		exit(1);
	}

	data[r_length] = '\0'; 
	*length = r_length;

	int i=1;  //6
	int pre=0;
	bin[0]=0;
	while(data[i]!='\0'){
		if(data[pre] == data[i]){
			bin[i] = 0;
		}else if(data[pre] != data[i]){
			bin[i] = 1;
		}

	}
	i=0;
	int j=0;
	while(bin[i] != '\0'){
		data[i] = strtol(bin,0,2);
		i++;
	}
	//
	
	

	return data;
}


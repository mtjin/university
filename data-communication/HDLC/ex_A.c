#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/stat.h>
#include <arpa/inet.h>

#define MAX_SIZE 400
#define M_SIZE 20

int sndsock,rcvsock;
int clen;	
struct sockaddr_in s_addr,r_addr,r_info;
int is_link = 0; //1
int ssn = 0; //Source sequence number
int dsn = 0; //Destination sequence number
void setSocket(void);
void *do_thread(void *);

struct U_FRAME{
	int start_flag;
	int address;
	int control[4];
	char mg_info[M_SIZE];
	int fcs;
	int end_flag;
};

struct I_FRAME{
	int start_flag;
	int address;
	int control[4];
	char data[M_SIZE];
	int fcs;
	int end_flag;
};

struct S_FRAME{
	int start_flag;
	int address;
	int control[4];
	int fcs;
	int end_flag;
};

void data_send(char *data, int length);
void data_receive();

static int my_address = 0x11;
static int dest_address = 0x22;

int main (void)
{

	char input[MAX_SIZE];
	int length;
	pthread_t tid;

	setSocket();
	pthread_create(&tid, NULL, do_thread, (void *)0);

	do {
		printf("\n");
		printf("Input message to send : \n");
		scanf("%s",input);
		
		length = strlen(input);
		data_send(input, strlen(input));
		while(getchar()!='\n');
		sleep(1);
			
	}while(strcmp(input,"exit"));

	close(sndsock);

}

void *do_thread(void *arg)
{

	char output[MAX_SIZE];
	int length;

	while (1) {
		data_receive();	
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

	memset((char *) &s_addr, 0, sizeof(s_addr));
	s_addr.sin_family = AF_INET;
	s_addr.sin_addr.s_addr=inet_addr("127.0.0.1");
	s_addr.sin_port = htons(9000);
	///////////////////////////////////////////////////////////////////

	//Create Receive Socket////////////////////////////////////////////
	memset(&r_info,0,sizeof(struct sockaddr_in));

	if((rcvsock=socket(PF_INET,SOCK_DGRAM,IPPROTO_UDP)) < 0) 
	{
		perror("socket error : ");
		exit(1);
	}

	clen = sizeof(r_addr);
	memset(&r_addr,0,sizeof(r_addr));

	r_addr.sin_family = AF_INET;
	r_addr.sin_addr.s_addr=htonl(INADDR_ANY);
	r_addr.sin_port = htons(9001);

	if(bind(rcvsock,(struct sockaddr *)&r_addr, sizeof(r_addr)) < 0) 
	{
		perror("bind error : ");
		exit(1);
	}
}

void data_send(char *data, int length)
{
	char temp[MAX_SIZE];

	memset(temp, 0x00, MAX_SIZE);
	memcpy(temp, data, length);
	
	struct U_FRAME u_frame;//1
	struct I_FRAME i_frame;//2

	// if connection is not established, send U-FRAME(SABM)
		// initialize the U-FRAME(SABM)
	if(is_link == 0){
		u_frame.start_flag = 01111110;
		u_frame.address= dest_address;
		u_frame.control[0] = 11;
		u_frame.control[1] = 11;
		u_frame.control[2] = 0;
		u_frame.control[3] = 100;
		u_frame.mg_info[M_SIZE] = 0;
		u_frame.fcs = 0;
		u_frame.end_flag = 01111110;
		length = sizeof(struct U_FRAME);
		memcpy(temp,(void *)&u_frame, length);
		printf("connection is not established, send U-FRAME(SABM)\n");
	}

	// if connection is established, send I-FRAME(data)
		// initialize the I-FRAME(data)
	else if(is_link == 1){
		i_frame.start_flag = 01111110;
		i_frame.address = dest_address;
		i_frame.control[0] = 0;
		i_frame.control[1] = ssn;
		i_frame.control[2] = 0;
		i_frame.control[3] = dsn;
		strcpy(i_frame.data, temp);
		i_frame.fcs = 0;
		i_frame.end_flag = 01111110;
		length = sizeof(struct I_FRAME);
		memcpy(temp, (void *)&i_frame, length);
		printf("send the I-frame(data_frame %d)\n", ssn++);
	}


	// if connection is established && strcmp(data, "close") == 0, send U-frame(DISC)
		// initialize the U-frame(DISC)
	if(is_link == 1 && strcmp(data, "close") == 0 ){
		printf("Disconnection is requrested, send the U-frame(DISC)\n");
		u_frame.start_flag = 01111110;
		u_frame.address = dest_address;
		u_frame.control[0] = 11;
		u_frame.control[1] = 00;
		u_frame.control[2] = 0;
		u_frame.control[3] = 010;       
		u_frame.mg_info[MAX_SIZE] = 0;
		u_frame.fcs = 0;
		u_frame.end_flag = 01111110;
		length = sizeof(struct U_FRAME);
		memcpy(temp, (void *)&u_frame, length);
	}

	if(sendto (sndsock, temp, length, 0, (struct sockaddr *)&s_addr,sizeof(s_addr)) <= 0)
	{
		perror("send error : ");
		exit(1);
	}
}

void data_receive(int *length)
{
	char* data = (char*)malloc(MAX_SIZE);
	char rec_buffer[MAX_SIZE];
	int r_length = 0;
	int s_length = 0;
	int flag;
	int address;
	int control[4];

	printf("\n");

	if((r_length = recvfrom(rcvsock, rec_buffer, MAX_SIZE, 0,(struct sockaddr *)&r_info, &clen)) <= 0)
	{
		perror("read error : ");
		exit(1);
	}

	memcpy(data,rec_buffer,MAX_SIZE);

	// decalsulate the flag field
	 memcpy(&flag, data, sizeof(flag));
	 data += sizeof(flag);
	
	// decalsulate the address field
	 memcpy(&address, data, sizeof(address));
	 data += sizeof(address);
 
	 printf("packet received (address: %d)\n", address);	
	
	// decalsulate the control field
	 memcpy(&control[0], data, sizeof(control[0]));
	 data += sizeof(control[0]);
	 
	 memcpy(&control[1], data, sizeof(control[1]));
	 data += sizeof(control[1]);
	
	// discard the dirty bit
	 memcpy(&control[2], data, sizeof(control[2]));
	 data += sizeof(control[2]);

	 memcpy(&control[3], data, sizeof(control[3]));
	 data += sizeof(control[3]);
		
	 //struct U_FRAME u;//
	 if(control[0] == 11) // if(U-frame)
	 {
		 if(control[1] == 11 && control[3] == 100) // received SABM
			// reply UA frame
		{
			printf("received frame type: U-frame(SABM)\n");
			struct U_FRAME u;
			u.start_flag = 01111110;
			u.address = dest_address;
			u.control[0] =11;
			u.control[1] = 00;
			u.control[2] = 0;
			u.control[3] = 110;
			u.mg_info[M_SIZE] = 0;
			u.fcs = 0;
			u.end_flag = 01111110;
			memcpy(data , (void *)&u, sizeof(struct U_FRAME));
	//	data_send(data, sizeof(struct U_FRAME));//
			is_link = 1;//concnect success
			printf("connection is established\n");
			printf("reply the U-frame(UA)\n\n");
			if(sendto (sndsock, data, sizeof(struct U_FRAME), 0, (struct sockaddr *)&s_addr,sizeof(s_addr)) <= 0)
			{
				perror("send error : ");
				exit(1);
			}

		
		}

		//ex) receive S_FRAME(UA)
		else if(control[1] == 00 && control[3] == 110){
			
			printf("received frame type: U-FRAME(UA)\n");
			if(is_link ==0)
			{
			is_link = 1; //connection success
			printf("connection is established\n");
			}
			else if(is_link == 1) {
			printf("received frame type; U-FRAME(DISC)\n");
			is_link = 0;
			}
		}
	 		
		 else if(control[1] == 00 && control[3] == 010)
			 //received DISC
			// reply UA frame
		 {
			struct U_FRAME u;
			u.start_flag = 01111110;
			u.address = dest_address;
			u.control[0] = 11;
			u.control[1] = 00;
			u.control[2] = 0;
			u.control[3] = 110;
			u.mg_info[M_SIZE] = 0;
			u.fcs = 0;
			u.end_flag = 01111110;
			memcpy(data, (void *)&u, sizeof(struct U_FRAME));
			if(sendto (sndsock, data, sizeof(struct U_FRAME), 0,(struct sockaddr *)&s_addr,sizeof(s_addr)) <= 0)
			{
				perror("send error : ");
				exit(1);
			}
			is_link = 0;//disconnect
			printf("received frame type: U-frame(DISC)\n");
			printf("reply the U-frame(UA)\n");
			printf("successfully disconnected with destination\n");

		 }

	}
	 else if(control[0] == 0) // if(I-frame)
		// reply RR frame
	 {
		struct S_FRAME s;
		s.start_flag = 01111110;
		s.address = dest_address;
		s.control[0] = 10;
		s.control[1] = 00;
		s.control[2] = 0;
		s.control[3] = dsn;
		s.fcs = 0;
		s.end_flag = 01111110;
		printf("received frame type: I-frame(data frame %d)\n", dsn++);
		printf("requested next packet sequence number : %d\n", ssn);
		printf("received message = %s\n", data);
		printf("send the S-frame(RR)\n");
		
		memcpy(data, (void *)&s, sizeof(struct S_FRAME));
		if(sendto (sndsock, data, sizeof(struct S_FRAME), 0, (struct sockaddr *)&s_addr,sizeof(s_addr)) <= 0)
		{
			perror("send error : ");
			exit(1);
		}   

	 }
				
	
	 else if(control[0] == 10 && control[1] == 00) // if(S-frame)
	 {
		 printf("received frame type: S-frame(RR)\n");
		 printf("requested next packet sequence number %d\n", ssn);

	 }
}

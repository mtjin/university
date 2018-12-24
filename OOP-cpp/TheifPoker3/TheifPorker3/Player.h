#pragma once
#include <iostream>
#include <string>
#include "Hand.h"
#include "Deck.h"

using namespace std;
class Player
{
private:
	int money;
	string name;
	Hand hand;
public:
	Player();
	Player(string name, int money);
	void drawCard(Deck *de);	//카드 5장 드로우
	int getMoney();
	string getName();
	Hand getHand();
	void addMoney(int);
	void subMoney(int);
	bool bet(int);  //베팅
	bool call(int betMoney); //콜
	bool stay(); //스테이
	bool fold(Player, int); //폴드
	void receiveReward(int reward); //이겼을시 보상
	bool ableToParticipateIn(); //게임참가 할 수 있는지 조사( 최소금액비용 100보다 낮은금액을 가졌을 경우)
	bool swapCard(Hand *hand1, Hand *hand2, int n1, int n2); //상대방과 카드 교환
	void displayMoney();
	void clearHand();
};


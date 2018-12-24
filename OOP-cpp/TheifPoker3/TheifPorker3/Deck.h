#pragma once
#include <iostream>
#include "Card.h"
class Deck {
private:
	Card deck[21]; 
	Card temp;
	int cardsUsed;
	int cardCt;
	int randomnum;
public:
	Deck();
	void shuffle(); //덱을 섞는 기능
	int cardsLeft();	//덱에 남아있는 카드 수 반환
	Card dealCard();	//덱에서 카드 가져오기
};


#pragma once
#include <vector>
#include<iostream>
#include "Deck.h"
#include "Card.h"
// 카드 한장을 나타내는 Hand타입
class Hand {
private:
	vector<Card> cards; //손에든 카드들
public:
	Hand();
	void clear();		//손에있는 카드 버리기
	void addCard(Card c);	//손에 카드 추가
	void removeCard(unsigned int position);	//특정위키 카드 제거
	int getCardcount();	//손에든 카드 개수반환
	Card getCard(unsigned int posi);	//특정위치의 카드 반환
	void changeCard(unsigned int posi, Card ca);	//특정위치의 카드를 삭제하고 다른카드 추가 (상대에게 줄 카드위치를 삭제하고 상대방이 준 카드를 저장)
	void displayHand(); //패의 카드들을 보여줌
	void handShuffle(); //손에든 카드 셔플
	void displayHiddenHand(); //패의 카드들을 가려서 보여줌
};


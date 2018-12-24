#include "pch.h"
#include "Hand.h"
#include "Card.h"
#include <iostream>
#include <vector>
#include <stdlib.h>
#include <cstdlib>
#include <ctime>
#include <time.h>

using namespace std;
Hand::Hand()
{
}

//손에 든 카드 다 버리기
void Hand::clear()	
{
	cards.clear();
}

//손에 카드 추가
void Hand::addCard(Card ca)	
{
	if (cards.size() < 4) cards.push_back(ca);
	else cout << "손에들린 카드가 4장을 넘었습니다." << endl;
}

//특정 위치의 카드 삭제
void Hand::removeCard(unsigned int posi)	
{
	
	if (posi >= 0 && posi < cards.size())
		cards.erase(cards.begin() + posi);
} 

//손에 든 카드 개수 반환
int Hand::getCardcount()	
{
	return cards.size();
}

//특정위치의 카드 반환
Card Hand::getCard(unsigned int posi)
{
	
	if (posi >= 0 && posi < cards.size()) {
		
		return (Card)(cards.at(posi));
		
	}
}

//특정위치의 카드를 삭제하고 다른카드 추가 
//(상대에게 줄 카드위치를 삭제하고 상대방이 준 카드를 저장)
void Hand::changeCard(unsigned int posi, Card ca) {
	if(posi >= 0 && posi < cards.size()) {
		removeCard(posi);//해당 자리의 카드를 삭제
		cards.insert(cards.begin() + posi, ca); //새 카드 추가
	}
}	

void Hand::displayHand() {
	cout << "-----------------------------" << endl;
	for (int i = 0; i < 4; i++) {
		cout << " | " << cards.at(i).getRank() << " | ";
	}
	cout << endl;
	cout << "-----------------------------"<<endl;
	cout << "  ①     ②     ③     ④" << endl << endl;
}

void Hand::displayHiddenHand() {
	cout << "-----------------------------" << endl;
	for (int i = 0; i < 4; i++) {
		cout << " | " << "#" << " | ";
	}
	cout << endl;
	cout << "-----------------------------" << endl << endl;
	cout << "  ①     ②     ③     ④" << endl;
}

void Hand::handShuffle() {
	for (int i = 0; i < 100; i++) {
		(srand((unsigned int)time(NULL)));
		int randomnum = (int)(rand() % (4));
		Card temp = cards.at(randomnum);
		int randomnum2 = i%4;
		//int randomnum2 = (int)(rand() % (4));
		Card temp2 = cards.at(randomnum2);
		removeCard(randomnum);//해당 자리의 카드를 삭제
		cards.insert(cards.begin() + randomnum, temp2); //새 카드 추가
		removeCard(randomnum2);//해당 자리의 카드를 삭제
		cards.insert(cards.begin() + randomnum2, temp); //새 카드 추가
	}
}
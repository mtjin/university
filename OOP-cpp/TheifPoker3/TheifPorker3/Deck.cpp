#include "pch.h"
#include "Deck.h"
#include <stdlib.h>
#include <cstdlib>
#include <ctime>
#include <time.h>

using namespace std;

//덱 생성
Deck::Deck()
{
	cardCt = 0;
	for (int i = 1; i <= 5; i++) {
		for (int j = 0; j <4; j++) {
			Card card(i);
			deck[cardCt] = card;
			cardCt++;
		}
	}
	deck[cardCt] = 0; //사신 카드
	cardsUsed = 0;
}

//난수(rand)를 사용하여 카드를 suffle
void Deck::shuffle()	
{
	for (int i = 21; i > 0; i--) {
		(srand((unsigned int)time(NULL)));
		randomnum = (int)(rand() % (i + 1));
	
		temp = deck[i];
		deck[i] = deck[randomnum];
		deck[randomnum] = temp;
	}
	cardsUsed = 0;
} 

//카드가 deck에서 사용될때, 남아있는 카드의 수를 반환
int Deck::cardsLeft()
{
	return 21 - cardsUsed;
}  

//카드를 21장 사용하였다면 shuffle하고 deck에서 카드를 한장 가져옴
Card Deck::dealCard()
{
	if (cardsUsed == 21) {
		cout << "덱이 소진되어 새로 생성하고 셔플합니다" << endl;
		shuffle();
	}
	cardsUsed++;
	return deck[cardsUsed - 1];
} 

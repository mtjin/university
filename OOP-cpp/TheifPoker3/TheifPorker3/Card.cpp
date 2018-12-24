#include "pch.h"
#include "Card.h"


Card::Card()
{
}

Card::Card(int x) {
	rank = x;
}

//카드 숫자 반환
int Card::getRank() {
	return this->rank;
}
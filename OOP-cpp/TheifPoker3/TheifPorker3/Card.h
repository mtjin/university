#pragma once
#include <iostream>


using namespace std;
class Card
{
private:
	int rank; //카드숫자
public:
	Card();
	Card(int x);
	int getRank();
};


#include "pch.h"
#include "Player.h"
#include <iostream>
#include <string>
#include "Hand.h"
#include "Deck.h"
using namespace std;

Player::Player()
{
}

//플레이어 이름과 돈 초기화
Player::Player(string name, int money) {
	this->name = name;
	this->money = money;
}

//카드 5장 드로우
void Player::drawCard(Deck *de) {	
	for (int i = 0; i < 4; i++) {
		hand.addCard(de->dealCard());
	}
}
int Player::getMoney() {
	return this->money;
}
string Player::getName() {
	return this->name;
}
Hand Player::getHand() {
	return this->hand;
}
void Player::addMoney(int money) {
	this->money += money;
}
void Player::subMoney(int money) {
	this->money -= money;
}

//베팅
bool Player::bet(int betMoney) {	
	if (betMoney <= money)
	{
		money -= betMoney;
		cout << name << "님이 $" << betMoney << " 만큼 베팅하셨습니다." << endl;
		return true;
	}
	else {
		cout << "소지한 금액이 부족합니다" << endl;
		return false;
	}
}

//콜
bool Player::call(int betMoney) {
	if (betMoney <= money)
	{
		money -= betMoney;
		cout << name << "님이 $" << betMoney << " 콜 하셨습니다." << endl;
		return true;
	}
	else {
		cout << "소지한 금액이 부족합니다" << endl;
		return false;
	}
}

//스테이
bool Player::stay() {
	cout << name << "님이 스테이를 선언하셨습니다. 턴을 종료합니다." << endl;
	return true;
	
}

//폴드
bool Player::fold( Player winner, int tablemoney) {
	winner.addMoney(tablemoney);
	cout << name << "님이 패배를 선언하셨습니다." << endl;
	cout << winner.getName() << "님의 승리!" << endl << winner.getName() << "님이 $" << tablemoney << " 를 얻었습니다!" << endl;
	return false;
}

//보상 획득
void Player::receiveReward(int reward) {
	money += reward;
}

//참가가능한 돈이 있는지 검사
bool Player::ableToParticipateIn() {	
	if (money < 100) {
		return false;
	}
	else {
		return true;
	}
}

bool Player::swapCard(Hand *hand1, Hand *hand2, int n1, int n2) {	//상대 플레이어와 카드 교환
	Card tmp1 = hand1->getCard(n1); //hand1이 갖고올 hand2의 카드 저장
	if (tmp1.getRank() == 0) {  //자신이 줄 패가 사신카드인 경우 1을 반환(바로 반칙패 만들것)
		cout << "사신카드는 상대에게 줄 수 없습니다 (반칙패)" << endl;
		return false;
	}
	Card tmp2 = hand2->getCard(n2);	 //hand2가 갖고올 hand1의 카드 저장
	if (tmp2.getRank() == 0) {	//상대의 사신코드를 골랐을 경우 (바로 패)
		return false;
	}
	hand1->changeCard(n1, tmp2);
	hand2->changeCard(n2, tmp1);

	return true;
}


void Player::displayMoney() {
	cout << "-----------------------------" << endl;
	cout << "  "<<this->name << " 님의 자본 : " << "$" << money << endl;
	cout << "-----------------------------" << endl;
}

void Player::clearHand() {
	hand.clear();
}
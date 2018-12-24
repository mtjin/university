#include "pch.h"
#include <iostream>
#include <stdlib.h>
#include <string>
#include <cstdlib>
#include <ctime>
#include <time.h>
#include "Card.h"
#include "Deck.h"
#include "Hand.h"
#include "Player.h"
#include "GameView.h"
#include "Evaluator.h"
#include <windows.h>
using namespace std;

GameView::GameView()
{
}


GameView::~GameView()
{
}

void GameView::gameRun() {
	string temp_name1, temp_name2;
	int temp_money1, temp_money2;

	int roundnumber = 1; // 라운드 수
	int dice1;	// 플레이어 1의 주사위 값
	int dice2;	// 플레이어 2의 주사위 값
	int starterplayer = 0;  //1이면 플레이어 1선, 2면 플레이어 2선
	bool dicewinner = false;	// 주사위 게임 승패 카운터
	int input; // 사용자 인풋 값
	int score1 = 0;
	int score2 = 0;
	int gameRule; // 게임모드 선택지
	string scorecard1, scorecard2;

	string startingMessage[] = { "♤","♣","◇","♥","♤","♣","◇","♥","도","둑"," ","포","커","에"," ","오","신","것","을"," ","환","영","합","니","다","!","♠","♧","◆","♡","♠","♧","◆","♡" };


	// 시작 문구
	for (int i = 0; i < 34; i++) {
		cout << startingMessage[i];
		Sleep(100);
	}
	cout << endl << endl;

	cout << "―――――――――――――――도둑 포커 규칙――――――――――――――――" << endl;
	cout << "| 1. 기본자금이 100원 미만이 되면 패배합니다.                              |" << endl;
	cout << "| 2. 첫번째 턴의 첫 플레이어는 stay를 할 수있습니다.                       |" << endl;
	cout << "| 3. Stay: 돈을 걸지 않고 기본 상태로 턴을 종료한다.                       |" << endl;
	cout << "| 4. Bet: 원하는 액수의 돈을 걸고 상대방과 카드를 교환 한다.               |" << endl;
	cout << "| 5. Call: 상대방이 베팅한 돈과 같은 금액을 지불하고 턴을 종료한다         |" << endl;
	cout << "| 6. Fold: 해당 라운드에서 패배를 선언한다.                                |" << endl;
	cout << "|     (기본요금 $100과 지금까지 베팅한 돈은 상대방이 가져가게 된다.)       |" << endl;
	cout << "| 7. 카드를 바꿀 때 사신카드를 선택하거나 마지막 까지 사신카드를 가지고    |" << endl;
	cout << "|    있게 되면, Table Money의 2배에 해당하는 금액을 내야한다.              |" << endl;
	cout << "| 8. 카드를 바꿀 때 상대방에게 줄 카드로 사신카드를 선택할 수 없다.        |" << endl;
	cout << "――――――――――――――――――――――――――――――――――――――" << endl << endl;

	cout << "――――――――도둑 포커 족보―――――――――" << endl;
	cout << "| 원페어: 카드 2장의 숫자가 같은 패            |" << endl;
	cout << "| 트리플: 카드 3장의 숫자가 같은 패            |" << endl;
	cout << "| 스트레이트: 카드 4장의 숫자가 연달아 있는 패 |" << endl;
	cout << "| 포카드: 카드 4장의 숫자가 같은 패            |" << endl;
	cout << "――――――――――――――――――――――――" << endl << endl;



	cout << endl << "계속하시려면 엔터를 눌러주세요..." << endl;
	cin.get();

	cout << endl;

	while (1) {
		cout << "<게임방식을 선택하십시오>" << endl;
		cout << "1. 패 공개모드 2. 패 비공개모드 " << endl << "선택: ";
		cin >> gameRule; cout << endl;
		if (gameRule == 1 || gameRule == 2)
			break;
		else
			cout << "잘못된 입력입니다." << endl << endl;
	}

	while (1) {
		if (gameRule == 1) {

			// 이름 및 금액 설정
			cout << "플레이어 1번님의 이름을 입력해 주십시오." << endl << "이름: ";
			cin >> temp_name1;
			while (1) {
				cout << "플레이어 1번님의 시작 금액을 입력해 주십시오." << endl << "금액: ";
				cin >> temp_money1;
				if (cin.fail()) {
					cout << "잘못된 입력입니다. 다시 입력해주세요" << endl << endl; // 에러메세지
					cin.clear(); cin.ignore(256, '\n');
				}
				else if (temp_money1 < 100) {
					cout << "!!시작 금액은 $100 이상이여야 합니다!!" << endl << endl;
				}
				else
					break;
			}

			cout << endl;

			cout << "플레이어 2번님의 이름을 입력해 주십시오." << endl << "이름: ";
			cin >> temp_name2;
			while (1) {
				cout << "플레이어 2번님의 시작 금액을 입력해 주십시오." << endl << "금액: ";
				cin >> temp_money2;
				if (cin.fail()) {
					cout << "!!--잘못된 입력입니다. 다시 입력해주세요--!!" << endl << endl; // 에러메세지
					cin.clear(); cin.ignore(256, '\n');
				}
				else if (temp_money2 < 100) {
					cout << "!!---시작 금액은 $100 이상이여야 합니다---!!" << endl << endl;
				}
				else
					break;
			}
			cout << endl;

			//플레이어 생성
			Player player1(temp_name1, temp_money1);
			Player player2(temp_name2, temp_money2);

			//주사위 굴리기 시작순서 정하기
			cout << "주사위를 던져 순서를 정합니다." << endl;
			Sleep(1000);
			while (1) {
				(srand((unsigned int)time(0)));
				dice1 = (int)(rand() % (6)) + 1;
				dice2 = (int)(rand() % (6)) + 1;
				cout << player1.getName() << "님의 주사위 값: " << dice1 << endl;
				Sleep(1000);

				cout << player2.getName() << "님의 주사위 값: " << dice2 << endl << endl;
				Sleep(1000);

				if (dice1 < dice2)
				{
					cout << player2.getName() << "님이 선을 갖습니다!" << endl << endl;
					starterplayer = 2;
					cout << "-----------엔터를 누르면 게임을 진행합니다----------" << endl << endl;
					cin.ignore();
					cin.get();
					break;
				}
				else if (dice1 > dice2)
				{
					cout << player1.getName() << "님이 선을 갖습니다!" << endl << endl;
					starterplayer = 1;
					cout << "-----------엔터를 누르면 게임을 진행합니다----------" << endl << endl;
					cin.ignore();
					cin.get();
					break;
				}
				else if (dice1 == dice2) {
					cout << "---동점! 주사위를 다시 굴리려면 엔터를 눌러주세요---" << endl << endl;
					cin.ignore();
					cin.get();
				}
			}


			//이밸류에이터 생성
			Evaluator eval;

			//덱생성
			cout << "-----------게임에 쓰일 덱을 생성중 입니다-----------" << endl << endl;
			Deck deck;
			Sleep(1000);


			//덱셔플
			cout << "--------------생성된 덱을 셔플중 입니다-------------" << endl << endl;
			deck.shuffle();
			Sleep(1000);


			//테이블 머니 생성
			int tableMoney = 0;
			int betMoney;
			int totalPlayer1_Money = 0;
			int totalPlayer2_Money = 0;
			int preBetMoney = 0;
			int myCard, yourCard; //카드 고를시 나의 카드, 상대방 카드
			bool isCanBetting = true; //베팅할 금액이있는지
			int roundNum = 0; //라운드수 (4라운드 이상이면 끝냄)
			bool roundOn = true;	//라운드 카운터
			bool isCanCall = true;
			int tmpMoney = 0;

			Hand hand1 = player1.getHand();
			Hand hand2 = player2.getHand();

			while (player1.ableToParticipateIn() || player2.ableToParticipateIn()) {

				//현재잔액 공개
				player1.displayMoney();
				player2.displayMoney();
				cout << endl;

				//손카드 버리기 및 새카드받기 (리셋)
				player1.clearHand();
				player2.clearHand();

				Sleep(1000);

				if (!(player1.ableToParticipateIn())) {
					cout << player1.getName() << "은 최소자본($100)이 없으므로 패배하였습니다." << endl;
					cout << player2.getName() << "가 최종승리자입니다. 축하드립니다!!" << endl << endl;
					cout << "게임을 종료합니다.";

					cin.ignore();
					cin.get();
					exit(1);
				}
				if (!(player2.ableToParticipateIn())) {
					cout << player2.getName() << "은 최소자본($100)이 없으므로 패배하였습니다." << endl;
					cout << player1.getName() << "가 최종승리자입니다. 축하드립니다!!" << endl << endl;
					cout << "게임을 종료합니다.";

					cin.ignore();
					cin.get();
					exit(1);
				}

				//카드 드로우
				cout << "카드를 드로우 합니다!" << endl;
				player1.drawCard(&deck);
				player2.drawCard(&deck);
				hand1 = player1.getHand();
				hand2 = player2.getHand();
				Sleep(1000);


				roundOn = true;	//라운드 카운터 초기화
				roundNum = 0; //라운터 턴 초기화
				totalPlayer1_Money = 0;
				totalPlayer2_Money = 0;
				betMoney = 0;
				tableMoney = 0;

				// 첫번째 라운드 첫순서 진행

				while (roundOn && roundNum++ < 4) {

					//몇턴인지 알림
					cout << endl;
					cout << "---------------" << "턴 #" << roundNum << " ----------------" << endl;
					cout << player1.getName() << " 님의 패" << endl;
					hand1.displayHand();
					cout << player2.getName() << " 님의 패" << endl;
					hand2.displayHand();

					isCanBetting = true; //초기화
					isCanCall = true;

					// 1번플레이어 차례 첫턴
					if (starterplayer == 1 && roundNum == 1) {
						cout << endl;
						cout << player1.getName() << "님의 차례입니다." << endl;
						while (1) {
							//betMoney = 0; // 베팅머니 초기화
							cout << "원하시는 행동에 맞는 번호를 선택하여 주십시오. (1. 스테이, 2.베팅, 3. 폴드): ";
							cin >> input; cout << endl;

							if (0 < input && input < 4) {
								switch (input) {
								case 1:
									roundOn = player1.stay();
									starterplayer = 2;
									break;
								case 2:
									while (isCanBetting) {
										//현재잔액 공개
										player1.displayMoney();
										player2.displayMoney();
										cout << endl << endl;

										cout << "베팅하실 금액을 입력하여 주십시오. (금액단위$): $";
										cin >> betMoney; cout << endl;
										if (betMoney > player2.getMoney()) {
											cout << " 상대방의 자금보다 더 많이 베팅할 수 없습니다! " << endl;
										}
										else {
											isCanBetting = !(player1.bet(betMoney));
										}
									}
									tableMoney += betMoney;
									totalPlayer1_Money += betMoney;

									cout << "교환하실 자신의 카드를 선택해 주십시오(1~4): " << endl;
									cin >> myCard;
									cout << "교환하실 상대방의 카드를 선택해 주십시오(1~4):" << endl;
									cin >> yourCard; cout << endl;
									roundOn = player1.swapCard(&hand1, &hand2, myCard - 1, yourCard - 1); // 카드 교환

									hand1.handShuffle();
									hand2.handShuffle();

									if (roundOn == true) {	//사신카드 안뽑을 경우	
										cout << player1.getName() << "님의 " << myCard << "번째 카드와 " << player2.getName() << "님의 " << yourCard << "번째 카드를 교환합니다." << endl;
										cout << player1.getName() << " 님의 패" << endl;
										hand1.displayHand();
										cout << player2.getName() << " 님의 패" << endl;
										hand2.displayHand();

										starterplayer = 2; //선은 2번한테감
									}
									else { //사신카드 뽑을경우
										player1.subMoney(totalPlayer1_Money);
										player2.addMoney(totalPlayer2_Money + totalPlayer1_Money*2);
										cout << player1.getName() << "님이 사신카드를 뽑으셨습니다!" << endl;
										cout << player2.getName() << "님의 승리!!" << player2.getName() << "님이 $" << totalPlayer1_Money*2 << " 를 얻었습니다!" << endl;
										roundOn = false;
										tableMoney = 0; //테이블 머니 리셋
									}
									starterplayer = 2;
									break;
								case 3:
									player1.subMoney(100);
									tableMoney += 100;
									roundOn = player1.fold(player2, tableMoney);
									player2.addMoney(tableMoney);
									tableMoney = 0; //테이블 머니 리셋
									starterplayer = 2;
									break;
								}
								break;
							}

							else {
								cout << "잘못된 입력입니다. 다시 선택해 주세요" << endl;

							}
						}
					}

					//2번플레이어 차례 첫턴
					else if (starterplayer == 2 && roundNum == 1) {
						cout << endl;
						cout << player2.getName() << "님의 차례입니다." << endl;

						while (1) {
							cout << "원하시는 행동에 맞는 번호를 선택하여 주십시오. (1. 스테이, 2.베팅, 3. 폴드): ";
							cin >> input; cout << endl;

							if (0 < input && input < 4) {
								switch (input) {
								case 1:
									roundOn = player2.stay();
									starterplayer = 1;
									break;
								case 2:
									while (isCanBetting) {
										//현재잔액 공개
										player1.displayMoney();
										player2.displayMoney();
										cout << endl << endl;

										cout << "베팅하실 금액을 입력하여 주십시오. (금액단위$): $";
										cin >> betMoney; cout << endl;
										if (betMoney > player1.getMoney()) {
											cout << " 상대방의 자금보다 더 많이 베팅할 수 없습니다! " << endl;
										}
										else {
											isCanBetting = !(player2.bet(betMoney));
										}
									}
									tableMoney += betMoney;
									totalPlayer2_Money += betMoney;

									cout << "교환하실 자신의 카드를 선택해 주십시오(1~4): " << endl;
									cin >> myCard;
									cout << "교환하실 상대방의 카드를 선택해 주십시오(1~4):" << endl;
									cin >> yourCard; cout << endl;
									roundOn = player2.swapCard(&hand2, &hand1, myCard - 1, yourCard - 1); // 카드 교환

									hand1.handShuffle();
									hand2.handShuffle();

									if (roundOn == true) {	//사신카드 안뽑을 경우	
										cout << player2.getName() << "님의 " << myCard << "번째 카드와 " << player1.getName() << "님의 " << yourCard << "번째 카드를 교환합니다." << endl;
										cout << player1.getName() << " 님의 패" << endl;
										hand1.displayHand();
										cout << player2.getName() << " 님의 패" << endl;
										hand2.displayHand();

									}
									else { //사신카드 뽑을경우
										player2.subMoney(totalPlayer2_Money);
										player1.addMoney(totalPlayer1_Money + totalPlayer2_Money * 2);
										cout << player2.getName() << "님이 사신카드를 뽑으셨습니다!" << endl;
										cout << player1.getName() << "님의 승리!!" << player1.getName() << "님이 $" << totalPlayer2_Money * 2 << " 를 얻었습니다!" << endl;
										roundOn = false;
										tableMoney = 0; //테이블 머니 리셋
									}
									starterplayer = 1;
									break;
								case 3:
									player2.subMoney(100);
									tableMoney += 100;
									roundOn = player2.fold(player1, tableMoney);
									player1.addMoney(tableMoney);
									tableMoney = 0; //테이블 머니 리셋
									starterplayer = 1;
									break;
								}
								break;
							}
							else {
								cout << "잘못된 입력입니다. 다시 선택해 주세요" << endl;
							}
						}
					}

					//두번째 턴, 1번플레이어 턴일때
					else if (starterplayer == 1 && (roundNum == 2 || roundNum == 3)) {

						cout << endl;
						cout << player1.getName() << "님의 차례입니다." << endl;
					jump:
						while (1) {
							cout << "원하시는 행동에 맞는 번호를 선택하여 주십시오. (1. 콜, 2.베팅, 3. 폴드): ";
							cin >> input; cout << endl;

							if (0 < input && input < 4) {
								switch (input) {
								case 1:

									if (betMoney > player1.getMoney()) {
										isCanCall = !(player1.call(betMoney));
										goto jump;
									}
									player1.call(betMoney);
									tableMoney += betMoney;
									totalPlayer1_Money += betMoney;
									starterplayer = 2;
									break;
								case 2:
									preBetMoney = betMoney;
									while (isCanBetting) {
										//현재잔액 공개
										player1.displayMoney();
										player2.displayMoney();
										cout << endl << endl;

										cout << "베팅하실 금액을 입력하여 주십시오. (금액단위$): $";
										cin >> tmpMoney; cout << endl;
										//cin >> betMoney; cout << endl;
										if (tmpMoney <= preBetMoney) {
											cout << "상대방보다 낮게 베팅할 수 없습니다" << endl;
											goto jump;
										}
										if (tmpMoney > player2.getMoney()) {
											cout << " 상대방의 자금보다 더 많이 베팅할 수 없습니다! " << endl;
										}
										else {
											betMoney = tmpMoney;  // 추가사항 
											isCanBetting = !(player1.bet(betMoney));
										}
									}
									tableMoney += betMoney;
									totalPlayer1_Money += betMoney;

									cout << "교환하실 자신의 카드를 선택해 주십시오(1~4): " << endl;
									cin >> myCard;
									cout << "교환하실 상대방의 카드를 선택해 주십시오(1~4):" << endl;
									cin >> yourCard; cout << endl;
									roundOn = player1.swapCard(&hand1, &hand2, myCard - 1, yourCard - 1); // 카드 교환

									hand1.handShuffle();
									hand2.handShuffle();

									if (roundOn == true) {	//사신카드 안뽑을 경우	
										cout << player1.getName() << "님의 " << myCard << "번째 카드와 " << player2.getName() << "님의 " << yourCard << "번째 카드를 교환합니다." << endl;
										cout << player1.getName() << " 님의 패" << endl;
										hand1.displayHand();
										cout << player2.getName() << " 님의 패" << endl;
										hand2.displayHand();

									}
									else { //사신카드 뽑을경우
										player1.subMoney(totalPlayer1_Money);
										player2.addMoney(totalPlayer2_Money + totalPlayer1_Money * 2);
										cout << player1.getName() << "님이 사신카드를 뽑으셨습니다!" << endl;
										cout << player2.getName() << "님의 승리!!" << player2.getName() << "님이 $" << totalPlayer1_Money * 2 << " 를 얻었습니다!" << endl;
										roundOn = false;
										tableMoney = 0; //테이블 머니 리셋
									}
									starterplayer = 2;
									break;
								case 3:
									player1.subMoney(100);
									tableMoney += 100;
									roundOn = player1.fold(player2, tableMoney);
									player2.addMoney(tableMoney);
									tableMoney = 0; //테이블 머니 리셋
									starterplayer = 2;
									break;
								}
								break;
							}
							else {
								cout << "잘못된 입력입니다. 다시 선택해 주세요" << endl;
							}
						}
					}

					//2번째턴 2번플레이어순서
					else if (starterplayer == 2 && (roundNum == 2 || roundNum == 3)) {
						cout << endl;
						cout << player2.getName() << "님의 차례입니다." << endl;
					jump1:
						while (1) {
							cout << "원하시는 행동에 맞는 번호를 선택하여 주십시오. (1. 콜, 2.베팅, 3. 폴드): ";
							cin >> input; cout << endl;

							if (0 < input && input < 4) {
								switch (input) {
								case 1:
									if (betMoney > player2.getMoney()) {
										isCanCall = !(player2.call(betMoney));
										goto jump1;
									}
									player2.call(betMoney);
									tableMoney += betMoney;
									totalPlayer2_Money += betMoney;
									starterplayer = 1;
									break;
								case 2:
									preBetMoney = betMoney;
									while (isCanBetting) {
										//현재잔액 공개
										player1.displayMoney();
										player2.displayMoney();
										cout << endl << endl;

										cout << "베팅하실 금액을 입력하여 주십시오. (금액단위$): $";
										cin >> tmpMoney; cout << endl;
										//cin >> betMoney; cout << endl;

										if (tmpMoney <= preBetMoney) {
											cout << "상대방보다 낮게 베팅할 수 없습니다" << endl;
											goto jump1;
										}
										if (tmpMoney > player1.getMoney()) {
											cout << " 상대방의 자금보다 더 많이 베팅할 수 없습니다! " << endl;
										}
										else {
											betMoney = tmpMoney; //추가사항
											isCanBetting = !(player2.bet(betMoney));
										}
									}
									tableMoney += betMoney;
									totalPlayer2_Money += betMoney;

									cout << "교환하실 자신의 카드를 선택해 주십시오(1~4): " << endl;
									cin >> myCard;
									cout << "교환하실 상대방의 카드를 선택해 주십시오(1~4):" << endl;
									cin >> yourCard; cout << endl;
									roundOn = player2.swapCard(&hand2, &hand1, myCard - 1, yourCard - 1); // 카드 교환

									hand1.handShuffle();
									hand2.handShuffle();

									if (roundOn == true) {	//사신카드 안뽑을 경우	
										cout << player2.getName() << "님의 " << myCard << "번째 카드와 " << player1.getName() << "님의 " << yourCard << "번째 카드를 교환합니다." << endl;
										cout << player1.getName() << " 님의 패" << endl;
										hand1.displayHand();
										cout << player2.getName() << " 님의 패" << endl;
										hand2.displayHand();

										starterplayer = 2; //선은 2번한테감
									}
									else { //사신카드 뽑을경우
										player2.subMoney(totalPlayer2_Money);
										player1.addMoney(totalPlayer1_Money + totalPlayer2_Money * 2);
										cout << player2.getName() << "님이 사신카드를 뽑으셨습니다!" << endl;
										cout << player1.getName() << "님의 승리!!" << player1.getName() << "님이 $" << totalPlayer2_Money * 2 << " 를 얻었습니다!" << endl;
										roundOn = false;
										tableMoney = 0; //테이블 머니 리셋
									}
									starterplayer = 1;
									break;
								case 3:
									player2.subMoney(100);
									tableMoney += 100;
									roundOn = player2.fold(player1, tableMoney);
									player1.addMoney(tableMoney);
									tableMoney = 0; //테이블 머니 리셋
									starterplayer = 1;
									break;
								}
								break;
							}
							else {
								cout << "잘못된 입력입니다. 다시 선택해 주세요" << endl;
							}
						}
					}

					//마지막턴 1번플레이어차례
					else if (starterplayer == 1 && roundNum == 4) {
						cout << endl;
						cout << player1.getName() << "님의 차례입니다." << endl;
					jump2:
						while (1) {
							cout << "원하시는 행동에 맞는 번호를 선택하여 주십시오. (1. 콜, 2.폴드): ";
							cin >> input; cout << endl;

							if (0 < input && input < 3) {
								switch (input) {
								case 1:

									if (betMoney > player1.getMoney()) {
										if ((totalPlayer1_Money + betMoney) > totalPlayer2_Money) {
											betMoney = totalPlayer2_Money - totalPlayer1_Money;
											cout << "콜 할 돈이 부족하므로 올인됩니다.  " << player1.getName() << "님의 Call Money 가 $" << betMoney << "로 적용 됩니다." << endl;
											player1.call(betMoney);
											tableMoney += betMoney;
											totalPlayer1_Money += betMoney;
											break;
										}
										isCanCall = !(player1.call(betMoney));
										goto jump2;
									}
									player1.call(betMoney);
									tableMoney += betMoney;
									totalPlayer1_Money += betMoney;
									break;
								case 2:
									player1.subMoney(100);
									tableMoney += 100;
									roundOn = player1.fold(player2, tableMoney);
									player2.addMoney(tableMoney);
									tableMoney = 0; //테이블 머니 리셋
									starterplayer = 2;
									break;

								}
								break;
							}
							else {
								cout << "잘못된 입력입니다. 다시 선택해 주세요" << endl;
							}
						}
					}

					//마지막턴 2번플레이어 차례
					else if (starterplayer == 2 && roundNum == 4) {
						cout << endl;
						cout << player2.getName() << "님의 차례입니다." << endl;
					jump3:
						while (1) {
							cout << "원하시는 행동에 맞는 번호를 선택하여 주십시오. (1. 콜, 2.폴드): ";
							cin >> input; cout << endl;

							if (0 < input && input < 3) {
								switch (input) {
								case 1:
									if (betMoney > player2.getMoney()) {
										if ((totalPlayer2_Money + betMoney) > totalPlayer1_Money) {
											betMoney = totalPlayer1_Money - totalPlayer2_Money;
											cout << "콜 할 돈이 부족하므로 올인됩니다.  " << player2.getName() << "님의 Call Money 가 $" << betMoney << "로 적용 됩니다." << endl;
											player2.call(betMoney);
											tableMoney += betMoney;
											totalPlayer2_Money += betMoney;
											break;
										}
										isCanCall = !(player2.call(betMoney));
										goto jump3;
									}
									player2.call(betMoney);
									totalPlayer2_Money += betMoney;
									tableMoney += betMoney;
									break;
								case 2:
									player2.subMoney(100);
									tableMoney += 100;
									roundOn = player2.fold(player1, tableMoney);
									player1.addMoney(tableMoney);
									tableMoney = 0; //테이블 머니 리셋
									starterplayer = 1;
									break;
								}
								break;
							}
							else {
								cout << "잘못된 입력입니다. 다시 선택해 주세요" << endl;
							}
						}
					}

					else if (player1.getMoney() == 0 || player2.getMoney() == 0) {
						roundNum = 4; roundOn = true;
						cout << "한쪽 플레이어의 돈이 전부다 베팅되었기때문에 바로 승패를 가립니다. Good Luck!" << endl << endl;
						goto Zero1;
					}

					// final라운드  종료 후
					if (roundNum == 4 && roundOn == true) {
					Zero1:
						cout << "모든 라운드가 종료되었습니다. 점수를 집계합니다." << endl;
						score1 = eval.checkScore(hand1);
						score2 = eval.checkScore(hand2);
						cout << player1.getName() << " 님의 패" << endl;
						hand1.displayHand();
						cout << player2.getName() << " 님의 패" << endl;
						hand2.displayHand();

						scorecard1 = eval.scoreCard(score1);
						scorecard2 = eval.scoreCard(score2);

						if (score1 == score2) {
							cout << player1.getName() << "님의 족보: " << scorecard1 << "   " << player2.getName() << "님의 족보: " << scorecard2 << endl;
							player1.addMoney(totalPlayer1_Money);
							player2.addMoney(totalPlayer2_Money);
							cout << "무승부입니다" << endl;
						}
						if (score1 > score2) {
							cout << player1.getName() << "님의 족보: " << scorecard1 << "   " << player2.getName() << "님의 족보: " << scorecard2 << endl;
							cout << player1.getName() << " 승리 " << endl;
							if (score2 == -1) { //사신카드
								cout << "사신카드를 들고있었으므로 2배 패널티가 적용됩니다" << endl;
								player1.addMoney(totalPlayer1_Money + totalPlayer2_Money * 2);
								player2.subMoney(totalPlayer2_Money);
							}
							else {
								player1.addMoney(tableMoney);
							}
							starterplayer = 1;
						}
						if (score1 < score2) {
							cout << player1.getName() << "님의 족보: " << scorecard1 << "   " << player2.getName() << "님의 족보: " << scorecard2 << endl;
							cout << player2.getName() << "승리" << endl;
							if (score1 == -1) { //사신카드
								cout << "사신카드를 들고있었으므로 2배 패널티가 적용됩니다" << endl;
								player2.addMoney(totalPlayer2_Money + totalPlayer1_Money * 2);
								player1.subMoney(totalPlayer1_Money);
							}
							else {
								player2.addMoney(tableMoney);
							}

							starterplayer = 2;
						}

						tableMoney = 0;
						roundOn = false;
					}
				}

				cout << endl << "계속하시려면 엔터를 눌러주세요..." << endl;
				while (1) {
					cin.ignore();
					if (cin.peek() == '\n') break;
				}
			}
		}
		else if (gameRule == 2) {
			cout << "상대방 패 비공개 모드를 선택하였습니다" << endl;

			// 이름 및 금액 설정
			cout << "플레이어 1번님의 이름을 입력해 주십시오." << endl << "이름: ";
			cin >> temp_name1;
			while (1) {
				cout << "플레이어 1번님의 시작 금액을 입력해 주십시오." << endl << "금액: ";
				cin >> temp_money1;
				if (cin.fail()) {
					cout << "잘못된 입력입니다. 다시 입력해주세요" << endl << endl; // 에러메세지
					cin.clear(); cin.ignore(256, '\n');
				}
				else if (temp_money1 < 100) {
					cout << "!!시작 금액은 $100 이상이여야 합니다!!" << endl << endl;
				}
				else
					break;
			}

			cout << endl;

			cout << "플레이어 2번님의 이름을 입력해 주십시오." << endl << "이름: ";
			cin >> temp_name2;
			while (1) {
				cout << "플레이어 2번님의 시작 금액을 입력해 주십시오." << endl << "금액: ";
				cin >> temp_money2;
				if (cin.fail()) {
					cout << "!!--잘못된 입력입니다. 다시 입력해주세요--!!" << endl << endl; // 에러메세지
					cin.clear(); cin.ignore(256, '\n');
				}
				else if (temp_money2 < 100) {
					cout << "!!---시작 금액은 $100 이상이여야 합니다---!!" << endl << endl;
				}
				else
					break;
			}
			cout << endl;


			//플레이어 생성
			Player player1(temp_name1, temp_money1);
			Player player2(temp_name2, temp_money2);;

			//주사위 굴리기 시작순서 정하기
			cout << "주사위를 던져 순서를 정합니다." << endl;
			Sleep(1000);
			while (1) {
				(srand((unsigned int)time(0)));
				dice1 = (int)(rand() % (6)) + 1;
				dice2 = (int)(rand() % (6)) + 1;
				cout << player1.getName() << "님의 주사위 값: " << dice1 << endl;
				Sleep(1000);

				cout << player2.getName() << "님의 주사위 값: " << dice2 << endl << endl;
				Sleep(1000);

				if (dice1 < dice2)
				{
					cout << player2.getName() << "님이 선을 갖습니다!" << endl << endl;
					starterplayer = 2;
					cout << "-----------엔터를 누르면 게임을 진행합니다----------" << endl << endl;
					cin.ignore();
					cin.get();
					break;
				}
				else if (dice1 > dice2)
				{
					cout << player1.getName() << "님이 선을 갖습니다!" << endl << endl;
					starterplayer = 1;
					cout << "-----------엔터를 누르면 게임을 진행합니다----------" << endl << endl;
					cin.ignore();
					cin.get();
					break;
				}
				else if (dice1 == dice2) {
					cout << "---동점! 주사위를 다시 굴리려면 엔터를 눌러주세요---" << endl << endl;
					cin.ignore();
					cin.get();
				}
			}


			//이밸류에이터 생성
			Evaluator eval;

			//덱생성
			cout << "-----------게임에 쓰일 덱을 생성중 입니다-----------" << endl << endl;
			Deck deck;
			Sleep(1000);


			//덱셔플
			cout << "--------------생성된 덱을 셔플중 입니다-------------" << endl << endl;
			deck.shuffle();
			Sleep(1000);


			//테이블 머니 생성
			int tableMoney = 0;
			int betMoney;
			int totalPlayer1_Money = 0;
			int totalPlayer2_Money = 0;
			int myCard, yourCard; //카드 고를시 나의 카드, 상대방 카드
			int preBetMoney = 0;
			bool isCanBetting = true; //베팅할 금액이있는지
			bool isCanCall = true;
			int roundNum = 0; //라운드수 (4라운드 이상이면 끝냄)
			bool roundOn = true;	//라운드 카운터

			Hand hand1 = player1.getHand();
			Hand hand2 = player2.getHand();

			while (player1.ableToParticipateIn() || player2.ableToParticipateIn()) {

				//현재잔액 공개
				player1.displayMoney();
				player2.displayMoney();
				cout << endl;

				if (!(player1.ableToParticipateIn())) {
					cout << player1.getName() << "은 최소자본($100)이 없으므로 패배하였습니다." << endl;
					cout << player2.getName() << "가 최종승리자입니다. 축하드립니다!!" << endl;
					break;
				}
				if (!(player2.ableToParticipateIn())) {
					cout << player2.getName() << "은 최소자본($100)이 없으므로 패배하였습니다." << endl;
					cout << player1.getName() << "가 최종승리자입니다. 축하드립니다!!" << endl;
					break;
				}

				//손카드 버리기 및 새카드받기 (리셋)
				player1.clearHand();
				player2.clearHand();

				//카드 드로우
				cout << "카드를 드로우 합니다!" << endl;
				player1.drawCard(&deck);
				player2.drawCard(&deck);
				hand1 = player1.getHand();
				hand2 = player2.getHand();
				Sleep(1000);


				roundOn = true;	//라운드 카운터 초기화
				roundNum = 0; //라운터 턴 초기화
				totalPlayer1_Money = 0;
				totalPlayer2_Money = 0;
				betMoney = 0;
				tableMoney = 0;

				// 첫번째 라운드 첫순서 진행

				while (roundOn && roundNum++ < 4) {

					//몇턴인지 알림
					cout << endl;
					cout << "---------------" << "턴 #" << roundNum << " ----------------" << endl;


					isCanBetting = true; //초기화
					isCanCall = true;

					// 1번플레이어 차례 첫턴
					if (starterplayer == 1 && roundNum == 1) {
						cout << player1.getName() << " 님의 패" << endl;
						hand1.displayHand();
						cout << player2.getName() << " 님의 패" << endl;
						hand2.displayHiddenHand();
						cout << endl;
						cout << player1.getName() << "님의 차례입니다." << endl;
						while (1) {
							//betMoney = 0; // 베팅머니 초기화
							cout << "원하시는 행동에 맞는 번호를 선택하여 주십시오. (1. 스테이, 2.베팅, 3. 폴드): ";
							cin >> input; cout << endl;

							if (0 < input && input < 4) {
								switch (input) {
								case 1:
									roundOn = player1.stay();
									starterplayer = 2;
									break;
								case 2:
									while (isCanBetting) {
										//현재잔액 공개
										player1.displayMoney();
										player2.displayMoney();
										cout << endl << endl;

										cout << "베팅하실 금액을 입력하여 주십시오. (금액단위$): $";
										cin >> betMoney; cout << endl;
										if (betMoney > player2.getMoney()) {
											cout << " 상대방의 자금보다 더 많이 베팅할 수 없습니다! " << endl;
										}
										else {
											isCanBetting = !(player1.bet(betMoney));
										}
									}
									tableMoney += betMoney;
									totalPlayer1_Money += betMoney;

									cout << "교환하실 자신의 카드를 선택해 주십시오(1~4): " << endl;
									cin >> myCard;
									cout << "교환하실 상대방의 카드를 선택해 주십시오(1~4):" << endl;
									cin >> yourCard; cout << endl;
									roundOn = player1.swapCard(&hand1, &hand2, myCard - 1, yourCard - 1); // 카드 교환

									hand1.handShuffle();
									hand2.handShuffle();

									if (roundOn == true) {	//사신카드 안뽑을 경우	
										cout << player1.getName() << "님의 " << myCard << "번째 카드와 " << player2.getName() << "님의 " << yourCard << "번째 카드를 교환합니다." << endl;
										cout << player1.getName() << " 님의 패" << endl;
										hand1.displayHand();
										cout << player2.getName() << " 님의 패" << endl;
										hand2.displayHand();

										starterplayer = 2; //선은 2번한테감
									}
									else { //사신카드 뽑을경우
										player1.subMoney(totalPlayer1_Money);
										player2.addMoney(totalPlayer2_Money + totalPlayer1_Money * 2);
										cout << player1.getName() << "님이 사신카드를 뽑으셨습니다!" << endl;
										cout << player2.getName() << "님의 승리!!" << player2.getName() << "님이 $" << totalPlayer1_Money * 2 << " 를 얻었습니다!" << endl;
										roundOn = false;
										tableMoney = 0; //테이블 머니 리셋
									}
									starterplayer = 2;
									break;
								case 3:
									player1.subMoney(100);
									tableMoney += 100;
									roundOn = player1.fold(player2, tableMoney);
									player2.addMoney(tableMoney);
									tableMoney = 0; //테이블 머니 리셋
									starterplayer = 2;
									break;
								}
								break;
							}

							else {
								cout << "잘못된 입력입니다. 다시 선택해 주세요" << endl;

							}
						}
					}

					//2번플레이어 차례 첫턴
					else if (starterplayer == 2 && roundNum == 1) {
						cout << player1.getName() << " 님의 패" << endl;
						hand1.displayHiddenHand();
						cout << player2.getName() << " 님의 패" << endl;
						hand2.displayHand();
						cout << endl;
						cout << player2.getName() << "님의 차례입니다." << endl;

						while (1) {
							cout << "원하시는 행동에 맞는 번호를 선택하여 주십시오. (1. 스테이, 2.베팅, 3. 폴드): ";
							cin >> input; cout << endl;

							if (0 < input && input < 4) {
								switch (input) {
								case 1:
									roundOn = player2.stay();
									starterplayer = 1;
									break;
								case 2:
									while (isCanBetting) {
										//현재잔액 공개
										player1.displayMoney();
										player2.displayMoney();
										cout << endl << endl;

										cout << "베팅하실 금액을 입력하여 주십시오. (금액단위$): $";
										cin >> betMoney; cout << endl;
										if (betMoney > player1.getMoney()) {
											cout << " 상대방의 자금보다 더 많이 베팅할 수 없습니다! " << endl;
										}
										else {
											isCanBetting = !(player2.bet(betMoney));
										}
									}
									tableMoney += betMoney;
									totalPlayer2_Money += betMoney;

									cout << "교환하실 자신의 카드를 선택해 주십시오(1~4): " << endl;
									cin >> myCard;
									cout << "교환하실 상대방의 카드를 선택해 주십시오(1~4):" << endl;
									cin >> yourCard; cout << endl;
									roundOn = player2.swapCard(&hand2, &hand1, myCard - 1, yourCard - 1); // 카드 교환

									hand1.handShuffle();
									hand2.handShuffle();

									if (roundOn == true) {	//사신카드 안뽑을 경우	
										cout << player2.getName() << "님의 " << myCard << "번째 카드와 " << player1.getName() << "님의 " << yourCard << "번째 카드를 교환합니다." << endl;
										cout << player1.getName() << " 님의 패" << endl;
										hand1.displayHand();
										cout << player2.getName() << " 님의 패" << endl;
										hand2.displayHand();

										starterplayer = 2; //선은 2번한테감
									}
									else { //사신카드 뽑을경우
										player2.subMoney(totalPlayer2_Money);
										player1.addMoney(totalPlayer1_Money + totalPlayer2_Money * 2);
										cout << player2.getName() << "님이 사신카드를 뽑으셨습니다!" << endl;
										cout << player1.getName() << "님의 승리!!" << player2.getName() << "님이 $" << totalPlayer2_Money*2 << " 를 얻었습니다!" << endl;
										roundOn = false;
										tableMoney = 0; //테이블 머니 리셋
									}
									starterplayer = 1;
									break;
								case 3:
									player2.subMoney(100);
									tableMoney += 100;
									roundOn = player2.fold(player1, tableMoney);
									player1.addMoney(tableMoney);
									tableMoney = 0; //테이블 머니 리셋
									starterplayer = 1;
									break;
								}
								break;
							}
							else {
								cout << "잘못된 입력입니다. 다시 선택해 주세요" << endl;
							}
						}
					}

					//두번째 턴, 1번플레이어 턴일때
					else if (starterplayer == 1 && (roundNum == 2 || roundNum == 3)) {
						cout << player1.getName() << " 님의 패" << endl;
						hand1.displayHand();
						cout << player2.getName() << " 님의 패" << endl;
						hand2.displayHiddenHand();
						cout << endl;
						cout << player1.getName() << "님의 차례입니다." << endl;
					jump4:
						while (1) {
							cout << "원하시는 행동에 맞는 번호를 선택하여 주십시오. (1. 콜, 2.베팅, 3. 폴드): ";
							cin >> input; cout << endl;

							if (0 < input && input < 4) {
								switch (input) {
								case 1:
									if (betMoney > player1.getMoney()) {
										isCanCall = !(player1.call(betMoney));
										goto jump4;
									}
									player1.call(betMoney);
									tableMoney += betMoney;
									totalPlayer1_Money += betMoney;
									starterplayer = 2;
									break;
								case 2:
									preBetMoney = betMoney;
									while (isCanBetting) {
										//현재잔액 공개
										player1.displayMoney();
										player2.displayMoney();
										cout << endl << endl;

										cout << "베팅하실 금액을 입력하여 주십시오. (금액단위$): $";
										cin >> betMoney; cout << endl;
										if (betMoney <= preBetMoney) {
											cout << "상대방보다 낮게 베팅할 수 없습니다" << endl;
											goto jump4;
										}
										if (betMoney > player2.getMoney()) {
											cout << " 상대방의 자금보다 더 많이 베팅할 수 없습니다! " << endl;
										}
										else {
											isCanBetting = !(player1.bet(betMoney));
										}
									}
									tableMoney += betMoney;
									totalPlayer1_Money += betMoney;

									cout << "교환하실 자신의 카드를 선택해 주십시오(1~4): " << endl;
									cin >> myCard;
									cout << "교환하실 상대방의 카드를 선택해 주십시오(1~4):" << endl;
									cin >> yourCard; cout << endl;
									roundOn = player1.swapCard(&hand1, &hand2, myCard - 1, yourCard - 1); // 카드 교환

									hand1.handShuffle();
									hand2.handShuffle();

									if (roundOn == true) {	//사신카드 안뽑을 경우	
										cout << player1.getName() << "님의 " << myCard << "번째 카드와 " << player2.getName() << "님의 " << yourCard << "번째 카드를 교환합니다." << endl;
										cout << player1.getName() << " 님의 패" << endl;
										hand1.displayHand();
										cout << player2.getName() << " 님의 패" << endl;
										hand2.displayHand();

										starterplayer = 2; //선은 2번한테감
									}
									else { //사신카드 뽑을경우
										player1.subMoney(totalPlayer1_Money);
										player2.addMoney(totalPlayer2_Money + totalPlayer1_Money * 2);
										cout << player1.getName() << "님이 사신카드를 뽑으셨습니다!" << endl;
										cout << player2.getName() << "님의 승리!!" << player2.getName() << "님이 $" << totalPlayer1_Money * 2 << " 를 얻었습니다!" << endl;
										roundOn = false;
										tableMoney = 0; //테이블 머니 리셋
									}
									starterplayer = 2;
									break;
								case 3:
									player1.subMoney(100);
									tableMoney += 100;
									roundOn = player1.fold(player2, tableMoney);
									player2.addMoney(tableMoney);
									tableMoney = 0; //테이블 머니 리셋
									starterplayer = 2;
									break;
								}
								break;
							}
							else {
								cout << "잘못된 입력입니다. 다시 선택해 주세요" << endl;
							}
						}
					}

					//2번째턴 2번플레이어순서
					else if (starterplayer == 2 && (roundNum == 2 || roundNum == 3)) {
						cout << player1.getName() << " 님의 패" << endl;
						hand1.displayHiddenHand();
						cout << player2.getName() << " 님의 패" << endl;
						hand2.displayHand();
						cout << endl;
						cout << player2.getName() << "님의 차례입니다." << endl;
					jump5:
						while (1) {
							cout << "원하시는 행동에 맞는 번호를 선택하여 주십시오. (1. 콜, 2.베팅, 3. 폴드): ";
							cin >> input; cout << endl;

							if (0 < input && input < 4) {
								switch (input) {
								case 1:
									if (betMoney > player2.getMoney()) {
										isCanCall = !(player2.call(betMoney));
										goto jump5;
									}
									player2.call(betMoney);
									tableMoney += betMoney;
									totalPlayer2_Money += betMoney;
									starterplayer = 1;
									break;
								case 2:
									preBetMoney = betMoney;
									while (isCanBetting) {
										//현재잔액 공개
										player1.displayMoney();
										player2.displayMoney();
										cout << endl << endl;

										cout << "베팅하실 금액을 입력하여 주십시오. (금액단위$): $";
										cin >> betMoney; cout << endl;

										if (betMoney <= preBetMoney) {
											cout << "상대방보다 낮게 베팅할 수 없습니다" << endl;
											goto jump5;
										}
										if (betMoney > player1.getMoney()) {
											cout << " 상대방의 자금보다 더 많이 베팅할 수 없습니다! " << endl;
										}
										else {
											isCanBetting = !(player2.bet(betMoney));
										}
									}
									tableMoney += betMoney;
									totalPlayer2_Money += betMoney;

									cout << "교환하실 자신의 카드를 선택해 주십시오(1~4): " << endl;
									cin >> myCard;
									cout << "교환하실 상대방의 카드를 선택해 주십시오(1~4):" << endl;
									cin >> yourCard; cout << endl;
									roundOn = player2.swapCard(&hand2, &hand1, myCard - 1, yourCard - 1); // 카드 교환

									hand1.handShuffle();
									hand2.handShuffle();

									if (roundOn == true) {	//사신카드 안뽑을 경우	
										cout << player2.getName() << "님의 " << myCard << "번째 카드와 " << player1.getName() << "님의 " << yourCard << "번째 카드를 교환합니다." << endl;
										cout << player1.getName() << " 님의 패" << endl;
										hand1.displayHand();
										cout << player2.getName() << " 님의 패" << endl;
										hand2.displayHand();

										starterplayer = 2; //선은 2번한테감
									}
									else { //사신카드 뽑을경우
										player2.subMoney(totalPlayer2_Money);
										player1.addMoney(totalPlayer1_Money + totalPlayer2_Money * 2);
										cout << player2.getName() << "님이 사신카드를 뽑으셨습니다!" << endl;
										cout << player1.getName() << "님의 승리!!" << player2.getName() << "님이 $" << totalPlayer2_Money * 2 << " 를 얻었습니다!" << endl;
										roundOn = false;
										tableMoney = 0; //테이블 머니 리셋
									}
									starterplayer = 1;
									break;
								case 3:
									player2.subMoney(100);
									tableMoney += 100;
									roundOn = player2.fold(player1, tableMoney);
									player1.addMoney(tableMoney);
									tableMoney = 0; //테이블 머니 리셋
									starterplayer = 1;
									break;
								}
								break;
							}
							else {
								cout << "잘못된 입력입니다. 다시 선택해 주세요" << endl;
							}
						}
					}

					//마지막턴 1번플레이어차례
					else if (starterplayer == 1 && roundNum == 4) {
						cout << player1.getName() << " 님의 패" << endl;
						hand1.displayHand();
						cout << player2.getName() << " 님의 패" << endl;
						hand2.displayHiddenHand();
						cout << endl;
						cout << player1.getName() << "님의 차례입니다." << endl;
					jump6:
						while (1) {
							cout << "원하시는 행동에 맞는 번호를 선택하여 주십시오. (1. 콜, 2.폴드): ";
							cin >> input; cout << endl;

							if (0 < input && input < 3) {
								switch (input) {
								case 1:
									if (betMoney > player1.getMoney()) {
										if ((totalPlayer1_Money + betMoney) > totalPlayer2_Money) {
											betMoney = totalPlayer2_Money - totalPlayer1_Money;
											cout << "콜 할 돈이 부족하므로 올인됩니다.  " << player1.getName() << "님의 Call Money 가 $" << betMoney << "로 적용 됩니다." << endl;
											player1.call(betMoney);
											tableMoney += betMoney;
											totalPlayer1_Money += betMoney;
											break;
										}
										isCanCall = !(player1.call(betMoney));
										goto jump6;
									}
									player1.call(betMoney);
									tableMoney += betMoney;
									totalPlayer1_Money += betMoney;
									break;
								case 2:
									player1.subMoney(100);
									tableMoney += 100;
									roundOn = player1.fold(player2, tableMoney);
									player2.addMoney(tableMoney);
									tableMoney = 0; //테이블 머니 리셋
									starterplayer = 2;
									break;

								}
								break;
							}
							else {
								cout << "잘못된 입력입니다. 다시 선택해 주세요" << endl;
							}
						}
					}

					//마지막턴 2번플레이어 차례
					else if (starterplayer == 2 && roundNum == 4) {
						cout << player1.getName() << " 님의 패" << endl;
						hand1.displayHiddenHand();
						cout << player2.getName() << " 님의 패" << endl;
						hand2.displayHand();
						cout << endl;
						cout << player2.getName() << "님의 차례입니다." << endl;
					jump7:
						while (1) {
							cout << "원하시는 행동에 맞는 번호를 선택하여 주십시오. (1. 콜, 2.폴드): ";
							cin >> input; cout << endl;

							if (0 < input && input < 3) {
								switch (input) {
								case 1:
									if (betMoney > player1.getMoney()) {
										if ((totalPlayer2_Money + betMoney) > totalPlayer1_Money) {
											betMoney = totalPlayer1_Money - totalPlayer2_Money;
											cout << "콜 할 돈이 부족하므로 올인됩니다.  " << player2.getName() << "님의 Call Money 가 $" << betMoney << "로 적용 됩니다." << endl;
											player2.call(betMoney);
											tableMoney += betMoney;
											totalPlayer2_Money += betMoney;
											break;
										}
										isCanCall = !(player2.call(betMoney));
										goto jump7;
									}
									player2.call(betMoney);
									totalPlayer2_Money += betMoney;
									tableMoney += betMoney;
									break;
								case 2:
									player2.subMoney(100);
									tableMoney += 100;
									roundOn = player2.fold(player1, tableMoney);
									player1.addMoney(tableMoney);
									tableMoney = 0; //테이블 머니 리셋
									starterplayer = 1;
									break;
								}
								break;
							}
							else {
								cout << "잘못된 입력입니다. 다시 선택해 주세요" << endl;
							}
						}
					}

					else if (player1.getMoney() == 0 || player2.getMoney() == 0) {
						roundNum = 4; roundOn = true;
						cout << "한쪽 플레이어의 돈이 전부다 베팅되었기때문에 바로 승패를 가립니다. Good Luck!" << endl << endl;
						goto Zero2;
					}

					// final라운드  종료 후
					if (roundNum == 4 && roundOn == true) {
					Zero2:
						cout << "모든 라운드가 종료되었습니다. 점수를 집계합니다." << endl;
						score1 = eval.checkScore(hand1);
						score2 = eval.checkScore(hand2);
						cout << player1.getName() << " 님의 패" << endl;
						hand1.displayHand();
						cout << player2.getName() << " 님의 패" << endl;
						hand2.displayHand();

						scorecard1 = eval.scoreCard(score1);
						scorecard2 = eval.scoreCard(score2);

						if (score1 == score2) {
							cout << player1.getName() << "님의 족보: " << scorecard1 << "   " << player2.getName() << "님의 족보: " << scorecard2 << endl;
							player1.addMoney(totalPlayer1_Money);
							player2.addMoney(totalPlayer2_Money);
							cout << "무승부입니다" << endl;
						}
						if (score1 > score2) {
							cout << player1.getName() << "님의 족보: " << scorecard1 << "   " << player2.getName() << "님의 족보: " << scorecard2 << endl;
							cout << player1.getName() << " 승리 " << endl;
							if (score2 == -1) { //사신카드
								cout << "사신카드를 들고있었으므로 2배 패널티가 적용됩니다" << endl;
								player1.addMoney(totalPlayer1_Money + totalPlayer2_Money * 2);
								player2.subMoney(totalPlayer2_Money);
							}
							else {
								player1.addMoney(tableMoney);
							}
							starterplayer = 1;
						}
						if (score1 < score2) {
							cout << player1.getName() << "님의 족보: " << scorecard1 << "   " << player2.getName() << "님의 족보: " << scorecard2 << endl;
							cout << player2.getName() << "승리" << endl;
							if (score1 == -1) { //사신카드
								cout << "사신카드를 들고있었으므로 2배 패널티가 적용됩니다" << endl;
								player2.addMoney(totalPlayer2_Money + totalPlayer1_Money * 2);
								player1.subMoney(totalPlayer1_Money);
							}
							else {
								player2.addMoney(tableMoney);
							}

							starterplayer = 2;
						}

						tableMoney = 0;
						roundOn = false;
					}
				}

				cout << endl << "계속하시려면 엔터를 눌러주세요..." << endl;
				while (1) {
					cin.ignore();
					if (cin.peek() == '\n') break;
				}
			}
		}
		else {
			cout << "번호를 잘못입력하였습니다. 다시 입력해주십시오" << endl;
			cin >> gameRule; cout << endl;
		}
	}
	exit(1);
}
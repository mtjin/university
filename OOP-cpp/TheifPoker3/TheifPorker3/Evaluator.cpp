#include "pch.h"
#include "Evaluator.h"
#include "Player.h"
#include "Hand.h"
#include <iostream>
#include <string>
#include <algorithm>  //소팅알고리즘

using namespace std;
Evaluator::Evaluator()
{
}


Evaluator::~Evaluator()
{
}


int Evaluator::checkScore(Hand tmphand) {
	int arr[4];
	int score = 0;	// 족보. 원카드 = 1, 트리플 = 2, 스트레이트 = 3, 포카드 = 4, 사신 = 0
	for (int i = 0; i < tmphand.getCardcount() ; i++) {  //손에들고있던 카드값들을 배열에 담아줌
		arr[i] = tmphand.getCard(i).getRank();
	}
	
	sort(arr, arr + 4);	// 가져온 패를 왼쪽부터 작은 숫자대로 정렬

	if (arr[0] == 0) {	// 사신카드 있음
		return -1;
	}
	else if (arr[0] == 1 && arr[1] == 2 && arr[2] == 3 && arr[3] == 4) {
		return 3;
	}
	else if (arr[0] == 2 && arr[1] == 3 && arr[2] == 4 && arr[3] == 5) {
		return 3;
	}
	else {
		for (int i = 0; i < 3; i++) {
			for (int j = i + 1; j < 4; j++) {
				if (arr[i] == arr[j]) {		// 원페어
					if (i == 2) {
						return 1;
					}
					else if (arr[i] == arr[j+1]) {		// 트리플
						if (i == 1) {
							return 2;
						}
						else if (arr[i] == arr[j+2]) {		// 포카드
							return 4;
						}
						
						return 2;
					}
					
					return 1;
				}
			}
		}
	}
	return 0;  //족보없는 카드인경우
}

string Evaluator::scoreCard(int score) {
	string temp;
	switch (score) {
	case 1:
		temp = "원페어";
		break;
	case 2:
		temp = "트리플";
		break;
	case 3:
		temp = "스트레이트";
		break;
	case 4:
		temp = "포카드";
		break;
	case -1:
		temp = "사신 카드";
		break;
	case 0:
		temp = "족보 없음";
		break;
	}
	return temp;						
}



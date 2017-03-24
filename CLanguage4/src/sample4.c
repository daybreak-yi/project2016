/*
 * sample4.c
 *
 *  Created on: 2017/03/24
 *      Author: admin
 */

#include <stdio.h>
#include "sample4.h"

int main() {
	int number = 5;
	int result;

	result = exec(calc, number);
	printf("%d", result);
	return 0;
}

/** 2乗を計算するプログラム */
int calc(int num) {
	return num * num;
}

/** 関数ポインタを実行するプログラム */
int exec(int (*p)(int), int num) {
	return p(num);
}

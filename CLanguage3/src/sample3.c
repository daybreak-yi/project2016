/*
 * sample3.c
 *
 *  Created on: 2017/03/24
 *      Author: admin
 */

#include <stdio.h>
#include "sample3.h"

int main() {
	int number = 5;
	int (*p)(int);
	p = calc;

	printf("%s(%d) = %d\r\n", "p", number, calc(number));
	printf("%s(%d) = %d\r\n", "p", number, p(number));

	return 0;
}

/** 2乗を計算するプログラム */
int calc(int num) {
	return num * num;
}

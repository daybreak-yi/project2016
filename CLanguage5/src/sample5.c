/*
 * sample5.c
 *
 *  Created on: 2017/03/24
 *      Author: admin
 */

#include <stdio.h>
#include "sample5.h"

int main() {
	int items[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	int result;

	result = calc(items);
	printf("%d", result);
	return 0;
}

/** 配列の内容を計算する */
int calc(int array[]) {
	int i = 0;
	int value = 0;
	for (i = 0; i < 9; i++) {
		value = value + array[i];
	}
	return value;
}

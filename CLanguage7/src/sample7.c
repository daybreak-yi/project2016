/*
 * sample7.c
 *
 *  Created on: 2017/03/24
 *      Author: admin
 */

#include <stdio.h>
#include "sample7.h"

int main() {
	int array[9] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	int result;

	result = exec(calc, array);
	printf("%d", result);
	return 0;
}

int calc(int array[9]) {
	int i = 0;
	int value = 0;
	for (i = 0; i < 9; i++) {
		value = value + array[i];
	}
	return value;
}

int exec(int (*p)(int[]), int l[9]) {
	return p(l);
}

/*
 * sample6.c
 *
 *  Created on: 2017/03/24
 *      Author: admin
 */

#include <stdio.h>
#include "sample6.h"

int main() {
	int array[3] = { 1, 2, 3 };
	int *p;
	int result;

	p = array;
	result = calc(p);
	printf("%d", result);
	return 0;
}

int calc(int *p) {
	int i = 0;
	int value = 0;
	for (i = 0; i < 3; i++) {
		value = value + p[i];
	}
	return value;
}

/*
 * sample2.c
 *
 *  Created on: 2017/03/24
 *      Author: admin
 */

#include <stdio.h>
#include "sample2.h"

int main() {
	int width;
	int height;
	int result;
	int result1;
	int *result2;
	int *result3;

	width = 800;
	height = 600;

	result = calc(width, height);
	printf("%-10s = %d\r\n", "result", result);

	result1 = pointerCalc1(&width, &height);
	printf("%-10s = %d\r\n", "result1", result1);

	result2 = pointerCalc2(width, height);
	printf("%-10s = %d\r\n", "*result2", *result2);

	result3 = pointerCalc3(&width, &height);
	printf("%-10s = %d\r\n", "*result3", *result3);

	return 0;
}

/** 計算処理 */
int calc(int x, int y) {
	return x * y;
}

/** ポインタを使った計算処理1 */
int pointerCalc1(int *x, int *y) {
	return *x * *y;
}

/** ポインタを使った計算処理2 */
int* pointerCalc2(int x, int y) {
	int z;
	z = x * y;
	return &z;
}

/** ポインタを使った計算処理3 */
int* pointerCalc3(int *x, int *y) {
	int z;
	z = *x * *y;
	return &z;
}

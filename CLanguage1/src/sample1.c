/*
 * sample1.c
 *
 *  Created on: 2017/03/24
 *      Author: admin
 */

#include <stdio.h>

int main() {
	int amount;
	int *p;

	amount = 321;

	printf("%-10s = %-10d\r\n", "amount", amount);
	printf("%-10s = 0x%010X\r\n", "amount", &amount);

	p = &amount;

	printf("%-10s = %-10d\r\n", "*p", *p);
	printf("%-10s = 0x%010X\r\n", "p", &p);

	return 0;
}

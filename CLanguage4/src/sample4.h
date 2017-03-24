/*
 * sample4.h
 *
 *  Created on: 2017/03/24
 *      Author: admin
 */

#ifndef SAMPLE4_H_
#define SAMPLE4_H_

/** 2乗を計算するプログラム */
int calc(int num);

/** 関数ポインタを実行するプログラム */
int exec(int (*p)(int), int num);

#endif /* SAMPLE4_H_ */

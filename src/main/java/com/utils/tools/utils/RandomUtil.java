package com.utils.tools.utils;

import java.util.Random;

public class RandomUtil {
	
	/**
	 * 生成6位不重复的随机数
	 * @return 6位不重复的随机数
	 */
	public static int get6npRandom(){
		int[] array = {1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 9; i > 1; i--) {
		    int index = rand.nextInt(i);
		    int tmp = array[index];
		    array[index] = array[i - 1];
		    array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < 6; i++){
		    result = result * 10 + array[i];
		}
		return result;
	}
	/**
	 * 生成指定位数的验证码
	 * getRandom:TODO
	 * @author notepad
	 * @param n
	 * @return
	 */
	public static int getRandom(int n){
		int[] array = {1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 9; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < n; i++){
			result = result * 10 + array[i];
		}
		return result;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(getRandom(4));
		}

	}

}

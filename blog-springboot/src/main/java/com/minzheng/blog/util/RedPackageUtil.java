package com.minzheng.blog.util;


import java.util.Arrays;
import java.util.Random;

public class RedPackageUtil {

    public static Integer[] splitRedPackageAlSearchAlgorithm(int totalMoney, int redPackageNumber) {
        Integer[] redPackageNumbers = new Integer[redPackageNumber];
        int sum=0;
        for (int i = 0; i < redPackageNumber; i++) {
            if (i==redPackageNumbers.length-1){
                redPackageNumbers[i]=totalMoney-sum;
            }else {
               int num=((totalMoney-sum )/ redPackageNumber-i )*2;
                redPackageNumbers[i]=1+new Random().nextInt(num-1);

            }
            sum += redPackageNumbers[i];
        }
        return redPackageNumbers;
    }

    public static void main(String[] args) {
        Integer[] integers = splitRedPackageAlSearchAlgorithm(100, 5);
        System.out.println(Arrays.toString(integers));
    }

}

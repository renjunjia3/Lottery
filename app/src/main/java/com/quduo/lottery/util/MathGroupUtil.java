package com.quduo.lottery.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 生成数据的工具类
 * Created by scene on 2017/12/20.
 */

public class MathGroupUtil {
    private static void recursionSub(LinkedList<String[]> list, int count, String[] array, int ind, int start, int... indexs) {
        start++;
        if (start > count - 1) {
            return;
        }
        if (start == 0) {
            indexs = new int[array.length];
        }
        for (indexs[start] = ind; indexs[start] < array.length; indexs[start]++) {
            recursionSub(list, count, array, indexs[start] + 1, start, indexs);
            if (start == count - 1) {
                String[] temp = new String[count];
                for (int i = count - 1; i >= 0; i--) {
                    temp[start - i] = array[indexs[start - i]];
                }
                list.add(temp);
            }
        }
    }

    public static int getCount(int totalChoosedNumber, int needNumber) {
        String[] A = new String[totalChoosedNumber];
        LinkedList<String[]> list = new LinkedList<>();
        recursionSub(list, needNumber, A, 0, -1);
        return list.size();
    }

    public static LinkedList<String[]> getList(int totalChoosedNumber, int needNumber) {
        String[] A = new String[totalChoosedNumber];
        LinkedList<String[]> list = new LinkedList<>();
        recursionSub(list, needNumber, A, 0, -1);
        return list;
    }

    /**
     * 生成随机数集合
     *
     * @param needNum 需要的数量
     * @param range   范围
     * @return 返回集合
     */
    public static int[] creatRandom(int needNum, int range) {
        int random[] = new int[needNum];
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < range; i++)
            arr.add(i);// 为ArrayList添加元素
        for (int j = 0; j < needNum; j++) {
            int index = (int) (Math.random() * range);// 产生一个随机数作为索引
            random[j] = arr.get(index);
            arr.remove(index);// 移除已经取过的元素
            range--;// 将随机数范围缩小1
        }
        return random;
    }

    /**
     * @return 生成一个随机数 包含0 不包含maxNumber
     */
    public static int createSingleRadom(int maxNumber) {
        return (int) (Math.random() * maxNumber);
    }


    /**
     * 计算阶乘
     *
     * @param bottomNum 下面的那个数（大）
     * @param topNum    上面的那个数（小）
     * @return 结果
     */
    private static int mathFactorial(int bottomNum, int topNum) {
        if (bottomNum < topNum || topNum == 0 || bottomNum == 0) {
            return 0;
        }
        int result;
        int x = 1;
        int y = 1;
        int z = 1;
        for (int i = 1; i <= bottomNum; i++) {
            x = x * i;
        }
        for (int i = 1; i <= (bottomNum - topNum); i++) {
            y = y * i;
        }

        for (int i = 1; i <= topNum; i++) {
            z = z * i;
        }
        if (x == 0 || y == 0 || z == 0) {
            return 0;
        }
        result = x / y / z;
        return result;
    }

    /**
     * 时时彩五星直选或五星通选
     *
     * @return 结果
     */
    private static int sscStar5(int wan, int qian, int bai, int shi, int ge) {
        return mathFactorial(wan, 1) * mathFactorial(qian, 1) * mathFactorial(bai, 1)
                * mathFactorial(shi, 1) * mathFactorial(ge, 1);
    }

    /**
     * 时时彩大小单双
     *
     * @return 结果
     */
    private static int sscBigSmallSingleDouble(int shi, int ge) {
        return mathFactorial(shi, 1) * mathFactorial(ge, 1);
    }

    /*
     *时时彩三星直选
     */
    private static int sscStar3Direct(int bai, int shi, int ge) {
        return mathFactorial(bai, 1) * mathFactorial(shi, 1) * mathFactorial(ge, 1);
    }

    /*
     *时时彩三星组三
     */
    private static int sscStar3Combination3(int number) {
        return number * (number - 1);
    }

    /*
     *时时彩三星组六
     */
    private static int sscStar3Combination6(int number) {
        return mathFactorial(number, 3);
    }

    /*
     *时时彩二星直选
     */
    private static int sscStar2Direct(int shi, int ge) {
        return mathFactorial(shi, 1) * mathFactorial(ge, 1);
    }

    /*
    *时时彩二星组选
    */
    private static int sscStar2Combination(int number) {
        return mathFactorial(number, 2);
    }

    /*
    *时时彩一星直选
    */
    private static int sscStar1Direct(int number) {
        return number;
    }

    public static int getSSCAllNumber(int playWayPosition, int wan, int qian, int bai, int shi, int ge) {
        int result = 0;
        switch (playWayPosition) {
            case 0:
            case 1:
                result = sscStar5(wan, qian, bai, shi, ge);
                break;
            case 2:
                result = sscBigSmallSingleDouble(shi, ge);
                break;
            case 3:
                result = sscStar3Direct(bai, shi, ge);
                break;
            case 4:
                result = sscStar3Combination3(ge);
                break;
            case 5:
                result = sscStar3Combination6(ge);
                break;
            case 6:
                result = sscStar2Direct(shi, ge);
                break;
            case 7:
                result = sscStar2Combination(ge);
                break;
            case 8:
                result = sscStar1Direct(ge);
                break;
        }
        return result;
    }
}

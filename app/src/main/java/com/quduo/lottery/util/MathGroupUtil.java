package com.quduo.lottery.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
}

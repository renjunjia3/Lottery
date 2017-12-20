package com.quduo.lottery.util;

import java.util.LinkedList;

/**
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
}

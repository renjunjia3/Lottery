package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.SSQFragment;
import com.quduo.lottery.ui.index.entity.SSQBallInfo;
import com.quduo.lottery.ui.index.model.SSQModel;
import com.quduo.lottery.ui.index.view.ISSQView;
import com.quduo.lottery.util.MathGroupUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 双色球
 * Created by scene on 2017/12/19.
 */

public class SSQPresenter extends BasePresenter<ISSQView> {
    private SSQModel model;

    public SSQPresenter(ISSQView view) {
        this.mView = view;
        model = new SSQModel();
    }

    public void setTotalNumAndPrice() {
        try {
            List<SSQBallInfo> redList = mView.getRedList();
            List<SSQBallInfo> blueList = mView.getBlueList();
            int redCheckNum = 0;
            int blueCheckNum = 0;
            int redListSize = redList.size();
            int blueListSize = blueList.size();
            for (int i = 0; i < redListSize; i++) {
                if (redList.get(i).isCheck()) {
                    redCheckNum++;
                }
            }
            for (int i = 0; i < blueListSize; i++) {
                if (blueList.get(i).isCheck()) {
                    blueCheckNum++;
                }
            }
            if (redCheckNum > 5 && blueCheckNum > 0) {
                int totalNumSize = MathGroupUtil.getCount(redCheckNum, SSQFragment.SSQ_RED_NUMBER) * blueCheckNum;
                mView.setTotalNumber(String.valueOf(totalNumSize));
                mView.setTotalPrice(String.valueOf(totalNumSize * 2));
            } else {
                mView.setTotalNumber(String.valueOf(0));
                mView.setTotalPrice(String.valueOf(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickMachine() {
        try {
            List<SSQBallInfo> redList = mView.getRedList();
            List<SSQBallInfo> blueList = mView.getBlueList();
            int redListSize = redList.size();
            int blueListSize = blueList.size();
            for (int i = 0; i < redListSize; i++) {
                redList.get(i).setCheck(false);
            }
            for (int i = 0; i < blueListSize; i++) {
                blueList.get(i).setCheck(false);
            }
            int redRandom[] = creatRandom(6, 33);
            int blueRandom[] = creatRandom(1, 16);
            for (int i = 0; i < redListSize; i++) {
                for (int randmon : redRandom) {
                    if (i == randmon) {
                        redList.get(i).setCheck(true);
                    }
                }
            }
            for (int i = 0; i < blueListSize; i++) {
                if (i == blueRandom[0]) {
                    blueList.get(i).setCheck(true);
                }
            }

            mView.notifyRedAdapter();
            mView.notifyBuleAdapter();
            setTotalNumAndPrice();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 生成随机数集合
     *
     * @param needNum 需要的数量
     * @param range   范围
     * @return 返回集合
     */
    private int[] creatRandom(int needNum, int range) {
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

package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.entity.SSQBallInfo;
import com.quduo.lottery.ui.index.model.SSCModel;
import com.quduo.lottery.ui.index.view.ISSCView;
import com.quduo.lottery.util.MathGroupUtil;

import java.util.List;

/**
 * 时时彩
 * Created by scene on 2017/12/20.
 */

public class SSCPresenter extends BasePresenter<ISSCView> {
    private SSCModel model;

    public SSCPresenter(ISSCView view) {
        this.mView = view;
        model = new SSCModel();
    }

    //机选
    public void clickMachine() {
        try {
            int listSize1 = mView.getList1().size();
            int listSize2 = mView.getList2().size();
            int listSize3 = mView.getList3().size();
            int listSize4 = mView.getList4().size();
            int listSize5 = mView.getList5().size();
            int listSizeBigSmallSingleDouble1 = mView.getListBigSmallSingleDouble1().size();
            int listSizeBigSmallSingleDouble2 = mView.getListBigSmallSingleDouble2().size();
            for (int i = 0; i < listSize1; i++) {
                mView.getList1().get(i).setCheck(false);
            }
            for (int i = 0; i < listSize2; i++) {
                mView.getList2().get(i).setCheck(false);
            }
            for (int i = 0; i < listSize3; i++) {
                mView.getList3().get(i).setCheck(false);
            }
            for (int i = 0; i < listSize4; i++) {
                mView.getList4().get(i).setCheck(false);
            }
            for (int i = 0; i < listSize5; i++) {
                mView.getList5().get(i).setCheck(false);
            }
            for (int i = 0; i < listSizeBigSmallSingleDouble1; i++) {
                mView.getListBigSmallSingleDouble1().get(i).setCheck(false);
            }
            for (int i = 0; i < listSizeBigSmallSingleDouble2; i++) {
                mView.getListBigSmallSingleDouble2().get(i).setCheck(false);
            }

            int currentPlayWayPosition = mView.getCurrentPlayWayPosition();
            if (currentPlayWayPosition == 4 || currentPlayWayPosition == 7) {
                int[] nums = MathGroupUtil.creatRandom(2, 10);
                for (int i = 0; i < listSize5; i++) {
                    for (int num : nums) {
                        if (i == num) {
                            mView.getList5().get(i).setCheck(true);
                        }
                    }
                }
            } else if (currentPlayWayPosition == 5) {
                int[] nums = MathGroupUtil.creatRandom(3, 10);
                for (int i = 0; i < listSize5; i++) {
                    for (int num : nums) {
                        if (i == num) {
                            mView.getList5().get(i).setCheck(true);
                        }
                    }
                }
            } else {
                int num1 = MathGroupUtil.createSingleRadom(10);
                int num2 = MathGroupUtil.createSingleRadom(10);
                int num3 = MathGroupUtil.createSingleRadom(10);
                int num4 = MathGroupUtil.createSingleRadom(10);
                int num5 = MathGroupUtil.createSingleRadom(10);
                int numBigSmallSingleDouble1 = MathGroupUtil.createSingleRadom(4);
                int numBigSmallSingleDouble2 = MathGroupUtil.createSingleRadom(4);

                mView.getList1().get(num1).setCheck(true);
                mView.getList2().get(num2).setCheck(true);
                mView.getList3().get(num3).setCheck(true);
                mView.getList4().get(num4).setCheck(true);
                mView.getList5().get(num5).setCheck(true);
                mView.getListBigSmallSingleDouble1().get(numBigSmallSingleDouble1).setCheck(true);
                mView.getListBigSmallSingleDouble2().get(numBigSmallSingleDouble2).setCheck(true);
            }
            mView.notifyAllAdapter();
            mathAllStake(mView.getCurrentPlayWayPosition());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //改变玩法布局
    public void changeLayoutView(int position) {
        try {
            switch (position) {
                case 0:
                    mView.showStar5Direct();
                    break;
                case 1:
                    mView.showStar5Combination();
                    break;
                case 2:
                    mView.showBigSmallSingleDouble();
                    break;
                case 3:
                    mView.showStar3Direct();
                    break;
                case 4:
                    mView.showStar3Combination3();
                    break;
                case 5:
                    mView.showStar3Combination6();
                    break;
                case 6:
                    mView.showStar2Direct();
                    break;
                case 7:
                    mView.showStar2Combination();
                    break;
                case 8:
                    mView.showStar1Direct();
                    break;
            }
            mView.clearAllChoosedBall();
            mView.showAllStakeAndPrice(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算所有的注数
     */
    public void mathAllStake(int position) {
        try {
            int result = 0;
            List<SSQBallInfo> list1 = mView.getList1();
            List<SSQBallInfo> list2 = mView.getList2();
            List<SSQBallInfo> list3 = mView.getList3();
            List<SSQBallInfo> list4 = mView.getList4();
            List<SSQBallInfo> list5 = mView.getList5();
            List<SSQBallInfo> bigSmallSingleDoubleList1 = mView.getListBigSmallSingleDouble1();
            List<SSQBallInfo> bigSmallSingleDoubleList2 = mView.getListBigSmallSingleDouble2();
            int wanNumber = 0;
            int qianNumber = 0;
            int baiNumber = 0;
            int shiNumber = 0;
            int geNumber = 0;
            int bigSmallSingleDoubleNumber1 = 0;
            int bigSmallSingleDoubleNumber2 = 0;
            for (SSQBallInfo info : list1) {
                if (info.isCheck()) {
                    wanNumber += 1;
                }
            }
            for (SSQBallInfo info : list2) {
                if (info.isCheck()) {
                    qianNumber += 1;
                }
            }
            for (SSQBallInfo info : list3) {
                if (info.isCheck()) {
                    baiNumber += 1;
                }
            }
            for (SSQBallInfo info : list4) {
                if (info.isCheck()) {
                    shiNumber += 1;
                }
            }
            for (SSQBallInfo info : list5) {
                if (info.isCheck()) {
                    geNumber += 1;
                }
            }
            for (SSQBallInfo info : bigSmallSingleDoubleList1) {
                if (info.isCheck()) {
                    bigSmallSingleDoubleNumber1 += 1;
                }
            }
            for (SSQBallInfo info : bigSmallSingleDoubleList2) {
                if (info.isCheck()) {
                    bigSmallSingleDoubleNumber2 += 1;
                }
            }
            switch (position) {
                case 0:
                case 1:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    result = MathGroupUtil.getSSCAllNumber(position, wanNumber, qianNumber, baiNumber, shiNumber, geNumber);
                    break;
                case 2:
                    result = MathGroupUtil.getSSCAllNumber(position, wanNumber, qianNumber, baiNumber, bigSmallSingleDoubleNumber1, bigSmallSingleDoubleNumber2);
                    break;
            }
            mView.showAllStakeAndPrice(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

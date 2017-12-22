package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.entity.SSQBallInfo;
import com.quduo.lottery.ui.index.model.SSCModel;
import com.quduo.lottery.ui.index.view.IFC3DView;
import com.quduo.lottery.util.MathGroupUtil;

import java.util.List;

/**
 * 福彩3D
 * Created by scene on 2017/12/20.
 */

public class FC3DPresenter extends BasePresenter<IFC3DView> {
    private SSCModel model;

    public FC3DPresenter(IFC3DView view) {
        this.mView = view;
        model = new SSCModel();
    }

    //机选
    public void clickMachine() {
        try {
            int listSize1 = mView.getList1().size();
            int listSize2 = mView.getList2().size();
            int listSize3 = mView.getList3().size();
            for (int i = 0; i < listSize1; i++) {
                mView.getList1().get(i).setCheck(false);
            }
            for (int i = 0; i < listSize2; i++) {
                mView.getList2().get(i).setCheck(false);
            }
            for (int i = 0; i < listSize3; i++) {
                mView.getList3().get(i).setCheck(false);
            }

            int currentPlayWayPosition = mView.getCurrentPlayWayPosition();
            if (currentPlayWayPosition == 1) {
                //组3最少要选2个
                int[] nums = MathGroupUtil.creatRandom(2, 10);
                for (int i = 0; i < listSize3; i++) {
                    for (int num : nums) {
                        if (i == num) {
                            mView.getList3().get(i).setCheck(true);
                        }
                    }
                }
            } else if (currentPlayWayPosition == 2) {
                //组6最少要选3个
                int[] nums = MathGroupUtil.creatRandom(3, 10);
                for (int i = 0; i < listSize3; i++) {
                    for (int num : nums) {
                        if (i == num) {
                            mView.getList3().get(i).setCheck(true);
                        }
                    }
                }
            } else {
                int num1 = MathGroupUtil.createSingleRadom(10);
                int num2 = MathGroupUtil.createSingleRadom(10);
                int num3 = MathGroupUtil.createSingleRadom(10);

                mView.getList1().get(num1).setCheck(true);
                mView.getList2().get(num2).setCheck(true);
                mView.getList3().get(num3).setCheck(true);
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
                    mView.showStar3Direct();
                    break;
                case 1:
                    mView.showStar3Combination3();
                    break;
                case 2:
                    mView.showStar3Combination6();
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
            List<SSQBallInfo> list1 = mView.getList1();
            List<SSQBallInfo> list2 = mView.getList2();
            List<SSQBallInfo> list3 = mView.getList3();
            int baiNumber = 0;
            int shiNumber = 0;
            int geNumber = 0;
            for (SSQBallInfo info : list1) {
                if (info.isCheck()) {
                    baiNumber += 1;
                }
            }
            for (SSQBallInfo info : list2) {
                if (info.isCheck()) {
                    shiNumber += 1;
                }
            }
            for (SSQBallInfo info : list3) {
                if (info.isCheck()) {
                    geNumber += 1;
                }
            }
            int result = MathGroupUtil.getFC3DAllNumber(position, baiNumber, shiNumber, geNumber);
            mView.showAllStakeAndPrice(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clickConfirm() {
        try {
            if (mView.getTotalStake() > 0) {
                mView.toConfirmBetPage();
            } else {
                clickMachine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

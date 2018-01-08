package com.quduo.lottery.ui.mine;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.quduo.lottery.AppConfig;
import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseBackMvpFragment;
import com.quduo.lottery.ui.mine.presenter.RechargePresenter;
import com.quduo.lottery.ui.mine.view.IRechargeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 充值
 * Created by scene on 17-8-16.
 */

public class RechargeFragment extends BaseBackMvpFragment<IRechargeView, RechargePresenter> implements IRechargeView {
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.money_1)
    RadioButton money1;
    @BindView(R.id.money_2)
    RadioButton money2;
    @BindView(R.id.money_3)
    RadioButton money3;
    @BindView(R.id.money_4)
    RadioButton money4;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.radio_wechat_pay)
    RadioButton radioWechatPay;
    @BindView(R.id.radio_alipay)
    RadioButton radioAlipay;
    @BindView(R.id.rg_pay_type)
    RadioGroup rgPayType;
    @BindView(R.id.recharge)
    TextView recharge;
    @BindView(R.id.priceCustom)
    EditText priceCustom;
    @BindView(R.id.money_5)
    RadioButton money5;
    @BindView(R.id.radioGroup2)
    RadioGroup radioGroup2;
    @BindView(R.id.money_0)
    RadioButton money0;

    private int cost = 50;


    public static RechargeFragment newInstance() {
        Bundle args = new Bundle();
        RechargeFragment fragment = new RechargeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recharge, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText(R.string.recharge);
        initToolbarNav(toolbar);
        initView();
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        String priceStr = String.valueOf(cost);
        priceCustom.setText(priceStr);
        priceCustom.setSelection(priceStr.length());
    }

    @Override
    public void initView() {
        priceCustom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if (v == priceCustom && hasFocus) {
                        radioGroup.clearCheck();
                        radioGroup2.clearCheck();
                        cost = 0;
                    } else {
                        hideSoftInput();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        priceCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroup.clearCheck();
                radioGroup2.clearCheck();
                cost = 0;
            }
        });

        if (AppConfig.DEFAULT_PAY_WAY == AppConfig.PAY_TYPE_WECHAT) {
            radioWechatPay.setChecked(true);
        } else {
            radioAlipay.setChecked(true);
        }
        money0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftInput();
                cost = 50;
                String priceStr = String.valueOf(50);
                priceCustom.setText(priceStr);
                priceCustom.setSelection(priceStr.length());
                radioGroup2.clearCheck();
            }
        });
        money1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftInput();
                cost = 100;
                String priceStr = String.valueOf(100);
                priceCustom.setText(priceStr);
                priceCustom.setSelection(priceStr.length());
                radioGroup2.clearCheck();
            }
        });
        money2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftInput();
                cost = 200;
                String priceStr = String.valueOf(200);
                priceCustom.setText(priceStr);
                priceCustom.setSelection(priceStr.length());
                radioGroup2.clearCheck();
            }
        });
        money3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftInput();
                cost = 500;
                String priceStr = String.valueOf(500);
                priceCustom.setText(priceStr);
                priceCustom.setSelection(priceStr.length());
                radioGroup2.clearCheck();

            }
        });
        money4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftInput();
                cost = 1000;
                String priceStr = String.valueOf(1000);
                priceCustom.setText(priceStr);
                priceCustom.setSelection(priceStr.length());
                radioGroup.clearCheck();
            }
        });
        money5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftInput();
                cost = 2000;
                String priceStr = String.valueOf(2000);
                priceCustom.setText(priceStr);
                priceCustom.setSelection(priceStr.length());
                radioGroup.clearCheck();
            }
        });
    }


    @Override
    public RechargePresenter initPresenter() {
        return new RechargePresenter(this);
    }


    @OnClick(R.id.recharge)
    public void onClickRecharge() {
        try {
            int payType = 0;
            if (rgPayType.getCheckedRadioButtonId() == R.id.radio_wechat_pay) {
                payType = AppConfig.PAY_TYPE_WECHAT;
            } else if (rgPayType.getCheckedRadioButtonId() == R.id.radio_alipay) {
                payType = AppConfig.PAY_TYPE_ALIPAY;
            }
            if (payType == 0) {
                showMessage("请选择支付方式");
                return;
            }
            int realCost;
            if (cost == 0) {
                String realCostStr = priceCustom.getText().toString().trim();
                realCost = Integer.parseInt(realCostStr);
            } else {
                realCost = cost;
            }
            if (realCost == 0) {
                showMessage("请输入你要充值的金额");
                return;
            }
            //presenter.recharge(realCost * 100, payType);
        } catch (Exception e) {
            e.printStackTrace();
            showMessage("请输入你要充值的金额");
        }

    }

    @Override
    public void showMessage(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void showMessage(@StringRes int resId) {
        ToastUtils.showShort(resId);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void showLoadingPage() {

    }

    @Override
    public void showContentPage() {

    }

    @Override
    public void showErrorPage() {

    }
}

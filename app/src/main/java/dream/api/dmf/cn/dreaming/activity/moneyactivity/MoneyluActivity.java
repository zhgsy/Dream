package dream.api.dmf.cn.dreaming.activity.moneyactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.activity.moneylu.BigBuyActivity;
import dream.api.dmf.cn.dreaming.activity.moneylu.BigSellActivity;
import dream.api.dmf.cn.dreaming.activity.moneylu.BuyActivity;
import dream.api.dmf.cn.dreaming.activity.moneylu.MoneyLuActivity;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;

public class MoneyluActivity extends BaseMvpActivity<presenter> implements Contract.Iview,View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.e_sell)
    TextView eSell;
    @BindView(R.id.e_buy)
    TextView eBuy;
    @BindView(R.id.b_sell)
    TextView bSell;
    @BindView(R.id.b_buy)
    TextView bBuy;
    private boolean username1;

    @Override
    public void getThisData() {
        SharedPreferences sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        username1 = sharedPreferences.getBoolean("Username", true);
        mTitle.setText("交易记录");

        if (username1 == true) {
            mTitle.setText("MDF交易记录");
            mTitle.setTextSize(16);
            eBuy.setOnClickListener(this);
            eSell.setOnClickListener(this);
            bBuy.setOnClickListener(this);
            bSell.setOnClickListener(this);
        } else if (username1 == false) {
            mTitle.setText("HYT交易记录");
            mTitle.setTextSize(16);
            eBuy.setOnClickListener(this);
            eSell.setOnClickListener(this);
            bBuy.setOnClickListener(this);
            bSell.setOnClickListener(this);
        }
    }

    @Override
    public void getInitData() {

    }

    @Override
    public int getView() {
        return R.layout.activity_moneylu;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.e_sell:
                    startActivity(new Intent(mContext,MoneyLuActivity.class));
                break;
            case R.id.e_buy:
                    startActivity(new Intent(mContext,BuyActivity.class));
                break;
                //大厅卖出
            case R.id.b_sell:
               startActivity(new Intent(mContext,BigBuyActivity.class));
                break;
                //大厅买入
            case R.id.b_buy:
                startActivity(new Intent(mContext,BigSellActivity.class));
                break;
        }
    }
}

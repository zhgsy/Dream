package dream.api.dmf.cn.dreaming.activity.mineactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;

public class ShouKActivity extends BaseMvpActivity<presenter> implements Contract.Iview {

    TextView tvTitle;
    ImageView ivBack;
    @BindView(R.id.tv_bank)
    TextView tvBank;
    @BindView(R.id.tv_aLi)
    TextView tvALi;
    @BindView(R.id.tv_weiChat)
    TextView tvWeiChat;
    @BindView(R.id.tv_paypal)
    TextView tvPaypal;

    @Override
    public void getThisData() {

    }

    @Override
    public void getInitData() {
        tvTitle=  findViewById(R.id.tv_title);
        ivBack=  findViewById(R.id.iv_back);
        tvTitle.setText("收款方式");
        tvTitle.setTextSize(18);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public int getView() {
        return R.layout.activity_shou_k;
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

    @OnClick({R.id.tv_bank, R.id.tv_aLi, R.id.tv_weiChat, R.id.tv_paypal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //银行卡绑定
            case R.id.tv_bank:
                    startActivity(new Intent(mContext,BankActivity.class));
                break;
                //支付宝绑定
            case R.id.tv_aLi:
                    startActivity(new Intent(mContext,AliActivity.class));
                break;
                //微信绑定
            case R.id.tv_weiChat:
                    startActivity(new Intent(mContext,WeChatActivity.class));
                break;
         /*       //paypal绑定
            case R.id.tv_paypal:
                    startActivity(new Intent(mContext,PaypleActivity.class));
                break;*/
        }
    }
}

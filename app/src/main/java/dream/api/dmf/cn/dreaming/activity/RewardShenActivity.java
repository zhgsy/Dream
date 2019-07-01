package dream.api.dmf.cn.dreaming.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.ReShenBean;

public class RewardShenActivity extends BaseMvpActivity<presenter> implements Contract.Iview {

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ss_ye)
    EditText mYe;
    @BindView(R.id.ss_num)
    EditText mNum;
    @BindView(R.id.ss_name)
    EditText mName;
    @BindView(R.id.ss_mame)
    EditText mMame;
    @BindView(R.id.ss_butn)
    Button ssButn;
    private String minePhone;
    private SharedPreferences sharedPreferences;

    @Override
    public void getThisData() {
        sharedPreferences = mContext.getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        minePhone = sharedPreferences.getString(UserApi.UserName, "");
        mTitle.setText("申请服务");
        mTitle.setTextSize(18);

    }

    @Override
    public void getInitData() {

    }

    @Override
    public int getView() {
        return R.layout.activity_reward_shen;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {
        if (object instanceof ReShenBean){
            ReShenBean reShenBean= (ReShenBean) object;
            if (reShenBean.status==200){
                Toast.makeText(mContext,reShenBean.message,Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(mContext,reShenBean.message,Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.ss_butn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ss_butn:
                String ye = mYe.getText().toString().trim();
                String number = mNum.getText().toString().trim();
                String name = mName.getText().toString().trim();
                String mname = mMame.getText().toString().trim();
                if ("".equals(ye)) {
                    Toast.makeText(mContext, "请输入您的业绩", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(number)) {
                    Toast.makeText(mContext, "请输入推荐人的编码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(name)) {
                    Toast.makeText(mContext, "请输入您的姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(mname)) {
                    Toast.makeText(mContext, "请输入备注", Toast.LENGTH_SHORT).show();
                    return;
                }
                HashMap<String, Object> headmap = new HashMap<>();
                HashMap<String, Object> map = new HashMap<>();
                map.put("yeji", ye);
                map.put("p_number", number);
                map.put("truename", name);
                map.put("remark", mname);
                map.put("phone",minePhone);
                mPresenter.postData(UserApi.getRESHEN,headmap,map,ReShenBean.class);
                break;
        }
    }
}

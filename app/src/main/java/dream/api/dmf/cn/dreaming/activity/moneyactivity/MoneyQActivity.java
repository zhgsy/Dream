package dream.api.dmf.cn.dreaming.activity.moneyactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import dream.api.dmf.cn.dreaming.bean.DmfMoneyBean;

public class MoneyQActivity extends BaseMvpActivity<presenter> implements Contract.Iview {


    ImageView mBack;
    @BindView(R.id.m_nums)
    EditText mNums;
    EditText mPass;
    @BindView(R.id.m_yue)
    TextView mYue;

    @BindView(R.id.hytyu)
    TextView hytyu;
    @BindView(R.id.login_exe)
    CheckBox loginExe;
    @BindView(R.id.m_butn)
    Button mButn;

    private String mNum;
    private String mPas;
    private SharedPreferences sharedPreferences;
    private String mUid;
    private String mShell;
    private boolean username1;
    private TextView mTitle;
    private TextView yue;
    private String regmoney;
    private String regdmf;
    private CheckBox mExe;

    @Override
    public void getThisData() {
        if (username1 == true) {
            yue.setText("DMF余额");
            mYue.setText(regdmf);
            mTitle.setText("DMF金元转换");
        } else if (username1 == false) {
            yue.setText("HYTB余额");
            mYue.setText(regdmf);
            mTitle.setText("HYT金元转换");
        }

    }

    @Override
    public void getInitData() {

        sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        regmoney = sharedPreferences.getString(UserApi.regmoney, "");
        regdmf = sharedPreferences.getString(UserApi.regmoneyDMF, "");
        username1 = sharedPreferences.getBoolean("Username", true);
        mTitle = findViewById(R.id.tv_title);
        mBack = findViewById(R.id.iv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        yue = findViewById(R.id.hytyu);
        mExe = findViewById(R.id.login_exe);
       mPass = findViewById(R.id.m_pass);
       findViewById(R.id.tv_title);
        if (mPass.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            //密码可见,点击之后设置成不可见的
            mPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        }else {
            //不可见设置成可见
            mPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        mExe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (mPass.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        //不可见设置成可见
                        mPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }
                }else {

                    //密码可见,点击之后设置成不可见的

                    mPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        // mTitle.setText("金元转换");
        // mTitle.setTextSize(16);
       /* mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/


    }

    @Override
    public int getView() {
        return R.layout.activity_money_q;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {
        if (object instanceof DmfMoneyBean) {
            DmfMoneyBean dmfMoneyBean = (DmfMoneyBean) object;
            if (dmfMoneyBean.error.equals("0")) {
                Toast.makeText(mContext, "金元转换成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(mContext, dmfMoneyBean.msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.login_exe, R.id.m_butn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_exe:

                break;
            case R.id.m_butn:
                mNum = mNums.getText().toString().trim();
                mPas = this.mPass.getText().toString().trim();
                if (mNum.isEmpty() && mPas.isEmpty()) {
                    Toast.makeText(mContext, "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (username1 == true) {
                        HashMap<String, Object> headmap = new HashMap<>();
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("uid", mUid);
                        map.put("shell", mShell);
                        map.put("howmoney", mNum);
                        map.put("repass", mPas);
                        mPresenter.postData(UserApi.getdMFMONEY, headmap, map, DmfMoneyBean.class);
                    } else if (username1 == false) {
                        HashMap<String, Object> headmap = new HashMap<>();
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("uid", mUid);
                        map.put("shell", mShell);
                        map.put("howmoney", mNum);
                        map.put("repass", mPas);
                        mPresenter.postData(UserApi.getHYTMONEY, headmap, map, DmfMoneyBean.class);


                    }

                }
                break;
        }
    }
}

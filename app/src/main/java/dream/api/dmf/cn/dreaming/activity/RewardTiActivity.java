package dream.api.dmf.cn.dreaming.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import dream.api.dmf.cn.dreaming.bean.ZhuanBean;

public class RewardTiActivity extends BaseMvpActivity<presenter> implements Contract.Iview {
    @BindView(R.id.m_nums)
    EditText mNums;
    @BindView(R.id.m_pass)
    EditText mPass;
    @BindView(R.id.login_exe)
    CheckBox loginExe;
    @BindView(R.id.hytyu)
    TextView hytyu;
    @BindView(R.id.m_yue)
    TextView mYue;
    @BindView(R.id.m_butn)
    Button mButn;
    private TextView mTitle;
    private ImageView mImage;
    private SharedPreferences sharedPreferences;
    private String mUid;
    private String mShell;
    private String mUsername;
    /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        getInit();
    }*/

    @Override
    public void getThisData() {

    }

    @Override
    public void getInitData() {
        sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        mUsername = sharedPreferences.getString(UserApi.UserName, "");
        getInit();

    }

    @Override
    public int getView() {
        return R.layout.activity_reward_ti;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    //初始化控件
    private void getInit() {
        mTitle = findViewById(R.id.tv_title);
        mTitle.setText("积分转换DMF");
        mTitle.setTextSize(18);
        mImage = findViewById(R.id.iv_back);
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void getData(Object object) {
            if (object instanceof  ZhuanBean){
                ZhuanBean zhuanBean= (ZhuanBean) object;
                    if (zhuanBean.status==200){
                        Toast.makeText(mContext,zhuanBean.message,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mContext,zhuanBean.message,Toast.LENGTH_SHORT).show();
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
                String nums = mNums.getText().toString().trim();
                String mPass = this.mPass.getText().toString().trim();
                if (!nums.isEmpty()&&!mPass.isEmpty()){
                    HashMap<String,Object> headmap=new HashMap<>();
                    HashMap<String,Object>map=new HashMap<>();
                    map.put("uid",mUid);
                    map.put("shell",mShell);
                    map.put("phone",mUsername);
                    map.put("num",nums);
                    map.put("password",mPass);
                    mPresenter.postData(UserApi.getZHUAN,headmap,map,ZhuanBean.class);
                }
                break;
        }
    }
}

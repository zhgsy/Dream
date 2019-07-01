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
import dream.api.dmf.cn.dreaming.bean.JFZBean;

public class RewardzzActivity extends BaseMvpActivity<presenter> implements Contract.Iview {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.zz_phone)
    EditText mPhone;
    @BindView(R.id.zz_num)
    EditText mNum;
    @BindView(R.id.zz_pass)
    EditText mPass;
    @BindView(R.id.zz_name)
    EditText mName;
    @BindView(R.id.zz_butn)
    Button zzButn;
    private SharedPreferences sharedPreferences;
    private String minePhone;

    @Override
    public void getThisData() {
        tvTitle.setText("积分转增");
        tvTitle.setTextSize(18);
        sharedPreferences = mContext.getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        minePhone = sharedPreferences.getString(UserApi.UserName, "");
    }

    @Override
    public void getInitData() {

    }

    @Override
    public int getView() {
        return R.layout.activity_rewardzz;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {
      if (object instanceof JFZBean){
          JFZBean jfzBean= (JFZBean) object;
          if (jfzBean.status==200){
              Toast.makeText(mContext,jfzBean.message,Toast.LENGTH_SHORT).show();
          }else{
              Toast.makeText(mContext,jfzBean.message,Toast.LENGTH_SHORT).show();
          }
      }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.zz_butn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.zz_butn:
                String phone = mPhone.getText().toString().trim();
                String nums = mNum.getText().toString().trim();
                String pass = mPass.getText().toString().trim();
                String name = mName.getText().toString().trim();
                if (!phone.isEmpty()&&!nums.isEmpty()&&!pass.isEmpty()&&!name.isEmpty()) {
                    //Toast.makeText(mContext,"该内容不能为空",Toast.LENGTH_SHORT).show();
                }
               /* if (){
                    Toast.makeText(mContext,"转增数量不能为空",Toast.LENGTH_SHORT).show();
                }
                if (){
                    Toast.makeText(mContext,"安全密码不能为空",Toast.LENGTH_SHORT).show();
                }
                if (){
                    Toast.makeText(mContext,"备注不能为空",Toast.LENGTH_SHORT).show();
                }*/
                HashMap<String,Object> headmap=new HashMap<>();
                HashMap<String,Object> map=new HashMap<>();
                map.put("phone",minePhone);
                map.put("be_phone",phone);
                map.put("num",nums);
                map.put("passward",pass);
                map.put("remark",name);
                mPresenter.postData(UserApi.getReZZ,headmap,map,JFZBean.class);
                break;
                 /*   HashMap<String,Object> headmap=new HashMap<>();
                    HashMap<String,Object> map=new HashMap<>();
                    map.put()
                    map.put()
                    map.put()
                    map.put();
                    mPresenter.postData(UserApi.getReZZ,headmap,map,);*/



        }
        }
    }

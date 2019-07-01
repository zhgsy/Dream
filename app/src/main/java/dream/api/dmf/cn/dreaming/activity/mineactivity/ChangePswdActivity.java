package dream.api.dmf.cn.dreaming.activity.mineactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
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
import dream.api.dmf.cn.dreaming.bean.UpdateBean;

public class ChangePswdActivity extends BaseMvpActivity<presenter> implements Contract.Iview {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    EditText etOldPswd;
    @BindView(R.id.login_exe1)
    CheckBox loginExe1;
    EditText etNewPswd;
    @BindView(R.id.login_exe2)
    CheckBox loginExe2;

    EditText etConfirm;
    @BindView(R.id.login_exe)
    CheckBox loginExe;
    @BindView(R.id.tv_change)
    TextView tvChange;
    private CheckBox mExe,mExe1,mExe2;
    private SharedPreferences sharedPreferences;
    private String mUid;
    private String mShell;

    @Override
    public void getThisData() {

    }

    @Override
    public void getInitData() {
        sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
         etOldPswd = findViewById(R.id.et_oldPswd);
         etNewPswd = findViewById(R.id.et_newPswd);
         etConfirm = findViewById(R.id.et_confirm);
        //tvTitle.setText("修改登陆密码");
        if (etOldPswd.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            //密码可见,点击之后设置成不可见的
            etOldPswd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        } else {
            //不可见设置成可见
            etOldPswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        }
        if (etNewPswd.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            //密码可见,点击之后设置成不可见的
            etNewPswd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        } else {
            //不可见设置成可见
            etNewPswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        }
        if (etConfirm.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            //密码可见,点击之后设置成不可见的
            etConfirm.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        } else {
            //不可见设置成可见
            etConfirm.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        }
        mExe=findViewById(R.id.login_exe);
        mExe1=findViewById(R.id.login_exe1);
        mExe2=findViewById(R.id.login_exe2);
        mExe1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (etOldPswd.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        //密码可见,点击之后设置成不可见的
                        etOldPswd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }
                } else {
                    //不可见设置成可见

                    etOldPswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        mExe2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (etNewPswd.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        //密码可见,点击之后设置成不可见的
                        etNewPswd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }
                } else {
                    //不可见设置成可见

                    etNewPswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        mExe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (etConfirm.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        //密码可见,点击之后设置成不可见的
                        etConfirm.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }
                } else {
                    //不可见设置成可见
                    etConfirm.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }
            }
        });
    }

    @Override
    public int getView() {
        return R.layout.activity_change_pswd;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {
            if (object instanceof UpdateBean){
                UpdateBean updateBean= (UpdateBean) object;
                if (updateBean.error.equals("0")){
                    Toast.makeText(mContext,"修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(mContext,updateBean.msg,Toast.LENGTH_SHORT).show();
                }
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_change:
                String oldpassword = etOldPswd.getText().toString().trim();
                String newPswd = etNewPswd.getText().toString().trim();
                String confirm = etConfirm.getText().toString().trim();
                if ("".equals(oldpassword)) {

                    Toast.makeText(mContext, "请填写原始密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(newPswd)) {

                    Toast.makeText(mContext, "请填写新密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!newPswd.equals(confirm)) {

                    Toast.makeText(mContext, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                HashMap<String,Object> headmap=new HashMap<>();
                HashMap<String,Object> map=new HashMap<>();
                map.put("uid",mUid);
                map.put("shell",mShell);
                map.put("oldpassword",oldpassword);
                map.put("password",newPswd);
                map.put("cpassword",confirm);
                mPresenter.postData(UserApi.getUpPass,headmap,map,UpdateBean.class);
                break;
        }
    }
}

package dream.api.dmf.cn.dreaming.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dream.api.dmf.cn.dreaming.MainActivity;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.LoginBean;
import dream.api.dmf.cn.dreaming.utils.StringUtils;
import dream.api.dmf.cn.dreaming.utils.WeiboDialogUtils;

public class LoginActivity extends BaseMvpActivity<presenter> implements Contract.Iview {


    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pswd)
    EditText etPswd;
    @BindView(R.id.login_exe)
    CheckBox loginExe;
    @BindView(R.id.tv_login)
    Button tvLogin;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    private String mPhone;
    private String mPswd;
    private CheckBox mExe;
    private Dialog loadingDialog;
    private SharedPreferences sharedPreferences;

    @Override
    public int getView() {
        return R.layout.activity_login;
    }

    @Override
    public void getThisData() {
        if (etPswd.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            //密码可见,点击之后设置成不可见的
            etPswd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        }else {
            //不可见设置成可见
            etPswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        mExe = findViewById(R.id.login_exe);
        mExe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (etPswd.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        //不可见设置成可见
                        etPswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    }
                }else {

                    //密码可见,点击之后设置成不可见的
                    etPswd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                }
            }
        });
    }

    @Override
    public void getInitData() {
        sharedPreferences = mContext.getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        //SharedPreferences login = mContext.getSharedPreferences("login", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(UserApi.UserName, "");
        if (username.equals("")){//第一次登陆
            sharedPreferences.edit().putBoolean("user",false).commit();
           /* login.edit().putBoolean("first",false).commit();
            finish();*/
            // Toast.makeText(this,"第一次",Toast.LENGTH_SHORT).show();
        }else{
            sharedPreferences.edit().putBoolean("user",true).commit();
            startActivity(new Intent(LoginActivity.this,MainActivity.class  ));
            finish();

            //Toast.makeText(this,"不是第一次登陆",Toast.LENGTH_SHORT).show();
        }
       /* sharedPreferences.edit().putString(Constant.NowPrice, isLoginBean.data.t_up_stop_cha).commit();
        sharedPreferences.edit().putString(Constant.SelectNum, gson.toJson(isLoginBean.data.buy_num)).commit();
        sharedPreferences.edit().putString(Constant.Alipay, isLoginBean.data.alipay).commit();
        sharedPreferences.edit().putString(Constant.Alipayfile, isLoginBean.data.alipayfile).commit();
        sharedPreferences.edit().putString(Constant.Alipayhm, isLoginBean.data.alipayhm).commit();
        sharedPreferences.edit().putString(Constant.Bankadd, isLoginBean.data.bankadd).commit();
        sharedPreferences.edit().putString(Constant.Bankcard, isLoginBean.data.bankcard).commit();
        sharedPreferences.edit().putString(Constant.Bankhm, isLoginBean.data.bankhm).commit();
        sharedPreferences.edit().putString(Constant.Bankname, isLoginBean.data.bankname).commit();
        sharedPreferences.edit().putString(Constant.Wechat, isLoginBean.data.wechat).commit();
        sharedPreferences.edit().putString(Constant.Wechatfile, isLoginBean.data.wechatfile).commit();
        sharedPreferences.edit().putString(Constant.Wechathm, isLoginBean.data.wechathm).commit();
        sharedPreferences.edit().putString(Constant.IdCard, isLoginBean.data.idcard).commit();
        sharedPreferences.edit().putString(Constant.Money, isLoginBean.data.money).commit();
        sharedPreferences.edit().putString(Constant.Balance, isLoginBean.data.balance).commit();
        sharedPreferences.edit().putString(Constant.Regmoney, isLoginBean.data.regmoney).commit();
        sharedPreferences.edit().putString(Constant.Stock, isLoginBean.data.stock).commit();
        sharedPreferences.edit().putString(Constant.SnMoney, isLoginBean.data.snmoney).commit();
        sharedPreferences.edit().putString(Constant.Getmoney, isLoginBean.data._getmoney).commit();
        sharedPreferences.edit().putString(Constant.KNums, isLoginBean.data.knums).commit();
        sharedPreferences.edit().putString(Constant.TNums, isLoginBean.data.tnums).commit();
        sharedPreferences.edit().putString(Constant.UserGroup, isLoginBean.data.usergroup).commit();*/
      /*  tvBalance.setText(sharedPreferences.getString(Constant.Money, ""));
        tvQuota.setText(sharedPreferences.getString(Constant.Balance, ""));
        tvWallet.setText(sharedPreferences.getString(Constant.Regmoney, ""));
        tvAssets.setText(sharedPreferences.getString(Constant.Stock, ""));
        tvBonus.setText(sharedPreferences.getString(Constant.SnMoney, ""));
        tvHarvest.setText(sharedPreferences.getString(Constant.Getmoney, ""));*/
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @Override
    public void getData(Object object) {
        if (object instanceof LoginBean){
            loadingDialog = WeiboDialogUtils.createLoadingDialog(mContext, "登陆中...");
            LoginBean bean= (LoginBean) object;
            switch (bean.error){
                case "0":
                    Toast.makeText(mContext,"登陆成功",Toast.LENGTH_SHORT).show();
                    sharedPreferences.edit().putString(UserApi.Uid, bean.uid).commit();
                    sharedPreferences.edit().putString(UserApi.Shell, bean.shell).commit();
                    sharedPreferences.edit().putString(UserApi.TrueName, bean.truename).commit();
                    sharedPreferences.edit().putString(UserApi.UserName, bean.username).commit();
                    sharedPreferences.edit().putString(UserApi.Groupname_cn, bean.groupname_cn).commit();
                    sharedPreferences.edit().putString(UserApi._UUID, bean.truename).commit();
                    startActivity(new Intent(mContext,MainActivity.class));
                    finish();
                    break;
                case "1":
                    Toast.makeText(mContext,"密码错误",Toast.LENGTH_SHORT).show();
                    loadingDialog.dismiss();
                    break;
                case "2":

                    break;
            }
         /*   if (bean.error.equals("0")){



            }else{
                Toast.makeText(mContext,"失败",Toast.LENGTH_SHORT).show();
            }*/

        }
    }
    @OnClick({R.id.tv_login, R.id.tv_forget, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                mPhone = etPhone.getText().toString().trim();
                mPswd = etPswd.getText().toString().trim();
                if ("".equals(mPhone)) {
                    Toast.makeText(mContext, "请填写手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!StringUtils.isMobileNO(mPhone)) {
                    Toast.makeText(mContext, "请填写正确手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(mPswd)) {
                    Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
             /*   if ("".equals(imei)) {
                    Toast.makeText(mContext, "请至权限中心打开本应用的访问权限", Toast.LENGTH_SHORT).show();
                    return;
                }*/
              /*  if (NetWorkUtil.isNetworkAvailable(mContext)) {

//                    login(phone, pswd);
                } else {
                    Toast.makeText(mContext, "请检查网络连接", Toast.LENGTH_SHORT).show();
                }*/
                HashMap<String,Object>headmap=new HashMap<>();
                HashMap<String,Object> map=new HashMap<>();
                map.put("username",mPhone);
                map.put("password",mPswd);
                mPresenter.postData(UserApi.getLogin,headmap,map,LoginBean.class);
                break;
            case R.id.tv_forget:
                    startActivity(new Intent(mContext,ForgetActivity.class));
                break;
            case R.id.tv_register:
                    startActivity(new Intent(mContext,RegstActivity.class));
                break;
        }
    }



}

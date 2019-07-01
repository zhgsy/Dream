package dream.api.dmf.cn.dreaming.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpFragment;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.EBuyBean;
import dream.api.dmf.cn.dreaming.utils.BuyNumDialog;
import dream.api.dmf.cn.dreaming.utils.LogUtils;


public class BuyFragment extends BaseMvpFragment<presenter> implements Contract.Iview {
    @BindView(R.id.e_teprice)
    TextView eTeprice;
    EditText mEdphone;
    CheckBox loginExe;
    Unbinder unbinder;
    @BindView(R.id.e_num)
    TextView eNum;
    @BindView(R.id.bank_nin)
    TextView bankNin;
    private String nums;
    private List<String> numList;
    private String dmfday;
    private List list;
    private String nus;
    private String mUid;
    private String mShell;
    private String mNum;
    private Button tvBug;
    private boolean username1;
    private String hytday;
    /*

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate( container, false);
    }
*/

    @Override
    protected presenter createPresenter() {
        return new presenter();

    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_buy;
    }

    @Override
    protected void initView(View view) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        dmfday = sharedPreferences.getString(UserApi.dmf_day_Today, "");
        hytday = sharedPreferences.getString(UserApi.HTODAY, "");
        username1 = sharedPreferences.getBoolean("Username", true);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        nums = sharedPreferences.getString(UserApi.DmfNUm, "");
        tvBug = view.findViewById(R.id.tv_bug);
         eTeprice = view.findViewById(R.id.e_teprice);
         mEdphone = view.findViewById(R.id.tv_edphone);
        loginExe = view.findViewById(R.id.login_exe);
        String[] splitNums = nums.substring(1, nums.length() - 1).replace("\"", "").split(",");
        numList = Arrays.asList(splitNums);
        list = Arrays.asList(getResources().getStringArray(R.array.bank));
        if (username1 ==true){


        }else if (username1 ==false){
            String Hed = sharedPreferences.getString(UserApi.HYTED, "");
            tvBug.setText("DMF买入");
            eTeprice.setText(hytday);
        }
        if (mEdphone.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            //密码可见,点击之后设置成不可见的
            mEdphone.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        }else {
            //不可见设置成可见
            mEdphone.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        loginExe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    if (mEdphone.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        //不可见设置成可见
                        mEdphone.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                }else {

                    //密码可见,点击之后设置成不可见的
                    mEdphone.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                }
            }
        });



    }

    @Override
    protected void getData() {
        eTeprice.setText(dmfday);
        eNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //new BuyNumDialog(mContext, numList, eNum);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNum = eNum.getText().toString();
                String mPhone = mEdphone.getText().toString().trim();
                String s1 = cutDoubleNumber(Double.valueOf((Double.parseDouble(mNum) * Double.parseDouble(dmfday) + "")));
                bankNin.setText(s1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @OnClick({R.id.e_num, R.id.bank_nin, R.id.tv_bug})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.e_num:
                new BuyNumDialog(mContext, numList, eNum);
                break;
            case R.id.bank_nin:
              //new BankDialog(mContext, list, bankNin);
                break;
            case R.id.tv_bug:
                if (username1==true){
                    HashMap<String,Object> headmap=new HashMap<>();
                    HashMap<String,Object> map=new HashMap<>();
                    mNum = eNum.getText().toString();
                    String mPhone = mEdphone.getText().toString().trim();
                    //String mprice = eTeprice.getText().toString();
                    map.put("uid",mUid);
                    map.put("shell",mShell);
                    map.put("repass",mPhone);
                    map.put("howmoney", mNum);
                    map.put("sellprice",bankNin);
                    mPresenter.postData(UserApi.getMDFBUG,headmap,map,EBuyBean.class);

                }else if (username1==false){
                 /*   String Hed = sharedPreferences.getString(UserApi.HYTED, "");*/
                    HashMap<String,Object> headmap=new HashMap<>();
                    HashMap<String,Object> map=new HashMap<>();
                    mNum = eNum.getText().toString();
                    String mPhone = mEdphone.getText().toString().trim();
                    //String mprice = eTeprice.getText().toString();
                    map.put("uid",mUid);
                    map.put("shell",mShell);
                    map.put("repass",mPhone);
                    map.put("howmoney", mNum);
                    map.put("sellprice",bankNin);
                    mPresenter.postData(UserApi.getMRBUG,headmap,map,EBuyBean.class);

                }

            /*    bankNin.setText( (Double.parseDouble(mNum)*Double.parseDouble(dmfday)+""));
                if ("".equals(phone)) {
                    Toast.makeText(ctx, "请填写手机号", Toast.LENGTH_SHORT).show();
                    return;
                }*/
            /*
                if (!StringUtils.isMobileNO(phone)) {
                    Toast.makeText(ctx, "请填写正确手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(pswd)) {
                    Toast.makeText(ctx, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(imei)) {
                    Toast.makeText(ctx, "请至权限中心打开本应用的访问权限", Toast.LENGTH_SHORT).show();
                    return;
                }//int nums = Integer.parseInt(mNum);
               // Double nu = Double.valueOf(String.valueOf(dmfday));

                //Double nus=nums*nu;
               // String mBank = bankNin.getText().toString();

              *//*  int nus=dmfday*mNum;*//*
            *//*    int atr1 = Integer.parseInt(mNum);
                int atr2 = Integer.parseInt(mprice);*//*
               *//* int  nus=atr2*atr1;*//*
                if (mPhone.isEmpty()){
                    Toast.makeText(mContext,"安全密码不能为空",Toast.LENGTH_SHORT).show();
                }else{

                }
*/           break;
        }
    }
    @Override
    public void getData(Object object) {
        LogUtils.v(object+"");
        if (object instanceof EBuyBean){
            EBuyBean eBuyBean= (EBuyBean) object;
            if (eBuyBean.error.equals("0")){

                Toast.makeText(mContext,"购买成功",Toast.LENGTH_SHORT).show();
                //eNum.setText("请选择购买数量");
                bankNin.setText("");
                mEdphone.setText("");
            }else{
                Toast.makeText(mContext,eBuyBean.msg,Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }
    public static String cutDoubleNumber(Double number) {
//
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.0");
        df.setRoundingMode(RoundingMode.FLOOR);
        String d=df.format(number);

        //四舍五入保留两位小数,number.toString()是一个Double值
//        double v = new BigDecimal(number.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }
}

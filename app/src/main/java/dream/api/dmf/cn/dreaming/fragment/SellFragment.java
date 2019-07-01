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
import android.widget.LinearLayout;
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
import dream.api.dmf.cn.dreaming.bean.DuSellBean;
import dream.api.dmf.cn.dreaming.bean.IsLoginBean;
import dream.api.dmf.cn.dreaming.utils.BankDialog;
import dream.api.dmf.cn.dreaming.utils.BuyNumDialog;

public class SellFragment extends BaseMvpFragment<presenter> implements Contract.Iview {

    Unbinder unbinder;
    @BindView(R.id.s_edsell)
    TextView sEdsell;
    @BindView(R.id.s_price)
    TextView sPrice;
    @BindView(R.id.ss_num)
    TextView ssNum;
    @BindView(R.id.s_nump)
    TextView sNump;
    @BindView(R.id.s_banck)
    TextView sBanck;
    @BindView(R.id.s_num)
    TextView sNum;
    EditText sRpass;
    CheckBox loginExe;
    @BindView(R.id.s_butn)
    Button sButn;
    Unbinder unbinder1;
    /*   TextView sNums;*/
    private String dmfday;
    private String nums;
    private List<String> numList;
    private List list;
    private String mEd;
    private String mUid;
    private String mShell;
    private String mEdx;
    private String paytype;
    private TextView mPrice;
    private TextView sNums;
    private Double Ter;
    private boolean username1;
    private LinearLayout layout;
    private String number;
    private String back;
    private TextView edone;
    private TextView edtwo;
    private IsLoginBean isLoginBean;

    @Override
    protected presenter createPresenter() {
        return new presenter();
    }

    @Override
    protected int getFragmentView() {

        return R.layout.fragment_sell;
    }

    @Override
    protected void initView(View view) {
        mPrice = view.findViewById(R.id.s_price);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
     /*   sNums = view.findViewById(R.id.ss_num);*/
        username1 = sharedPreferences.getBoolean("Username", true);
        edone = view.findViewById(R.id.ed_one);
        edtwo = view.findViewById(R.id.ed_num);
        if (username1 ==true){
        /*    edone.setText(isLoginBean.stock_mdf);
            edtwo.setText(isLoginBean.regmoney_dmf);*/

        }else if (username1 ==false){
           /* edone.setText(isLoginBean.stock);
            edtwo.setText(isLoginBean.regmoney);
*/
            /*String Hed = sharedPreferences.getString(UserApi.HYTED, "");
            tvBug.setText("DMF买入");
            eTeprice.setText(hytday);*/
        }
        dmfday = sharedPreferences.getString(UserApi.dmf_day_Today, "");
        mEd = sharedPreferences.getString(UserApi.DMFED, "");
        nums = sharedPreferences.getString(UserApi.DmfNUm, "");
        String[] splitNums = nums.substring(1, nums.length() - 1).replace("\"", "").split(",");
        numList = Arrays.asList(splitNums);
         loginExe = view.findViewById(R.id.login_exe);
        sRpass = view.findViewById(R.id.s_rpass);
        list = Arrays.asList(getResources().getStringArray(R.array.bank));
        if (sRpass.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            //密码可见,点击之后设置成不可见的
            sRpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        }else {
            //不可见设置成可见
            sRpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        loginExe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    if (sRpass.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        //不可见设置成可见
                        sRpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                }else {
                    //密码可见,点击之后设置成不可见的
                    sRpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                }
            }
        });
    }

    @Override
    protected void getData() {
        mPrice.setText(dmfday);

        ssNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

               // new BuyNumDialog(mContext, numList, sNums);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mNum = ssNum.getText().toString();
                Double myu= (Double.parseDouble(mNum)*Double.parseDouble(mEd));
                double num = Double.parseDouble(mNum);
                sNump.setText(num+myu+"");
                //sNum.setText((Double.parseDouble(mNum)*);
                String s1 = cutDoubleNumber(Double.valueOf(Double.parseDouble(mNum) * Double.parseDouble(dmfday) + ""));
               //cutDoubleNumber(Double.parseDouble(dmfday));
                sNum.setText(s1);
                ;
                //Toast.makeText(mContext, "111111111", Toast.LENGTH_SHORT).show();
                 //String mNum = ssNum.getText().toString();
                //sNump.setText(Double.parseDouble(mEd) * Double.parseDouble(ssNum) + "");
              // sNum.setText((Double.parseDouble(mNum) * Double.parseDouble(dmfday) + (Double.parseDouble(mEd) * Double.parseDouble(String.valueOf(mNum))) + ""));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        HashMap<String,Object> headmap=new HashMap<>();
        HashMap<String,Object> map=new HashMap<>();
        map.put("uid", mUid);
        map.put("shell", mShell);
        mPresenter.postData(UserApi.getIsLogin, headmap, map, IsLoginBean.class);
        //sEdsell.setText(mEdx);
    }

    @Override
    public void getData(Object object) {
        if (object instanceof DuSellBean){
            DuSellBean duSellBean= (DuSellBean) object;
            if (duSellBean.error.equals("0")){
                Toast.makeText(mContext,"卖出成功",Toast.LENGTH_SHORT).show();
                number.isEmpty();
                sNump.setText("");
                sNum.setText("");
                sRpass.setText("");
                sBanck.setText("");
            }else{
                Toast.makeText(mContext,duSellBean.msg,Toast.LENGTH_SHORT).show();
            }
        }
        if (object instanceof IsLoginBean){
            isLoginBean = (IsLoginBean) object;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        //unbinder = ButterKnife.bind(this, rootView);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unbinder.unbind();
        unbinder1.unbind();
    }

    @OnClick({ R.id.ss_num, R.id.s_nump, R.id.s_banck, R.id.s_num, R.id.s_rpass, R.id.login_exe, R.id.s_edsell, R.id.s_butn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击
            case R.id.ss_num:
                new BuyNumDialog(mContext, numList, ssNum);

                //Toast.makeText(mContext, "111111111", Toast.LENGTH_SHORT).show();
                break;
                //实付数量
            case R.id.s_nump:

                break;
            case R.id.s_banck:
                new BankDialog(mContext, list, sBanck);
                    if (list.get(0).equals("银行卡")){
                        paytype="1";
                        return;
                    }else if (list.get(1).equals("支付宝")){
                        paytype="2";
                        return;
                    }else if(list.get(2).equals("微信")){
                        paytype="3";
                        return;
                    }
               // back = sBanck.getText().toString();
                break;
                //收款
            case R.id.s_num:
                break;
                //安全密码
            case R.id.s_rpass:
                break;
            case R.id.login_exe:
                break;
            case R.id.s_edsell:
                break;
            case R.id.s_butn:
                if (username1 ==true){

                    HashMap<String,Object> headmap=new HashMap<>();
                    HashMap<String,Object> map=new HashMap<>();
                    number = ssNum.getText().toString().trim();
                    map.put("uid",mUid);
                    map.put("shell",mShell);
                    map.put("howmoney", number);
                    map.put("paytype",paytype);
                    mPresenter.postData(UserApi.getMDFSELL,headmap,map,DuSellBean.class);

                }else if (username1 ==false){
                    HashMap<String,Object> headmap=new HashMap<>();
                    HashMap<String,Object> map=new HashMap<>();
                   String number = ssNum.getText().toString().trim();
                    map.put("uid",mUid);
                    map.put("shell",mShell);
                    map.put("howmoney", number);
                    map.put("paytype",paytype);
                    mPresenter.postData(UserApi.getMCSell,headmap,map,DuSellBean.class);
                }

                break;
        }
    }

  /*  @OnClick({R.id.ss_num, R.id.s_banck, R.id.s_num, R.id.login_exe, R.id.s_butn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ss_num:


                break;
            case R.id.s_banck:
                //new BankDialog(mContext, list, sBanck);
                break;
            //收款
            case R.id.s_num:

                break;
            //小眼睛
            case R.id.login_exe:

                break;
            //卖出
            case R.id.s_butn:

             *//*   String mNum = sNums.getText().toString();
                sNump.setText(Double.parseDouble(mEd) * Double.parseDouble(mNum) + "");
                sNum.setText((Double.parseDouble(mNum) * Double.parseDouble(dmfday) + (Double.parseDouble(mEd) * Double.parseDouble(mNum)) + ""));*//*
               // String mPhone = sRpass.getText().toString().trim();
                map.put("uid", mUid);
                map.put("shell", mShell);
                *//*  map.put("   ")*//*
     *//*  mPresenter.postData(UserApi.getMCSell,);*//*
                break;
        }
    }*/
  @Override
  public void onHiddenChanged(boolean hidden) {
      super.onHiddenChanged( hidden );
      if (hidden) {// 不在最前端界面显示
      } else {// 重新显示到最前端中
          //initDBView();
      }
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

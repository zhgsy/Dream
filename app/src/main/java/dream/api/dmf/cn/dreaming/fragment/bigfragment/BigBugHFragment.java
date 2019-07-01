package dream.api.dmf.cn.dreaming.fragment.bigfragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import dream.api.dmf.cn.dreaming.adapter.HeAdapter;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpFragment;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.BuyListBean;
import dream.api.dmf.cn.dreaming.bean.EBuyBean;
import dream.api.dmf.cn.dreaming.utils.BuyNumDialog;
import dream.api.dmf.cn.dreaming.utils.LogUtils;

//买入
public class BigBugHFragment extends BaseMvpFragment<presenter> implements Contract.Iview {

    @BindView(R.id.e_teprice)
    TextView eTeprice;
    @BindView(R.id.e_num)
    TextView eNum;
    @BindView(R.id.bank_nin)
    TextView bankNin;
    @BindView(R.id.tv_edphone)
    EditText tvEdphone;
    @BindView(R.id.login_exe)
    CheckBox loginExe;
    @BindView(R.id.tv_bug)
    Button tvBug;
    Unbinder unbinder;
    private List list;
    private String nums;
    private String nus;
    private String mUid;
    private String mShell;
    private String mNum;
    private boolean username1;
    private String hytday;
    private String dmfday;
    private List<String> numList;
    private RecyclerView mRecy;
    private RecyclerView recy;
    private String c="2";
    private String start;
    private String type;
    private EditText mPass;
    private CheckBox mExe;

    public static Fragment newInstance() {
        BigBugHFragment fragment = new BigBugHFragment();
        return fragment;
    }

    @Override
    protected presenter createPresenter() {
        return new presenter();
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_big_bug_h;
    }

    @Override
    protected void initView(View view) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        dmfday = sharedPreferences.getString(UserApi.dmf_day_Today, "");
        hytday = sharedPreferences.getString(UserApi.HTODAY, "");
        username1 = sharedPreferences.getBoolean("Username", true);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        nums = sharedPreferences.getString(UserApi.tdmf_num, "");
        tvBug = view.findViewById(R.id.tv_bug);
        eTeprice = view.findViewById(R.id.e_teprice);
        mRecy = view.findViewById(R.id.recyo);
        recy = view.findViewById(R.id.recyt);
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecy.setLayoutManager(manager);
        LinearLayoutManager manager1=new LinearLayoutManager(mContext);
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        recy.setLayoutManager(manager1);
        String[] splitNums = nums.substring(1, nums.length() - 1).replace("\"", "").split(",");
        numList = Arrays.asList(splitNums);
        list = Arrays.asList(getResources().getStringArray(R.array.bank));
        if (username1 ==true){


        }else if (username1 ==false){
            String Hed = sharedPreferences.getString(UserApi.HYTED, "");
            tvBug.setText("DMF买入");
            eTeprice.setText(hytday);
        }
        mPass = view.findViewById(R.id.tv_edphone);
        mExe = view.findViewById(R.id.login_exe);
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
                if (!isChecked){
                    if (mPass.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        //不可见设置成可见
                        mPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                }else {

                    mPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    //密码可见,点击之后设置成不可见的


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
                String mPhone = tvEdphone.getText().toString().trim();
                String s1 = cutDoubleNumber(Double.valueOf((Double.parseDouble(mNum) * Double.parseDouble(dmfday) + "")));
                bankNin.setText(s1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        if (username1==true) {
            type = "2";
            HashMap<String,Object> headtmap=new HashMap<>();
            HashMap<String,Object> map1=new HashMap<>();
            start="0";
            c="2";
            map1.put("uid",mUid);
            map1.put("shell",mShell);
            map1.put("c",c);
            map1.put("status",start);
            map1.put("type",type);
            mPresenter.postData(UserApi.getBUYLIST,headtmap,map1,BuyListBean.class);
            type = "2";
            HashMap<String,Object> headmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            start="4";
            c="2";
            map.put("uid",mUid);
            map.put("shell",mShell);
            map.put("c",c);
            map.put("status",start);
            map.put("type",type);
            mPresenter.postData(UserApi.getBUYLIST,headmap,map,BuyListBean.class);
        }else if (username1==false){
            type="1";
            HashMap<String,Object> headsmap=new HashMap<>();
            HashMap<String,Object> map1=new HashMap<>();
            start="4";
            c="2";
            map1.put("uid",mUid);
            map1.put("shell",mShell);
            map1.put("c",c);
            map1.put("status",start);
            map1.put("type",type);
            mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,BuyListBean.class);
            type = "1";
            HashMap<String,Object> headmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            start="4";
            c="2";
            map.put("uid",mUid);
            map.put("shell",mShell);
            map.put("c",c);
            map.put("status",start);
            map.put("type",type);
            mPresenter.postData(UserApi.getBUYLIST,headmap,map,BuyListBean.class);
        }

    }

    @Override
    public void getData(Object object) {
        LogUtils.v(object+"");
        if (object instanceof EBuyBean){
            EBuyBean eBuyBean= (EBuyBean) object;
            if (eBuyBean.error.equals("0")){
                Toast.makeText(mContext,"购买成功",Toast.LENGTH_SHORT).show();
               /* if (username1==true) {
                    type = "2";
                    HashMap<String,Object> headtmap=new HashMap<>();
                    HashMap<String,Object> map1=new HashMap<>();
                    start="4";
                    map1.put("uid",mUid);
                    map1.put("shell",mShell);
                    map1.put("c",c);
                    map1.put("status",start);
                    map1.put("type",type);
                    mPresenter.postData(UserApi.getBUYLIST,headtmap,map1,BuyListBean.class);
                }else if (username1==false){
                    HashMap<String,Object> headsmap=new HashMap<>();
                    HashMap<String,Object> map1=new HashMap<>();
                    start="4";
                    map1.put("uid",mUid);
                    map1.put("shell",mShell);
                    map1.put("c",c);
                    map1.put("status",start);
                    map1.put("type",type);
                    mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,BuyListBean.class);
                }*/
            }else{
                Toast.makeText(mContext,eBuyBean.msg,Toast.LENGTH_SHORT).show();
               /* if (username1==true) {
                    type = "2";
                    HashMap<String,Object> headtmap=new HashMap<>();
                    HashMap<String,Object> map1=new HashMap<>();
                    start="4";
                    map1.put("uid",mUid);
                    map1.put("shell",mShell);
                    map1.put("c",c);
                    map1.put("status",start);
                    map1.put("type",type);
                    mPresenter.postData(UserApi.getBUYLIST,headtmap,map1,BuyListBean.class);
                }else if (username1==false){
                    HashMap<String,Object> headsmap=new HashMap<>();
                    HashMap<String,Object> map1=new HashMap<>();
                    start="4";
                    map1.put("uid",mUid);
                    map1.put("shell",mShell);
                    map1.put("c",c);
                    map1.put("status",start);
                    map1.put("type",type);
                    mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,BuyListBean.class);
                }*/
            }

        }
        if (object instanceof BuyListBean){
            BuyListBean buyListBean= (BuyListBean) object;
            if (buyListBean.error==0){
                List<BuyListBean.DataBean> data = buyListBean.data;
                HeAdapter heAdapter=new HeAdapter(mContext,data);
                mRecy.setAdapter(heAdapter);
                recy.setAdapter(heAdapter);
                //BuyListAdapter buyListAdapter=new BuyListAdapter(mContext,data);
               // mRecy.setAdapter(buyListAdapter);
               // Toast.makeText(mContext,"成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(mContext,buyListBean.msg,Toast.LENGTH_SHORT).show();
            }
        }
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

    @OnClick({R.id.e_teprice, R.id.e_num, R.id.bank_nin, R.id.tv_edphone, R.id.login_exe, R.id.tv_bug})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.e_teprice:
                break;
            case R.id.e_num:
                new BuyNumDialog(mContext, numList, eNum);
                break;
            case R.id.bank_nin:
                break;
            case R.id.tv_edphone:
                break;
            case R.id.login_exe:
                break;
            case R.id.tv_bug:
                if (username1==true){
                    type="2";
                    HashMap<String,Object> headmap=new HashMap<>();
                    HashMap<String,Object> map=new HashMap<>();
                    mNum = eNum.getText().toString();
                    String mPhone = tvEdphone.getText().toString().trim();
                    //String mprice = eTeprice.getText().toString();
                    map.put("uid",mUid);
                    map.put("shell",mShell);
                    map.put("repass",mPhone);
                    map.put("howmoney", mNum);
                    map.put("sellprice",bankNin);
                    mPresenter.postData(UserApi.getBIGHYTBBUY,headmap,map,EBuyBean.class);
                  /*  HashMap<String,Object> headtmap=new HashMap<>();
                    HashMap<String,Object> map1=new HashMap<>();
                    start="4";
                    map1.put("uid",mUid);
                    map1.put("shell",mShell);
                    map1.put("c",c);
                    map1.put("status",start);
                    map1.put("type",type);
                    mPresenter.postData(UserApi.getBUYLIST,headtmap,map1,BuyListBean.class);*/

                }else if (username1==false){
                    type="1";
                    /*   String Hed = sharedPreferences.getString(UserApi.HYTED, "");*/
                    HashMap<String,Object> headmap=new HashMap<>();
                    HashMap<String,Object> map=new HashMap<>();
                    mNum = eNum.getText().toString();
                    String mPhone = tvEdphone.getText().toString().trim();
                    //String mprice = eTeprice.getText().toString();
                    map.put("uid",mUid);
                    map.put("shell",mShell);
                    map.put("repass",mPhone);
                    map.put("howmoney", mNum);
                    //map.put("sellprice",bankNin);
                    mPresenter.postData(UserApi.getBIGHYTBBUY,headmap,map,EBuyBean.class);
                 /*   HashMap<String,Object> headsmap=new HashMap<>();
                    HashMap<String,Object> map1=new HashMap<>();
                    start="4";
                    map1.put("uid",mUid);
                    map1.put("shell",mShell);
                    map1.put("c",c);
                    map1.put("status",start);
                    map1.put("type",type);
                    mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,BuyListBean.class);*/

                }
                break;
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

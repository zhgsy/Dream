package dream.api.dmf.cn.dreaming.fragment.bigfragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.adapter.FinishAdapter;
import dream.api.dmf.cn.dreaming.adapter.OKBean;
import dream.api.dmf.cn.dreaming.adapter.OkAdapter;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpFragment;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.BuyListBean;
import dream.api.dmf.cn.dreaming.bean.DuSellBean;
import dream.api.dmf.cn.dreaming.bean.SellBean;
import dream.api.dmf.cn.dreaming.utils.BankDialog;
import dream.api.dmf.cn.dreaming.utils.BuyNumDialog;


//卖出的DMF
public class BigSellHFragment extends BaseMvpFragment<presenter> implements Contract.Iview {
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
    @BindView(R.id.s_rpass)
    EditText sRpass;
    @BindView(R.id.login_exe)
    CheckBox loginExe;
    @BindView(R.id.s_edsell)
    TextView sEdsell;
    @BindView(R.id.s_butn)
    Button sButn;
    Unbinder unbinder;
    private String mEd;
    private String mUid;
    private String mShell;
    private TextView mPrice;
    private String dmfday;
    private String nums;
    private String paytype;
    private List<String> numList;
    private List list;
    private boolean username1;
    private String howmey;
    private String c="2";
    private String status;
    public String type;
    private RecyclerView recy;
    private RecyclerView recyt;
    private EditText mPass;
    private CheckBox mExe;
    private FinishAdapter adapter;
    private MaterialRefreshLayout materialRefreshLayout;

    public static Fragment newInstance() {
        BigSellHFragment fragment = new BigSellHFragment();
        return fragment;
    }

    @Override
    protected presenter createPresenter() {
        return new presenter();
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_big_sell_h;
    }

    @Override
    protected void initView(View view) {
        mPrice = view.findViewById(R.id.s_price);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        username1 = sharedPreferences.getBoolean("Username", true);
        dmfday = sharedPreferences.getString(UserApi.dmf_day_Today, "");
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        mEd = sharedPreferences.getString(UserApi.DMFED, "");
        nums = sharedPreferences.getString(UserApi.BUYNUM, "");
        String[] splitNums = nums.substring(1, nums.length() - 1).replace("\"", "").split(",");
        numList = Arrays.asList(splitNums);
        list = Arrays.asList(getResources().getStringArray(R.array.bank));
        recy = view.findViewById(R.id.recyo);
        recyt = view.findViewById(R.id.recyt);
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recy.setLayoutManager(manager);
        LinearLayoutManager manager1=new LinearLayoutManager(mContext);
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyt.setLayoutManager(manager1);
        mPass = view.findViewById(R.id.s_rpass);
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

                    //密码可见,点击之后设置成不可见的
                    mPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                }
            }
        });
        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        materialRefreshLayout.setIsOverLay(false);
        materialRefreshLayout.setWaveShow(false);

    }

    @Override
    protected void getData() {
        if (username1 ==true){
            type="2";
            status="0";
            c="2";
            HashMap<String,Object> headsmap=new HashMap<>();
            HashMap<String,Object> map1=new HashMap<>();
            map1.put("uid",mUid);
            map1.put("shell",mShell);
            map1.put("c",c);
            map1.put("status",status);
            map1.put("type",type);
            mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,SellBean.class);
            type="2";
            status="5";
            c="2";
            HashMap<String,Object> headmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            map.put("uid",mUid);
            map.put("shell",mShell);
            map.put("c",c);
            map.put("status",status);
            map.put("type",type);
            mPresenter.postData(UserApi.getBUYLIST,headmap,map1,OKBean.class);

        }else if (username1 ==false){
            status="0";
            type="1";
            c="2";
            HashMap<String,Object> headsmap=new HashMap<>();
            HashMap<String,Object> map1=new HashMap<>();
            map1.put("uid",mUid);
            map1.put("shell",mShell);
            map1.put("c",c);
            map1.put("status",status);
            map1.put("type",type);
            mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,SellBean.class);
            status="5";
            type="1";
            c="2";
            HashMap<String,Object> headmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            map.put("uid",mUid);
            map.put("shell",mShell);
            map.put("c",c);
            map.put("status",status);
            map.put("type",type);
            mPresenter.postData(UserApi.getBUYLIST,headmap,map,OKBean.class);

        }

        mPrice.setText(dmfday);
        ssNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // new BuyNumDialog(mContext, numList, sNums);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mNum = ssNum.getText().toString();
                Double num=((Double.parseDouble(mNum) * Double.parseDouble(mEd)));
                Double nu=(Double.parseDouble(mNum));
                sNump.setText(num+nu+"");
                String s1 = cutDoubleNumber(Double.valueOf((Double.parseDouble(mNum) * Double.parseDouble(dmfday) + "")));
                sNum.setText(s1);
                //Toast.makeText(mContext, "111111111", Toast.LENGTH_SHORT).show();
                //String mNum = ssNum.getText().toString();
                //sNump.setText(Double.parseDouble(mEd) * Double.parseDouble(ssNum) + "");
                // sNum.setText((Double.parseDouble(mNum) * Double.parseDouble(dmfday) + (Double.parseDouble(mEd) * Double.parseDouble(String.valueOf(mNum))) + ""));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
     }



    @Override
    public void getData(Object object) {
        if (object instanceof  OKBean){
            OKBean okBean= (OKBean) object;
            if (okBean.error==0){
                List<OKBean.DataBean> data = okBean.data;
                OkAdapter okAdapter=new OkAdapter(data,mContext);
                recyt.setAdapter(okAdapter);
            }
        }
        if (object instanceof SellBean){
            SellBean sellBean= (SellBean) object;
            if (sellBean.error==0){
                List<SellBean.DataBean> datase = sellBean.data;
                adapter = new FinishAdapter(mContext, datase);
                recy.setAdapter(adapter);

                //Toast.makeText(mContext,"成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(mContext,sellBean.msg,Toast.LENGTH_SHORT).show();
            }

        }
        if (object instanceof DuSellBean){
            DuSellBean duSellBean= (DuSellBean) object;
            if (duSellBean.error.equals("0")){
               Toast.makeText(mContext,"卖出成功",Toast.LENGTH_SHORT).show();
                //sBanck.setText("");
                ssNum.setText("");
                sNum.setText("");
                mPass.setText("");
            }else{
                Toast.makeText(mContext,duSellBean.msg,Toast.LENGTH_SHORT).show();

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

    @OnClick({R.id.ss_num, R.id.s_nump, R.id.s_banck, R.id.s_num, R.id.s_rpass, R.id.login_exe, R.id.s_edsell, R.id.s_butn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ss_num:
                new BuyNumDialog(mContext, numList, ssNum);
                break;
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
                break;
            case R.id.s_num:
                break;
            case R.id.s_rpass:
                break;
            case R.id.login_exe:
                break;
            case R.id.s_edsell:
                break;
            case R.id.s_butn:
                howmey = ssNum.getText().toString().trim();
                if (username1 ==true){
                    type="2";
                    HashMap<String,Object> headmap=new HashMap<>();
                    HashMap<String,Object> map=new HashMap<>();
                    map.put("uid",mUid);
                    map.put("shell",mShell);
                    map.put("howmoney", howmey);
                    map.put("paytype",paytype);
                    mPresenter.postData(UserApi.getBIGHYTBSell,headmap,map,DuSellBean.class);

                /*    HashMap<String,Object> headsmap=new HashMap<>();
                    HashMap<String,Object> map1=new HashMap<>();
                    map1.put("uid",mUid);
                    map1.put("shell",mShell);
                    map1.put("c",c);
                    map1.put("status",status);
                    map1.put("type",type);
                    mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,BuyListBean.class);*/

                }else if (username1 ==false){
                    type="1";
                    HashMap<String,Object> headmap=new HashMap<>();
                    HashMap<String,Object> map=new HashMap<>();
                    map.put("uid",mUid);
                    map.put("shell",mShell);
                    map.put("howmoney", howmey);
                    map.put("paytype",paytype);
                    mPresenter.postData(UserApi.getBIGHYTBSell,headmap,map,SellBean.class);

                /*    HashMap<String,Object> headsmap=new HashMap<>();
                    HashMap<String,Object> map1=new HashMap<>();
                    map1.put("uid",mUid);
                    map1.put("shell",mShell);
                    map1.put("c",c);
                    map1.put("status",status);
                    map1.put("type",type);
                    mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,SellBean.class);*/



                }
                break;
        }
    }
    public void getInstance(){
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (username1 ==true){
                            type="2";
                            HashMap<String,Object> headmap=new HashMap<>();
                            HashMap<String,Object> map=new HashMap<>();
                            map.put("uid",mUid);
                            map.put("shell",mShell);
                            map.put("howmoney", howmey);
                            map.put("paytype",paytype);
                            mPresenter.postData(UserApi.getBIGHYTBSell,headmap,map,DuSellBean.class);

                            HashMap<String,Object> headsmap=new HashMap<>();
                            HashMap<String,Object> map1=new HashMap<>();
                            map1.put("uid",mUid);
                            map1.put("shell",mShell);
                            map1.put("c",c);
                            map1.put("status",status);
                            map1.put("type",type);
                            mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,BuyListBean.class);

                        }else if (username1 ==false){
                            type="1";
                            HashMap<String,Object> headmap=new HashMap<>();
                            HashMap<String,Object> map=new HashMap<>();
                            map.put("uid",mUid);
                            map.put("shell",mShell);
                            map.put("howmoney", howmey);
                            map.put("paytype",paytype);
                            mPresenter.postData(UserApi.getBIGHYTBSell,headmap,map,SellBean.class);

                         /*   HashMap<String,Object> headsmap=new HashMap<>();
                            HashMap<String,Object> map1=new HashMap<>();
                            map1.put("uid",mUid);
                            map1.put("shell",mShell);
                            map1.put("c",c);
                            map1.put("status",status);
                            map1.put("type",type);
                            mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,SellBean.class);*/
                        }
                        materialRefreshLayout.finishRefresh();
                    }

                },3000);


            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {



            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getInstance();
       /* howmey = ssNum.getText().toString().trim();
        if (username1 ==true){
            type="2";
            HashMap<String,Object> headmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            map.put("uid",mUid);
            map.put("shell",mShell);
            map.put("howmoney", howmey);
            map.put("paytype",paytype);
            mPresenter.postData(UserApi.getBIGHYTBSell,headmap,map,DuSellBean.class);
            HashMap<String,Object> headsmap=new HashMap<>();
            HashMap<String,Object> map1=new HashMap<>();
            map1.put("uid",mUid);
            map1.put("shell",mShell);
            map1.put("c",c);
            map1.put("status",status);
            map1.put("type",type);
            mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,BuyListBean.class);

        }else if (username1 ==false){
            type="1";
            HashMap<String,Object> headmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            map.put("uid",mUid);
            map.put("shell",mShell);
            map.put("howmoney", howmey);
            map.put("paytype",paytype);
            mPresenter.postData(UserApi.getBIGHYTBSell,headmap,map,SellBean.class);

            HashMap<String,Object> headsmap=new HashMap<>();
            HashMap<String,Object> map1=new HashMap<>();
            map1.put("uid",mUid);
            map1.put("shell",mShell);
            map1.put("c",c);
            map1.put("status",status);
            map1.put("type",type);
            mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,SellBean.class);

        }*/
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

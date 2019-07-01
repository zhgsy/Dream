package dream.api.dmf.cn.dreaming.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.activity.MoneyActivity;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpFragment;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.IsLoginBean;
import dream.api.dmf.cn.dreaming.bean.TradingBean;
import dream.api.dmf.cn.dreaming.utils.LogUtils;
import dream.api.dmf.cn.dreaming.view.ChartView;

public class TradingFragment extends BaseMvpFragment<presenter> implements Contract.Iview {
    @BindView(R.id.r_butn)
    Button mButn;
    @BindView(R.id.r_butn2)
    Button mButn2;
    Unbinder unbinder;
    @BindView(R.id.r_oname)
    TextView mOname;
    @BindView(R.id.r_tname)
    TextView mTname;
    @BindView(R.id.spinner)
    AppCompatSpinner spinner;
    @BindView(R.id.ttt)
    TextView ttt;
    @BindView(R.id.t_newprice)
    TextView mNewprice;
    @BindView(R.id.t_addprice)
    TextView mAddprice;
    @BindView(R.id.h_newprice)
    TextView mhNewprice;
    @BindView(R.id.t_haddprice)
    TextView mHaddprice;
    private List<String> xValue = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Float> yValue = new ArrayList<>();
    //折线对应的数据
    private Map<String, Float> value = new HashMap<>();
    private ChartView chartView;
    private TradingBean bean;
    private String ids;
    private RecyclerView mRecy;
    private String tex;
    private String te;
    private IsLoginBean isLoginBean;
    private SharedPreferences sharedPreferences;
    private String teup;
    private String hye;
    private String htoday;
    private String hupdate;
    private String unmae;
    private TextView pid;
    private String mUid;
    private String mShell;

    @Override
    protected presenter createPresenter() {
        return new presenter();
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_trading;
    }

    @Override
    protected void initView(View view) {
        chartView = view.findViewById(R.id.chartview);
        sharedPreferences = mContext.getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        /*.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MoneyActivity.class));
            }
        });*/
        pid = view.findViewById(R.id.ttt);
    }

    @Override
    protected void getData() {
        ids = "1";
        pid.setText("HYT折线图");
        HashMap<String, Object> headmap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid", mUid);
        map.put("shell", mShell);
        map.put("type", ids);
        mPresenter.postData(UserApi.getMoney, headmap, map, TradingBean.class);
        pid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             /*   ids="2";
                pid.setText("DMF折线图");
                HashMap<String, Object> headmap = new HashMap<>();
                HashMap<String, Object> map = new HashMap<>();
                 //*   sharedPreferences = mContext.getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
                String mUid = sharedPreferences.getString(UserApi.Uid, "");
                String mShell = sharedPreferences.getString(UserApi.Shell, "");
                map.put("uid", mUid);
                map.put("shell", mShell);
                map.put("type", ids);
                mPresenter.postData(UserApi.getMoney, headmap, map, TradingBean.class);*/
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ids = "2";
                pid.setText("DMF折线图");
                HashMap<String, Object> headmap = new HashMap<>();
                HashMap<String, Object> map = new HashMap<>();
                //*   sharedPreferences = mContext.getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
                String mUid = sharedPreferences.getString(UserApi.Uid, "");
                String mShell = sharedPreferences.getString(UserApi.Shell, "");
                map.put("uid", mUid);
                map.put("shell", mShell);
                map.put("type", ids);
                mPresenter.postData(UserApi.getMoney, headmap, map, TradingBean.class);
            }

            @Override
            public void afterTextChanged(Editable s) {
                ids = "2";
                pid.setText("DMF折线图");
                HashMap<String, Object> headmap = new HashMap<>();
                HashMap<String, Object> map = new HashMap<>();
                //*   sharedPreferences = mContext.getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
                String mUid = sharedPreferences.getString(UserApi.Uid, "");
                String mShell = sharedPreferences.getString(UserApi.Shell, "");
                map.put("uid", mUid);
                map.put("shell", mShell);
                map.put("type", ids);
                mPresenter.postData(UserApi.getMoney, headmap, map, TradingBean.class);
            }
        });


        HashMap<String, Object> headm = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("uid", mUid);
        map2.put("shell", mShell);
        mPresenter.postData(UserApi.getIsLogin, headm, map2, IsLoginBean.class);

    }

    @Override
    public void getData(Object object) {
        LogUtils.v(object.toString());
        if (object instanceof TradingBean) {
            bean = (TradingBean) object;
            initData();
        }
        if (object instanceof IsLoginBean) {
            isLoginBean = (IsLoginBean) object;
            mNewprice.setText(isLoginBean.dmf_day_price.today);
            mAddprice.setText(isLoginBean.dmf_day_price.updatemoney);
            mhNewprice.setText(isLoginBean.hyt_day_price.today);
            mHaddprice.setText(isLoginBean.hyt_day_price.updatemoney);
            // Gson gson=new Gson();
            //String jsonStr = gson.toJson(isLoginBean.hyt_day_price);
            tex = isLoginBean.dmf_day_price.yestoday;
            te = isLoginBean.dmf_day_price.today;
            teup = isLoginBean.dmf_day_price.updatemoney;
            hye = isLoginBean.hyt_day_price.yestoday;
            htoday = isLoginBean.hyt_day_price.today;
            hupdate = isLoginBean.hyt_day_price.updatemoney;
            sharedPreferences.edit().putString(UserApi.dmf_day_price, tex).commit();
            sharedPreferences.edit().putString(UserApi.dmf_day_Today, te).commit();
            sharedPreferences.edit().putString(UserApi.updatemoney, teup).commit();
            sharedPreferences.edit().putString(UserApi.DmfNUm, String.valueOf(isLoginBean.dmf_num)).commit();
            sharedPreferences.edit().putString(UserApi.HYE, hye).commit();
            sharedPreferences.edit().putString(UserApi.HTODAY, htoday).commit();
            sharedPreferences.edit().putString(UserApi.HUPDATE, hupdate).commit();
            sharedPreferences.edit().putString(UserApi.ac_status, isLoginBean.ac_status).commit();
            //dmf
            sharedPreferences.edit().putString(UserApi.STock_mdf, isLoginBean.stock_mdf).commit();
            sharedPreferences.edit().putString(UserApi.balanceDMF, isLoginBean.balance_dmf).commit();
            sharedPreferences.edit().putString(UserApi.regmoneyDMF, isLoginBean.regmoney_dmf).commit();
            sharedPreferences.edit().putString(UserApi.credit3, isLoginBean.credit3).commit();
            sharedPreferences.edit().putString(UserApi.STOCK, isLoginBean.stock).commit();
            sharedPreferences.edit().putString(UserApi.balance, isLoginBean.balance).commit();
            sharedPreferences.edit().putString(UserApi.regmoney, isLoginBean.regmoney).commit();
            sharedPreferences.edit().putString(UserApi.credit4, isLoginBean.credit4).commit();
            sharedPreferences.edit().putString(UserApi.dmf_day_Today, isLoginBean.dmf_day_price.today).commit();
            sharedPreferences.edit().putString(UserApi.updatemoney, isLoginBean.dmf_day_price.updatemoney).commit();
            sharedPreferences.edit().putString(UserApi.HYTT_PRICE, isLoginBean.hyt_day_price.today).commit();
            sharedPreferences.edit().putString(UserApi.DMFED, String.valueOf(isLoginBean.dmfed)).commit();
            sharedPreferences.edit().putString(UserApi.tdmf_num, String.valueOf(isLoginBean.tdmf_num)).commit();
            sharedPreferences.edit().putString(UserApi.STock_mdf, isLoginBean.balance_dmf).commit();
            sharedPreferences.edit().putString(UserApi.HYTED, String.valueOf(isLoginBean.hyted)).commit();
            sharedPreferences.edit().putString(UserApi.BUYNUM, String.valueOf(isLoginBean.jy4)).commit();
            sharedPreferences.edit().putString(UserApi.idcard, (String) isLoginBean.idcard).commit();
            unmae = sharedPreferences.getString(UserApi.ac_status, "");


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

    @OnClick({R.id.r_butn, R.id.r_butn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.r_butn:
                mOname.setText("DMF");
                mOname.setTextSize(16);
                if (unmae.equals("1")) {
                    Intent intent = new Intent(getActivity(), MoneyActivity.class);
                    intent.putExtra("name", tex);
                    intent.putExtra("today", te);
                    intent.putExtra("update", teup);
                    intent.putExtra("uname", "1");
                    sharedPreferences.edit().putBoolean("Username", true).commit();
                    //sharedPreferences.edit().putString(UserApi.Hnum,"one").commit();
                    startActivity(intent);
                    break;
                } else {
                    Toast.makeText(mContext, "未激活", Toast.LENGTH_LONG).show();
                }
                break;


            case R.id.r_butn2:
                if (unmae.equals("1")) {
                    Intent intent2 = new Intent(getActivity(), MoneyActivity.class);
                    intent2.putExtra("name", hye);
                    intent2.putExtra("today", htoday);
                    intent2.putExtra("update", hupdate);
                    intent2.putExtra("uname", "2");
                    sharedPreferences.edit().putBoolean("Username", false).commit();
                    startActivity(intent2);
                    break;
                } else {
                    Toast.makeText(mContext, "未激活", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    public void initData() {
        //x轴坐标对应的数据
        for (int i = 0; i < bean.data.size(); i++) {
            TradingBean.DataBean dataBean = bean.data.get(i);
            Log.i("zzzaaa", "initData: date = " + dataBean.date);
            Log.i("zzzaaa", "initData: price = " + dataBean.price + "      sPrice = " + Float.valueOf(dataBean.price));
            Log.i("zzzaaa", "initData: amount = " + dataBean.amount + "     sAmount = " + Float.valueOf(dataBean.amount));
            xValue.add(dataBean.date);
            yValue.add(Float.valueOf(dataBean.price));
            value.put(dataBean.date, Float.valueOf(dataBean.amount));
        }
        chartView.setValue(value, xValue, yValue);


    }


}

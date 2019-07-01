package dream.api.dmf.cn.dreaming.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.activity.moneyactivity.MoneyMLActivity;
import dream.api.dmf.cn.dreaming.activity.moneyactivity.MoneyQActivity;
import dream.api.dmf.cn.dreaming.activity.moneyactivity.MoneyluActivity;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpFragment;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;


public class DMFFragment extends BaseMvpFragment<presenter> implements Contract.Iview {
    @BindView(R.id.m_money_lu)
    LinearLayout mMoneyLu;
    @BindView(R.id.m_money_qq)
    LinearLayout mMoneyQq;
    @BindView(R.id.m_money_q)
    LinearLayout mMoneyQ;
    Unbinder unbinder;
    private boolean username1;
    private TextView name;
    private TextView mname;
    private TextView one;
    private TextView two;
    private TextView three;
    private TextView four;
    private String done;
    private String dtwo;
    private String dthree;
    private String dfour;
    private String hone;
    private String htwo;
    private String hthree;
    private String hfour;

    public static Fragment newInstance() {
        DMFFragment fragment = new DMFFragment();

        return fragment;
    }


    @Override
    protected presenter createPresenter() {
        return new presenter();
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_dmf;
    }

    @Override
    protected void initView(View view) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        username1 = sharedPreferences.getBoolean("Username", true);
        done = sharedPreferences.getString(UserApi.STock_mdf, "");
        dtwo = sharedPreferences.getString(UserApi.balanceDMF, "");
        dthree = sharedPreferences.getString(UserApi.regmoneyDMF, "");
        dfour = sharedPreferences.getString(UserApi.credit3, "");
        hone = sharedPreferences.getString(UserApi.STOCK, "");
        htwo = sharedPreferences.getString(UserApi.balance, "");
        hthree = sharedPreferences.getString(UserApi.regmoney, "");
        hfour = sharedPreferences.getString(UserApi.credit4, "");
        name = view.findViewById(R.id.dname);
        mname = view.findViewById(R.id.d_name);
        one = view.findViewById(R.id.num_one);
        two = view.findViewById(R.id.num_two);
        three = view.findViewById(R.id.num_three);
        four = view.findViewById(R.id.num_four);
        if (username1 ==true){
            name.setText("DMFB");
            mname.setText("FDMFB");
            one.setText(dtwo);
            two.setText(done);
            three.setText(hfour);
            four.setText(dthree);

        }else if (username1 ==false){
            one.setText(hone);
            two.setText(htwo);
            three.setText(dfour);
            four.setText(hthree);
        }
    }

    @Override
    protected void getData() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);

    }

    @Override
    public void getData(Object object) {

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

    @OnClick({ R.id.m_money_lu, R.id.m_money_qq, R.id.m_money_q})
    public void onViewClicked(View view) {
        switch (view.getId()) {
           /* //转账
            case R.id.m_money_zz:
                startActivity(new Intent(getActivity(),MoneyZActivity.class));
                break;*/
            //交易记录
            case R.id.m_money_lu:
                startActivity(new Intent(getActivity(),MoneyluActivity.class));
                break;
            //钱包
            case R.id.m_money_qq:
                startActivity(new Intent(getActivity(),MoneyMLActivity.class));
                break;
         /*    //HYT转入
            case R.id.m_money_r:
                startActivity(new Intent(getActivity(),MoneyHRActivity.class));
                break;
             //HYT转出
            case R.id.m_money_c:
                startActivity(new Intent(getActivity(),MoneyHCActivity.class));
                break;*/
            //金元转换
            case R.id.m_money_q:
                startActivity(new Intent(getActivity(),MoneyQActivity.class));
                break;
        }
    }
}

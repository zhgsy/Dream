package dream.api.dmf.cn.dreaming.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.api.UserApi;

public class MoneyFragment extends Fragment {

    private RadioGroup mGroup;
    private RadioButton mButton1,mButton2;
    private ViewPager mPage;
    private ArrayList<Fragment> list;
    private List mHytlist;
    private Button mUpMoney;
    private View view,view2;
    private TextView mYesDay,mYesDay2;
    private TextView mToDay,mToDay2,mUpMoney2;
    private String peice;
    private String hToday;
    private String hye;
    private String yUpdate;
    private String updatep;

    public static Fragment newInstance() {
        MoneyFragment fragment = new MoneyFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        boolean username1 = sharedPreferences.getBoolean("Username", true);
        String dmfday = sharedPreferences.getString(UserApi.dmf_day_Today, "");
        Log.i("aaaaaaaa",dmfday);
        peice = sharedPreferences.getString(UserApi.dmf_day_price, "");
        updatep = sharedPreferences.getString(UserApi.updatemoney, "");
        hToday = sharedPreferences.getString(UserApi.HTODAY, "");
        hye = sharedPreferences.getString(UserApi.HYE, "");
        yUpdate = sharedPreferences.getString(UserApi.HUPDATE, "");
        view = View.inflate(getActivity(),R.layout.fragment_money,null);
        mGroup = view.findViewById(R.id.tGroup);
        mButton1 = view.findViewById(R.id.m_button1);
        mButton2 = view.findViewById(R.id.m_button2);
        mPage = view.findViewById(R.id.m_pager);
        mYesDay = view.findViewById(R.id.yesteday);
        mToDay = view.findViewById(R.id.today);
        mUpMoney = view.findViewById(R.id.updatemoney);

        Log.i("aaaaaaaa", updatep);
        //sharedPreferences.getString("")
        if (username1==true){
           ;
            mToDay.setText(dmfday);
            if (Double.parseDouble(updatep)<0){

                mUpMoney.setText("-"+  cutDoubleNumber(Double.parseDouble(updatep)));
            }else{
                mUpMoney.setText("+"+  cutDoubleNumber(Double.parseDouble(updatep)));
            }

            mYesDay.setText(peice);

        }else if (username1==false){
            mToDay.setText(hToday);
            if (Double.parseDouble(yUpdate)<1){
                mUpMoney.setText("-"+ cutDoubleNumber(Double.parseDouble(yUpdate)));
            }else{
                mUpMoney.setText("+"+ cutDoubleNumber(Double.parseDouble(yUpdate)));
            }
           //mUpMoney.setText(yUpdate);
            mYesDay.setText(hye);

        }
        initdata();
        return view;
    }
    protected void initdata() {
        list = new ArrayList<>();
        SellFragment frag_01=new SellFragment();
        BuyFragment frag_02=new BuyFragment();
        list.add(frag_01);
        list.add(frag_02);
        mPage.setAdapter(new FragmentPagerAdapter(this.getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        mGroup.check(mGroup.getChildAt(0).getId());
        mPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mGroup.check(mGroup.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.m_button1:
                        mPage.setCurrentItem(0);
                        break;
                    case R.id.m_button2:
                        mPage.setCurrentItem(1);
                        break;

                }
            }
        });

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

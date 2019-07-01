package dream.api.dmf.cn.dreaming.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpFragment;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.fragment.bigfragment.BigBugFragment;
import dream.api.dmf.cn.dreaming.fragment.bigfragment.BigBugHFragment;
import dream.api.dmf.cn.dreaming.fragment.bigfragment.BigSellFragment;
import dream.api.dmf.cn.dreaming.fragment.bigfragment.BigSellHFragment;

public class BigTFragment extends BaseMvpFragment<presenter> implements Contract.Iview {

    @BindView(R.id.m_button1)
    RadioButton mButton1;
    @BindView(R.id.m_button2)
    RadioButton mButton2;
    @BindView(R.id.m_button3)
    RadioButton mButton3;
    @BindView(R.id.m_button4)
    RadioButton mButton4;
    @BindView(R.id.tGroup)
    RadioGroup mGroup;
    @BindView(R.id.m_pager)
    ViewPager mPager;
    Unbinder unbinder;
    private ViewPager mViewpa;
    private TabLayout mTab;
    private ArrayList<Fragment> list;
    private SharedPreferences sharedPreferences;
    private boolean username1;
    public static Fragment newInstance() {
        BigTFragment fragment = new BigTFragment();
        return fragment;
    }

    @Override
    protected presenter createPresenter() {
        return new presenter();
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_big_t;
    }

    @Override
    protected void initView(View view) {
        sharedPreferences = getActivity().getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        username1 = sharedPreferences.getBoolean("Username", true);
        if (username1 ==true){
            mButton3 = view.findViewById(R.id.m_button3);
            mButton4= view.findViewById(R.id.m_button4);
            mButton3.setText("卖出DMF");
            mButton4.setText("买入DMF");
        }else if (username1 ==false){


        }
    }

    @Override
    protected void getData() {
        list = new ArrayList<>();
        BigSellFragment frag01 =new BigSellFragment();
        BigBugFragment frag02 =new BigBugFragment();
        BigSellHFragment frag03=new BigSellHFragment();
        BigBugHFragment frag04=new BigBugHFragment();
        list.add(frag01);
        list.add(frag02);
        list.add(frag03);
        list.add(frag04);
        mPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
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
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    @OnClick({R.id.m_button1, R.id.m_button2, R.id.m_button3, R.id.m_button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_button1:
                mPager.setCurrentItem(0);
                break;
            case R.id.m_button2:
                mPager.setCurrentItem(1);
                break;
            case R.id.m_button3:
                mPager.setCurrentItem(2);
                break;
            case R.id.m_button4:
                mPager.setCurrentItem(3);
                break;
        }
    }

 /*   final class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list[position];
        }


        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TabText[position];

        }
    }*/

}

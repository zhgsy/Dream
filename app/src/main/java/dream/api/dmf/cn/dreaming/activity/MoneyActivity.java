package dream.api.dmf.cn.dreaming.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.activity.moneyactivity.MoneyluActivity;
import dream.api.dmf.cn.dreaming.fragment.BigTFragment;
import dream.api.dmf.cn.dreaming.fragment.DMFFragment;
import dream.api.dmf.cn.dreaming.fragment.MoneyFragment;

public class MoneyActivity extends AppCompatActivity {
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.m_lu)
    ImageView mLu;
    private ViewPager mViewpa;
    private TabLayout mTab;
    private Fragment[] list = new Fragment[3];
    private String[] TabText = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        ButterKnife.bind(this);

        mTab = findViewById(R.id.tablayout);
        mViewpa = findViewById(R.id.tab_viewpager);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mLu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoneyActivity.this,MoneyluActivity.class));
            }
        });
        Intent intent = getIntent();
        String uname = intent.getStringExtra("uname");
       // Toast.makeText(this, uname, Toast.LENGTH_SHORT).show();
        if (uname.equals("1")) {
            TabText[0] = "额度交易";
            TabText[1] = "大厅交易";
            TabText[2] = "DMF";
            mTab.setTabMode(TabLayout.MODE_FIXED);
            list[0] = MoneyFragment.newInstance();
            list[1] = BigTFragment.newInstance();
            list[2] = DMFFragment.newInstance();
            PagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
            mViewpa.setAdapter(pagerAdapter);
            //将ViewPager和TabLayout绑定
            mTab.setupWithViewPager(mViewpa);
            //mViewpa.setCurrentItem(2);
            DMFFragment.newInstance().setTargetFragment(DMFFragment.newInstance(),1);
            //startActivity(new Intent(this,DMFFragment.class));
        } else if (uname.equals("2")) {
            TabText[0] = "额度交易";
            TabText[1] = "大厅交易";
            TabText[2] = "HTY";
            mTab.setTabMode(TabLayout.MODE_FIXED);
            list[0] = MoneyFragment.newInstance();
            list[1] = BigTFragment.newInstance();
            list[2] = DMFFragment.newInstance();
            PagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
            mViewpa.setAdapter(pagerAdapter);
            mTab.setupWithViewPager(mViewpa);

        }


    }

    final class MyViewPagerAdapter extends FragmentPagerAdapter {
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
    }
}

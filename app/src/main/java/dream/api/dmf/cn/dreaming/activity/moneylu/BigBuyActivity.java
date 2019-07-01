package dream.api.dmf.cn.dreaming.activity.moneylu;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.HashMap;
import java.util.List;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.adapter.MoneyBigAdapter;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.BigBean;

public class BigBuyActivity extends BaseMvpActivity<presenter> implements Contract.Iview {

    private RecyclerView recy;
    private String mUid;
    private String mShell;
    private boolean username1;
    private String type;
    private String c;
    private String status;
    private TextView mTitle;
    private MaterialRefreshLayout materialRefreshLayout;
    private MoneyBigAdapter adapter1;
    private SharedPreferences sharedPreferences;

    @Override
    public void getThisData() {
        if (username1 == true) {
            type="2";
            c="2";
            status="0";
            HashMap<String,Object> headmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            map.put("c",c);
            map.put("type",type);
            map.put("uid",mUid);
            map.put("shell",mShell);
            map.put("status",status);
            mPresenter.postData(UserApi.getSelllist,headmap,map,BigBean.class);
        } else if (username1 == false) {
            type="1";
            c="2";
            HashMap<String,Object> headmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            map.put("c",c);
            map.put("type",type);
            map.put("uid",mUid);
            map.put("shell",mShell);
            map.put("status",status);
            mPresenter.postData(UserApi.getSelllist,headmap,map,BigBean.class);

        }
    }

    @Override
    public void getInitData() {
        sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        username1 = sharedPreferences.getBoolean("Username", true);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        recy = findViewById(R.id.mrecy);
        mTitle = findViewById(R.id.tv_title);
        mTitle.setText("卖出记录");
        mTitle.setTextSize(16);
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recy.setLayoutManager(manager);
        materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        materialRefreshLayout.setIsOverLay(false);
        materialRefreshLayout.setWaveShow(false);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                adapter1.notifyDataSetChanged();
            }
        });
        // refresh complete
        materialRefreshLayout.finishRefresh();
        // load more refresh complete
        materialRefreshLayout.finishRefreshLoadMore();

    }

    @Override
    public int getView() {
        return R.layout.activity_big_buy;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {

        if (object instanceof BigBean){
            BigBean bigBean= (BigBean) object;
            if (bigBean.error==0){
                List<BigBean.DataBean> data = bigBean.data;
                adapter1 = new MoneyBigAdapter(mContext,data);
                recy.setAdapter(adapter1);

            }else{
                Toast.makeText(mContext,bigBean.msg,Toast.LENGTH_SHORT).show();
            }
        }
    }
}

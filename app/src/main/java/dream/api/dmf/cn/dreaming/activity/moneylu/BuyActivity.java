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
import dream.api.dmf.cn.dreaming.adapter.BuyListAdapter;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.BuyListBean;

public class BuyActivity extends BaseMvpActivity<presenter> implements Contract.Iview {
    private String mUid;
    private String mShell;
    private boolean username1;
    private RecyclerView mRecy;
    private String c;
    private String type;
    private String status;

     private MaterialRefreshLayout materialRefreshLayout;
    private BuyListAdapter buyListAdapter;
    private TextView mTitle;

    @Override
    public void getThisData() {
        if (username1 == true) {
            type="2";
            c="1";
            status="0";
            HashMap<String,Object> headsmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            map.put("uid",mUid);
            map.put("shell",mShell);
            map.put("c",c);
            map.put("status",status);
            map.put("type",type);
            mPresenter.postData(UserApi.getBUYLIST,headsmap,map,BuyListBean.class);

        } else if (username1 == false) {
            type="1";
            c="1";
            status="0";
            HashMap<String,Object> headsmap=new HashMap<>();
            HashMap<String,Object> map1=new HashMap<>();
            map1.put("uid",mUid);
            map1.put("shell",mShell);
            map1.put("c",c);
            map1.put("status",status);
            map1.put("type",type);
            mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,BuyListBean.class);
        }
    }

    @Override
    public void getInitData() {


        SharedPreferences sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        username1 = sharedPreferences.getBoolean("Username", true);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        mRecy = findViewById(R.id.b_recy);
        mTitle = findViewById(R.id.tv_title);
        mTitle.setText("买入记录");
        mTitle.setTextSize(16);
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecy.setLayoutManager(manager);
        materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        materialRefreshLayout.setIsOverLay(false);
        materialRefreshLayout.setWaveShow(false);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                if (username1 == true) {
                    type="2";
                    c="1";
                    status="0";
                    HashMap<String,Object> headsmap=new HashMap<>();
                    HashMap<String,Object> map=new HashMap<>();
                    map.put("uid",mUid);
                    map.put("shell",mShell);
                    map.put("c",c);
                    map.put("status",status);
                    map.put("type",type);
                    mPresenter.postData(UserApi.getBUYLIST,headsmap,map,BuyListBean.class);
                   // buyListAdapter.notifyDataSetChanged();
                } else if (username1 == false) {
                    type="1";
                    c="1";
                    status="0";
                    HashMap<String,Object> headsmap=new HashMap<>();
                    HashMap<String,Object> map1=new HashMap<>();
                    map1.put("uid",mUid);
                    map1.put("shell",mShell);
                    map1.put("c",c);
                    map1.put("status",status);
                    map1.put("type",type);
                    mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,BuyListBean.class);
                    //buyListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                if (username1 == true) {
                    type="2";
                    c="1";
                    status="0";
                    HashMap<String,Object> headsmap=new HashMap<>();
                    HashMap<String,Object> map=new HashMap<>();
                    map.put("uid",mUid);
                    map.put("shell",mShell);
                    map.put("c",c);
                    map.put("status",status);
                    map.put("type",type);
                    mPresenter.postData(UserApi.getBUYLIST,headsmap,map,BuyListBean.class);

                } else if (username1 == false) {
                    type="1";
                    c="1";
                    status="0";
                    HashMap<String,Object> headsmap=new HashMap<>();
                    HashMap<String,Object> map1=new HashMap<>();
                    map1.put("uid",mUid);
                    map1.put("shell",mShell);
                    map1.put("c",c);
                    map1.put("status",status);
                    map1.put("type",type);
                    mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,BuyListBean.class);
                }
            }
        });
        // refresh complete
        materialRefreshLayout.finishRefresh();
        // load more refresh complete
        materialRefreshLayout.finishRefreshLoadMore();


    }

    @Override
    public int getView() {
        return R.layout.activity_buy;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {
        BuyListBean buyListBean= (BuyListBean) object;
        if (buyListBean.error==0){
            List<BuyListBean.DataBean> data = buyListBean.data;
            BuyListAdapter buyListAdapter=new BuyListAdapter(mContext,data);
            mRecy.setAdapter(buyListAdapter);
            /* List<BuyListBean.DataBean> data = buyListBean.data;*/
          /*  buyListAdapter = new BuyListAdapter(mContext,data);
            mRecy.setAdapter(buyListAdapter);*/
            //Toast.makeText(mContext,"成功",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext,buyListBean.msg,Toast.LENGTH_SHORT).show();
        }
    }
    }


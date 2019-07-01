package dream.api.dmf.cn.dreaming.fragment.bigfragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.HashMap;
import java.util.List;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.adapter.BigBuyAdapter;
import dream.api.dmf.cn.dreaming.adapter.BuyListAdapter;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpFragment;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.BigBean;
import dream.api.dmf.cn.dreaming.bean.BuyListBean;

public class BigBugFragment extends BaseMvpFragment<presenter> implements Contract.Iview {

    private String mUid;
    private String mShell;
    private BigBean bigBean;
    private String  t;
    private String type;
    private String c="2";

    private boolean username1;
    private RecyclerView mRecyo;
    private String status="5";
    private MaterialRefreshLayout materialRefreshLayout;
    private RecyclerView mRecyt;

    public static Fragment newInstance() {
        BigBugFragment fragment = new BigBugFragment();
        return fragment;
    }

    @Override
    protected presenter createPresenter() {
        return new presenter();
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_big_bug;
    }

    @Override
    protected void initView(View view) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        username1 = sharedPreferences.getBoolean("Username", true);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        mRecyo = view.findViewById(R.id.recyo);
        mRecyt = view.findViewById(R.id.recyt);
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyo.setLayoutManager(manager);
        LinearLayoutManager manager1=new LinearLayoutManager(mContext);
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyt.setLayoutManager(manager1);
        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        materialRefreshLayout.setIsOverLay(false);
        materialRefreshLayout.setWaveShow(false);

    }

    @Override
    protected void getData() {
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
                            map.put("t",t);
                            map.put("type",type);
                            map.put("uid",mUid);
                            map.put("shell",mShell);
                            mPresenter.postData(UserApi.getBigShow,headmap,map,BigBean.class);
                            HashMap<String,Object> headsmap=new HashMap<>();
                            HashMap<String,Object> map1=new HashMap<>();
                            map1.put("uid",mUid);
                            map1.put("shell",mShell);
                            map1.put("c",c);
                            map1.put("status",5);
                            map1.put("type",type);
                            mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,BuyListBean.class);
                            //*  BigSellAdapter bigSellAdapter=new BigSellAdapter(mContext,data);
                            //mRecy1.setAdapter(bigSellAdapter);*//*
                        }else if (username1 ==false){
                            type="1";
                            HashMap<String,Object> headmap=new HashMap<>();
                            HashMap<String,Object> map=new HashMap<>();
                            map.put("t",t);
                            map.put("type",type);
                            map.put("uid",mUid);
                            map.put("shell",mShell);
                            mPresenter.postData(UserApi.getBigShow,headmap,map,BigBean.class);
                            HashMap<String,Object> headsmap=new HashMap<>();
                            HashMap<String,Object> map1=new HashMap<>();
                            map1.put("uid",mUid);
                            map1.put("shell",mShell);
                            map1.put("c",c);
                            map1.put("status",5);
                            map1.put("type",type);
                            mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,BuyListBean.class);

                        }
                        materialRefreshLayout.finishRefresh();
                    }

                },3000);


            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {



            }

        });

        materialRefreshLayout.finishRefresh();
        materialRefreshLayout.finishRefreshLoadMore();
        // load more refresh complete
        materialRefreshLayout.finishRefreshLoadMore();
        if (username1 ==true){
            type="2";
            t="0";
            HashMap<String,Object> headmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            map.put("t",t);
            map.put("type",type);
            map.put("uid",mUid);
            map.put("shell",mShell);
            mPresenter.postData(UserApi.getBigShow,headmap,map,BigBean.class);
            HashMap<String,Object> headsmap=new HashMap<>();
            HashMap<String,Object> map1=new HashMap<>();
            map1.put("uid",mUid);
            map1.put("shell",mShell);
            map1.put("c",c);
            map1.put("status",5);
            map1.put("type",type);
            mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,BuyListBean.class);
            //*  BigSellAdapter bigSellAdapter=new BigSellAdapter(mContext,data);
            //mRecy1.setAdapter(bigSellAdapter);*//*
        }else if (username1 ==false){
            type="1";
            t="0";
            HashMap<String,Object> headmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            map.put("t",t);
            map.put("type",type);
            map.put("uid",mUid);
            map.put("shell",mShell);
            mPresenter.postData(UserApi.getBigShow,headmap,map,BigBean.class);
            HashMap<String,Object> headsmap=new HashMap<>();
            HashMap<String,Object> map1=new HashMap<>();
            map1.put("uid",mUid);
            map1.put("shell",mShell);
            map1.put("c",c);
            map1.put("status",5);
            map1.put("type",type);
            mPresenter.postData(UserApi.getBUYLIST,headsmap,map1,BuyListBean.class);

        }
    }

    @Override
    public void getData(Object object) {
        if (object instanceof BigBean) {
            BigBean bigBean = (BigBean) object;
            if (bigBean.error == 0) {
                //Toast.makeText(mContext,"成功",Toast.LENGTH_SHORT).show();
                List<BigBean.DataBean> data = bigBean.data;
                BigBuyAdapter Adapter = new BigBuyAdapter(mContext, data);
               // mRecy.setAdapter(bigSellAdapter);
                mRecyo.setAdapter(Adapter);
            } else {
                Toast.makeText(mContext, bigBean.msg, Toast.LENGTH_SHORT).show();
            }
        }
        if (object instanceof BuyListBean){
            BuyListBean buyListBean= (BuyListBean) object;
            if (buyListBean.error==0){
                List<BuyListBean.DataBean> data = buyListBean.data;
                BuyListAdapter buyListAdapter=new BuyListAdapter(mContext,data);
                mRecyt.setAdapter(buyListAdapter);
                //Toast.makeText(mContext,"成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(mContext,buyListBean.msg,Toast.LENGTH_SHORT).show();
            }
        }
    }

  /*  @Override
    protected presenter createPresenter() {
        return new presenter();
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_big_bug;
    }*/

   /* @Override
    protected void initView(View view) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        username1 = sharedPreferences.getBoolean("Username", true);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        mRecy1 = view.findViewById(R.id.recyo);
        mRecyt = view.findViewById(R.id.recyt);
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecy1.setLayoutManager(manager);
    }*/

//    @Override
//    protected void getData() {
//        if (username1 ==true){
//            type="2";
//            HashMap<String,Object> headmap=new HashMap<>();
//            HashMap<String,Object> map=new HashMap<>();
//            map.put("t",t);
//            map.put("type",type);
//            map.put("uid",mUid);
//            map.put("shell",mShell);
//           // mPresenter.postData(UserApi.getBigShow,headmap,map,BigBean.class);
//            //*  BigSellAdapter bigSellAdapter=new BigSellAdapter(mContext,data);
//            //mRecy1.setAdapter(bigSellAdapter);*//*
//        }else if (username1 ==false){
//            type="1";
//            HashMap<String,Object> headmap=new HashMap<>();
//            HashMap<String,Object> map=new HashMap<>();
//            map.put("t",t);
//            map.put("type",type);
//            map.put("uid",mUid);
//            map.put("shell",mShell);
//           // mPresenter.postData(UserApi.getBigShow,headmap,map,BigBean.class);
//
//        }
    }

  /*  @Override
    public void getData(Object object) {
        if (object instanceof BigBean) {
            BigBean bigBean = (BigBean) object;
            data = bigBean.data;
            if (bigBean.error == 0) {
                //Toast.makeText(mContext,"成功",Toast.LENGTH_SHORT).show();
                BigSellAdapter bigSellAdapter = new BigSellAdapter(mContext, this.data);
                mRecy1.setAdapter(bigSellAdapter);
                mRecyt.setAdapter(bigSellAdapter);
            } else {
                Toast.makeText(mContext, bigBean.msg, Toast.LENGTH_SHORT).show();
            }
        }
    }*/


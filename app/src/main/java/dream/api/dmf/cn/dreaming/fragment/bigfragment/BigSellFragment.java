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
import dream.api.dmf.cn.dreaming.adapter.BigSellAdapter;
import dream.api.dmf.cn.dreaming.adapter.FinishAdapter;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpFragment;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.BigBean;
import dream.api.dmf.cn.dreaming.bean.SellBean;

public class BigSellFragment extends BaseMvpFragment<presenter> implements Contract.Iview {

    private RecyclerView mRecy;
    private RecyclerView mRecyt;
    private String mUid;
    private String mShell;
    private BigBean bigBean;
    private String  t;
    private String type;
    private String cc="2";
    private String status="4";
    private String types;
    private List<BigBean.DataBean> data;
    private RecyclerView mRecy1;
    private boolean username1;
    private String c;
    private MaterialRefreshLayout materialRefreshLayout;

    public static Fragment newInstance() {
        BigSellFragment fragment = new BigSellFragment();
        return fragment;
    }

    @Override
    protected presenter createPresenter() {
        return new presenter();
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_big_sell;
    }

    @Override
    protected void initView(View view) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        username1 = sharedPreferences.getBoolean("Username", true);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        mRecy1 = view.findViewById(R.id.recyo);
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecy1.setLayoutManager(manager);
        mRecyt = view.findViewById(R.id.recyt);
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
                            t="2";
                            HashMap<String,Object> headmap=new HashMap<>();
                            HashMap<String,Object> map=new HashMap<>();
                            map.put("uid",mUid);
                            map.put("shell",mShell);
                            map.put("type",type);
                            map.put("t",t);
                            mPresenter.postData(UserApi.getBigShow,headmap,map,BigBean.class);
                            type="2";
                            c="2";
                            status="4";
                            HashMap<String,Object> headsmap=new HashMap<>();
                            HashMap<String,Object> maps=new HashMap<>();
                            maps.put("c",c);
                            maps.put("type",type);
                            maps.put("uid",mUid);
                            maps.put("shell",mShell);
                            maps.put("status",status);
                            mPresenter.postData(UserApi.getSelllist,headsmap,maps,BigBean.class);

                        }else if (username1 ==false){
                            type="1";
                            t="2";
                            HashMap<String,Object> headmap=new HashMap<>();
                            HashMap<String,Object> map=new HashMap<>();
                            map.put("uid",mUid);
                            map.put("shell",mShell);
                            map.put("t",t);
                            map.put("type",type);
                            mPresenter.postData(UserApi.getBigShow,headmap,map,BigBean.class);
                            type="1";
                            c="2";
                            status="4";
                            HashMap<String,Object> headhmap=new HashMap<>();
                            HashMap<String,Object> maph=new HashMap<>();
                            maph.put("c",c);
                            maph.put("type",type);
                            maph.put("uid",mUid);
                            maph.put("shell",mShell);
                            maph.put("status",status);
                            mPresenter.postData(UserApi.getSelllist,headhmap,maph,BigBean.class);
                        }
                        materialRefreshLayout.finishRefresh();
                    }

                },3000);


            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
              /*  new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (username1 == true) {
                            type = "2";
                            t = "2";
                            HashMap<String, Object> headmap = new HashMap<>();
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("uid", mUid);
                            map.put("shell", mShell);
                            map.put("type", type);
                            map.put("t", t);
                            mPresenter.postData(UserApi.getBigShow, headmap, map, BigBean.class);
                            type = "2";
                            c = "2";
                            status = "4";
                            HashMap<String, Object> headsmap = new HashMap<>();
                            HashMap<String, Object> maps = new HashMap<>();
                            maps.put("c", c);
                            maps.put("type", type);
                            maps.put("uid", mUid);
                            maps.put("shell", mShell);
                            maps.put("status", status);
                            mPresenter.postData(UserApi.getSelllist, headsmap, maps, BigBean.class);

                        } else if (username1 == false) {
                            type = "1";
                            t = "2";
                            HashMap<String, Object> headmap = new HashMap<>();
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("uid", mUid);
                            map.put("shell", mShell);
                            map.put("t", t);
                            map.put("type", type);
                            mPresenter.postData(UserApi.getBigShow, headmap, map, BigBean.class);
                            type = "1";
                            c = "2";
                            status = "4";
                            HashMap<String, Object> headhmap = new HashMap<>();
                            HashMap<String, Object> maph = new HashMap<>();
                            maph.put("c", c);
                            maph.put("type", type);
                            maph.put("uid", mUid);
                            maph.put("shell", mShell);
                            maph.put("status", status);
                            mPresenter.postData(UserApi.getSelllist, headhmap, maph, BigBean.class);
                        }
                    }
                }, 3000);
                materialRefreshLayout.finishRefreshLoadMore();
*/

            }

            });
        // refresh complete

        materialRefreshLayout.finishRefresh();
        materialRefreshLayout.finishRefreshLoadMore();
        // load more refresh complete
        materialRefreshLayout.finishRefreshLoadMore();
        if (username1 ==true){
            type="2";
            t="2";
            HashMap<String,Object> headmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            map.put("uid",mUid);
            map.put("shell",mShell);
            map.put("type",type);
            map.put("t",t);
            mPresenter.postData(UserApi.getBigShow,headmap,map,BigBean.class);
            type="2";
            c="2";
            status="4";
            HashMap<String,Object> headsmap=new HashMap<>();
            HashMap<String,Object> maps=new HashMap<>();
            maps.put("c",c);
            maps.put("type",type);
            maps.put("uid",mUid);
            maps.put("shell",mShell);
            maps.put("status",status);
            mPresenter.postData(UserApi.getSelllist,headsmap,maps,BigBean.class);

        }else if (username1 ==false){
            type="1";
            t="2";
            HashMap<String,Object> headmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            map.put("uid",mUid);
            map.put("shell",mShell);
            map.put("t",t);
            map.put("type",type);
            mPresenter.postData(UserApi.getBigShow,headmap,map,BigBean.class);
            type="1";
            c="2";
            status="4";
            HashMap<String,Object> headhmap=new HashMap<>();
            HashMap<String,Object> maph=new HashMap<>();
            maph.put("c",c);
            maph.put("type",type);
            maph.put("uid",mUid);
            maph.put("shell",mShell);
            maph.put("status",status);
            mPresenter.postData(UserApi.getSelllist,headhmap,maph,BigBean.class);
        }


    }

    @Override
    public void getData(Object object) {
        if (object instanceof BigBean) {
            BigBean bigBean = (BigBean) object;
            if (bigBean.error == 0) {
                //Toast.makeText(mContext,"成功",Toast.LENGTH_SHORT).show();
                data = bigBean.data;
                BigSellAdapter bigSellAdapter = new BigSellAdapter(mContext, this.data);
                mRecy1.setAdapter(bigSellAdapter);
                //mRecyt.setAdapter(bigSellAdapter);
            } else {
                Toast.makeText(mContext, bigBean.msg, Toast.LENGTH_SHORT).show();
            }
        }
        if (object instanceof SellBean){
            SellBean sellBean= (SellBean) object;
            if (sellBean.error==0){
                List<SellBean.DataBean> datase = sellBean.data;
                FinishAdapter adapter = new FinishAdapter(mContext, datase);
                mRecyt.setAdapter(adapter);
                //Toast.makeText(mContext,"成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(mContext,sellBean.msg,Toast.LENGTH_SHORT).show();
            }
        }
    }
/*
    @Override
    protected presenter createPresenter() {
        return new presenter();
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_big_sell;
    }

    @Override
    protected void initView(View view) {
        mRecy1 = view.findViewById(R.id.recyo);
        mRecyt = view.findViewById(R.id.recyt);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        username1 = sharedPreferences.getBoolean("Username", true);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");

        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecy1.setLayoutManager(manager);

        //mRecyt.setLayoutManager(manager);
        getData();
    }

    @Override
    protected void getData() {
        if (username1 ==true){
            type="2";
            HashMap<String,Object> headmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            map.put("t",t);
            map.put("type",type);
            map.put("uid",mUid);
            map.put("shell",mShell);
            //mPresenter.postData(UserApi.getBigShow,headmap,map,BigBean.class);
          *//*  BigSellAdapter bigSellAdapter=new BigSellAdapter(mContext,data);
            mRecy1.setAdapter(bigSellAdapter);*//*
        }else if (username1 ==false){
            type="1";
            HashMap<String,Object> headmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            map.put("t",t);
            map.put("type",type);
            map.put("uid",mUid);

            map.put("shell",mShell);

            //mPresenter.postData(UserApi.getBigShow,headmap,map,BigBean.class);

        }

       // mPresenter.postData(UserApi.getBigShow,headmap,map,BigBean.class);

    }

    @Override
    public void getData(Object object) {
        //LogUtils.v(object+"");
        if (object instanceof BigBean){
           BigBean bigBean = (BigBean) object;
            if (bigBean.error==0){
                //Toast.makeText(mContext,"成功",Toast.LENGTH_SHORT).show();
                BigSellAdapter bigSellAdapter=new BigSellAdapter(mContext,data);
                mRecy1.setAdapter(bigSellAdapter);
            }else{
                Toast.makeText(mContext,bigBean.msg,Toast.LENGTH_SHORT).show();
            }
            //


        }
    }*/
}

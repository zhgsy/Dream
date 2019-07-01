package dream.api.dmf.cn.dreaming.activity.moneylu;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.adapter.SellAdapter;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.SellBean;

public class MoneyLuActivity extends BaseMvpActivity<presenter> implements Contract.Iview {
    //交易记录展示
    private boolean username1;
    private RecyclerView mRecy;
    private String mUid;
    private String mShell;
    private String type;
    private String c;
    private String status;
    private TextView mTitle;

    @Override
    public void getThisData() {

        if (username1 == true) {
            type="2";
            c="1";
            status="0";
            HashMap<String,Object> headsmap=new HashMap<>();
            HashMap<String,Object> map2=new HashMap<>();
            map2.put("uid",mUid);
            map2.put("shell",mShell);
            map2.put("c",c);
            map2.put("status",status);
            map2.put("type",type);
            mPresenter.postData(UserApi.getSelllist,headsmap,map2,SellBean.class);
        } else if (username1 == false) {
            type="1";
            c="1";
            status="0";
            HashMap<String,Object> headsmap=new HashMap<>();
            HashMap<String,Object> map2=new HashMap<>();
            map2.put("uid",mUid);
            map2.put("shell",mShell);
            map2.put("c",c);
            map2.put("status",status);
            map2.put("type",type);
            mPresenter.postData(UserApi.getSelllist,headsmap,map2,SellBean.class);
        }
    }

    @Override
    public void getInitData() {
        SharedPreferences sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        username1 = sharedPreferences.getBoolean("Username", true);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        mRecy = findViewById(R.id.recy);
        mTitle = findViewById(R.id.tv_title);
        mTitle.setText("卖出记录");
        mTitle.setTextSize(16);
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecy.setLayoutManager(manager);
    }

    @Override
    public int getView() {
        return R.layout.activity_money_lu;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {
        SellBean sellBean= (SellBean) object;
       if (object instanceof SellBean){
           if (sellBean.error==0){
               List<SellBean.DataBean> datase = sellBean.data;

                SellAdapter adapter = new SellAdapter(mContext, datase);
               mRecy.setAdapter(adapter);
               //Toast.makeText(mContext,"成功",Toast.LENGTH_SHORT).show();
           }else{
               Toast.makeText(mContext,sellBean.msg,Toast.LENGTH_SHORT).show();
           }
       }
    }
}

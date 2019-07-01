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
import dream.api.dmf.cn.dreaming.adapter.MoneyBigAdapter;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.BigBean;

public class BigSellActivity extends BaseMvpActivity<presenter> implements Contract.Iview {

    private String mUid;
    private String mShell;
    private boolean username1;
    private RecyclerView recy;
    private String type;
    private String c;
    private TextView mTitle;
    private String status;

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
            map.put("status",status);
            map.put("shell",mShell);
            mPresenter.postData(UserApi.getBUYLIST,headmap,map,BigBean.class);
        } else if (username1 == false) {
            type="1";
            c="2";
            status="0";
            HashMap<String,Object> headmap=new HashMap<>();
            HashMap<String,Object> map=new HashMap<>();
            map.put("c",c);
            map.put("type",type);
            map.put("uid",mUid);
            map.put("shell",mShell);
            map.put("status",status);
            mPresenter.postData(UserApi.getBUYLIST,headmap,map,BigBean.class);

        }
    }

    @Override
    public void getInitData() {
        SharedPreferences sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        username1 = sharedPreferences.getBoolean("Username", true);
        recy = findViewById(R.id.recy);
        mTitle = findViewById(R.id.tv_title);
        mTitle.setText("买入记录");
        mTitle.setTextSize(16);
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recy.setLayoutManager(manager);

    }

    @Override
    public int getView() {
        return R.layout.activity_big_sell;
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
                MoneyBigAdapter adapter= new MoneyBigAdapter(mContext,data);
                recy.setAdapter(adapter);

            }else{
                Toast.makeText(mContext,bigBean.msg,Toast.LENGTH_SHORT).show();
            }
        }
    }
}

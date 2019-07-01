package dream.api.dmf.cn.dreaming.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.adapter.TuiAdapter;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.TuiBean;

public class RewardDanActivity extends BaseMvpActivity<presenter> implements Contract.Iview {


    private RecyclerView mRecy;
    private TextView mTitle;
    private ImageView mBack;
    private SharedPreferences sharedPreferences;
    private String mPhone;
    private String mUid;
    private String mShell;
    @Override
    public void getThisData() {
        HashMap<String,Object> headmap=new HashMap<>();
        HashMap<String,Object> map=new HashMap<>();
        map.put("phone",mPhone);
        map.put("uid",mUid);
        map.put("shell",mShell);
        mPresenter.postData(UserApi.getTUI,headmap,map,TuiBean.class);


    }

    @Override
    public void getInitData() {
        sharedPreferences = mContext.getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        mPhone = sharedPreferences.getString(UserApi.UserName, "");
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        mTitle = findViewById(R.id.tv_title);
        mBack = findViewById(R.id.iv_back);
        mTitle.setText("推荐清单");
        mTitle.setTextSize(18);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecy = findViewById(R.id.mrecy);
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecy.setLayoutManager(manager);

    }

    @Override
    public int getView() {
        return  R.layout.activity_reward_dan;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {
        if (object instanceof TuiBean){
            TuiBean tuiBean= (TuiBean) object;
            if (tuiBean.status==200){
                //Toast.makeText(mContext,tuiBean.message,Toast.LENGTH_SHORT).show();
                List<TuiBean.DataBean> data = tuiBean.data;
                TuiAdapter tuiAdapter=new TuiAdapter(data,mContext);
                mRecy.setAdapter(tuiAdapter);
            }else{
                Toast.makeText(mContext,tuiBean.message,Toast.LENGTH_SHORT).show();
            }
        }
    }
}

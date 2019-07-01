package dream.api.dmf.cn.dreaming.activity.moneyactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;

public class MoneyMLActivity extends BaseMvpActivity<presenter> implements Contract.Iview {

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.iv_back)
    ImageView mBack;

    private TextView mOne,mTwo,mThree,mFour;

    @Override
    public void getThisData() {
        HashMap<String,Object> headmap=new HashMap<>();
        HashMap<String,Object> map=new HashMap<>();
    }

    @Override
    public void getInitData() {
        mOne = findViewById(R.id.mone);
        mTwo = findViewById(R.id.mtwo);
        mThree = findViewById(R.id.mthree);
        mFour = findViewById(R.id.mfour);
        SharedPreferences sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        String mUid = sharedPreferences.getString(UserApi.Uid, "");
        String mShell = sharedPreferences.getString(UserApi.Shell, "");
    }

    @Override
    public int getView() {
        return R.layout.activity_money_ml;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {

    }
}

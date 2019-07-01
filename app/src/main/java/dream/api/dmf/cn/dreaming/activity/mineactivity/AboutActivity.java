package dream.api.dmf.cn.dreaming.activity.mineactivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;

public class AboutActivity extends BaseMvpActivity<presenter> implements Contract.Iview {

    private TextView mTittle;
    private ImageView mBack;
    private LinearLayout mLin;

    @Override
    public void getThisData() {

    }

    @Override
    public void getInitData() {
        mTittle = findViewById(R.id.tv_title);
        mBack = findViewById(R.id.iv_back);
        mLin = findViewById(R.id.ll_team);
        mTittle.setText("关于平台");
        mTittle.setTextSize(16);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"暂无更新",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getView() {
        return R.layout.activity_about;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {

    }
}

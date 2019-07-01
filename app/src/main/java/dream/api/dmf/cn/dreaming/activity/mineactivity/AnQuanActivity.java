package dream.api.dmf.cn.dreaming.activity.mineactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;

public class AnQuanActivity extends BaseMvpActivity<presenter> implements Contract.Iview {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_gesture)
    TextView tvGesture;
    @BindView(R.id.tv_pswd)
    TextView tvPswd;
    @BindView(R.id.tv_safe)
    TextView tvSafe;
    private TextView mTitle;
    private ImageView mBack;

    @Override
    public void getThisData() {

    }

    @Override
    public void getInitData() {
        mTitle = findViewById(R.id.tv_title);
        mTitle.setText("安全中心");
        mTitle.setTextSize(16);

        mBack = findViewById(R.id.iv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public int getView() {
        return R.layout.activity_an_quan;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_gesture, R.id.tv_pswd, R.id.tv_safe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
                //手势解锁
            case R.id.tv_gesture:
                    startActivity(new Intent(mContext,GestureActivity.class));
                break;
            case R.id.tv_pswd:
                startActivity(new Intent(mContext,ChangePswdActivity.class));
                break;
            case R.id.tv_safe:
                startActivity(new Intent(mContext,ChangeSafePswdActivity.class));
                break;
        }
    }
}

package dream.api.dmf.cn.dreaming.activity.moneyactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dream.api.dmf.cn.dreaming.R;

public class MoneyZActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.iv_back)
    ImageView mBack;
    @BindView(R.id.moeny_mright)
    RelativeLayout moenyMright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_z);
        ButterKnife.bind(this);
        mTitle.setText("钱包转账");
        mTitle.setTextSize(16);

    }

    @OnClick({R.id.iv_back, R.id.moeny_mright})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.moeny_mright:
                startActivity(new Intent(this,MoneyHYTBActivity.class));
                break;
        }
    }
}

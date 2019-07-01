package dream.api.dmf.cn.dreaming.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import dream.api.dmf.cn.dreaming.R;

public class YinActivity extends AppCompatActivity {

    private TextView textView;
    private int i=3;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(i<0){
                i--;
                textView.setText(i+"S");
                handler.sendEmptyMessageDelayed(0,1000);
            }else{
                Intent intent = new Intent(YinActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    //引导页
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yin);
        handler.sendEmptyMessageDelayed(0,1000);
    }
}

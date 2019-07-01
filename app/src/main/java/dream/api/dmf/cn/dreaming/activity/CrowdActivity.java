package dream.api.dmf.cn.dreaming.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import dream.api.dmf.cn.dreaming.R;

public class CrowdActivity extends AppCompatActivity {

    private Button butn;
    private Button mFalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crowd);
        butn = findViewById(R.id.c_butn);
        mFalse = findViewById(R.id.c_false);
        mFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(CrowdActivity.this,LoginActivity.class));
            }
        });
        butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(CrowdActivity.this,MainActivity.class));
            }
        });
    }
}

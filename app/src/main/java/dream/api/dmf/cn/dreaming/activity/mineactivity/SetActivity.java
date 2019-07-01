package dream.api.dmf.cn.dreaming.activity.mineactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.activity.LoginActivity;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.app.MyApp;

public class SetActivity extends AppCompatActivity {

    private TextView mtitle;
    private ImageView mBack;
    private TextView mBa;
    private SharedPreferences sharedPreferences;
    private TextView mMode;
    private TextView mwallet;
    private TextView account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        mtitle = findViewById(R.id.tv_title);
        account = findViewById(R.id.tv_account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetActivity.this,AnQuanActivity.class));
            }
        });
        mtitle.setText("设置");
        mtitle.setTextSize(18);
        sharedPreferences = getSharedPreferences(UserApi.SP, MODE_PRIVATE);
        mBack = findViewById(R.id.iv_back);
        mMode = findViewById(R.id.tv_mode);
        mwallet = findViewById(R.id.tv_clear);
        mwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().clear().commit();
                Toast.makeText(SetActivity.this, "清除成功", Toast.LENGTH_LONG).show();

            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetActivity.this,ShouKActivity.class));
                finish();
            }
        });
        mBa = findViewById(R.id.tv_exit);
        mBa.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          AlertDialog.Builder builder=new AlertDialog.Builder(SetActivity.this);
                                          builder.setTitle("设置");
                                          builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                              public void onClick(DialogInterface dialog, int which) {
                                                    startActivity(new Intent(SetActivity.this,LoginActivity.class));
                                                    finish();
                                              }
                                          });
                                          builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                                              public void onClick(DialogInterface dialog, int which) {
                                                  //Toast.makeText(SetActivity.this, "我是消极", Toast.LENGTH_LONG).show();

                                              }
                                          });


                                          builder.show();

                                          sharedPreferences.edit().clear().commit();
                                          MyApp.getInstance().exit();
                                      }
                                  }
        );
    }
}

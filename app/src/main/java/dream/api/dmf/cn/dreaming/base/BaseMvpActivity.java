package dream.api.dmf.cn.dreaming.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseMvpActivity <P extends BasePresenter> extends AppCompatActivity
{
    public P mPresenter;
    public Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        mContext=this;
        getInitData();
        if(mPresenter ==null)
        {
            mPresenter =createP();

        }

        mPresenter.attachV(this);
        stateNetWork();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    //透明状态栏
                    //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    //透明导航栏
                    //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                }


            }






    private void stateNetWork()
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            String[] mStatenetwork =new String[]
                    {
                            "android.permission.WRITE_EXTERNAL_STORAGE",
                            "android.permission.READ_EXTERNAL_STORAGE",
                            "android.permission.ACCESS_NETWORK_STATE",
                            "android.permission.ACCESS_WIFI_STATE",
                            "android.permission.READ_PHONE_STATE",
                            "android.permission.INTERNET",
                            "android.permission.CAMERA",
                            "android.permission.WRITE_APN_SETTINGS",
                            "android.permission.ACCESS_NETWORK_STATE",
                            "android.permission.ACCESS_COARSE_LOCATION",
                            "android.permission.ACCESS_FINE_LOCATION",
                            "android.permission.RECORD_AUDIO"
                    };
            ActivityCompat.requestPermissions((Activity)this, mStatenetwork, 100);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getThisData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachV();
    }


    public abstract void getThisData();

    public abstract void getInitData();
    public abstract int getView();

    protected abstract P createP();
}

package dream.api.dmf.cn.dreaming.activity.mineactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.api.UserApi;

public class MJListActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private String username;
    private WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mjlist);
        sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(UserApi.UserName, "");
        mWeb = findViewById(R.id.m_web);
        getData();
    }
    public void getData(){
        WebSettings webSettings = mWeb.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

        //设置自适应屏幕，两者合用
        mWeb.setWebViewClient(new WebViewClient());
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setSupportMultipleWindows(false);
        //缩放操作
        //        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        //        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        //        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        // Date curDate = new Date(System.currentTimeMillis());
        // mTime = formatter.format(curDate);
        /* long timeStamp = System.currentTimeMillis();*/
       /* Log.i("1111111",username+ "a85GhBeA73J3"+timeStamp);
        String tex="a85GhBeA73J3";
        token = MD5Utils.MD5(username+tex+timeStamp);
        Log.i("1111111", token);
        *//*Uri uri=Uri.parse()*/

        mWeb.loadUrl(" https://shop.xg360.cc/addons/ewei_shopv2/template/mobile/default/notice/record.html?phone="+ username);
    }
}

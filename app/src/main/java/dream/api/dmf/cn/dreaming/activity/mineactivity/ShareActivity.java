package dream.api.dmf.cn.dreaming.activity.mineactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.api.UserApi;

public class ShareActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.iv_back)
    ImageView mBack;
    private WebView mWeb;
    private SharedPreferences sharedPreferences;
    private String username;
    private String mUid;
    private String mShell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        username = sharedPreferences.getString(UserApi.UserName, "");
        mTitle.setText("客服中心");
        mTitle.setTextSize(18);
        mWeb = findViewById(R.id.mweb);
        getDate();
    }


    public void getDate(){
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
        mWeb.loadUrl("http://api.xg360.cc/index.php?mod=mobile&act=contact");
    }
    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}

package dream.api.dmf.cn.dreaming.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.LinkedList;
import java.util.List;

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

    private static Context context;
    public static Context getApplication() {
        return context;

    }
    private static MyApp instance;
    //单例模式中获取唯一的MyApplication实例
    public static MyApp getInstance() {
        if(null == instance) {
            instance = new MyApp();
        }
        return instance;
    }

    private List<Activity> activityList = new LinkedList<Activity>();
    //添加Activity到容器中
    public void addActivity(Activity activity)  {
        activityList.add(activity);
    }
    //遍历所有Activity并finish
    public void exit() {
        for(Activity activity:activityList) {
            activity.finish();
        }
        activityList.clear();
    }
}

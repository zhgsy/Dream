package dream.api.dmf.cn.dreaming.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetWorkUtil {

    public final static int NONE = 0;
    public final static int WIFI = 1;
    public final static int MOBILE = 2;

    //平板上面报空，废弃
//    public static int getNetWorkState(Context context) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        State state = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
//        if (state == State.CONNECTED || state == State.CONNECTING) {
//            return MOBILE;
//        }
//        state = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
//        if (state == State.CONNECTED || state == State.CONNECTING) {
//            return WIFI;
//        }
//        return NONE;
//    }

    /**
     * 获取当前网络状态(是否可用)
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager != null) {
            /**
             * 获取网络信息实体
             * 由于从系统服务中获取数据属于进程间通信，基本类型外的数据必须实现Parcelable接口，
             * NetworkInfo实现了Parcelable，获取到的activeNetInfo相当于服务中网络信息实体对象的一个副本（拷贝），
             * 所以，不管系统网络服务中的实体对象是否置为了null，此处获得的activeNetInfo均不会发生变化
             */
            NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
            if (activeNetInfo != null) {
                return activeNetInfo.isAvailable();
            }
        }
        return false;
    }

//    /**
//     * 获取当前网络状态(是否可用)
//     */
//    public static String getBaseUrl(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
//        int systemType = sharedPreferences.getInt(Constant.SystemType, 0);
//        String baseUrl;
//        if (0 == systemType) {
//            baseUrl = Constant.BaseYuMi;
//        } else {
//            baseUrl = Constant.BaseXiaoMai;
//        }
//        return baseUrl;
//    }

}

package dream.api.dmf.cn.dreaming.utils;


import java.util.concurrent.TimeUnit;

import dream.api.dmf.cn.dreaming.api.Api;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils
{
    private static RetrofitUtils instance;
    private final Retrofit retrofit;
    //单例模式
    public static RetrofitUtils getInstance(){
        if(instance==null){
            synchronized (RetrofitUtils.class){
                if(instance ==null){
                    instance = new RetrofitUtils();
                }
            }
        }
        return  instance;
    }

    private RetrofitUtils() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getHttpClient())
                .build();

    }
    private OkHttpClient getHttpClient(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
               /* .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        //拿到请求
                        Request original = chain.request();
                        //取出sp中存入的userid, sessionid
                        SharedPreferences sharedPreferences = MyApp.getApplication().getSharedPreferences("hh", Context.MODE_PRIVATE);
                        String userId = sharedPreferences.getString("userId", "");
                        String sessionId = sharedPreferences.getString("sessionId", "");
                        //重新构造请求
                        Request.Builder requestBuilder = original.newBuilder();
                        //放入请求的参数
                        requestBuilder.method(original.method(), original.body());
                        //添加userId, sessionId;
                        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(sessionId))
                        {
                            requestBuilder.addHeader("userId", userId);
                            requestBuilder.addHeader("sessionId", sessionId);

                        }
                        //打包
                        Request request = requestBuilder.build();
                        //返回response响应
                        return chain.proceed(request);
                        }
                })*/
                .sslSocketFactory(TrustAllCerts.createSSLSocketFactory())
                .hostnameVerifier(new TrustAllCerts.TrustAllHostnameVerifier())
                .readTimeout(3000,TimeUnit.MILLISECONDS)
                .writeTimeout(3000,TimeUnit.MILLISECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }
    //创建
    public <T>T create(Class<T> clazz){
        return retrofit.create(clazz);
    }
}

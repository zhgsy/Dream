package dream.api.dmf.cn.dreaming.api;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService
{
  @GET
  Observable<ResponseBody> getData(@Url String url,@HeaderMap Map<String, Object> headMap,  @QueryMap Map<String, Object> map);

  @POST
  @FormUrlEncoded
  Observable<ResponseBody> postData(@Url String url, @HeaderMap Map<String, Object> headMap, @FieldMap Map<String, Object> map);

  @PUT
  Observable<ResponseBody> putData(@Url String url,@HeaderMap Map<String, Object> headMap,  @QueryMap Map<String, Object> map);

  @DELETE
  Observable<ResponseBody> deleteData(@Url String url,@HeaderMap Map<String, Object> headMap,  @QueryMap Map<String, Object> map);

  @POST
  @Multipart
  Observable<ResponseBody> postHeadData(@Url String url,@HeaderMap Map<String, Object> headMap, @Part MultipartBody.Part part);

  @POST
  @Multipart
  Observable<ResponseBody> postMorePicture(@Url String url,@HeaderMap Map<String, Object> headMap,  @QueryMap Map<String, Object> bodyMap, @Part MultipartBody.Part img);


}

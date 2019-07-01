package dream.api.dmf.cn.dreaming.base.mvp;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface Contract
{
    //model层
    interface Imodel
    {
        void getData(String url, Map<String, Object> headMap, Map<String, Object> map, Class clazz, GetDataCallBack getDataCallBack);
        void postData(String url, Map<String, Object> headMap, Map<String, Object> map, Class clazz, GetDataCallBack getDataCallBack);
        void putData(String url, Map<String, Object> headMap, Map<String, Object> map, Class clazz, GetDataCallBack getDataCallBack);
        void delData(String url, Map<String, Object> headMap, Map<String, Object> map, Class clazz, GetDataCallBack getDataCallBack);
        //上传头像
        void postHeadData(String url, Map<String, Object> headMap, File part, Class clazz, GetDataCallBack getDataCallBack);
        //多图上传
        void postMorePicture(String url, Map<String, Object> headMap, Map<String, Object> map, List<File> img, Class clazz, GetDataCallBack getDataCallBack);
    }
    //presenter
    interface Ipresenter{
        void getData(String url, Map<String, Object> headMap, Map<String, Object> map, Class clazz);
         void postData(String url, Map<String, Object> headMap, Map<String, Object> map, Class clazz);
   /*   void postData(String url, Map<String, Object> map, Class clazz);*/
        void putData(String url, Map<String, Object> headMap, Map<String, Object> map, Class clazz);
        void delData(String url, Map<String, Object> headMap, Map<String, Object> map, Class clazz);

        void postHeadData(String url, Map<String, Object> headMap, File part, Class clazz);
        void postMorePicture(String url, Map<String, Object> headMap, Map<String, Object> bodyMap, List<File> img, Class clazz);

    }
    //View层
    interface Iview {
        void getData(Object object);
    }
    interface GetDataCallBack{
        void getDataTrue(Object data);
        void getDataFalse(Throwable e);
    }
}

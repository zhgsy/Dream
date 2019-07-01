package dream.api.dmf.cn.dreaming.base.mvp.presenter;


import java.io.File;
import java.util.List;
import java.util.Map;

import dream.api.dmf.cn.dreaming.base.BasePresenter;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.model.model;

public  class presenter extends BasePresenter<Contract.Iview, model> implements Contract.Ipresenter, Contract.GetDataCallBack
{
    @Override
    public void getData(String url, Map<String, Object> headMap, Map<String, Object> map, Class clazz)
    {
        mModel.getData(url,headMap,map,clazz,this);
    }

    @Override
    public void postData(String url,Map<String, Object> headMap,  Map<String, Object> map, Class clazz)
    {
        mModel.postData(url,headMap,map,clazz,this);
    }

    @Override
    public void putData(String url, Map<String, Object> headMap, Map<String, Object> map, Class clazz)
    {
        mModel.putData(url,headMap,map,clazz,this);

    }

    @Override
    public void delData(String url,Map<String, Object> headMap,  Map<String, Object> map, Class clazz) {

        mModel.delData(url,headMap,map,clazz,this);
    }

    @Override
    public void postHeadData(String url, Map<String, Object> headMap, File part, Class clazz) {
        mModel.postHeadData(url,headMap,part,clazz,this);
    }

    @Override
    public void postMorePicture(String url,Map<String, Object> headMap,  Map<String, Object> bodyMap, List<File> img, Class clazz) {

        mModel.postMorePicture(url,headMap,bodyMap,img,clazz,this);
    }

    @Override
    public void getDataTrue(Object data)
    {
        mView.getData(data);
    }

    @Override
    public void getDataFalse(Throwable e)
    {
        mView.getData(e);

    }

    @Override
    public model createM() {
        return new model();
    }
}

package dream.api.dmf.cn.dreaming.base;

//泛型 V :View层 M :Model层
public abstract class BasePresenter<V,M>
{

    public V  mView;
    public M  mModel;
    //绑定v层
    public void attachV(V mIView ){
        this.mView = mIView;
        //如果mModel为空的时候创建M层
        if(mModel == null){
            mModel = createM();
        }
    }

    //创建M层
    public abstract M createM();
    //解绑 M层与V层
    protected void  detachV()
    {
        mView = null;
        mModel = null;
    }




}

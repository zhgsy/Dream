package dream.api.dmf.cn.dreaming.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseMvpFragment<P extends BasePresenter> extends Fragment
{
    protected P mPresenter;
    public FragmentActivity mContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentView(),container,false);
        mContext=getActivity();
        initView(view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachV();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mPresenter ==null){
            mPresenter = createPresenter();
        }
        mPresenter.attachV(this);
        getData();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    protected abstract P createPresenter();

    protected abstract int getFragmentView();

    protected abstract void initView(View view);

    protected abstract void getData();


}

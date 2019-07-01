package dream.api.dmf.cn.dreaming.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.adapter.ToolOftenAdapter;
import dream.api.dmf.cn.dreaming.base.BaseMvpFragment;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;

public class HomeFragment extends BaseMvpFragment<presenter> implements Contract.Iview {


    private FlyBanner mBanner;
    private RecyclerView recyclerView;

    @Override
    protected presenter createPresenter() {
        return new presenter();
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        mBanner = view.findViewById(R.id.banner);
        recyclerView = view.findViewById(R.id.recForeign);

    }

    @Override
    protected void getData() {
 /*       HashMap<String,Object> headmap=new HashMap<>();
        HashMap<String,Object> map=new HashMap<>();*/

        List<String> list=new ArrayList<>();
        list.add("http://api.xg360.cc/upload/img/1.jpg");
        list.add("http://api.xg360.cc/upload/img/2.jpg");
        list.add("http://api.xg360.cc/upload/img/3.jpg");
        mBanner.setImagesUrl(list);
        //常用工具
        List<String> itemStrs = new ArrayList<>();

        List<Integer> itemIcons = new ArrayList<>();
        itemStrs.add("强力省点");
        itemStrs.add("手机降温");
        itemStrs.add("通知栏");
        itemStrs.add("软件管理");
        itemStrs.add("省电锁屏");
        itemStrs.add("应用锁");
        itemIcons.add(R.mipmap.ic_launcher);
        itemIcons.add(R.mipmap.ic_launcher);
        itemIcons.add(R.mipmap.ic_launcher);
        itemIcons.add(R.mipmap.ic_launcher);
        itemIcons.add(R.mipmap.ic_launcher);
        itemIcons.add(R.mipmap.ic_launcher);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());

        ToolOftenAdapter toolOftenAdapter = new ToolOftenAdapter(getActivity(),itemStrs,itemIcons);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(toolOftenAdapter);

    }

    @Override
    public void getData(Object object) {


    }
}

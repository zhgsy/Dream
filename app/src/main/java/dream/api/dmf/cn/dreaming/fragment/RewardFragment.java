package dream.api.dmf.cn.dreaming.fragment;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.activity.RewardDanActivity;
import dream.api.dmf.cn.dreaming.activity.RewardListActivity;
import dream.api.dmf.cn.dreaming.activity.RewardMxActivity;
import dream.api.dmf.cn.dreaming.activity.RewardPicesActivity;
import dream.api.dmf.cn.dreaming.activity.RewardShenActivity;
import dream.api.dmf.cn.dreaming.activity.RewardTiActivity;
import dream.api.dmf.cn.dreaming.activity.RewardzzActivity;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpFragment;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.ReadBean;


public class RewardFragment extends BaseMvpFragment<presenter> implements Contract.Iview {

    Unbinder unbinder1;
    @BindView(R.id.r_head_num)
    TextView rHeadNum;
    @BindView(R.id.r_head_name)
    TextView rHeadName;
    @BindView(R.id.mhead_image)
    RelativeLayout mheadImage;
    @BindView(R.id.r_image_left)
    ImageView rImageLeft;
    @BindView(R.id.r_quanreward)
    LinearLayout rQuanreward;
    @BindView(R.id.r_zong)
    TextView rZong;
    @BindView(R.id.r_xian)
    TextView rXian;
    @BindView(R.id.r_tui)
    TextView rTui;
    @BindView(R.id.r_tuan)
    TextView rTuan;
    @BindView(R.id.r_hui_left)
    ImageView rHuiLeft;
    @BindView(R.id.r_huireward)
    LinearLayout rHuireward;
    @BindView(R.id.r_tu)
    LinearLayout rTu;
    @BindView(R.id.r_cai_left)
    ImageView rCaiLeft;
    @BindView(R.id.r_reward)
    LinearLayout rReward;
    @BindView(R.id.r_showlist)
    ImageView rShowlist;
    @BindView(R.id.mjf_list)
    TextView mjfList;
    @BindView(R.id.r_xiang)
    ImageView rXiang;
    @BindView(R.id.mjf_xi)
    TextView mjfXi;
    @BindView(R.id.r_tixian)
    ImageView rTixian;
    @BindView(R.id.mjd_ti)
    TextView mjdTi;
    @BindView(R.id.r_shen)
    ImageView rShen;
    @BindView(R.id.r_jifen)
    ImageView rJifen;
    @BindView(R.id.mjf_zz)
    TextView mjfZz;
    @BindView(R.id.mjf_shen)
    TextView mjfShen;
    private View view;
    private LinearLayout mRan;
    private SimpleDraweeView mImage;
    private TextView lNum;
    private TextView rNum;
    private SharedPreferences sharedPreferences;
    private String uPhone;
    private ReadBean.DataBean data;
    private TextView mZong;
    private TextView mXian;
    private TextView mTui;
    private TextView mTuan;


    @Override
    protected presenter createPresenter() {
        return new presenter();
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_reward;
    }

    @Override
    protected void initView(View view) {
        sharedPreferences = mContext.getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        uPhone = sharedPreferences.getString(UserApi.UserName, "");
        mRan = view.findViewById(R.id.r_dan);
        mImage = view.findViewById(R.id.r_head_image);
        lNum = view.findViewById(R.id.left_num);
        rNum = view.findViewById(R.id.right_num);
        mZong = view.findViewById(R.id.r_zong);
        mXian = view.findViewById(R.id.r_xian);
        mTui = view.findViewById(R.id.r_tui);
        mTuan = view.findViewById(R.id.r_tuan);
        mRan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RewardDanActivity.class));
            }
        });


    }

    @Override
    protected void getData() {
        HashMap<String,Object> headmap=new HashMap<>();
        HashMap<String,Object> map=new HashMap<>();
        map.put("phone",uPhone);
        mPresenter.postData(UserApi.getRewardList,headmap,map,ReadBean.class);
    }

    @Override
    public void getData(Object object) {
        ReadBean readBean= (ReadBean) object;
        if (readBean.status.equals("200")){

            data = readBean.data;
            if (data.info==null){
                lNum.setText("0");
                rNum.setText("0");
                rHeadNum.setText("13522301073");
                mZong.setText("0");
                mXian.setText("0");
                mTui.setText("0");
                mTuan.setText("0");
                return;
            }else{
                lNum.setText(data.info.left_duipeng);
                rNum.setText(data.info.right_duipeng);
                rHeadNum.setText(data.info.phone);
                mZong.setText(data.info.total_rewqrd);
                mXian.setText(data.info.now_jifen);
                mTui.setText(data.info.commend_count);
                mTuan.setText(data.info.team_count);
            }
        }else{
            Toast.makeText(mContext,readBean.message,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        /**
         * let token = (Login.username + "a85GhBeA73J3" + "\(timeStamp)").MD5
         *         let url = "https://shop.xg360.cc/app/index.php?i=2&c=entry&m=ewei_shopv2&do=mobile&r=news.login&mobile=" + Login.username + "&token=" + token + "&time=" + "\(timeStamp)"
         */
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.r_showlist, R.id.r_xiang, R.id.r_tixian, R.id.r_shen, R.id.r_jifen, R.id.mjf_zz, R.id.r_tu})
    public void onViewClicked(View view) {
        switch (view.getId()) {


            //奖励列表
            case R.id.r_showlist:
                startActivity(new Intent(getActivity(), RewardListActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
            //积分明细
            case R.id.r_xiang:
                startActivity(new Intent(getActivity(), RewardMxActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
            //积分体现
            case R.id.r_tixian:
                startActivity(new Intent(getActivity(), RewardTiActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
            //申请服务
            case R.id.r_shen:
                startActivity(new Intent(getActivity(), RewardShenActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
            //积分转增
            case R.id.r_jifen:
                startActivity(new Intent(getActivity(), RewardzzActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
         /*
            case R.id.mjf_zz:

                break;*/
            //跳转到网络图谱
            case R.id.r_tu:
                startActivity(new Intent(getActivity(), RewardPicesActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
        }
    }


  /*  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.mjf_list, R.id.mjf_xi, R.id.mjd_ti, R.id.mjf_zz, R.id.mjf_shen, R.id.r_pices,R.id.r_dan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //奖励列表
            case R.id.mjf_list:
                startActivity(new Intent(getActivity(), RewardListActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                //startActivity(new Intent(getActivity(), RewardListActivity.class));
                break;
            //积分明细
            case R.id.mjf_xi:
                startActivity(new Intent(getActivity(), RewardMxActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                //startActivity(new Intent(getActivity(), RewardMxActivity.class));
                break;
            //积分体现
            case R.id.mjd_ti:
                startActivity(new Intent(getActivity(), RewardTiActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                //startActivity(new Intent(getActivity(), RewardTiActivity.class));
                break;
            //积分转增
            case R.id.mjf_zz:
                startActivity(new Intent(getActivity(), RewardzzActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                //startActivity(new Intent(getActivity(), RewardzzActivity.class));
                break;
            //申请服务
            case R.id.mjf_shen:
                startActivity(new Intent(getActivity(), RewardShenActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                //startActivity(new Intent(getActivity(), RewardShenActivity.class));
                break;
            //跳转到网络图谱
            case R.id.r_pices:
                //淡若淡出
                startActivity(new Intent(getActivity(), RewardPicesActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                //startActivity(new Intent(getActivity(), RewardPicesActivity.class));
                break;
            //推荐清单
            case R.id.r_dan:
                //转场动画
                startActivity(new Intent(getActivity(), RewardDanActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                //startActivity(new Intent(getActivity(),RewardDanActivity.class));
                break;
        }
    }*/

    @Override
    public void onResume() {
        super.onResume();

    }

}

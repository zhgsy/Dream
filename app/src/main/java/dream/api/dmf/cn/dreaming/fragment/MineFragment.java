package dream.api.dmf.cn.dreaming.fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.activity.minactivity.AllActivity;
import dream.api.dmf.cn.dreaming.activity.minactivity.FahuoActivity;
import dream.api.dmf.cn.dreaming.activity.minactivity.FinishActivity;
import dream.api.dmf.cn.dreaming.activity.minactivity.ShouActivity;
import dream.api.dmf.cn.dreaming.activity.minactivity.WaitActivity;
import dream.api.dmf.cn.dreaming.activity.mineactivity.AboutActivity;
import dream.api.dmf.cn.dreaming.activity.mineactivity.AddressActivity;
import dream.api.dmf.cn.dreaming.activity.mineactivity.AnQuanActivity;
import dream.api.dmf.cn.dreaming.activity.mineactivity.LoadDownActivity;
import dream.api.dmf.cn.dreaming.activity.mineactivity.MJListActivity;
import dream.api.dmf.cn.dreaming.activity.mineactivity.SetActivity;
import dream.api.dmf.cn.dreaming.activity.mineactivity.ShareActivity;
import dream.api.dmf.cn.dreaming.activity.mineactivity.ShopActivity;
import dream.api.dmf.cn.dreaming.activity.mineactivity.ShouKActivity;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpFragment;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;


public class MineFragment extends BaseMvpFragment<presenter> implements Contract.Iview {


    @BindView(R.id.mine_head_img)
    SimpleDraweeView mineHeadImg;
    TextView rHeadNum;
    @BindView(R.id.mine_head_image)
    ImageView mineHeadImage;
    @BindView(R.id.mine_head_phone)
    TextView mineHeadPhone;
    @BindView(R.id.mine_head_num)
    TextView mineHeadNum;
    @BindView(R.id.head_s_image)
    ImageView headSImage;
    @BindView(R.id.mhead_image)
    RelativeLayout mheadImage;
    @BindView(R.id.m_fen)
    TextView mFen;
    @BindView(R.id.m_edu)
    TextView mEdu;
    @BindView(R.id.m_tone)
    ImageView mTone;
    @BindView(R.id.m_ttwo)
    ImageView mTtwo;
    @BindView(R.id.m_tshare)
    ImageView mTshare;
    @BindView(R.id.m_tfoure)
    ImageView mTfoure;
    @BindView(R.id.r_cai_left)
    ImageView rCaiLeft;
    LinearLayout rReward;
    @BindView(R.id.m_pay)
    ImageView mPay;
    @BindView(R.id.m_waito)
    ImageView mWaito;
    @BindView(R.id.m_Waitt)
    ImageView mWaitt;
    @BindView(R.id.m_finish)
    ImageView mFinish;
    @BindView(R.id.mjf_list)
    TextView mjfList;
    @BindView(R.id.mjf_xi)
    TextView mjfXi;
    @BindView(R.id.mjd_ti)
    TextView mjdTi;
    @BindView(R.id.mjf_shen)
    TextView mjfShen;
    @BindView(R.id.m_shop)
    ImageView mShop;
    @BindView(R.id.m_address)
    ImageView mAddress;
    @BindView(R.id.m_fu)
    ImageView mFu;
    @BindView(R.id.m_set)
    ImageView mSet;
    Unbinder unbinder;
    private TextView mMoney;
    private SharedPreferences sharedPreferences;
    private String mMone;
    private TextView mJin;
    private WebView mWeb;
    private String username;
    private String mone;
    private String mtwo;

    @Override
    protected presenter createPresenter() {
        return new presenter();
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        sharedPreferences = mContext.getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(UserApi.UserName, "");
        mone = sharedPreferences.getString(UserApi.credit3, "");
        mtwo = sharedPreferences.getString(UserApi.credit4, "");
     /*   double one = Double.parseDouble(mone);
        double two = Double.parseDouble(mtwo);*/
        mMoney = view.findViewById(R.id.m_edu);
        mJin = view.findViewById(R.id.m_jin);
        rHeadNum= view.findViewById(R.id.r_head_num);
       // mMoney.setText(one+two+"");
        rHeadNum.setText(username);

        mJin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MJListActivity.class));
            }
        });
    rReward = view.findViewById(R.id.r_reward);
        rReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AllActivity.class));
            }
        });

    }

    @Override
    protected void getData() {



    }

    @Override
    public void getData(Object object) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.head_s_image, R.id.m_fen, R.id.m_edu, R.id.m_tone, R.id.m_ttwo, R.id.m_tshare, R.id.m_tfoure, R.id.m_pay, R.id.m_waito, R.id.m_Waitt, R.id.m_finish, R.id.mjf_list, R.id.mjf_xi, R.id.mjd_ti, R.id.mjf_shen, R.id.m_shop, R.id.m_address, R.id.m_fu, R.id.m_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //扫码
            case R.id.head_s_image:
                startQrCode();
                break;
            //惠民积分
            case R.id.m_fen:
                break;
            //额度
            case R.id.m_edu:
                break;
            //收款方式
            case R.id.m_tone:
                startActivity(new Intent(getActivity(),ShouKActivity.class));
                break;
            //安全中心
            case R.id.m_ttwo:
                startActivity(new Intent(getActivity(),AnQuanActivity.class));
                break;
            //分享下载
            case R.id.m_tshare:
                startActivity(new Intent(getActivity(),LoadDownActivity.class));
                break;
            //关于平台
            case R.id.m_tfoure:
                startActivity(new Intent(getActivity(),AboutActivity.class));
                break;
            //代付款
            case R.id.m_pay:
                startActivity(new Intent(getActivity(), WaitActivity.class));
                break;
            //代发货
            case R.id.m_waito:
                startActivity(new Intent(getActivity(), FahuoActivity.class));
                break;
            //待收货
            case R.id.m_Waitt:
                startActivity(new Intent(getActivity(), ShouActivity.class));
                break;
            //已完成
            case R.id.m_finish:
                startActivity(new Intent(getActivity(), FinishActivity.class));
                break;
            case R.id.mjf_list:
                break;
            case R.id.mjf_xi:
                break;
            case R.id.mjd_ti:
                break;
            case R.id.mjf_shen:
                break;
            //购物车
            case R.id.m_shop:
                startActivity(new Intent(getActivity(), ShopActivity.class));
                break;
            //收货地址
            case R.id.m_address:
                startActivity(new Intent(getActivity(), AddressActivity.class));
                break;
            //客服中心
            case R.id.m_fu:
                startActivity(new Intent(getActivity(), ShareActivity.class));
                break;
            //设置
            case R.id.m_set:
                startActivity(new Intent(getActivity(), SetActivity.class));
                break;
        }
    }

    // 开始扫码
    private void startQrCode() {
        // 申请相机权限
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, UserApi.REQ_PERM_CAMERA);
            return;
        }
        // 申请文件读写权限（部分朋友遇到相册选图需要读写权限的情况，这里一并写一下）
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, UserApi.REQ_PERM_EXTERNAL_STORAGE);
            return;
        }
        // 二维码扫码
      /*  Intent intent = new Intent(mContext, CaptureActivity.class);
        startActivityForResult(intent, Constant.REQ_QR_CODE);*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
      /*  if (requestCode == Constant.REQ_QR_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);
            //将扫描出的信息显示出来
//            tvResult.setText(scanResult);
            Toast.makeText(ctx, scanResult, Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
          /*  case Constant.REQ_PERM_CAMERA:
                // 摄像头权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    Toast.makeText(ctx, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show();
                }
                break;
            case Constant.REQ_PERM_EXTERNAL_STORAGE:
                // 文件读写权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    Toast.makeText(mContext, "请至权限中心打开本应用的文件读写权限", Toast.LENGTH_LONG).show();
                }
                break;*/
        }
    }
}

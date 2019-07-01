package dream.api.dmf.cn.dreaming.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.BuyIdBean;
import dream.api.dmf.cn.dreaming.fragment.bigfragment.BigBugFragment;

public class BuyInActivity extends BaseMvpActivity<presenter> implements Contract.Iview {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private SharedPreferences sharedPreferences;
    private Boolean username1;
    private String mUid;
    private String mShell;
    private TextView mReplay;
    private TextView mNums;
    private TextView mPricess;
    private TextView mButn;
    private Button mFalse;
    private String mid;

    @Override
    public void getThisData() {
        sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        username1 = sharedPreferences.getBoolean("Username", true);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
    }

    @Override
    public void getInitData() {
        Intent intent = getIntent();
        String replay = intent.getStringExtra("replay");
        String amount = intent.getStringExtra("amount");
        String price = intent.getStringExtra("price");
        mid = intent.getStringExtra("id");
        mReplay = findViewById(R.id.buy_replay);
        mNums = findViewById(R.id.buy_nums);
        mPricess = findViewById(R.id.buy_prices);
        mFalse = findViewById(R.id.buy_false);
        mButn = findViewById(R.id.buy_butn);
        mReplay.setText(replay);
        mNums.setText(amount);
        mPricess.setText(price);
        mFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, BigBugFragment.class));
            }
        });
        mButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username1 == true) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(BuyInActivity.this);
                    // 创建对话框构建器
                    View view=View.inflate(BuyInActivity.this,R.layout.updawho,null);
                    //*  View view = View.inflate(QueueActivity.this, R.layout.updawho, null);*//*
                    // 获取布局中的控件
                    TextView edmail = (TextView) view.findViewById(R.id.bbuy);
                    final TextView unfalse = (TextView) view.findViewById(R.id.fal_qu);
                    unfalse.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
//                    // 设置参数
                    builder.setTitle("买入")
                            .setView(view);
                    // 创建对话框
                    final AlertDialog alertDialog = builder.create();
                    edmail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Toast.makeText(mContext,"买入",Toast.LENGTH_LONG).show();
                            if (username1 ==true){
                                HashMap<String,Object>headmap=new HashMap<>();
                                HashMap<String,Object>map=new HashMap<>();
                                map.put("uid",mUid);
                                map.put("shell",mShell);
                                map.put("id",mid);
                                mPresenter.postData(UserApi.getBUYid,headmap,map,BuyIdBean.class);
                        /*    edone.setText(isLoginBean.stock_mdf);
                            edtwo.setText(isLoginBean.regmoney_dmf);*/

                            }else if (username1 ==false){
                                HashMap<String,Object>headmap=new HashMap<>();
                                HashMap<String,Object>map=new HashMap<>();
                                map.put("uid",mUid);
                                map.put("shell",mShell);
                                map.put("id",mid);
                                //map.put("paytype",paytype);
                                mPresenter.postData(UserApi.getHYTBYID,headmap,map,BuyIdBean.class);
                               /* edone.setText(isLoginBean.stock);
                                edtwo.setText(isLoginBean.regmoney);
                    */
                                /*String Hed = sharedPreferences.getString(UserApi.HYTED, "");
                                tvBug.setText("DMF买入");
                                eTeprice.setText(hytday);*/
                            }
                         /*   // TODO Auto-generated method stub
                            String uname = username.getText().toString().trim();
                            String edma = edmail.getText().toString().trim();
                            String sexo = wsex.getText().toString().trim();
                            String sext = nsex.getText().toString().trim();

                            if (uname.equals("") && edma.equals("")) {
                                Toast.makeText(QueueActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(QueueActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            }*/
                            //alertDialog.dismiss();// 对话框消失
                        }
                    });
                    alertDialog.show();
/*                      //
                    AlertDialog.Builder builder=new AlertDialog.Builder(QueueActivity.this);
                    // 创建对话框构建器
                    View view=View.inflate(BuyInActivity.this,R.layout.updawho,null);
                    *//*  View view = View.inflate(QueueActivity.this, R.layout.updawho, null);*//*
                    // 获取布局中的控件
                    final EditText edmail = (EditText) view.findViewById(R.id.edmail);
                    final EditText username = (EditText) view.findViewById(R.id.edname);
                    final CheckBox wsex = (CheckBox) view.findViewById(R.id.csex);
                    final CheckBox nsex = (CheckBox) view.findViewById(R.id.cnsex);
                    final Button button = (Button) view.findViewById(R.id.butnupdate);
                    // 设置参数
                    builder.setTitle("修改用户信息").setIcon(R.mipmap.fanhdpi)
                            .setView(view);
                    // 创建对话框
                    final AlertDialog alertDialog = builder.create();
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            String uname = username.getText().toString().trim();
                            String edma = edmail.getText().toString().trim();
                            String sexo = wsex.getText().toString().trim();
                            String sext = nsex.getText().toString().trim();

                            if (uname.equals("") && edma.equals("")) {
                                Toast.makeText(QueueActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(QueueActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            }
                            alertDialog.dismiss();// 对话框消失
                        }
                    });*/
                /*    alertDialog.show();*/
                    //DMF
/*
                    HashMap<String, Object> headmap = new HashMap<>();
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("uid", mUid);
                    map.put("shell", mShell);
                    map.put("id", mid);
                    mPresenter.postData(UserApi.getSELLid, headmap, map, BuyIdBean.class);*/
               /* } else if (username1 == false) {
                    //HYT
                    HashMap<String, Object> headmap = new HashMap<>();
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("uid", mUid);
                    map.put("shell", mShell);
                    map.put("id", mid);
                    mPresenter.postData(UserApi.getHYTBYIDSELL, headmap, map, BuyIdBean.class);*/

                }

            }
        });

     /*   map.put("id",0);
        mPresenter.postData(UserApi.getBUYid);*/
    }

    @Override
    public int getView() {
        return R.layout.activity_buy_in;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {
        if (object instanceof BuyIdBean) {
            BuyIdBean buBean = (BuyIdBean) object;
            if (buBean.error.equals("0")) {
                Toast.makeText(mContext, "成功", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(mContext, buBean.msg, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}

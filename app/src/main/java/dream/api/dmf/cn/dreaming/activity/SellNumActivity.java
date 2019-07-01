package dream.api.dmf.cn.dreaming.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.BuBean;
import dream.api.dmf.cn.dreaming.utils.BankDialog;

public class SellNumActivity extends BaseMvpActivity<presenter> implements Contract.Iview {


    private TextView mReplay;
    private TextView mAmout;
    private TextView mprice;
    private SharedPreferences sharedPreferences;
    private String mUid;
    private String mShell;
    private String mId;
    private Button quebutn;
    private boolean username1;
    private TextView mBack;
    private List list;
    private String paytype;
    @Override
    public void getThisData() {
        quebutn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    AlertDialog.Builder builder=new AlertDialog.Builder(SellNumActivity.this);
                    // 创建对话框构建器
                    View view=View.inflate(SellNumActivity.this,R.layout.updawho,null);
                    //*  View view = View.inflate(QueueActivity.this, R.layout.updawho, null);*//*
                    // 获取布局中的控件
                    TextView edmail = (TextView) view.findViewById(R.id.bbuy);
                    final TextView unfalse = (TextView) view.findViewById(R.id.fal_qu);

//                    // 设置参数
                   builder.setTitle("卖出")
                            .setView(view);
                    // 创建对话框
                unfalse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
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
                                map.put("id",mId);
                                map.put("paytype",paytype);
                                mPresenter.postData(UserApi.getSELLid,headmap,map,BuBean.class);
                        /*    edone.setText(isLoginBean.stock_mdf);
                            edtwo.setText(isLoginBean.regmoney_dmf);*/

                            }else if (username1 ==false){
                                HashMap<String,Object>headmap=new HashMap<>();
                                HashMap<String,Object>map=new HashMap<>();
                                map.put("uid",mUid);
                                map.put("shell",mShell);
                                map.put("id",mId);
                                map.put("paytype",paytype);
                                mPresenter.postData(UserApi.getHYTBYIDSELL,headmap,map,BuBean.class);
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
            }
        });
     /*   HashMap<String,Object> headmap=new HashMap<>();
        HashMap<String,Object> map=new HashMap<>();
        map.put("uid",mUid);
        map.put("shell",mShell);
        map.put("id",mId);*/
       // mPresenter.postData(UserApi.getBUYid,headmap,map,);
    }

    @Override
    public void getInitData() {
        sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        username1 = sharedPreferences.getBoolean("Username", true);
        mReplay = findViewById(R.id.reply);
        mAmout = findViewById(R.id.num);
        mprice = findViewById(R.id.prices);
        quebutn = findViewById(R.id.buy_butn);
        mBack = findViewById(R.id.s_banck);
        list = Arrays.asList(getResources().getStringArray(R.array.bank));
        Intent intent = getIntent();
        String realpay = intent.getStringExtra("realpay");
        String amount = intent.getStringExtra("amount");
        String price = intent.getStringExtra("price");
        mId = intent.getStringExtra("position");
        mReplay.setText(realpay);
        mAmout.setText(amount);
        mprice.setText(price);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BankDialog(mContext, list, mBack);
                if (list.get(0).equals("银行卡")){
                    paytype="1";
                    return;
                }else if (list.get(1).equals("支付宝")){
                    paytype="2";
                    return;
                }else if(list.get(2).equals("微信")){
                    paytype="6";
                    return;
            }
        }
    });
    }

    @Override
    public int getView() {
        return R.layout.activity_sell_num;
        }



       /* http://api.xg360.cc/index.php?mod=mobile&act=buy_dmf_trade2
        http://api.xg360.cc/index.php?mod=mobile&act=buy_stock_trade2*/
    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {

           if(object instanceof BuBean){
               BuBean buyIdBean= (BuBean) object;
               if(buyIdBean.error.equals("0")){
                   Toast.makeText(mContext,"购买成功",Toast.LENGTH_LONG).show();
                   finish();
               }else{
                   Toast.makeText(mContext,buyIdBean.msg,Toast.LENGTH_LONG).show();
               }
           }
        /*
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
    }
}

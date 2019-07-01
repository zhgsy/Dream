package dream.api.dmf.cn.dreaming.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.transition.Fade;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.MouthBean;
import dream.api.dmf.cn.dreaming.bean.RewardBean;

public class RewardListActivity extends BaseMvpActivity<presenter> implements View.OnClickListener, Contract.Iview {
    @BindView(R.id.r_left)
    ImageView mLeft;
    @BindView(R.id.r_left_time)
    TextView mLeftTime;
    @BindView(R.id.r_right_time)
    TextView mRightTime;
    @BindView(R.id.r_right)
    ImageView mRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.r_listnew)
    TextView rListnew;
    @BindView(R.id.r_lin_butn)
    Button rLinButn;
    @BindView(R.id.lione)
    TextView lione;
    @BindView(R.id.litwo)
    TextView litwo;
    @BindView(R.id.lithr)
    TextView lithr;
    @BindView(R.id.lifour)
    TextView lifour;
    @BindView(R.id.lifive)
    TextView lifive;
    @BindView(R.id.lisix)
    TextView lisix;
    @BindView(R.id.liserven)
    TextView liserven;
    @BindView(R.id.zz_one)
    TextView zzOne;
    @BindView(R.id.zz_two)
    TextView zzTwo;
    @BindView(R.id.zz_three)
    TextView zzThree;
    @BindView(R.id.zz_four)
    TextView zzFour;
    @BindView(R.id.zz_five)
    TextView zzFive;
    @BindView(R.id.zz_six)
    TextView zzSix;
    @BindView(R.id.zz_serven)
    TextView zzServen;
    @BindView(R.id.yy_one)
    TextView yyOne;
    @BindView(R.id.yy_two)
    TextView yyTwo;
    @BindView(R.id.yy_three)
    TextView yyThree;
    @BindView(R.id.yy_four)
    TextView yyFour;
    @BindView(R.id.yy_five)
    TextView yyFive;
    @BindView(R.id.yy_six)
    TextView yySix;
    @BindView(R.id.yy_serven)
    TextView yyServen;
    private TextView mTitle, mNew;
    private ImageView mBack;
    private Button mNButn;
    private TimePickerView pvTime, mTime;
    private boolean m = true;
    private String righttime;
    private String lEftime;
    private SharedPreferences sharedPreferences;
    private String username;
    private String mMouth;
    private TextView mOne;
    private TextView mTwo;
    private TextView mThree;
    private TextView mFour;
    private TextView mFive;
    private TextView mSix;
    private TextView mPhone;
    private TextView mNumber;


    @Override
    public void getThisData() {
        HashMap<String, Object> headmap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        String lefttime = mLeftTime.getText().toString();
        String righttime = mRightTime.getText().toString();
        long timeStamp = System.currentTimeMillis();
        map.put("phone", "15111111111");
        map.put("start", lefttime);
        map.put("end", righttime);
        mPresenter.postData(UserApi.getREList, headmap, map, RewardBean.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void getInitData() {
        sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(UserApi.UserName, "");
        initView();
        initTimePicker();
        initTime();
        getWindow().setEnterTransition(new Fade().setDuration(2000));
        mTitle.setText("奖励列表");
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mOne = findViewById(R.id.mouth_one);
        mTwo = findViewById(R.id.mouth_two);
        mThree = findViewById(R.id.mouth_three);
        mFour = findViewById(R.id.mouth_four);
        mFive = findViewById(R.id.mouth_five);
        mSix = findViewById(R.id.mouth_six);
        mPhone = findViewById(R.id.nunhui);
        mNumber = findViewById(R.id.mnumber);

    }

    @Override
    public int getView() {
        return R.layout.activity_reward_list;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

   /* @Override
    public int getLayoutResId() {
        return R.layout.activity_reward_list;
    }*/

    //初始化控件
    public void initView() {
        mTitle = findViewById(R.id.tv_title);
        mBack = findViewById(R.id.iv_back);
        mNew = findViewById(R.id.r_listnew);
        mNButn = findViewById(R.id.r_lin_butn);
        mNButn.setOnClickListener(this);
        Calendar selectedDate = Calendar.getInstance();//用来设置默认选中的日期
        Calendar startDate = Calendar.getInstance();
        startDate.set(2013, 1, 1);//用来设置起始日期

        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 1, 1);//用来设置终止日期

    }

 /*   @Override
    public void initData() {

    }
*/
  /*  @Override
    public void initListener() {

    }
*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.r_lin_butn:
                if (pvTime != null) {
                    pvTime.show(v);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
                }
                mMouth = rListnew.getText().toString().trim();
                HashMap<String,Object> headmap=new HashMap<>();
                HashMap<String,Object> map=new HashMap<>();
                map.put("phone","15111111111");
                map.put("date",mMouth);
                mPresenter.postData(UserApi.getMouth,headmap,map,MouthBean.class);
                break;
        }
    }

    private void initTimePicker() {//Dialog 模式下，在底部弹出

        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mNew.setText(getTime(date));

                Log.i("pvTime", "onTimeSelect");

            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                }).setType(new boolean[]{true, true, false, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "时", "", "")//默认设置为年月日时分秒

                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }

    private void initTime() {//Dialog 模式下，在底部弹出

        mTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (m == true) {
                    mLeftTime.setText(getTime2(date));
                } else {
                    mRightTime.setText(getTime2(date));
                }

                Log.i("pvTime", "onTimeSelect");

            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                }).setType(new boolean[]{true, true, true      , false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "时", "", "")//默认设置为年月日时分秒
                // .setRangDate(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR) + 20)
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .build();

        Dialog mDialog = mTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            mTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

    private String getTime2(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

   /* @OnClick({R.id.r_left, R.id.r_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.r_left:
                m=true;
                if (mTime != null) {
                    mTime.show(view);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
                }
                lEftime = mLeftTime.getText().toString().trim();
                break;
            case R.id.r_right:
                m=false;
                if (mTime != null) {
                    mTime.show(view);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
                }
                righttime = mRightTime.getText().toString().trim();
                HashMap<String,Object> headmap=new HashMap<>();
                HashMap<String,Object> map=new HashMap<>();
                map.put("phone",username);
                map.put("start",lEftime);
                map.put("end",righttime);
                mPresenter.postData(UserApi.getREList,headmap,map,RewardBean.class);
                break;
        }
    }*/

    @Override
    public void getData(Object object) {
        if (object instanceof RewardBean) {
            RewardBean rewardBean = (RewardBean) object;
            if (rewardBean.status.equals("200")) {
                RewardBean.DataBean.FirstBean first = rewardBean.data.first;
                lione.setText(first.commend);
                litwo.setText(first.share);
                lithr.setText(first.share_commission);
                lifour.setText(first.share_jifen);
                lifive.setText(first.form);
                lisix.setText(first.manger);
                liserven.setText(first.real_reward);
                RewardBean.DataBean.LastBean last = rewardBean.data.last;
                zzOne.setText(last.commend);
                zzTwo.setText(last.share);
                zzThree.setText(last.share_commission);
                zzFour.setText(last.share_jifen);
                zzFive.setText(last.form);
                zzSix.setText(last.manger);
                zzServen.setText(last.real_reward);
                RewardBean.DataBean.TotalBean total = rewardBean.data.total;
                yyOne.setText(total.commend);
                yyTwo.setText(total.share);
                yyThree.setText(total.share_commission);
                yyFour.setText(total.share_jifen);
                yyFive.setText(total.form);
                yySix.setText(total.manger);
                yyServen.setText(total.real_reward);
                mPhone.setText(rewardBean.data.number);
                //mNumber.setText(rewardBean.data.total);
               /* zzone.setText(first.form);
                litwo.setText(first.share);
                lithr.setText(first.share_commission);
                lifour.setText(first.share_commission);
                lifive.setText(first.form);
                lisix.setText(first.manger);
                liserven.setText(first.real_reward);
                lione.setText(first.form);
                litwo.setText(first.share);
                lithr.setText(first.share_commission);
                lifour.setText(first.share_commission);
                lifive.setText(first.form);
                lisix.setText(first.manger);
                liserven.setText(first.real_reward);*/
                //Toast.makeText(mContext, rewardBean.message, Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(mContext,rewardBean.message,Toast.LENGTH_LONG).show();
            }
        }
        if (object instanceof MouthBean){
            MouthBean mouthBean= (MouthBean) object;
            if (mouthBean.status==200){
                mOne.setText(mouthBean.data.commend);
                mTwo.setText(mouthBean.data.share);
                mThree.setText(mouthBean.data.share_commission);
                mFour.setText(mouthBean.data.share_jifen);
                mFive.setText(mouthBean.data.form);
                mSix.setText(mouthBean.data.real_reward);
               // mPhone.setText(mouthBean.data.);
                //Toast.makeText(mContext,mouthBean.message,Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(mContext,mouthBean.message,Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.r_left_time, R.id.r_right_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.r_left_time:
                m = true;
                if (mTime != null) {
                    mTime.show(view);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
                }
                lEftime = mLeftTime.getText().toString().trim();
                break;
            case R.id.r_right_time:
                m = false;
                if (mTime != null) {
                    mTime.show(view);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
                }
                righttime = mRightTime.getText().toString().trim();
                HashMap<String, Object> headmap = new HashMap<>();
                HashMap<String, Object> map = new HashMap<>();
                map.put("phone", "15111111111");
                map.put("start", lEftime);
                map.put("end", righttime);
                mPresenter.postData(UserApi.getREList, headmap, map, RewardBean.class);
                break;
        }
    }
}

package dream.api.dmf.cn.dreaming.activity.mineactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.api.UserApi;
import dream.api.dmf.cn.dreaming.base.BaseMvpActivity;
import dream.api.dmf.cn.dreaming.base.mvp.Contract;
import dream.api.dmf.cn.dreaming.base.mvp.presenter.presenter;
import dream.api.dmf.cn.dreaming.bean.BankBean;
import dream.api.dmf.cn.dreaming.utils.BankDialog;

public class BankActivity extends BaseMvpActivity<presenter> implements Contract.Iview {

    TextView mTitle;
    ImageView mBack;
    EditText etName;
    TextView mBank;
    EditText etAddress;
    EditText etCode;
    @BindView(R.id.tv_send)
    TextView tvSend;
    //private List<String> numList;
    private List list;
    private String mUid;
    private String mShell;
    private String method="bankcard";
    private String back;
    private String adddress;
    private String code;
    private String mName;
    private SharedPreferences sharedPreferences;
    private String mBName;

    @Override
    public void getThisData() {

    }

    @Override
    public void getInitData() {
        sharedPreferences = getSharedPreferences(UserApi.SP, Context.MODE_PRIVATE);
        mUid = sharedPreferences.getString(UserApi.Uid, "");
        mShell = sharedPreferences.getString(UserApi.Shell, "");
        String mBName = sharedPreferences.getString(UserApi.BACK, "");
        String mCode = sharedPreferences.getString(UserApi.BACKCode, "");
        String mAddress = sharedPreferences.getString(UserApi.BACKADDRESS, "");
        String mBackName = sharedPreferences.getString(UserApi.BACKNAME, "");
        mTitle = findViewById(R.id.tv_title);
        mBack = findViewById(R.id.iv_back);
        etName = findViewById(R.id.et_name);
        mBank = findViewById(R.id.tv_bank);
        etCode = findViewById(R.id.et_code);
        etAddress = findViewById(R.id.et_address);
     /*   String[] splitNums = nums.substring(1, nums.length() - 1).replace("\"", "").split(",");
        numList = Arrays.asList(splitNums);*/

        etName.setText(mBName);
        mBank.setText(mBackName);
        etAddress.setText(mAddress);
        etCode.setText(mCode);
        list = Arrays.asList(getResources().getStringArray(R.array.Bank));
        mTitle.setText("银行卡");
        mTitle.setTextSize(16);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }

    @Override
    public int getView() {
        return R.layout.activity_bank;
    }

    @Override
    protected presenter createP() {
        return new presenter();
    }

    @Override
    public void getData(Object object) {
        if (object instanceof BankBean) {
            BankBean bankBean = (BankBean) object;
            if (bankBean.error.equals("0")) {
                Toast.makeText(mContext, "绑定成功", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(mContext, bankBean.msg, Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_bank, R.id.tv_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_bank:
                new BankDialog(mContext, list, mBank);

                break;
            case R.id.tv_send:
                HashMap<String,Object> headmap=new HashMap<>();
                HashMap<String,Object> map =new HashMap<>();
                back = mBank.getText().toString().trim();
                mName = etName.getText().toString().trim();
                adddress = etAddress.getText().toString().trim();
                code = etCode.getText().toString().trim();
                if ("".equals(mName)) {
                    Toast.makeText(mContext, "开户姓名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(code)) {
                    Toast.makeText(mContext, "卡号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(adddress)) {
                    Toast.makeText(mContext, "开户行地址不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                sharedPreferences.edit().putString(UserApi.BACK,mName).commit();
                sharedPreferences.edit().putString(UserApi.BACKCode,code).commit();
                sharedPreferences.edit().putString(UserApi.BACKADDRESS,adddress).commit();
                sharedPreferences.edit().putString(UserApi.BACKNAME,back).commit();
                map.put("uid",mUid);
                map.put("shell",mShell);
                map.put("method",method);
                map.put("bankhm", mName);
                map.put("bankname",back);
                map.put("bankcard",code);
                map.put("bankadd", adddress);
                mPresenter.postData(UserApi.getBACK,headmap,map,BankBean.class);
                break;
        }
    }
}

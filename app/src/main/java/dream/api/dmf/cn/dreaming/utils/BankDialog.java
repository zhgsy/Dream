package dream.api.dmf.cn.dreaming.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.adapter.BuyNumAdapter;


/**
 * Created by SongNing on 2017/7/3.
 * email: 836883891@qq.com
 */

public class BankDialog {
    private Context ctx;


    public BankDialog(Context ctx, List<String> title, TextView textView) {
        this.ctx = ctx;

        showDialog(title,textView);
    }


    private Dialog dialog;

    //弹框
    private void showDialog(final List<String> title, final TextView textView) {
        if (dialog == null) {
            dialog = new Dialog(ctx, R.style.dialog_bottom_style);
        }
        View view = View.inflate(ctx, R.layout.dialog_bank, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ctx);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        BuyNumAdapter adapter = new BuyNumAdapter(ctx, title);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(new BuyNumAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                textView.setText(title.get(position));
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        Window window = (Window) dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = ScreenSizeUtil.getScreenWidth(ctx);
        window.setAttributes(attributes);
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.dialog_select_pic);
        dialog.show();
    }

}

package dream.api.dmf.cn.dreaming.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.bean.TuiBean;

/**
 * Created by SongNing on 2019/6/26.
 * email: 836883891@qq.com
 */
public class TuiAdapter extends RecyclerView.Adapter<TuiAdapter.ViewHolder> {
    List<TuiBean.DataBean> data;
    Context mContext;

    public TuiAdapter(List<TuiBean.DataBean> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.tui_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.mName.setText(data.get(i).realname);
            viewHolder.mTime.setText(data.get(i).createtime);
            viewHolder.money.setText(data.get(i).level);
            viewHolder.mPhone.setText(data.get(i).number);
            viewHolder.tMoney.setText(data.get(i).total_rewqrd);
    }

    @Override
    public int getItemCount() {
        if (data==null){
            return 0;
        }
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView mIamge;
        private final TextView mName;
        private final TextView mPhone;
        private final TextView mTime;
        private final TextView money;
        private final TextView tMoney;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIamge = itemView.findViewById(R.id.t_image);
            mName = itemView.findViewById(R.id.t_name);
            mPhone = itemView.findViewById(R.id.t_phone);
            mTime = itemView.findViewById(R.id.t_time);
            money = itemView.findViewById(R.id.t_money);
            tMoney = itemView.findViewById(R.id.tt_money);

        }
    }
}

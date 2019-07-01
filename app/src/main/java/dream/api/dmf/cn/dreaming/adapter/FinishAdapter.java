package dream.api.dmf.cn.dreaming.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.bean.SellBean;

/**
 * Created by SongNing on 2019/6/27.
 * email: 836883891@qq.com
 */
public class FinishAdapter extends RecyclerView.Adapter<FinishAdapter.ViewHolder> {
    Context mContext;
    List<SellBean.DataBean> data;
    ViewHolder holder;
    public FinishAdapter(Context mContext, List<SellBean.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =View.inflate(mContext,R.layout.item_trading,null);      //LayoutInflater.from(mContext).inflate(, parent, false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        holder.tvMoney.setText(data.get(i).realpay);
        holder.tvNum.setText(data.get(i).amount);
        holder.tvPrice.setText(data.get(i).price);
        holder.tvSell.setText((data.get(i).checked));
    }

    @Override
    public int getItemCount() {
        if (data==null){
            return 0;
        }
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNum;
        TextView tvPrice;
        TextView tvMoney;
        TextView tvSell;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNum = itemView.findViewById(R.id.tv_num);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvMoney = itemView.findViewById(R.id.tv_money);
            tvSell = itemView.findViewById(R.id.tv_sell);
        }
    }
}

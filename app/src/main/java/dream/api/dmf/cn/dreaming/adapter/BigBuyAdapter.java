package dream.api.dmf.cn.dreaming.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.activity.SellNumActivity;
import dream.api.dmf.cn.dreaming.bean.BigBean;

/**
 * Created by SongNing on 2019/6/29.
 * email: 836883891@qq.com
 */
public class BigBuyAdapter extends RecyclerView.Adapter<BigBuyAdapter.ViewHolder> {
    Context mContext;
    List<BigBean.DataBean> data;
    private ViewHolder holder;

    public BigBuyAdapter(Context mContext, List<BigBean.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =View.inflate(mContext,R.layout.bigbuy_item,null);      //LayoutInflater.from(mContext).inflate(, parent, false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        holder.tvMoney.setText(data.get(i).realpay);
        holder.tvNum.setText(data.get(i).amount);
        holder.tvPrice.setText(data.get(i).price);
        holder.tvSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,SellNumActivity.class);
                intent.putExtra("realpay",data.get(i).realpay);
                intent.putExtra("amount",data.get(i).amount);
                intent.putExtra("price",data.get(i).price);
                intent.putExtra("position",data.get(i).buy_uid);
               mContext.startActivity(intent);
            }
        });
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

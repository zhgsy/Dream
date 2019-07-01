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
 * Created by SongNing on 2019/6/29.
 * email: 836883891@qq.com
 */
public class SellAdapter extends RecyclerView.Adapter<SellAdapter.ViewHolder> {
    Context mContext;
    List<SellBean.DataBean> datase;
    private ViewHolder holder;

    public SellAdapter(Context mContext, List<SellBean.DataBean> datase) {
        this.mContext = mContext;
        this.datase = datase;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =View.inflate(mContext,R.layout.lusell_item,null);      //LayoutInflater.from(mContext).inflate(, parent, false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            holder.headnumber.setText(datase.get(i).realpay);
            holder.number.setText(datase.get(i).amount);
            holder.daipay.setText(datase.get(i).checked);
            holder.buytime.setText(datase.get(i).addtime);
            holder.price.setText(datase.get(i).price);
    }

    @Override
    public int getItemCount() {
        if (datase==null){
            return 0;
        }
        return datase.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView headnumber;
        private final TextView number;
        private final TextView price;
        private final TextView daipay;
        private final TextView buytime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headnumber = itemView.findViewById(R.id.buy_num);
            number = itemView.findViewById(R.id.buy_number);
            price = itemView.findViewById(R.id.buy_price);
            daipay = itemView.findViewById(R.id.buy_dai);
            buytime = itemView.findViewById(R.id.buy_time);
        }
    }
}

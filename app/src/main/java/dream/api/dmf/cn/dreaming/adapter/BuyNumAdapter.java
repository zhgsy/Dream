package dream.api.dmf.cn.dreaming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import dream.api.dmf.cn.dreaming.R;

/**
 * Created by Xionghu on 2018/4/7.
 * Desc:
 */

public class BuyNumAdapter extends RecyclerView.Adapter<BuyNumAdapter.MyViewHolder> {


    private List<String> list;
    private Context ctx;
    private final RequestOptions requestOptions;

    public BuyNumAdapter(Context context, List<String> list) {
        this.list = list;
        this.ctx = context;
        requestOptions = new RequestOptions()
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_num, parent, false);

        return new MyViewHolder(view, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.tvName.setText((list.get(position)));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        private MyItemClickListener mListener;

        public MyViewHolder(View itemView, MyItemClickListener myItemClickListener) {
            super(itemView);
            this.mListener = myItemClickListener;
            tvName = itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(view, getPosition());
            }
        }
    }

    private MyItemClickListener mItemClickListener;

    /**
     * 创建一个回调接口
     */
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 在activity里面adapter就是调用的这个方法,将点击事件监听传递过来,并赋值给全局的监听
     *
     * @param myItemClickListener
     */
    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }

}

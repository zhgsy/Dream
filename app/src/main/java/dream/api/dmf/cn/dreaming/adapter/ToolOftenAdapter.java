package dream.api.dmf.cn.dreaming.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dream.api.dmf.cn.dreaming.R;
import dream.api.dmf.cn.dreaming.utils.OnRvItemClickListener;

/**
 * Created by jason on 2019/5/24.
 */
public class ToolOftenAdapter extends RecyclerView.Adapter<ToolOftenAdapter.MineFgListViewHolder>{
    private Context mContext;
    private List<String> itemListText;
    private List<Integer> iconIds;

    private OnRvItemClickListener itemClickedListener;

    public void setItemClickedListener(OnRvItemClickListener itemClickedListener) {
        this.itemClickedListener = itemClickedListener;
    }

    public ToolOftenAdapter(Context context, List<String> itemList, List<Integer> iconList) {
        this.mContext = context;
        this.itemListText = itemList;
        this.iconIds = iconList;
    }

    @NonNull
    @Override
    public MineFgListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.fragment_tools_often, viewGroup, false);
        MineFgListViewHolder viewHolder = new MineFgListViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MineFgListViewHolder mineFgListViewHolder, final int position) {
        mineFgListViewHolder.tool_text_often.setText(itemListText.get(position));
        mineFgListViewHolder.tool_image_often.setImageResource(iconIds.get(position));
        if (null != itemClickedListener){
            mineFgListViewHolder.tool_text_often.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickedListener.OnItemClicked(position,itemListText.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemListText.size();
    }

    class MineFgListViewHolder extends RecyclerView.ViewHolder{
        ImageView tool_image_often;
        TextView tool_text_often;
        public MineFgListViewHolder(@NonNull View itemView) {
            super(itemView);
            tool_image_often = itemView.findViewById(R.id.tool_image_often);
            tool_text_often = itemView.findViewById(R.id.tool_text_often);
        }
    }
}

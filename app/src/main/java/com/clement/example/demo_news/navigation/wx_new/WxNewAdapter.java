package com.clement.example.demo_news.navigation.wx_new;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.clement.example.demo_news.R;
import com.clement.example.demo_news.entity.WxNew;
import com.clement.example.demo_news.recycleview.FooterViewHolder;
import com.clement.example.demo_news.recycleview.OnItemClickListener;
import com.clement.example.demo_news.recycleview.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 16/11/10.
 */

public class WxNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<WxNew> list ;
    private Context context;
    //item点击事件
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener){
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public WxNewAdapter(Context context, List<WxNew> list){
        this.context = context;
        if(list==null){
            list = new ArrayList<>();
        }
        this.list = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder ;
        //根据viewType返回不同的view
        switch (viewType){
            default:
            case TYPE_NORMAL:
                holder = new WxNewViewHolder(LayoutInflater.from(
                        context).inflate(R.layout.item_wx_new,parent,false));
                break;
            case TYPE_FOOTER:
                holder = new FooterViewHolder(LayoutInflater.from(
                        context).inflate(R.layout.rcv_load_more_layout,parent,false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        //如果是底部加载更多的布局
        if(holder instanceof FooterViewHolder){
            ((FooterViewHolder)holder).progressBar.setVisibility(View.VISIBLE);
            return ;
        }
        //展示数据
        WxNew data = null;
        if(list!=null&&!list.isEmpty()){
            data = list.get(position);
        }
        if(data!=null){
            ((WxNewViewHolder)holder).tvTitle.setText(data.getTitle());
            ((WxNewViewHolder)holder).tvTime.setText(data.getTime());
            Glide.with(context)
                    .load(data.getPicUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop()
                    .into(((WxNewViewHolder)holder).ivPreview);
        }
        //如果设置了点击事件
        if(onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView,pos);
                }
            });
        }
        //如果设置了长按事件
        if(onItemLongClickListener!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onItemLongClickListener.onItemLongClick(holder.itemView,pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public final static int TYPE_FOOTER = 2;//底部--往往是loading_more
    public final static int TYPE_NORMAL = 1; // 正常的一条新闻
    /**处理底部加载更多
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        WxNew data = list.get(position);
        if(data==null){
            return TYPE_FOOTER;
        }else{
            return TYPE_NORMAL;
        }
    }

    public List<WxNew> getList() {
        return list;
    }

    public void setList(List<WxNew> list) {
        this.list = list;
    }

    /**
     * ViewHolder
     */
    class WxNewViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivPreview;
        private TextView tvTitle;
        private TextView tvTime;

        public WxNewViewHolder(View view){
            super(view);
            ivPreview = (ImageView) view.findViewById(R.id.iv_preview);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvTime = (TextView) view.findViewById(R.id.tv_time);
        }
    }

}

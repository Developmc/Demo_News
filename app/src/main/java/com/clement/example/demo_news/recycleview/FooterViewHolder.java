package com.clement.example.demo_news.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.clement.example.demo_news.R;

/**底部加载更多
 * Created by clement on 16/11/10.
 */

public class FooterViewHolder extends RecyclerView.ViewHolder{
    public ProgressBar progressBar;
    public FooterViewHolder(View itemView) {
        super(itemView);
        progressBar = (ProgressBar)itemView.findViewById(R.id.rcv_load_more);
    }
}

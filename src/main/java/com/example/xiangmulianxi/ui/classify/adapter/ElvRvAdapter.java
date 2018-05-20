package com.example.xiangmulianxi.ui.classify.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.xiangmulianxi.R;
import com.example.xiangmulianxi.bean.ProductCatagoryBean;
import com.example.xiangmulianxi.inter.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ElvRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ProductCatagoryBean.DataBean.ListBean> listBeans;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public ElvRvAdapter(Context context, List<ProductCatagoryBean.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
        inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rvclass_item, parent, false);
        ElvRvAdapterViewHolder elvRvAdapterViewHolder = new ElvRvAdapterViewHolder(view);
        return elvRvAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ElvRvAdapterViewHolder elvRvAdapterViewHolder = (ElvRvAdapterViewHolder) holder;
        ProductCatagoryBean.DataBean.ListBean listBean = listBeans.get(position);
        elvRvAdapterViewHolder.iv.setImageURI(listBean.getIcon());
        elvRvAdapterViewHolder.tv.setText(listBean.getName());

        //给条目设置点击事件
        elvRvAdapterViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    class ElvRvAdapterViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView iv;
        private final TextView tv;
        private final LinearLayout ll;

        public ElvRvAdapterViewHolder(View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}

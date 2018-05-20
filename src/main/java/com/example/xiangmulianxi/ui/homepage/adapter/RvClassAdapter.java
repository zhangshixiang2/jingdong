package com.example.xiangmulianxi.ui.homepage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.xiangmulianxi.R;
import com.example.xiangmulianxi.bean.CatagoryBean;
import com.example.xiangmulianxi.inter.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class RvClassAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CatagoryBean.DataBean> list;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public RvClassAdapter(Context context, List<CatagoryBean.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rvclass_item, parent, false);
        ClassViewHoler classViewHoler = new ClassViewHoler(view);
        return classViewHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        //显示数据
        ClassViewHoler classViewHoler = (ClassViewHoler) holder;
        CatagoryBean.DataBean dataBean = list.get(position);
        classViewHoler.iv.setImageURI(dataBean.getIcon());
        classViewHoler.tv.setText(dataBean.getName());

        //给条目设置点击事件
        classViewHoler.ll.setOnClickListener(new View.OnClickListener() {
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
        return list.size();
    }

    class ClassViewHoler extends RecyclerView.ViewHolder {

        private final SimpleDraweeView iv;
        private final TextView tv;
        private final LinearLayout ll;

        public ClassViewHoler(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}

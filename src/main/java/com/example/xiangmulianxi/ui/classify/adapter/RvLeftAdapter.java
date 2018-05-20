package com.example.xiangmulianxi.ui.classify.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.xiangmulianxi.R;
import com.example.xiangmulianxi.bean.CatagoryBean;
import com.example.xiangmulianxi.inter.OnItemClickListener;

import java.util.List;

public class RvLeftAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CatagoryBean.DataBean> list;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public RvLeftAdapter(Context context, List<CatagoryBean.DataBean> list) {
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
        View view = inflater.inflate(R.layout.rvleft_item, parent, false);
        LeftViewHolder leftViewHolder = new LeftViewHolder(view);
        return leftViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        CatagoryBean.DataBean dataBean = list.get(position);
        LeftViewHolder leftViewHolder = (LeftViewHolder) holder;
        leftViewHolder.tv.setText(dataBean.getName());
        //设置字体颜色
        if (dataBean.isChecked()) {
            leftViewHolder.tv.setTextColor(Color.RED);
            leftViewHolder.tv.setBackgroundColor(Color.GRAY);
        } else {
            leftViewHolder.tv.setTextColor(Color.BLACK);
            leftViewHolder.tv.setBackgroundColor(Color.WHITE);
        }


        leftViewHolder.tv.setOnClickListener(new View.OnClickListener() {
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

    class LeftViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;

        public LeftViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    /**
     * 选中后，改变字体颜色和背景色
     *
     * @param position
     * @param bool
     */
    public void changeCheck(int position, boolean bool) {
        //先还原一下
        for (int i = 0;i<list.size();i++){
            list.get(i).setChecked(false);
        }
        CatagoryBean.DataBean dataBean = list.get(position);
        dataBean.setChecked(bool);
        //刷新界面
        notifyDataSetChanged();
    }
}

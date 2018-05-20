package com.example.xiangmulianxi.ui.classify.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import com.example.xiangmulianxi.R;
import com.example.xiangmulianxi.bean.ProductCatagoryBean;
import com.example.xiangmulianxi.inter.OnItemClickListener;
import com.example.xiangmulianxi.ui.classify.ListActivity;

import java.util.List;

public class ElvAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> groupList;
    private List<List<ProductCatagoryBean.DataBean.ListBean>> childList;
    private LayoutInflater inflater;

    public ElvAdapter(Context context, List<String> groupList, List<List<ProductCatagoryBean.DataBean.ListBean>>
            childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;//因为二级列表是一个RecylerView，所以返回1即可
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = inflater.inflate(R.layout.rvleft_item, null);
            groupViewHolder.tv = convertView.findViewById(R.id.tv);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        //显示数据
        groupViewHolder.tv.setText(groupList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup
            parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = inflater.inflate(R.layout.elv_rv, null);
            childViewHolder.rv = convertView.findViewById(R.id.rv);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        //显示数据
        final List<ProductCatagoryBean.DataBean.ListBean> listBeans = childList.get(groupPosition);
        //设置布局管理器
        childViewHolder.rv.setLayoutManager(new GridLayoutManager(context, 3));
        //设置适配器
        ElvRvAdapter elvRvAdapter = new ElvRvAdapter(context, listBeans);
        childViewHolder.rv.setAdapter(elvRvAdapter);

        elvRvAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //点击跳转到列表页面
                Intent intent = new Intent(context, ListActivity.class);
                int pscid = listBeans.get(position).getPscid();
                intent.putExtra("pscid", pscid);
                context.startActivity(intent);
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        TextView tv;
    }

    class ChildViewHolder {
        RecyclerView rv;
    }
}

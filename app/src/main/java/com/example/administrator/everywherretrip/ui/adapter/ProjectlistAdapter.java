package com.example.administrator.everywherretrip.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.bean.ProjectlistBean;

import java.util.ArrayList;

public class ProjectlistAdapter extends RecyclerView.Adapter<ProjectlistAdapter.ViewHolder> {
    private ArrayList<ProjectlistBean.ResultBean.BundlesBean> list=new ArrayList<>();
    private Context context;

    public ProjectlistAdapter(ArrayList<ProjectlistBean.ResultBean.BundlesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<ProjectlistBean.ResultBean.BundlesBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_projectlist, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final ProjectlistBean.ResultBean.BundlesBean bundlesBean = list.get(i);
        Glide.with(context).load(bundlesBean.getCardURL()).into(viewHolder.home);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClick!=null){
                    onClick.onSend(i,bundlesBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView home;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            home=itemView.findViewById(R.id.home);
        }
    }
    private  onClick onClick;

    public void setOnClick(ProjectlistAdapter.onClick onClick) {
        this.onClick = onClick;
    }

    public  interface  onClick{
        void onSend(int position,ProjectlistBean.ResultBean.BundlesBean bundlesBean);
    }
}

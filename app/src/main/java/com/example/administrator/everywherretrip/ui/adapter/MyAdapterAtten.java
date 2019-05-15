package com.example.administrator.everywherretrip.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.bean.AttenList;
import com.example.administrator.everywherretrip.bean.StayBean;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterAtten extends RecyclerView.Adapter<MyAdapterAtten.ViewHolder> {
    private ArrayList<AttenList.ResultBean.BanmiBean> mList;
    private Context context;
    private boolean mBoolean;

    public MyAdapterAtten(Context context, boolean aBoolean) {
        this.context = context;
        this.mBoolean = aBoolean;
    }

    public void addList(List<AttenList.ResultBean.BanmiBean> lists) {
        mList.clear();
        mList.addAll(lists);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_stay, null);
        return new MyAdapterAtten.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        AttenList.ResultBean.BanmiBean bean = mList.get(i);
        viewHolder.mTv_name_stay.setText(bean.getName());
        viewHolder.tv_person.setText(bean.getId() + "人关注");
        viewHolder.mTv_region.setText(bean.getLocation());
        viewHolder.mTv_status.setText(bean.getOccupation());
        RequestOptions options = RequestOptions.placeholderOf(R.mipmap.zhanweitu_touxiang);
        Glide.with(context).load(bean.getPhoto()).apply(options).into(viewHolder.img_stay);

        if (mBoolean) {
            viewHolder.mImg.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mImg.setVisibility(View.INVISIBLE);
        }
        boolean isFollowed = bean.isIsFollowed();
        if (isFollowed) {
            Glide.with(context).load(R.mipmap.follow).into(viewHolder.mImg);
        } else {
            Glide.with(context).load(R.mipmap.follow_unselected).into(viewHolder.mImg);
        }
        viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemOnFollow!=null){
                    itemOnFollow.onSend(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_stay;
        TextView mTv_name_stay;
        TextView tv_person;
        TextView mTv_region;
        TextView mTv_status;
        ImageView mImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_stay=itemView.findViewById(R.id.img_stay);
            mTv_name_stay=itemView.findViewById(R.id.mTv_name_stay);
            tv_person=itemView.findViewById(R.id.tv_person);
            mTv_region=itemView.findViewById(R.id.mTv_region);
            mTv_status=itemView.findViewById(R.id.mTv_status);
            mImg=itemView.findViewById(R.id.mImg);
        }
    }
    private MyAdapterStay.itemOnFollow itemOnFollow;

    public void setItemOnFollow(MyAdapterStay.itemOnFollow itemOnFollow) {
        this.itemOnFollow = itemOnFollow;
    }

    public  interface  itemOnFollow{
        void onSend(int positionn);
    }
}

package com.example.administrator.everywherretrip.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.bean.WithPath;

import java.util.ArrayList;


public class LinePathAdapter extends RecyclerView.Adapter<LinePathAdapter.ViewHolder>{
    private ArrayList<WithPath.ResultBean.RoutesBean> mList;
    private Context context;

    public LinePathAdapter(ArrayList<WithPath.ResultBean.RoutesBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    public void setmList(ArrayList<WithPath.ResultBean.RoutesBean> mList) {
        this.mList = mList;
    }

    /*   public void addData(ArrayList<WithPath.ResultBean.RoutesBean> data) {
            if (data != null) {
                mList.clear();
                mList.addAll(data);
            }
            notifyDataSetChanged();
        }*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_with, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final WithPath.ResultBean.RoutesBean bean = mList.get(i);
        viewHolder.mTv_title.setText(bean.getTitle());
        viewHolder.mTv_area.setText(bean.getCity());
        viewHolder.mTv_str.setText(bean.getIntro());
        viewHolder.mTv_quantity.setText(bean.getPriceInCents() + "人关注");
        viewHolder.mBtn_price.setText("¥" + bean.getPrice());
        Glide.with(context).load(bean.getCardURL()).into(viewHolder.mImg_back);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg_back;
        TextView mTv_title;
        TextView mTv_area;
        Button mBtn_price;
        TextView mTv_str;
        TextView mTv_quantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv_title = itemView.findViewById(R.id.mTv_title);
            mTv_area = itemView.findViewById(R.id.mTv_area);
            mBtn_price = itemView.findViewById(R.id.mBtn_price);
            mTv_str = itemView.findViewById(R.id.mTv_str);
            mTv_quantity = itemView.findViewById(R.id.mTv_quantity);
            mImg_back = itemView.findViewById(R.id.img_back);
        }
    }
}

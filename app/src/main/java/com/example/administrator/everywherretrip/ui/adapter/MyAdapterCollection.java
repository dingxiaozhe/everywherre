package com.example.administrator.everywherretrip.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.bean.CollectionList;

import java.util.ArrayList;


public class MyAdapterCollection extends RecyclerView.Adapter<MyAdapterCollection.ViewHolder>{
    private Context context;
    private ArrayList<CollectionList.ResultBean.CollectedRoutesBean> mList = new ArrayList<>();

    public MyAdapterCollection(Context context) {
        this.context = context;
    }

    public void addList(ArrayList<CollectionList.ResultBean.CollectedRoutesBean> list) {
        if (list != null) {
            mList.clear();
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_collection, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CollectionList.ResultBean.CollectedRoutesBean bean = mList.get(i);
        viewHolder.mTv_name_stay.setText(bean.getTitle());
        viewHolder.tv_person.setText(bean.getIntro());
        RequestOptions options = RequestOptions.placeholderOf(R.mipmap.zhanweitu_touxiang);
        Glide.with(context).load(bean.getCardURL()).apply(options).into(viewHolder.img_stay);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_stay;
        TextView mTv_name_stay;
        TextView tv_person;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_stay = itemView.findViewById(R.id.img_stay);
            mTv_name_stay = itemView.findViewById(R.id.mTv_name_stay);
            tv_person = itemView.findViewById(R.id.tv_person);
        }
    }
}

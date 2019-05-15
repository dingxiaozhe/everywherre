package com.example.administrator.everywherretrip.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.bean.AppraiseBean;
import com.example.administrator.everywherretrip.widget.GlideApp;

import java.util.ArrayList;


public class AppraiseAdapter extends RecyclerView.Adapter<AppraiseAdapter.ViewHolder> {
    private ArrayList<AppraiseBean.ResultBean.ReviewsBean> list;
    private Context context;


    public AppraiseAdapter(ArrayList<AppraiseBean.ResultBean.ReviewsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<AppraiseBean.ResultBean.ReviewsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_appraise, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AppraiseBean.ResultBean.ReviewsBean reviewsBean = list.get(i);
        viewHolder.name.setText(reviewsBean.getUserName());
        viewHolder.time.setText(reviewsBean.getCreatedAt());
        viewHolder.count.setText(reviewsBean.getContent());
        //Glide 圆形图片
        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                .skipMemoryCache(true);//不做内存缓存
        GlideApp.with(context).load(reviewsBean.getUserPhoto()).apply(mRequestOptions).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        private TextView time;
        private TextView count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.time);
            count=itemView.findViewById(R.id.count);

        }
    }
}

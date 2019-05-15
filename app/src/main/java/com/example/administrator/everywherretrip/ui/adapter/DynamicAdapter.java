package com.example.administrator.everywherretrip.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.bean.DynamicBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.ViewHolder> {



    private Context context;
    private ArrayList<DynamicBean.ResultBean.ActivitiesBean> list;
    private static final String TAG = "DynamicAdapter";


    public DynamicAdapter(Context context, ArrayList<DynamicBean.ResultBean.ActivitiesBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(ArrayList<DynamicBean.ResultBean.ActivitiesBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_dynamic, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DynamicBean.ResultBean.ActivitiesBean bean = list.get(i);
        if (bean != null) {
            viewHolder.just.setText(bean.getDate());
            if (!TextUtils.isEmpty(bean.getAudioURL())) {
                viewHolder.just.setText(bean.getDate());
                if (!TextUtils.isEmpty(bean.getAudioURL())) {
                    viewHolder.imgViode.setVisibility(GridView.VISIBLE);
                    viewHolder.imgDynamic.setVisibility(GridView.GONE);
                    viewHolder.update.setText(context.getResources().getString(R.string.update));
                    viewHolder.voice.setVisibility(GridView.VISIBLE);
                    viewHolder.cardDynmaic.setVisibility(GridView.GONE);

                    viewHolder.thumb.setText(bean.getLikeCount() + "");
                    viewHolder.tvTop2.setText(bean.getReplyCount() + "");
                    viewHolder.tvAuth.setText(bean.getAudioLength() + "");
                } else {
                    viewHolder.imgDynamic.setVisibility(GridView.VISIBLE);
                    viewHolder.imgViode.setVisibility(GridView.GONE);
                    viewHolder.update.setText(context.getResources().getString(R.string.send_state));
                    viewHolder.voice.setVisibility(GridView.GONE);
                    viewHolder.cardDynmaic.setVisibility(GridView.VISIBLE);
                    if (bean.getImages().size() > 0) {
                        Glide.with(context).load(bean.getImages().get(0)).into(viewHolder.img);
                    }
                    viewHolder.tvCount.setText(bean.getContent());
                    viewHolder.thumb2.setText(bean.getLikeCount() + "");
                    viewHolder.tvTop.setText(bean.getReplyCount() + "");

                }
            }
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dian)
        ImageView dian;
        @BindView(R.id.img_viode)
        ImageView imgViode;
        @BindView(R.id.img_dynamic)
        ImageView imgDynamic;
        @BindView(R.id.just)
        TextView just;
        @BindView(R.id.all_state)
        TextView allState;
        @BindView(R.id.update)
        TextView update;
        @BindView(R.id.line_button)
        ImageView lineButton;
        @BindView(R.id.tv_auth)
        TextView tvAuth;
        @BindView(R.id.img_praise)
        ImageView imgPraise;
        @BindView(R.id.thumb)
        TextView thumb;
        @BindView(R.id.img_comment)
        ImageView imgComment;
        @BindView(R.id.tv_top2)
        TextView tvTop2;
        @BindView(R.id.voice)
        CardView voice;
        @BindView(R.id.headline)
        TextView headline;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.img_praise2)
        ImageView imgPraise2;
        @BindView(R.id.thumb2)
        TextView thumb2;
        @BindView(R.id.img_comment2)
        ImageView imgComment2;
        @BindView(R.id.tv_top)
        TextView tvTop;
        @BindView(R.id.card_dynmaic)
        CardView cardDynmaic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}

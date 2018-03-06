package com.nj.dota2info.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nj.dota2info.R;
import com.nj.dota2info.gson.Hero;

import java.util.List;

/**
 * Created by Administrator on 2018-03-06.
 */

public class InfoRecycleAdapter extends RecyclerView.Adapter<InfoRecycleAdapter.ViewHolder> {
    private List<Hero> mHeroList;
    private Context mContext;

    public InfoRecycleAdapter(List<Hero> heroList) {
        mHeroList = heroList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView mCardView;
        private final ImageView mIvPic;
        private final TextView mTvName;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView;
            mIvPic = itemView.findViewById(R.id.iv_pic);
            mTvName = itemView.findViewById(R.id.tv_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.info_recycle_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hero hero = mHeroList.get(position);
        holder.mTvName.setText(hero.name);
        Glide.with(mContext).load(hero.img).into(holder.mIvPic);
    }

    @Override
    public int getItemCount() {
        return mHeroList.size();
    }
}

package com.example.dell.fder.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.fder.ObjectClass.Moments;
import com.example.dell.fder.MomentsActivity;
import com.example.dell.fder.R;

import java.util.List;

public class MomentsAdapter extends RecyclerView.Adapter<MomentsAdapter.ViewHolder>{

    private static final String TAG = "MomentsAdapter";

    private Context mContext;

    private List<Moments> mMomentsList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView momentsImage;
        TextView momentsName;
        TextView  content;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            momentsImage = (ImageView) view.findViewById(R.id.moments_image);
            momentsName = (TextView) view.findViewById(R.id.moments_name);
            content=(TextView)view.findViewById(R.id.content_of_squareItem);
        }
    }

    public MomentsAdapter(List<Moments> momentsList) {
        mMomentsList = momentsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.moments, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Moments moments = mMomentsList.get(position);
                Intent intent = new Intent(mContext, MomentsActivity.class);
                intent.putExtra(MomentsActivity.MOMENT_NAME, moments.getName());
                intent.putExtra(MomentsActivity.MOMENT_IMAGE_ID, moments.getImageId());
                intent.putExtra(MomentsActivity.CONTENT_OF_MOMENT,moments.getContentOfMoments());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Moments moments = mMomentsList.get(position);
        holder.momentsName.setText(moments.getName());
        holder.content.setText(moments.getContentOfMoments());
        Glide.with(mContext).load(moments.getImageId()).into(holder.momentsImage);
    }

    @Override
    public int getItemCount() {
        return mMomentsList.size();
    }

}

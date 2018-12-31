package com.example.dell.fder.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.fder.ObjectClass.Friend;
import com.example.dell.fder.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder>{

    private static final String TAG = "FriendAdapter";

    private Context mContext;

    private List<Friend> mFriendList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        CircleImageView headerImage;
        TextView name;
        TextView  signature;


        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            headerImage = (CircleImageView) view.findViewById(R.id.header_of_friend);
            name = (TextView) view.findViewById(R.id.name_of_friend);
            signature=(TextView)view.findViewById(R.id.signature_of_friend);

        }
    }

    public FriendAdapter(List<Friend> friendList) {
        mFriendList = friendList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.friend_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //这一部分是卡片项目的点击事件，点击后展开，还没写对话界面
//                int position = holder.getAdapterPosition();
//                Friend friend = mFriendList.get(position);
//                Intent intent = new Intent(mContext, FriendActivity.class);
//                intent.putExtra(FriendActivity.FRUIT_NAME, friend.getName());
//                intent.putExtra(FriendActivity.FRUIT_IMAGE_ID, friend.getImageId());
//                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Friend friend = mFriendList.get(position);
        holder.name.setText(friend.getNameOfFriend());
        holder.signature.setText(friend.getSignature());
        Glide.with(mContext).load(friend.getHeadId()).into(holder.headerImage);
    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }

}

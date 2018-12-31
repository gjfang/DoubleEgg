package com.example.dell.fder.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.fder.ObjectClass.Message;
import com.example.dell.fder.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    private static final String TAG = "MessageAdapter";

    private Context mContext;

    private List<Message> mMessageList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        CircleImageView headerImage;
        TextView authorName;
        TextView  contentOfMessage;
        TextView dateOfMessage;
        

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            headerImage = (CircleImageView) view.findViewById(R.id.header_of_message);
            authorName = (TextView) view.findViewById(R.id.author_of_message);
            contentOfMessage=(TextView)view.findViewById(R.id.content_of_message);
            dateOfMessage=(TextView)view.findViewById(R.id.time_of_message);
        }
    }

    public MessageAdapter(List<Message> messageList) {
        mMessageList = messageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.message_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                //这一部分是卡片项目的点击事件，点击后展开，还没写对话界面
//                int position = holder.getAdapterPosition();
//                Message message = mMessageList.get(position);
//                Intent intent = new Intent(mContext, MessageActivity.class);
//                intent.putExtra(MessageActivity.FRUIT_NAME, message.getName());
//                intent.putExtra(MessageActivity.FRUIT_IMAGE_ID, message.getImageId());
//                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = mMessageList.get(position);
        holder.authorName.setText(message.getAuthorOfMessage());
        holder.contentOfMessage.setText(message.getContentOfMessage());
        holder.dateOfMessage.setText(message.getDateOfMessage());
        Glide.with(mContext).load(message.getHeaderOfMessageId()).into(holder.headerImage);
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

}

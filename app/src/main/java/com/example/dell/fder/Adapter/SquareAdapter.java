package com.example.dell.fder.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.fder.ObjectClass.Friend;
import com.example.dell.fder.R;
import com.example.dell.fder.ObjectClass.Square;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SquareAdapter extends RecyclerView.Adapter<SquareAdapter.ViewHolder>{

    private static final String TAG = "SquareAdapter";

    private Context mContext;

    private List<Square> mSquareList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        CircleImageView headerImage;
        ImageView  pictureOfSquareItem;
        TextView authorName;
        TextView  contentOfSquare;
        TextView dateOfSquare;
        Friend author_of_square;


        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            headerImage = (CircleImageView) view.findViewById(R.id.header_of_squareItem);
            pictureOfSquareItem=(ImageView)view.findViewById(R.id.picture_of_squareItem);
            authorName = (TextView) view.findViewById(R.id.author_of_squareItem);
            contentOfSquare=(TextView)view.findViewById(R.id.content_of_squareItem);
            dateOfSquare=(TextView)view.findViewById(R.id.date_of_squareItem);
        }
    }

    public SquareAdapter(List<Square> squareList) {
        mSquareList = squareList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.squre_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //这一部分是卡片项目的点击事件，点击后展开，还没写square具体界面
//                int position = holder.getAdapterPosition();
//                Square square = mSquareList.get(position);
//                Intent intent = new Intent(mContext, SquareActivity.class);
//                intent.putExtra(SquareActivity.FRUIT_NAME, square.getName());
//                intent.putExtra(SquareActivity.FRUIT_IMAGE_ID, square.getImageId());
//                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Square square = mSquareList.get(position);
        holder.authorName.setText(square.getAuthorOfSquare().getNameOfFriend());
        holder.contentOfSquare.setText(square.getContentOfSquare());
        holder.dateOfSquare.setText(square.getDate());
        Glide.with(mContext).load(square.getAuthorOfSquare().getHeadId()).into(holder.headerImage);
        Glide.with(mContext).load(square.getIdOfPicture()).into(holder.pictureOfSquareItem);
    }

    @Override
    public int getItemCount() {
        return mSquareList.size();
    }

}

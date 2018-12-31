package com.example.dell.fder.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.search.core.PoiInfo;
import com.example.dell.fder.PositionActivity;
import com.example.dell.fder.R;

import java.util.List;


//2018-12-31 0:09 根据点击listview里的事件对position activity进行UI 更新，采用接口回调方法，然后根据textview 的值再传回数据给上一个活动
/**
 * 显示位置列表的ListView适配器
 */
public class PlaceAdapter extends BaseAdapter {

    private Context mContext;
    private List<PoiInfo> mPoiInfoList;
    private LayoutInflater mInflater;
    private onClickMyTextView onClickMyTextView;

    public interface onClickMyTextView{
        public void myTextViewClick(int id);
    }
    public void setOnClickMyTextView(onClickMyTextView onClickMyTextView) {
        this.onClickMyTextView = onClickMyTextView;
    }


    //构造方法用来接受从activity传过来的信息
    public PlaceAdapter(Context context, List<PoiInfo> poiInfoList) {
        mContext = context;
        mPoiInfoList = poiInfoList;
        mInflater = LayoutInflater.from(mContext);
    }

    public void replaceAll(List<PoiInfo> poiInfoList) {
        mPoiInfoList = poiInfoList;
        notifyDataSetChanged();
    }

    public void addAll(List<PoiInfo> poiInfoList) {
        mPoiInfoList.addAll(poiInfoList);
        notifyDataSetChanged();
    }

    public void clear() {
        mPoiInfoList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mPoiInfoList == null ? 0 : mPoiInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPoiInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final PoiInfo poiInfo = mPoiInfoList.get(position);
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_location_address, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvLocationTitle.setText(poiInfo.name);
        viewHolder.tvLocationAddress.setText(poiInfo.address);

        if (onClickMyTextView!=null) {
            viewHolder.tvLocationTitle.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    onClickMyTextView.myTextViewClick(position);

                }
            });
        }


        return convertView;
    }

    static class ViewHolder {

        TextView tvLocationTitle, tvLocationAddress;

        public ViewHolder(View view) {
            tvLocationTitle = view.findViewById(R.id.item_title);
            tvLocationAddress = view.findViewById(R.id.item_address);
        }
    }

}

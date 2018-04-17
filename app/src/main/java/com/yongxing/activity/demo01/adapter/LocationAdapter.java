package com.yongxing.activity.demo01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yongxing.activity.demo01.R;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018-01-18.
 */

public class LocationAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Map<String, Object>> mData;

    public LocationAdapter(Context context, List<Map<String, Object>> mdata) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mdata;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {

            holder = new ViewHolder();

            convertView = mInflater.inflate(R.layout.item_home_layout, null);
            holder.title = (TextView) convertView.findViewById(R.id.push_name);
            holder.info = (TextView) convertView.findViewById(R.id.push_content);
            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }


        holder.title.setText((String) mData.get(position).get("title"));
        holder.info.setText((String) mData.get(position).get("info"));


        return convertView;
    }

    public final class ViewHolder {
        public TextView title;
        public TextView info;
    }
}



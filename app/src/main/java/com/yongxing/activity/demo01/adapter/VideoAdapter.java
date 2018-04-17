package com.yongxing.activity.demo01.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yongxing.activity.demo01.R;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018-01-18.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    private List<Map<String, Object>> mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    private MyItemClickListener mItemClickListener;
    private List<String> groupkey;
    private OnRecyclerViewLongItemClickListener mLongListener;

    public VideoAdapter(Context context, List<Map<String, Object>> datas, List<String> groupkey) {
        this.mContext = context;
        this.mDatas = datas;
        this.groupkey = groupkey;
        inflater = LayoutInflater.from(mContext);
    }

    private static final int NORMAL_TYPE = 0;
    private static final int CHECK_TYPE = 1;

    @Override
    public int getItemViewType(int position) {
        if (groupkey.contains(mDatas.get(position).get("group"))) {

            return NORMAL_TYPE;
        }
        return CHECK_TYPE;
    }

    @Override
    public int getItemCount() {

        return mDatas.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv.setText(mDatas.get(position).get("title").toString());
        if (mDatas.get(position).get("group").equals("")){
            holder.setVisibility(true);
        }else{
            holder.setVisibility((Boolean) mDatas.get(position).get("show"));
        }

    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == NORMAL_TYPE) {
            view = inflater.inflate(R.layout.item_home_layout, parent, false);
        } else {
            view = inflater.inflate(R.layout.item_listview_title, parent, false);

        }
        MyViewHolder holder = new MyViewHolder(view, mItemClickListener,mLongListener);

        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

        TextView tv;
        ImageView videoChange;
        private MyItemClickListener mListener;
        private OnRecyclerViewLongItemClickListener myLongListener;

        public MyViewHolder(View itemView, MyItemClickListener myItemClickListener,OnRecyclerViewLongItemClickListener myLongListener) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.push_name);
            videoChange = (ImageView) itemView.findViewById(R.id.video_onchuange);

            //将全局的监听赋值给接口
            this.mListener = myItemClickListener;
            this.myLongListener=myLongListener;
            itemView.setOnLongClickListener((View.OnLongClickListener) this);
            itemView.setOnClickListener(this);
        }

        public void setVisibility(boolean isVisible) {
            RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            if (isVisible) {
                param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                param.width = LinearLayout.LayoutParams.MATCH_PARENT;
                itemView.setVisibility(View.VISIBLE);
            } else {
                itemView.setVisibility(View.GONE);
                param.height = 0;
                param.width = 0;
            }
            itemView.setLayoutParams(param);
        }

        /**
         * 实现OnClickListener接口重写的方法
         *
         * @param v
         */
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }

        }


        @Override
        public boolean onLongClick(View v) {
            if(myLongListener!=null){
                myLongListener.onLongItemClick(v,getPosition());
            }
            return false;
        }
    }


    /**
     * 创建一个回调接口
     */
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 在activity里面adapter就是调用的这个方法,将点击事件监听传递过来,并赋值给全局的监听
     *
     * @param myItemClickListener
     */
    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }

    /**
     * 创建一个回调接口
     */
    public interface OnRecyclerViewLongItemClickListener {
        void onLongItemClick(View view, int position);
    }

    /**
     * 在activity里面adapter就是调用的这个方法,将点击事件监听传递过来,并赋值给全局的监听
     *
     * @param myLongListener
     */
    public void setOnLongItemClickListener(OnRecyclerViewLongItemClickListener myLongListener) {
        this.mLongListener = myLongListener;
    }
}



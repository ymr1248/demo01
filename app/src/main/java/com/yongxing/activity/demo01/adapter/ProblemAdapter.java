package com.yongxing.activity.demo01.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.MyViewHolder> {
    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    private MyItemClickListener mItemClickListener;
    public ProblemAdapter(Context context, List<String> datas){
        this. mContext=context;
        this. mDatas=datas;
        inflater=LayoutInflater. from(mContext);
    }

    @Override
    public int getItemCount() {

        return mDatas.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv.setText( mDatas.get(position));
        Log.d("YMR", "onBindViewHolder: "+position);

      /*  if(isVisible){
            holder.setVisibility(true);
        }else{
            holder.setVisibility(false);
        }*/
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout. item_problem_layout,parent, false);
        Log.d("YMR", "onCreateViewHolder: ");
        MyViewHolder holder= new MyViewHolder(view, mItemClickListener );
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv;
        LinearLayout problem_show;
        ImageView problem_image;
        private MyItemClickListener mListener;
        public MyViewHolder(View itemView,MyItemClickListener myItemClickListener) {
            super(itemView);
            tv=(TextView) itemView.findViewById(R.id. problem_name);
            problem_image=(ImageView) itemView.findViewById(R.id.problem_image);
            problem_show=(LinearLayout) itemView.findViewById(R.id.problem_show);
            this.mListener = myItemClickListener;
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
        @Override
        public void onClick(View v) {
            if (mListener != null) {

                mListener.onItemClick(v, getPosition());
            }
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
        this.mItemClickListener =  myItemClickListener;
    }
}



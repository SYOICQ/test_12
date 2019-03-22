package com.example.a13703.test_12;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 13703 on 2019/3/21.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<String> mData;
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.tv_item);
            textView.setOnClickListener(this);
        }

        //通过接口回调来实现RecyclerView的点击事件
        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(null, v, getPosition(), R.id.tv_item);
            }
        }
    }

    public RecyclerAdapter(List<String> data){
        mData = data;
    }

    public interface OnItemClickListener{
        void onItemClick(View view , int position);
    }
    public  AdapterView.OnItemClickListener itemClickListener;
    public void  setOnItemClickListener(AdapterView.OnItemClickListener listener){
        itemClickListener = listener;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.textView.setText(mData.get(position) + position);
    }

    @Override
    public int getItemCount() {
      return mData.size();
    }


}

package com.redrock.my.smusic.PlayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.redrock.my.smusic.R;

import java.util.List;

/**
 * Created by ASUS on 2016/5/15.
 */
public class PAdapter extends RecyclerView.Adapter<PAdapter.MyViewHolder>{

    private List<PlayList> mDatas;
    private LayoutInflater layoutInflater;
    private Context mContex;

    PAdapter(Context context,List<PlayList> datas){
        this.mDatas = datas;
        this.mContex = context;
        layoutInflater = LayoutInflater.from(context);
    }

   //定义一个接口，传入两个点击方法
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private OnItemClickListener onItemClickListener;//定义

    //获取方法类
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.play_list_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        PlayList playList = mDatas.get(position);
        holder.textView1.setText(playList.getName());
        holder.textView2.setText(playList.getAuthor());

        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView,layoutPos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView,layoutPos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView1;
        TextView textView2;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.play_list_name);
            textView2 = (TextView) itemView.findViewById(R.id.play_list_author);
        }
    }

    public void del(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
}

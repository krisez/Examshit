package com.redrock.my.smusic.SearchS;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrock.my.smusic.R;

import java.util.List;

/**
 * Created by ASUS on 2016/5/15.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder>{
    private List<Search> mDatas;
    private LayoutInflater layoutInflater;
    private Context mContex;

    public SearchAdapter(Context mContex,List<Search> mDatas) {
        this.mDatas = mDatas;
        this.mContex = mContex;
        layoutInflater = LayoutInflater.from(mContex);
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
        View view = layoutInflater.inflate(R.layout.bangdan_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,int position) {
        final Search search = mDatas.get(position);
        holder.imageView.setImageBitmap(search.getSmallAlbum());
        holder.tv1.setText(search.getSongName());
        holder.tv2.setText(search.getSongAuthor());

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
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv1;
        TextView tv2;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.song_item_album);
            tv1 = (TextView) itemView.findViewById(R.id.song_item_name);
            tv2 = (TextView) itemView.findViewById(R.id.song_item_author);
        }
    }
}

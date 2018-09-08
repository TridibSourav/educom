package com.example.tspc.educom.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tspc.educom.Model.Item;
import com.example.tspc.educom.R;

import java.util.List;

public class videoListAdapter extends RecyclerView.Adapter<videoListAdapter.ViewHolder> {

    Context mContext;
    List<Item> videoList;

    public videoListAdapter(Context mContext, List<Item> videoList) {
        this.mContext = mContext;
        this.videoList = videoList;
    }


    @NonNull
    @Override
    public videoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.video_single_item, null);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull videoListAdapter.ViewHolder holder, int position) {
        Item video=videoList.get(position);
        holder.t1.setText(video.getSnippet().getTitle());
        holder.t2.setText(video.getSnippet().getDescription());

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView t1, t2;

        public ViewHolder(View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.title);
            t2=itemView.findViewById(R.id.desc);
        }
    }
}

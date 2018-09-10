package com.example.tspc.educom.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tspc.educom.AllItemLIst;
import com.example.tspc.educom.Model.Item;
import com.example.tspc.educom.PlayerActivity;
import com.example.tspc.educom.R;
import com.squareup.picasso.Picasso;

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

        final String video_id=video.getContentDetails().getVideoId();
        holder.t1.setText(video.getSnippet().getTitle());
        Picasso.get().load(video.getSnippet().getThumbnails().getDefault().getUrl()).into(holder.imageView);
        holder.videoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext, PlayerActivity.class);
                intent.putExtra("video_id",video_id);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView t1;
        public ImageView imageView;
        public CardView videoCard;


        public ViewHolder(View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.title);
            imageView=itemView.findViewById(R.id.thumb);
            videoCard=itemView.findViewById(R.id.videoCardItem);
        }
    }
}

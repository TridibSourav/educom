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

import com.example.tspc.educom.Model.QuizList;
import com.example.tspc.educom.QuizItem;
import com.example.tspc.educom.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuizSetListAdapter extends RecyclerView.Adapter<QuizSetListAdapter.ViewHolder>{

    Context mContext;
    List<QuizList> quizLists;
    String category;

    public QuizSetListAdapter(Context mContext, List<QuizList> quizLists, String category) {
        this.mContext = mContext;
        this.quizLists = quizLists;
        this.category = category;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_set_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final QuizList quizList=quizLists.get(position);
        holder.textView.setText(quizList.getTitle());

        Picasso.get().load(quizList.getIcon()).into(holder.imageView);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext, QuizItem.class);
                intent.putExtra("quiz_set_no",quizList.getList_no());
                intent.putExtra("cat",category);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        CardView item;


        public ViewHolder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.quiz_set_icon);
            textView=itemView.findViewById(R.id.quiz_set_title);
            item=itemView.findViewById(R.id.quiz_set_card);

        }
    }
}

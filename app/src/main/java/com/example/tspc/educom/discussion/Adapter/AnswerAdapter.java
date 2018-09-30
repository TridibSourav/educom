package com.example.tspc.educom.discussion.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tspc.educom.R;
import com.example.tspc.educom.discussion.discussionModel.AnswerModel;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    Context mContext;
    List<AnswerModel> answerList;

    public AnswerAdapter(Context mContext, List<AnswerModel> answerList) {
        this.mContext = mContext;
        this.answerList = answerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.ques_single_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AnswerModel answer=answerList.get(position);
        holder.ans.setText(answer.getAns());
        holder.author.setText(answer.getName());


    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ans, author;
        public ViewHolder(View itemView) {
            super(itemView);
            ans=itemView.findViewById(R.id.titleQuestion);
            author=itemView.findViewById(R.id.authorName);
        }
    }
}

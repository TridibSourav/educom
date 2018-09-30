package com.example.tspc.educom.discussion.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tspc.educom.AnsActivity;
import com.example.tspc.educom.R;
import com.example.tspc.educom.discussion.DiscussionActivity;
import com.example.tspc.educom.discussion.discussionModel.QuestionModel;

import java.util.List;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.ViewHolder>{
    Context mContext;
    List<QuestionModel> questionLists;
    String author_name;

    public DiscussionAdapter(Context mContext, List<QuestionModel> questionLists, String author_name) {
        this.mContext = mContext;
        this.questionLists = questionLists;
        this.author_name = author_name;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.ques_single_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionModel Question= questionLists.get(position);
        final String ID=Question.getQues_id();
        holder.tile.setText(Question.getQuestion());
        holder.author.setText(Question.getAuthor());
        holder.crd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,AnsActivity.class);
                intent.putExtra("questionId",ID);
                intent.putExtra("author",author_name);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return questionLists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tile,author;
        CardView crd;
        public ViewHolder(View itemView) {
            super(itemView);
            tile=itemView.findViewById(R.id.titleQuestion);
            author=itemView.findViewById(R.id.authorName);
            crd=itemView.findViewById(R.id.quesCard);
        }
    }
}

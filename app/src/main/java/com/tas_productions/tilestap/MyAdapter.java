package com.tas_productions.tilestap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Scores> scoresList;

    public MyAdapter(List<Scores> scoresList) {
        this.scoresList = scoresList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rankking_list_layout,parent,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     Scores score = scoresList.get(position);
     holder.userName.setText(score.getUserName());
     holder.userScore.setText(String.valueOf(score.getScore()));
    }

    @Override
    public int getItemCount() {
        return scoresList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userName,userScore;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.nameRankingListID);
            userScore = (TextView) itemView.findViewById(R.id.scoreRankingListID);
        }
    }
}

package com.example.bt_lon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_lon.R;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.question.Question;

import java.util.List;

public class ForgotPasswordAdapter extends RecyclerView.Adapter<ForgotPasswordAdapter.ViewHolder> {
    private List<Question> questionList;
    private Context mContext;

    public ForgotPasswordAdapter(Context context, List<Question> questionList) {
        this.mContext = context;
        this.questionList = questionList;
    }


    @NonNull
    @Override
    public ForgotPasswordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_question, parent, false);
        return new ForgotPasswordAdapter.ViewHolder(listItem);
    }


    @Override
    public void onBindViewHolder(@NonNull ForgotPasswordAdapter.ViewHolder holder, int position) {
        Question question = questionList.get(position);
    }


    @Override
    public int getItemCount() {
        return questionList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

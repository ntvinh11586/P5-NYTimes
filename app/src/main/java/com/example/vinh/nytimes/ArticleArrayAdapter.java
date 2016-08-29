package com.example.vinh.nytimes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Vinh on 8/29/2016.
 */
public class ArticleArrayAdapter extends
        RecyclerView.Adapter<ArticleArrayAdapter.ViewHolder> {

    private ArrayList<Article> mArticles;

    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public ImageView ivImage;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
        }
    }

    public ArticleArrayAdapter(Context context, ArrayList<Article> contacts) {
        mArticles = contacts;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public ArticleArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_article_result, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleArrayAdapter.ViewHolder viewHolder, int position) {

        Article contact = mArticles.get(position);

        TextView textView = viewHolder.tvTitle;
        textView.setText(contact.getHeadline());

        String thumbnail = contact.getThumbNail();

        if (!TextUtils.isEmpty(thumbnail)) {
            Picasso.with(getContext()).load(thumbnail).into(viewHolder.ivImage);
        }
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }
}
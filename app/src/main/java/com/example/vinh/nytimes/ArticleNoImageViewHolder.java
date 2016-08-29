package com.example.vinh.nytimes;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Vinh on 8/30/2016.
 */
public class ArticleNoImageViewHolder extends ArticleArrayAdapter.ViewHolder {

    public TextView tvTitle;
    public TextView tvSnippet;
    public ArticleNoImageViewHolder(View itemView) {
        super(itemView);

        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvSnippet = (TextView) itemView.findViewById(R.id.tvSnippet);
    }

}

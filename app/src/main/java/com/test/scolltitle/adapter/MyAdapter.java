package com.test.scolltitle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sarath on 16/2/17.
 */
public class MyAdapter extends HeaderFooterAdapter<String> {


    public MyAdapter(Context context, int headerResId, int footerResId, List<String> list) {
        super(context, headerResId, footerResId, list);
    }

    public MyAdapter(View headerView, View footerView, List<String> list) {
        super(headerView, footerView, list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, Integer viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(Integer position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(HeaderFooterAdapter<String>.ViewHolder holder, Integer position) {
        if (holder instanceof MyAdapter.ViewHolder) {
            MyAdapter.ViewHolder viewHolder = (MyAdapter.ViewHolder) holder;
            viewHolder.mTextView.setText(getItem(position));
        }
    }

    public class ViewHolder extends HeaderFooterAdapter.ViewHolder {
        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
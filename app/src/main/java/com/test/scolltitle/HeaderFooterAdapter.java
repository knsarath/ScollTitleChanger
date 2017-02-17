package com.test.scolltitle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class HeaderFooterAdapter<T> extends RecyclerView.Adapter<HeaderFooterAdapter.ViewHolder> {
    public static final int NO_HEADER = -1;
    public static final int NO_FOOTER = -1;
    protected static final int TYPE_HEADER = -1;
    protected static final int TYPE_FOOTER = -2;
    protected View mHeaderView;
    protected View mFooterView;
    protected List<T> mList = new ArrayList<>();

    public HeaderFooterAdapter(Context context, int headerResId, int footerResId, List<T> list) {
        final View header = headerResId != NO_HEADER ? LayoutInflater.from(context).inflate(headerResId, null) : null;
        final View footer = footerResId != NO_FOOTER ? LayoutInflater.from(context).inflate(footerResId, null) : null;
        init(list, header, footer);
    }

    public HeaderFooterAdapter(View headerView, View footerView, List<T> list) {
        init(list, headerView, footerView);
    }

    private void init(List<T> list, View header, View footer) {
        mList = list;
        mHeaderView = header;
        mFooterView = footer;
    }

    @Override
    public final HeaderFooterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                RecyclerView.LayoutParams hlp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mHeaderView.setLayoutParams(hlp);
                return new HeaderView(mHeaderView);
            case TYPE_FOOTER:
                RecyclerView.LayoutParams flp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mFooterView.setLayoutParams(flp);
                return new FooterView(mFooterView);
            default:
                Integer vt = viewType;
                return onCreateViewHolder(parent, vt);
        }

    }

    public abstract HeaderFooterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, Integer viewType);

    @Override
    public final int getItemViewType(int position) {
        if (mHeaderView != null && isPositionHeader(position))
            return TYPE_HEADER;
        if (mFooterView != null && isPositionFooter(position))
            return TYPE_FOOTER;
        else {
            Integer itemPosition = mHeaderView != null ? position - 1 : position;
            return this.getItemViewType(itemPosition);
        }

    }

    public abstract int getItemViewType(Integer position);

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position == getItemCount() - 1;
    }

    protected T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public final void onBindViewHolder(HeaderFooterAdapter.ViewHolder holder, int position) {
        Integer itemPosition = mHeaderView != null ? position - 1 : position;
        this.onBindViewHolder(holder, itemPosition);
    }

    public abstract void onBindViewHolder(ViewHolder holder, Integer position);

    @Override
    public int getItemCount() {
        return mList.size() + (mHeaderView != null ? 1 : 0) + (mFooterView != null ? 1 : 0);
    }

    public List<T> getItems() {
        return mList;
    }

    private class HeaderView extends HeaderFooterAdapter.ViewHolder {
        public HeaderView(View itemView) {
            super(itemView);

        }
    }

    private class FooterView extends HeaderFooterAdapter.ViewHolder {
        public FooterView(View itemView) {
            super(itemView);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        /**
         * use this method to get the actual adapter position regardless of header and footer position.
         * using getAdapterPosition() may return incorrect index if header is present , because header also it will take as an item of recycler view
         *
         * @return
         */
        public int getActualAdapterPosition() {
            return mHeaderView != null ? getAdapterPosition() - 1 : getAdapterPosition();
        }
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public View getFooterView() {
        return mFooterView;
    }
}

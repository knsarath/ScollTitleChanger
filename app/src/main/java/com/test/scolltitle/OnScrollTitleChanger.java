package com.test.scolltitle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sarath on 17/2/17.
 */
public class OnScrollTitleChanger extends RecyclerView.OnScrollListener {
    private TextView mToolbarTitleTextView;
    private TextView mListSubTitleTextView;
    private TextView mListTitleTextView;
    private String mToolbarTitle;
    private String mListTitle;
    private String mListSubTitle;


    private OnScrollTitleChanger(TextView toolbarTitleTextView, TextView listTitleTextView, TextView listSubTitleTextView, String toolbarTitle, String listTitle, String listSubTitle) {
        mToolbarTitleTextView = toolbarTitleTextView;
        mListSubTitleTextView = listSubTitleTextView;
        mListTitleTextView = listTitleTextView;
        mToolbarTitle = toolbarTitle;
        mListTitle = listTitle;
        mListSubTitle = listSubTitle;
    }

    private static final String TAG = OnScrollTitleChanger.class.getSimpleName();


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (layoutManager.findFirstVisibleItemPosition() == 0) { // assume that recycler views 0th item is your header
            final View titleView = layoutManager.findViewByPosition(0);
            final float endY = recyclerView.getY() - titleView.getHeight();
            final float percentage = Math.abs(titleView.getY()) / Math.abs(endY);
            // final float actualY = endY + titleView.getHeight();
            // Log.d(TAG, "Actual Y :" + actualY + " , End y :" + endY + "currentY =" + titleView.getY() + " percentage = " + (percentage));

            mToolbarTitleTextView.setText(mToolbarTitle);
            mToolbarTitleTextView.setAlpha(1 - percentage);

            if (percentage > 0.5) {
                mListTitleTextView.setText(mListTitle);
                mListSubTitleTextView.setText(mListSubTitle);
                mListSubTitleTextView.setAlpha(percentage);
                mListTitleTextView.setAlpha(percentage);
            } else {
                mListSubTitleTextView.setAlpha(0);
                mListTitleTextView.setAlpha(0);
            }

        } else {
            mToolbarTitleTextView.setAlpha(0);
        }


    }


    /**
     * Builder class to create OnScrollTitleChanger object
     */
    public static class Builder {

        private TextView mToolbarTitleTextView;
        private TextView mListSubTitleTextView;
        private TextView mListTitleTextView;
        private String mToolbarTitle;
        private String mListTitle;
        private String mListSubTitle;


        public Builder setToolbarTitleTextView(TextView toolbarTitleTextView) {
            mToolbarTitleTextView = toolbarTitleTextView;
            return this;
        }

        public Builder setListSubTitleTextView(TextView listSubTitleTextView) {
            mListSubTitleTextView = listSubTitleTextView;
            return this;
        }

        public Builder setListTitleTextView(TextView listTitleTextView) {
            mListTitleTextView = listTitleTextView;
            return this;
        }

        public Builder setToolbarTitle(String toolbarTitle) {
            mToolbarTitle = toolbarTitle;
            return this;
        }

        public Builder setListTitle(String listTitle) {
            mListTitle = listTitle;
            return this;
        }

        public Builder setListSubTitle(String listSubTitle) {
            mListSubTitle = listSubTitle;
            return this;
        }

        public OnScrollTitleChanger create() {
            return new OnScrollTitleChanger(mToolbarTitleTextView, mListTitleTextView, mListSubTitleTextView, mToolbarTitle, mListTitle, mListSubTitle);
        }
    }
}

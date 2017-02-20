package com.test.scolltitle;

import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by sarath on 17/2/17.
 */
public class OnScrollTitleChanger implements ViewTreeObserver.OnScrollChangedListener {

    private View mToolbar;
    private View mHeaderView;
    private TextView mToolbarTitleTextView;
    private TextView mListSubTitleTextView;
    private TextView mListTitleTextView;
    private String mToolbarTitle;
    private String mListTitle;
    private String mListSubTitle;
    private boolean initialPositionSet = false;
    private float initialToolbarY;
    private float initialTitleY;
    private float initialSubTitleY;

    private int[] toolbarXY = new int[2];
    private int[] headerXY = new int[2];

    private float mToolbarTop;
    private float mHeaderTop;
    private int Y_COORDINATE = 1;


    private OnScrollTitleChanger(View toolbar, View headerView, TextView toolbarTitleTextView, TextView listTitleTextView, TextView listSubTitleTextView, String toolbarTitle, String listTitle, String listSubTitle) {
        mToolbarTitleTextView = toolbarTitleTextView;
        mListSubTitleTextView = listSubTitleTextView;
        mListTitleTextView = listTitleTextView;
        mToolbarTitle = toolbarTitle;
        mListTitle = listTitle;
        mListSubTitle = listSubTitle;
        mToolbar = toolbar;
        mHeaderView = headerView;
    }

    private static final String TAG = OnScrollTitleChanger.class.getSimpleName();


    /**
     * VTO callback
     */
    @Override
    public void onScrollChanged() {

        initValuesForTheFirstTime();

        mToolbar.getLocationOnScreen(toolbarXY);
        mHeaderView.getLocationOnScreen(headerXY);
        mToolbarTop = toolbarXY[Y_COORDINATE];
        mHeaderTop = headerXY[Y_COORDINATE];
        final float toolbarBottom = mToolbarTop + mToolbar.getHeight();
        final float endY = toolbarBottom - mHeaderView.getHeight();
        final float percentage = Math.abs(mHeaderView.getY()) / Math.abs(endY);
        mToolbarTitleTextView.setAlpha(1 - percentage);
        mListTitleTextView.setAlpha(percentage);
        mListSubTitleTextView.setAlpha(percentage);
        if (mHeaderTop < toolbarBottom) { // set subtitles and
            final float diff = (Math.abs(toolbarBottom - mHeaderTop));
            mToolbarTitleTextView.animate().y(initialToolbarY - diff).setDuration(0).start();
        } else {
            mToolbarTitleTextView.animate().y(initialToolbarY).setDuration(0).start();
        }
    }



    private void initValuesForTheFirstTime() {
        if (!initialPositionSet) {
            initialToolbarY = mToolbarTitleTextView.getY();
            initialTitleY = mListTitleTextView.getY();
            initialSubTitleY = mListSubTitleTextView.getY();
            Log.d(TAG, "Original title pos =" + initialToolbarY);
            mToolbarTitleTextView.setText(mToolbarTitle);
            mListTitleTextView.setText(mListTitle);
            mListSubTitleTextView.setText(mListSubTitle);
            initialPositionSet = true;
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
        private View mToolbar;
        private View mHeaderView;


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

        public Builder setToolbar(View toolbar) {
            mToolbar = toolbar;
            return this;
        }

        public Builder setHeaderView(View headerView) {
            mHeaderView = headerView;
            return this;
        }

        public OnScrollTitleChanger create() {
            return new OnScrollTitleChanger(mToolbar, mHeaderView, mToolbarTitleTextView, mListTitleTextView, mListSubTitleTextView, mToolbarTitle, mListTitle, mListSubTitle);
        }

    }
}

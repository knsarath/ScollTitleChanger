package com.test.scolltitle;

import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by sarath on 17/2/17.
 */
public class OnScrollTitleChanger implements ViewTreeObserver.OnScrollChangedListener {
    private static final String TAG = OnScrollTitleChanger.class.getSimpleName();
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
    private final static int  Y_COORDINATE = 1;
    private boolean isIdle;


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


    /**
     * ViewTreeObserver callback
     */
    @Override
    public void onScrollChanged() {

        initValuesForTheFirstTime();

        /**
         * Finding the coordinates of toolbar and header view (Based of screen not based on its parent)
         */
        mToolbar.getLocationOnScreen(toolbarXY);
        mHeaderView.getLocationOnScreen(headerXY);
        mToolbarTop = toolbarXY[Y_COORDINATE];
        mHeaderTop = headerXY[Y_COORDINATE];
        final float toolbarBottom = mToolbarTop + mToolbar.getHeight();

        // Do all these animations only if at least the below header has touched the bottom of toolbar or crossed the toolbar
        if (mHeaderTop <= toolbarBottom) {
            /**
             * Find the percentage of distance moved and set transparency to that percentage
             */
            final float endY = toolbarBottom - mHeaderView.getHeight();
            final float percentage = Math.abs(mHeaderTop - toolbarBottom) / Math.abs(endY);
            mToolbarTitleTextView.setAlpha(1 - percentage);
            mListTitleTextView.setAlpha(percentage);
            mListSubTitleTextView.setAlpha(percentage);

            final int gap = mToolbar.getHeight() / 2; // setting a gap between the toolbar title and list title.  while scrolling this gap has to be kept. Eg: If toolbar title has moved up/down by some pixel ,
            // then the subtitle should be the there below this gap always

            final float gapBtwnHeaderAndToolbar = (Math.abs(toolbarBottom - mHeaderTop));
            mToolbarTitleTextView.animate().y(initialToolbarY - gapBtwnHeaderAndToolbar).setDuration(0).start();
            final float headerTextViewsY = mToolbarTitleTextView.getY() + gap;
            final float subHeaderTextViewsY = mToolbarTitleTextView.getY() + mListTitleTextView.getHeight() + gap;
            if (headerTextViewsY >= initialTitleY && mToolbarTitleTextView.getY() >= (initialTitleY - gap)) {
                mListTitleTextView.animate().y(headerTextViewsY).setDuration(0).start();
                mListSubTitleTextView.animate().y(subHeaderTextViewsY).setDuration(0).start();
            } else {
                mListTitleTextView.animate().y(initialTitleY).setDuration(0).start();
                mListSubTitleTextView.animate().y(initialSubTitleY).setDuration(0).start();
            }

            isIdle = false; // idle flag is set to false to denote some animation is going on

        } else {// header is below somewhere in the screen , it has not touched the toolbar , so show toolbar text only , no need to show list title and subtitle in toolbar
            reset();
        }
    }


    private void initValuesForTheFirstTime() {
        if (!initialPositionSet) {
            /**
             * saving the actual positions of toolbar and title subtitle texts
             */
            initialToolbarY = mToolbarTitleTextView.getY();
            initialTitleY = mListTitleTextView.getY();
            initialSubTitleY = mListSubTitleTextView.getY();
            Log.d(TAG, "Original title pos =" + initialToolbarY);
            reset();
            initialPositionSet = true;
        }
    }

    private void reset() {
        if (!isIdle) {  // this flag is just to avoid unnecessary execution of this reset function again and again when the header is away from toolbar.
            Log.d(TAG, "Resetting");
            mToolbarTitleTextView.setText(mToolbarTitle);
            mListTitleTextView.setText(mListTitle);
            mListSubTitleTextView.setText(mListSubTitle);
            mToolbarTitleTextView.animate().y(initialToolbarY).alpha(1).setDuration(0).start();
            mListTitleTextView.animate().y(initialTitleY).alpha(0).setDuration(0).start();
            mListSubTitleTextView.animate().y(initialSubTitleY).alpha(0).setDuration(0).start();
            isIdle = true;
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
            if (mToolbar == null) {
                throwError("Toolbar");
                return null;
            } else if (mHeaderView == null) {
                throwError("Header view");
                return null;
            } else if (mToolbarTitleTextView == null) {
                throwError("Toolbar title textview");
                return null;
            } else if (mListTitleTextView == null) {
                throwError("List title textview");
                return null;
            } else if (mListSubTitleTextView == null) {
                throwError("List subtitle textview");
                return null;
            } else if (mToolbarTitle == null) {
                throwError("Toolbar title text");
                return null;
            } else if (mListTitle == null) {
                throwError("List title text");
                return null;
            } else if (mListSubTitle == null) {
                throwError("List sub title");
                return null;
            } else {
                return new OnScrollTitleChanger(mToolbar, mHeaderView, mToolbarTitleTextView, mListTitleTextView, mListSubTitleTextView, mToolbarTitle, mListTitle, mListSubTitle);
            }
        }

        private static void throwError(String emptyComponent) {
            throw new RuntimeException(emptyComponent + " can not be null. Please set a valid " + emptyComponent);
        }

    }
}

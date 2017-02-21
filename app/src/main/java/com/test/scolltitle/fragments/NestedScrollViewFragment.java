package com.test.scolltitle.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.scolltitle.OnScrollTitleChanger;
import com.test.scolltitle.R;

/**
 * Created by sarath on 20/2/17.
 */

public class NestedScrollViewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_nested_scrollview, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView toolbarTitleTextView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        TextView listTitleTextView = (TextView) toolbar.findViewById(R.id.title);
        TextView listSubTitleTextView = (TextView) toolbar.findViewById(R.id.subtitle);
        toolbarTitleTextView.setText("Engagement Details");

        final View header = rootView.findViewById(R.id.list_title);


        OnScrollTitleChanger onScrollTitleChanger = new OnScrollTitleChanger.Builder()
                .setToolbarTitleTextView(toolbarTitleTextView)
                .setListTitleTextView(listTitleTextView)
                .setListSubTitleTextView(listSubTitleTextView)
                .setToolbarTitle("Engagement Details")
                .setListTitle("Dragan Grubestic")
                .setListSubTitle("User Interface designer")
                .setHeaderView(header)
                .setToolbar(toolbar)
                .create();

        header.getViewTreeObserver().addOnScrollChangedListener(onScrollTitleChanger);

        return rootView;
    }

    public static Fragment createInstance() {
        return new NestedScrollViewFragment();
    }
}

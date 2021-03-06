package com.test.scolltitle.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.scolltitle.DummyData;
import com.test.scolltitle.adapter.HeaderFooterAdapter;
import com.test.scolltitle.adapter.MyAdapter;
import com.test.scolltitle.OnScrollTitleChanger;
import com.test.scolltitle.R;

/**
 * Created by sarath on 16/2/17.
 */

public class RecyclerViewFragment extends Fragment {
    private static final String TAG = RecyclerViewFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;


    public static Fragment createInstance() {
        return new RecyclerViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView toolbarTitleTextView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        TextView listTitleTextView = (TextView) toolbar.findViewById(R.id.title);
        TextView listSubTitleTextView = (TextView) toolbar.findViewById(R.id.subtitle);


        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        final MyAdapter adapter = new MyAdapter(this.getContext(), R.layout.header, HeaderFooterAdapter.NO_FOOTER, DummyData.getList());
        mRecyclerView.setAdapter(adapter);

        OnScrollTitleChanger onScrollTitleChanger = new OnScrollTitleChanger.Builder()
                .setToolbarTitleTextView(toolbarTitleTextView)
                .setListTitleTextView(listTitleTextView)
                .setListSubTitleTextView(listSubTitleTextView)
                .setToolbarTitle("Engagement Details")
                .setListTitle("Dragan Grubestic")
                .setListSubTitle("User Interface designer")
                .setHeaderView(adapter.getHeaderView())
                .setToolbar(toolbar)
                .create();

        adapter.getHeaderView().getViewTreeObserver().addOnScrollChangedListener(onScrollTitleChanger);

        return rootView;
    }


}

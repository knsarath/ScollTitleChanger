package com.test.scolltitle;

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

import java.util.ArrayList;

/**
 * Created by sarath on 16/2/17.
 */

public class MyFragment extends Fragment {
    private static final String TAG = MyFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;


    public static Fragment createInstance() {
        return new MyFragment();
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
        final MyAdapter adapter = new MyAdapter(this.getContext(), R.layout.header, HeaderFooterAdapter.NO_FOOTER, getList());
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

    private ArrayList<String> getList() {
        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("Item " + i);
        }
        return list;
    }
}

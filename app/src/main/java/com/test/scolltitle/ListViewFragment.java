package com.test.scolltitle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by sarath on 20/2/17.
 */

public class ListViewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.list_view_fragment, container, false);


        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView toolbarTitleTextView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        TextView listTitleTextView = (TextView) toolbar.findViewById(R.id.title);
        TextView listSubTitleTextView = (TextView) toolbar.findViewById(R.id.subtitle);


        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, DummyData.getList()));
        final View headerView = LayoutInflater.from(getContext()).inflate(R.layout.header, null);
        listView.addHeaderView(headerView);

        OnScrollTitleChanger onScrollTitleChanger = new OnScrollTitleChanger.Builder()
                .setToolbarTitleTextView(toolbarTitleTextView)
                .setListTitleTextView(listTitleTextView)
                .setListSubTitleTextView(listSubTitleTextView)
                .setToolbarTitle("Engagement Details")
                .setListTitle("Dragan Grubestic")
                .setListSubTitle("User Interface designer")
                .setHeaderView(headerView)
                .setToolbar(toolbar)
                .create();

        headerView.getViewTreeObserver().addOnScrollChangedListener(onScrollTitleChanger);

        return rootView;
    }

    public static Fragment createInstance() {
        return new ListViewFragment();
    }
}

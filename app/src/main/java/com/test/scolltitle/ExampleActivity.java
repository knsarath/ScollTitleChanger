package com.test.scolltitle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.test.scolltitle.fragments.ListViewFragment;
import com.test.scolltitle.fragments.NestedScrollViewFragment;
import com.test.scolltitle.fragments.RecyclerViewFragment;
import com.test.scolltitle.fragments.ScrollViewFragment;

public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        final Fragment instance = getFragmentFromIntent();
        fragmentTransaction.add(R.id.container, instance);
        fragmentTransaction.commit();

    }

    private Fragment getFragmentFromIntent() {
        Fragment fragment = RecyclerViewFragment.createInstance();
        final String type = getIntent().getStringExtra("type");
        switch (type) {
            case "recycler_view":
                fragment = RecyclerViewFragment.createInstance();
                break;
            case "list_view":
                fragment = ListViewFragment.createInstance();
                break;
            case "nested_scroll_view":
                fragment = NestedScrollViewFragment.createInstance();
                break;
            case "scroll_view":
                fragment = ScrollViewFragment.createInstance();
                break;
        }
        return fragment;
    }
}

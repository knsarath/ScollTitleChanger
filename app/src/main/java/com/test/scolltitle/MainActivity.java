package com.test.scolltitle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.main);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            if (viewGroup.getChildAt(i) instanceof Button) {
                viewGroup.getChildAt(i).setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        final Intent intent = new Intent(this, ExampleActivity.class);
        switch (v.getId()) {
            case R.id.recycler_view_btn:
                intent.putExtra("type", "recycler_view");
                break;
            case R.id.list_view_btn:
                intent.putExtra("type", "list_view");
                break;
            case R.id.nested_scrollview_btn:
                intent.putExtra("type", "nested_scroll_view");
                break;
            case R.id.scrollview_btn:
                intent.putExtra("type", "scroll_view");
                break;
        }
        startActivity(intent);
    }
}

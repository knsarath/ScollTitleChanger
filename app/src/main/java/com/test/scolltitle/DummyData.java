package com.test.scolltitle;

import java.util.ArrayList;

/**
 * Created by sarath on 20/2/17.
 */
public class DummyData {
    public static ArrayList<String> getList() {
        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("Item " + i);
        }
        return list;
    }
}

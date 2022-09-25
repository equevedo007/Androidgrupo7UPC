package com.example.androidgrupo7upc.util.ui;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class UiUtil {

    public static <T> List<T> findViewsWithType(ViewGroup root, Class<T> type) {
        List<T> views = new ArrayList<>();
        for (int i = 0; i < root.getChildCount(); i++) {
            View view = root.getChildAt(i);
            if (type.isInstance(view)) {
                views.add(type.cast(view));
            }
        }
        return views;
    }

}

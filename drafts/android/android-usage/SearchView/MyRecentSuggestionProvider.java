package com.tpout.basicusage.searchview;

import android.content.SearchRecentSuggestionsProvider;

public class MyRecentSuggestionProvider extends SearchRecentSuggestionsProvider {
    //唯一字符串即可
    public final static String AUTHORITY = "com.yourena.tpout.MyRecentSuggestionProvider";
    //表示双行提示
    public final static int MODE = DATABASE_MODE_QUERIES | DATABASE_MODE_2LINES;;

    public MyRecentSuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
package com.tpout.basicusage.searchview;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import com.tpout.basicusage.R;

/**
 * Created by shengjieli on 17-7-10.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class SearchableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_searchable);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) findViewById(R.id.sv_searchView);
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        //在每个搜索建议条处添加插入按钮
        searchView.setQueryRefinementEnabled(true);

        handleIntent();
    }

    private void handleIntent() {
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Bundle appData = getIntent().getBundleExtra(SearchManager.APP_DATA);
            if (appData != null) {
//                boolean jargon = appData.getBoolean(SearchableActivity.JARGON);
            }
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    MyRecentSuggestionProvider.AUTHORITY, MyRecentSuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);
            doMySearch(query);
        } else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            // Handle a suggestions click (because the suggestions all use ACTION_VIEW)
            Uri data = intent.getData();
            showResult(data);
        }
    }

    private void clearHistory() {
//        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
//                HelloSuggestionProvider.AUTHORITY, HelloSuggestionProvider.MODE);
//        suggestions.clearHistory();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent();
    }

    private void doMySearch(String query) {
        ((TextView) findViewById(R.id.tv_searchResult)).setText(query);
    }

    private void showResult(Uri data) {

    }
}
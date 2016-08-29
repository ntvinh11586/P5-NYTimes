package com.example.vinh.nytimes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.vinh.nytimes.Article;
import com.example.vinh.nytimes.ContactsAdapter;
import com.example.vinh.nytimes.ItemClickSupport;
import com.example.vinh.nytimes.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

//    EditText etQuery;
//    GridView gvResults;
//    Button btnSearch;
    RecyclerView rvResult;

    ArrayList<Article> articles;
//    ArticleArrayAdapter adapter;
    String searchQuery = "";
    ContactsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("NYTimesSearch");

        setupViews();

        onArticleSearch();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here
                searchQuery = query;
                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();

                onArticleSearch();
                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void setupViews() {
        rvResult = (RecyclerView)findViewById(R.id.rvContacts);

//        gvResults = (GridView)findViewById(R.id.gvResults);
        articles = new ArrayList<>();
//        adapter = new ArticleArrayAdapter(this, articles);

        adapter = new ContactsAdapter(this, articles);
        rvResult.setAdapter(adapter);
//        rvResult.setLayoutManager(new LinearLayoutManager(this));

        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        // Attach the layout manager to the recycler view
        rvResult.setLayoutManager(gridLayoutManager);

//        gvResults.setAdapter(adapter);

//        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//                Intent i = new Intent(getApplicationContext(), ArticleActivity.class);
//
//                Article article = articles.get(pos);
//
//                i.putExtra("article", article);
//
//                startActivity(i);
//            }
//        });
        ItemClickSupport.addTo(rvResult).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent i = new Intent(getApplicationContext(), ArticleActivity.class);

                        Article article = articles.get(position);

                        i.putExtra("article", article);

                        startActivity(i);
                    }
                }
        );
    }

    public void onArticleSearch() {



        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json";

        RequestParams params = new RequestParams();
        params.put("api-key", "51065f56d04445baa91280fa70489e8e");
        params.put("page", 0);

        if (!searchQuery.equals(""))
            params.put("q", searchQuery);

        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                Log.d("DEBUG", response.toString());
                JSONArray articleJsonResults = null;

                try {
//                    adapter.clear();
                    articles.clear();
                    adapter.notifyDataSetChanged();
                    articleJsonResults = response.getJSONObject("response").getJSONArray("docs");
//                    adapter.addAll(Article.fromJSONArray(articleJsonResults));
//                    adapter.notifyDataSetChanged();
                    articles.addAll(Article.fromJSONArray(articleJsonResults));
                    adapter.notifyDataSetChanged();
                    Log.d("DEBUG", articles.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Toast.makeText(MainActivity.this, res.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}

package com.suyal.socialx.newsapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.suyal.socialx.R;
import com.suyal.socialx.newsapi.modal.NewsApiResponse;
import com.suyal.socialx.newsapi.modal.NewsHeadlines;
import com.suyal.socialx.newsapi.modal.SelectListener;

import java.util.List;

public class NewsActivity extends AppCompatActivity implements SelectListener {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        searchView = findViewById(R.id.search_view);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                dialog.setTitle("Fetching news articles of "+query);
//                dialog.show();
//                RequestManager manager = new RequestManager(NewsActivity.this);
//                manager.getNewsHeadlines(listener,"general",query);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });


        setContentView(R.layout.activity_news);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching new articles...");
        dialog.show();

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener,"general",null);
    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
           if(list.isEmpty()){
               Toast.makeText(NewsActivity.this, "No data found!!!", Toast.LENGTH_SHORT).show();
           }else{
               showNews(list);
               dialog.dismiss();
           }
        }


        @Override
        public void onError(String message) {

            Toast.makeText(NewsActivity.this, "An Error Occurred!!!", Toast.LENGTH_SHORT).show();

        }
    };

    private void showNews(List<NewsHeadlines> list){

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CustomAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {

        startActivity(new Intent(NewsActivity.this,DetailsActivity.class)
                .putExtra("data",headlines));

    }
}
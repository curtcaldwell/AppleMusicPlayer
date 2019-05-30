package com.curtcaldwell.nikecodechallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.curtcaldwell.nikecodechallenge.model.Feed;
import com.curtcaldwell.nikecodechallenge.model.Rss;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String BASE_URL = "https://rss.itunes.apple.com/api/v1/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        FeedApi feedApi = retrofit.create(FeedApi.class);

        Call<Rss> call = feedApi.getRss();

        call.enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(Call<Rss> call, Response<Rss> response) {
                Log.d(TAG, "onResponse: feed: " + response.body().getChannel().getItemList());
                Log.d(TAG, "onResponse: Server Response " + response.toString());

                List<Rss.Item> items = response.body().getChannel().getItemList();

                ArrayList<Post> posts = new ArrayList<Post>();
                for (int i = 0; i < items.size(); i++){
                    posts.add(new Post(
                            items.get(i).getTitle(),
                            items.get(i).getDescription(),
                            items.get(i).getLink()
                    ));
                }
                ListView listView = findViewById(R.id.listView);
                CustomListAdapter customListAdapter = new CustomListAdapter(MainActivity.this, R.layout.card_layout_main, posts);
                listView.setAdapter(customListAdapter);

            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {
                Log.e(TAG, "onFailure: Unable to retrieve Rss: " + t.getMessage() );
                Toast.makeText(MainActivity.this, "An Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

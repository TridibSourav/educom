package com.example.tspc.educom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.tspc.educom.Model.Item;
import com.example.tspc.educom.Model.YoutubeDataResponse;
import com.example.tspc.educom.adapters.videoListAdapter;
import com.example.tspc.educom.networkInterfaces.youtubeInterface;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllItemLIst extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Item> videolist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_item_list);

        videolist= new ArrayList<>();
        recyclerView=findViewById(R.id.itrmrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        youtubeInterface Api=retrofit.create(youtubeInterface.class);

        Api.getPlayListData("PLgH5QX0i9K3p9xzYLFGdfYliIRBLVDRV5").enqueue(new Callback<YoutubeDataResponse>() {
            @Override
            public void onResponse(Call<YoutubeDataResponse> call, Response<YoutubeDataResponse> response) {
                Log.w("#####",response.body().getEtag());
                Log.w("#####",response.body().getEtag());
                Log.w("$$$$$$$$",response.body().getItems().get(0).getContentDetails().getVideoId());

                viewUpdate(response.body().getItems());
            }

            @Override
            public void onFailure(Call<YoutubeDataResponse> call, Throwable t) {
                Log.w("******",t.toString());
            }
        });
    }

    public void viewUpdate(List<Item> itemList){
        videoListAdapter adapter = new videoListAdapter(AllItemLIst.this,itemList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}

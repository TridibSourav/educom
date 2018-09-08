package com.example.tspc.educom.networkInterfaces;

import com.example.tspc.educom.Model.YoutubeDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface youtubeInterface {

    public final static String base_url="https://www.googleapis.com/youtube/v3/";

    @GET("playlistItems?maxResults=25&part=snippet%2CcontentDetails&key=AIzaSyCn39eU5tAVvKBGQaTiB1q-AiF-nYzjucc")
    Call<YoutubeDataResponse> getPlayListData(@Query("playlistId") String playlistid);
}

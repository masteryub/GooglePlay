package com.yubing.googleplay.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yu on 2017/3/26.
 */

public interface Api {
    @GET("hot")
    Call<List<String>> ListHot();
    @GET("recommend")
    Call<List<String>> ListRecommend();
}

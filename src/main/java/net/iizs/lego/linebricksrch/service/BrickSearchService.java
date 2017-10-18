package net.iizs.lego.linebricksrch.service;

import net.iizs.lego.linebricksrch.model.ItemSearchResult;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BrickSearchService {
    @GET("item_number/{query}/")
    Call<ItemSearchResult> getByItemNumber(@Path("query") String itemNumber);
}

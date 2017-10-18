package net.iizs.lego.linebricksrch.service;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public final class BrickSearchServiceBuilder {
    public static BrickSearchServiceBuilder create() {
        return new BrickSearchServiceBuilder();
    }

    private BrickSearchServiceBuilder() {  }

    public BrickSearchService build() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://iizs.net/legosrch/api/v1/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(BrickSearchService.class);
    }
}

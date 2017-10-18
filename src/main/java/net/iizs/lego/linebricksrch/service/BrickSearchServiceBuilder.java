package net.iizs.lego.linebricksrch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public final class BrickSearchServiceBuilder {
    public static BrickSearchServiceBuilder create() {
        return new BrickSearchServiceBuilder();
    }

    private BrickSearchServiceBuilder() {  }

    public BrickSearchService build() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://iizs.net/legosrch/api/v1/")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        return retrofit.create(BrickSearchService.class);
    }
}

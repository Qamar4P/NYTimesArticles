package dev.qamar.nytimesarticles.data;

import java.util.List;

import dev.qamar.nytimesarticles.data.model.ApiResponse;
import dev.qamar.nytimesarticles.data.model.NewsArticle;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface NYTApi {
    @GET("mostpopular/v2/mostviewed/all-sections/{period}.json")
    Flowable<ApiResponse<List<NewsArticle>>> mostViewedArticles(@Path("period") int period);
}
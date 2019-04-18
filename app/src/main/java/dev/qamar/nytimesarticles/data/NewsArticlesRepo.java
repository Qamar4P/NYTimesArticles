package dev.qamar.nytimesarticles.data;

import android.arch.lifecycle.LiveData;
import android.util.Base64;

import java.util.List;

import dev.qamar.nytimesarticles.data.model.ApiResponse;
import dev.qamar.nytimesarticles.data.model.NewsArticle;
import dev.qamar.nytimesarticles.utils.AppConst;
import dev.qamar.nytimesarticles.utils.Utils;
import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Qamar4P on 18/4/2019.
 * Islamabad, Pakistan.
 */
public class NewsArticlesRepo {

    public Flowable<List<NewsArticle>> mostViewedArticles(int period){
        return ApiClientFactory.getNYTApi()
                .mostViewedArticles(period)
                .map(ApiResponse::getResults);
    }


}

package dev.qamar.nytimesarticles.data;

import java.io.IOException;

import dev.qamar.nytimesarticles.BuildConfig;
import dev.qamar.nytimesarticles.utils.AppConst;
import dev.qamar.nytimesarticles.utils.Utils;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Qamar4P on 18/4/2019.
 * Islamabad, Pakistan.
 */
public class ApiClientFactory {
    public static NYTApi getNYTApi() {
        return getClient(AppConst.BASE_URL).create(NYTApi.class);
    }
    public static Retrofit getClient(String baseUrl) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("api-key", Utils.decode(AppConst.K)).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        });

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
    }
}

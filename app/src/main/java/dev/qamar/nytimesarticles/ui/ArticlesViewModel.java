package dev.qamar.nytimesarticles.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dev.qamar.nytimesarticles.NYTimesApp;
import dev.qamar.nytimesarticles.data.NewsArticlesRepo;
import dev.qamar.nytimesarticles.data.model.NewsArticle;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ArticlesViewModel extends ViewModel {
    private static final String TAG = ArticlesViewModel.class.getSimpleName();

    private CompositeDisposable disposable = new CompositeDisposable();
    private NewsArticlesRepo repo = new NewsArticlesRepo();

    private final MutableLiveData<List<NewsArticle>> articles = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @VisibleForTesting
    void loadArticles(int period) {
        loading.setValue(true);
        disposable.add(
                repo.mostViewedArticles(period).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(newsArticles -> {
                                    loading.setValue(false);
                                    articles.setValue(newsArticles);
                                },
                                error -> {
                                    loading.setValue(false);
                                    NYTimesApp.getInstance().toast("Error: "+error.getLocalizedMessage());
                                    Log.e(TAG, "Unable to get news", error);
                                }));
    }

    @VisibleForTesting
    MutableLiveData<List<NewsArticle>> getArticles() {
        return articles;
    }

    @NonNull
    MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}

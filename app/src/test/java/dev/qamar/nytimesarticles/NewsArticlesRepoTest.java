package dev.qamar.nytimesarticles;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import dev.qamar.nytimesarticles.data.NewsArticlesRepo;
import io.reactivex.Flowable;

/**
 * Created by Qamar4P on 18/4/2019.
 * Islamabad, Pakistan.
 */
public class NewsArticlesRepoTest {


    private NewsArticlesRepo repo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        repo = new NewsArticlesRepo();
    }

    @Test
    public void getNewsArticle_daily() {

        repo.mostViewedArticles(1)
                .test()
                .assertValueCount(1).assertNoErrors();
    }
    @Test
    public void getNewsArticle_weekly() {

        repo.mostViewedArticles(7)
                .test()
                .assertValueCount(1).assertNoErrors();
    }
    @Test
    public void getNewsArticle_monthly() {

        repo.mostViewedArticles(30)
                .test()
                .assertValueCount(1).assertNoErrors();
    }
}

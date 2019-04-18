package dev.qamar.nytimesarticles.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dev.qamar.nytimesarticles.R;

public class MainActivity extends AppCompatActivity {

    public static final int DEFAULT_NEWS_PERIOD = 1;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        showArticles(DEFAULT_NEWS_PERIOD);
    }

    private void showArticles(int period) {
        setTitle(getTitleForPeriod(period));
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer,ArticlesFragment.newInstance(1))
                    .commit();
        }else {
            ((ArticlesFragment)fragment).loadArticles(period);
        }
    }

    @OnClick({R.id.buttonDaily,R.id.buttonWeekly,R.id.buttonMonthly})
    public void tapButton(View v){
        switch (v.getId()) {
            case R.id.buttonDaily:
                showArticles(1);
                break;
            case R.id.buttonWeekly:
                showArticles(7);
                break;
            case R.id.buttonMonthly:
                showArticles(30);
                break;
        }
    }

    private int getTitleForPeriod(int period) {
        switch (period){
            case 1:
                return R.string.daily_news;
            case 7:
                return R.string.weekly_news;
            case 30:
                return R.string.monthly_news;
        }
        return R.string.news;
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}

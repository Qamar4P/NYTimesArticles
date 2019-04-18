package dev.qamar.nytimesarticles;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Qamar4P on 18/4/2019.
 * Islamabad, Pakistan.
 */
public class NYTimesApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}

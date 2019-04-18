package dev.qamar.nytimesarticles;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Qamar4P on 18/4/2019.
 * Islamabad, Pakistan.
 */
public class NYTimesApp extends Application {

    private static NYTimesApp mInstance;

    public static NYTimesApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    public void toast(String msg){
        Toast.makeText(mInstance,msg,Toast.LENGTH_SHORT).show();
    }
}

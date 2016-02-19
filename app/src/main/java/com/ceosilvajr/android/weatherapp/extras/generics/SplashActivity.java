package com.ceosilvajr.android.weatherapp.extras.generics;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by ceosilvajr on 17/10/2015.
 */
public abstract class SplashActivity extends AppCompatActivity {

    private static int MILLI_SECONDS = 1000;

    private Handler mHandler;

    public abstract int setLayout();

    public abstract int setTimeInSeconds();

    public abstract void init();

    public abstract void done();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(setLayout());
        init();
        mHandler = new Handler();
    }

    private Runnable mRunnable = this::done;

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, setTimeInSeconds() * MILLI_SECONDS);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        removeCallbacks();
    }

    @Override
    protected void onStop() {
        super.onStop();
        removeCallbacks();
    }

    private void removeCallbacks() {
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

}

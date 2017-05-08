package com.mhmt.foodbasket;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import butterknife.ButterKnife;
import timber.log.Timber;

public class Foodbasket extends Application {

  private static Foodbasket instance;

  public static Foodbasket getInstance() {
    return instance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    if (BuildConfig.DEBUG) {
      initializeLeakCanary();
    }
    initializeButterknife();
    initializeTimber();
    instance = this;
  }

  private void initializeLeakCanary() {// NOTICE: Disabled for now.
    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);
  }

  private void initializeButterknife() {
    if (BuildConfig.DEBUG) {
      ButterKnife.setDebug(true);
    } else {
      ButterKnife.setDebug(false);
    }
  }

  private void initializeTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

}

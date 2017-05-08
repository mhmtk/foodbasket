package com.mhmt.foodbasket;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
public final class AppModule {

  @Singleton
  @Provides static Context context() {
    return Foodbasket.getInstance().getApplicationContext();
  }


  @Provides EventBus eventBus() {
    return EventBus.getDefault();
  }

  @Reusable
  @Provides
  Executor executor() {
    return Executors.newCachedThreadPool();
  }

}

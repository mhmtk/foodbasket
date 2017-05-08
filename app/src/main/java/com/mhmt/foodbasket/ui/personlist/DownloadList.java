package com.mhmt.foodbasket.ui.personlist;

import com.mhmt.foodbasket.Injector;
import com.mhmt.foodbasket.domain.Person;
import com.mhmt.foodbasket.network.DataService;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

public class DownloadList implements Runnable {

  @Inject EventBus eventBus;
  @Inject DataService dataService;

  public DownloadList() {
    Injector.getNetworkJobComponent().inject(this);
  }

  @Override public void run() {
    try {
      final Response<List<Person>> response = dataService.fetchPersons().execute();
      if (!response.isSuccessful()) {
        throw new IOException();
      } else {
        eventBus.postSticky(new ListDownloaded(response.body()));
      }
    } catch (IOException e) {
      eventBus.postSticky(new ListDownloadFailed());
    }
  }
}

package com.mhmt.foodbasket.ui.persondetail;

import com.mhmt.foodbasket.Injector;
import com.mhmt.foodbasket.domain.PersonDetail;
import com.mhmt.foodbasket.network.DataService;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;

public class DownloadPersonDetail implements Runnable {

  @Inject EventBus eventBus;
  @Inject DataService dataService;
  private int personId;

  public DownloadPersonDetail(int personId) {
    Injector.getNetworkJobComponent().inject(this);
    this.personId = personId;
  }

  @Override public void run() {
    try {
      final Response<PersonDetail> response = dataService.fetchPersonDetail(personId).execute();
      if (!response.isSuccessful()) {
        throw new IOException();
      } else {
        eventBus.postSticky(new PersonDetailDownloaded(response.body()));
      }
    } catch (IOException e) {
      eventBus.postSticky(new PersonDetailDownloadFailed());
    }
  }
}

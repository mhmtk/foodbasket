package com.mhmt.foodbasket.ui.persondetail;

import com.mhmt.foodbasket.Injector;
import com.mhmt.foodbasket.domain.PersonDetail;
import com.mhmt.foodbasket.network.DataService;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;

public class UpdatePersonDetail implements Runnable {

  private PersonDetail personDetail;

  @Inject EventBus eventBus;
  @Inject DataService dataService;

  public UpdatePersonDetail(final PersonDetail personDetail) {
    this.personDetail = personDetail;
    Injector.getNetworkJobComponent().inject(this);
  }

  @Override public void run() {
    try {
      final Response<PersonDetail> response = dataService.updatePersonDetail(personDetail.getId(), personDetail).execute();
      if (!response.isSuccessful()) {
        throw new IOException();
      } else {
        eventBus.postSticky(new PersonDetailUploaded(response.body()));
      }
    } catch (IOException e) {
      eventBus.postSticky(new PersonDetailUploadFailed());
    }
  }
}

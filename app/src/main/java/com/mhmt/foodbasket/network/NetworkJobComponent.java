package com.mhmt.foodbasket.network;

import com.mhmt.foodbasket.annotations.PerJob;
import com.mhmt.foodbasket.ui.login.LogIn;
import com.mhmt.foodbasket.ui.persondetail.DownloadPersonDetail;
import com.mhmt.foodbasket.ui.persondetail.UpdatePersonDetail;
import com.mhmt.foodbasket.ui.personlist.DownloadList;

import javax.inject.Singleton;

import dagger.Component;

@PerJob
@Singleton
@Component(modules = { NetworkServiceModule.class,  })
public interface NetworkJobComponent {

  void inject(DownloadList downloadList);

  void inject(DownloadPersonDetail downloadPersonDetail);

  void inject(UpdatePersonDetail updatePersonDetail);

  void inject(LogIn logIn);
}

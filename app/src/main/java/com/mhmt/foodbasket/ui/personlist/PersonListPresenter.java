package com.mhmt.foodbasket.ui.personlist;

import com.mhmt.foodbasket.Injector;
import com.mhmt.foodbasket.R;
import com.mhmt.foodbasket.ui.base.BasePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class PersonListPresenter extends BasePresenter<PersonListMvpView> implements PersonListMvpPresenter {

  @Inject protected EventBus eventBus;
  @Inject protected Executor executor;

  public PersonListPresenter() {
    Injector.getPresenterComponent().inject(this);
  }

  @Override public void onCreate(final PersonListMvpView mvpView) {
    super.onCreate(mvpView);
    getMvpView().initiateUI();
    executor.execute(new DownloadList());
  }


  @Override public void onResume() {
    eventBus.register(this);
  }

  @Override public void onPause() {
    eventBus.unregister(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
  }

  @SuppressWarnings("UnusedDeclaration")
  @Subscribe  (threadMode = ThreadMode.MAIN, sticky = true)
  public void listDownloaded(final ListDownloaded event) {
    eventBus.removeStickyEvent(event);
    getMvpView().populateList(event.getPersons());
    getMvpView().hideNonBlockingProgressDialog();
  }

  @SuppressWarnings("UnusedDeclaration")
  @Subscribe (threadMode = ThreadMode.MAIN, sticky = true)
  public void listDownloadFailed(final ListDownloadFailed event) {
    eventBus.removeStickyEvent(event);
    getMvpView().hideNonBlockingProgressDialog();
    getMvpView().displayError(R.string.data_download_failed);
  }

  @SuppressWarnings("UnusedDeclaration")
  @Subscribe public void personItemClicked(final PersonItemClicked event) {
    getMvpView().startPersonDetailActivity(event.getPersonId());
  }
}

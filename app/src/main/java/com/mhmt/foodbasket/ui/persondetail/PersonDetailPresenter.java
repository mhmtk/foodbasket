package com.mhmt.foodbasket.ui.persondetail;

import com.mhmt.foodbasket.Injector;
import com.mhmt.foodbasket.R;
import com.mhmt.foodbasket.domain.PersonDetail;
import com.mhmt.foodbasket.ui.base.BasePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class PersonDetailPresenter extends BasePresenter<PersonDetailMvpView> implements PersonDetailMvpPresenter {

  @Inject protected EventBus eventBus;
  @Inject protected Executor executor;
  private int personId;

  public PersonDetailPresenter(final int personId) {
    Injector.getPresenterComponent().inject(this);
    this.personId = personId;
  }

  @Override public void onCreate(final PersonDetailMvpView mvpView) {
    super.onCreate(mvpView);
    getMvpView().initiateUI();
    executor.execute(new DownloadPersonDetail(personId));
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
  @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
  public void detailDownloaded(final PersonDetailDownloaded event) {
    eventBus.removeStickyEvent(event);
    getMvpView().updateUI(event.getPersonDetail());
    getMvpView().hideNonBlockingProgressDialog();
  }

  @SuppressWarnings("UnusedDeclaration")
  @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
  public void listDownloadFailed(final PersonDetailDownloadFailed event) {
    eventBus.removeStickyEvent(event);
    getMvpView().hideNonBlockingProgressDialog();
    getMvpView().displayError(R.string.data_download_failed);
  }

  @SuppressWarnings("UnusedDeclaration")
  @Subscribe public void personDetailUploaded(final PersonDetailUploaded event) {
    getMvpView().dismissProgressDialog();
    /** The library I use for mocking data uses assets to mock the responses which means I cannot change them.
     * Therefore this event has the initial data, not the updated data. Normally, it'll have the updated data,
     * so code line commented out below will update the UI to reflect the successful data update
     **/
//    getMvpView().updateUI(event.getPersonDetail());
  }

  @SuppressWarnings("UnusedDeclaration")
  @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
  public void personDetailUploadFailed(final PersonDetailUploadFailed event) {
    eventBus.removeStickyEvent(event);
    getMvpView().dismissProgressDialog();
    getMvpView().hideNonBlockingProgressDialog();
    getMvpView().displayError(R.string.data_upload_failed);
  }

  public void saveClicked(final String cellNo, final String workNo, final String address) {
    getMvpView().showProgressDialog();
    executor.execute(new UpdatePersonDetail(new PersonDetail(personId, cellNo, workNo, address)));
  }
}

package com.mhmt.foodbasket.ui.personlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mhmt.foodbasket.R;
import com.mhmt.foodbasket.domain.Person;
import com.mhmt.foodbasket.ui.base.BaseActivity;
import com.mhmt.foodbasket.ui.persondetail.PersonDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonListActivity extends BaseActivity implements PersonListMvpView {

  protected PersonListPresenter presenter;

  @BindView(R.id.text_view_error)
  protected TextView errorText;
  @BindView(R.id.list_recycler_view)
  protected RecyclerView recyclerView;
  @BindView(R.id.progress_loading_bar)
  protected ProgressBar progressBar;

  private PersonListAdapter personListAdapter;

  public static void start(final Activity activity) {
    Intent intent = new Intent(activity, PersonListActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    setDefaultHeaderToolbar(true);
    ButterKnife.bind(this);
    presenter = new PersonListPresenter();
    presenter.onCreate(this);
  }

  @Override protected void onResume() {
    presenter.onResume();
    super.onResume();
  }

  @Override protected void onPause() {
    presenter.onPause();
    super.onPause();
  }

  @Override protected void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }


  @Override public void initiateUI() {
    personListAdapter = new PersonListAdapter(new ArrayList<Person>());
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(personListAdapter);
  }

  @Override public void populateList(final List<Person> persons) {
    personListAdapter.updateData(persons);
  }

  @Override public void hideNonBlockingProgressDialog() {
    progressBar.setVisibility(View.GONE);
  }

  @Override public void startPersonDetailActivity(final int personId) {
    PersonDetailActivity.start(this, personId);
  }

  @Override public void displayError(@StringRes int message) {
    errorText.setText(message);
    errorText.setVisibility(View.VISIBLE);
  }
}

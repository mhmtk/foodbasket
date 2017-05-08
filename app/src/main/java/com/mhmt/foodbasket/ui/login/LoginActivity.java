package com.mhmt.foodbasket.ui.login;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mhmt.foodbasket.R;
import com.mhmt.foodbasket.ui.base.BaseActivity;
import com.mhmt.foodbasket.ui.personlist.PersonListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginMvpView {

  protected LoginPresenter presenter;
  @BindView(R.id.edit_text_username)
  protected TextView userNameText;
  @BindView(R.id.edit_text_password)
  protected TextView passwordText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    setDefaultHeaderToolbar(false);
    ButterKnife.bind(this);
    presenter = new LoginPresenter();
    presenter.onCreate(this);
  }

  @OnClick(R.id.button_login)
  public void loginButtonClicked() {
    presenter.loginButtonClicked(userNameText.getText().toString(), passwordText.getText().toString());
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

  @Override public void startListActivity() {
    PersonListActivity.start(LoginActivity.this);
  }

}

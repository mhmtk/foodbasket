package com.mhmt.foodbasket.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.mhmt.foodbasket.R;

public class BaseActivity extends AppCompatActivity {

  private ContentLoadingProgressDialog contentLoadingProgressDialog;
  private InputMethodManager inputMethodManager;

  @Override protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected void onResume() {
    super.onResume();
  }

  @Override protected void onPause() {
    super.onPause();
  }

  protected void setDefaultHeaderToolbar(final boolean canGoBack) {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      setSupportActionBar(toolbar);

    }
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      if (canGoBack) {
        actionBar.setDisplayShowHomeEnabled(true); // show or hide the default home button
        actionBar.setDisplayHomeAsUpEnabled(true);
      } else {
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher_quantico);
      }
      actionBar.show();
    }
  }

  public void showProgressDialog() {
    if (contentLoadingProgressDialog == null) {
      contentLoadingProgressDialog = new ContentLoadingProgressDialog(this);
      contentLoadingProgressDialog.setCancelable(false);
    }
    contentLoadingProgressDialog.show();
  }

  public void dismissProgressDialog() {
    if (contentLoadingProgressDialog != null) {
      contentLoadingProgressDialog.dismiss();
    }
  }

  public void hideSoftKeyboard() {
    final View focusedView = getCurrentFocus();
    if (focusedView != null) {
      inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
    }
  }

  public void displayError(@StringRes int message) {
    Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
    toast.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
    toast.show();
  }
}

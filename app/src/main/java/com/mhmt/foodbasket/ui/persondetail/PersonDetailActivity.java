package com.mhmt.foodbasket.ui.persondetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.mhmt.foodbasket.PhoneTextChangeListener;
import com.mhmt.foodbasket.R;
import com.mhmt.foodbasket.domain.PersonDetail;
import com.mhmt.foodbasket.ui.base.BaseActivity;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;
import com.terrakok.phonematter.PhoneFormat;

import de.hdodenhof.circleimageview.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonDetailActivity extends BaseActivity implements PersonDetailMvpView {

  private PersonDetailPresenter presenter;
  public static final SimpleDateFormat VISIBLE_DATE_FORMAT =
      new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

  private static final String EXTRA_ID = "extra_id";

  @BindView(R.id.profile_image)
  protected CircleImageView profileImage;
  @BindView(R.id.text_view_name)
  protected TextView nameText;
  @BindView(R.id.text_view_birthdate)
  protected MaterialEditText birthdateText;
  @BindView(R.id.text_view_cellphone)
  protected MaterialEditText cellphoneText;
  @BindView(R.id.text_view_workphone)
  protected MaterialEditText workphoneText;
  @BindView(R.id.text_view_address)
  protected MaterialEditText addressText;
  private PhoneFormat phoneFormat;

  public static void start(final Activity activity, final int id) {
    Intent intent = new Intent(activity, PersonDetailActivity.class);
    intent.putExtra(EXTRA_ID, id);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_person_detail);
    setDefaultHeaderToolbar(true);
    ButterKnife.bind(this);
    if (!getIntent().hasExtra(EXTRA_ID)) {
      throw new IllegalStateException("PersonDetailActivity should be started with its static start method");
    }
    presenter = new PersonDetailPresenter(getIntent().getIntExtra(EXTRA_ID, -1));
    presenter.onCreate(this);
  }

  @Override public boolean onCreateOptionsMenu(final Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.person_details, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(final MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_save_action:
        presenter.saveClicked(cellphoneText.getText().toString(),
                              workphoneText.getText().toString(),
                              addressText.getText().toString());
        break;
      default:
        return super.onOptionsItemSelected(item);
    }
    return super.onOptionsItemSelected(item);
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
    profileImage.requestFocus();
    phoneFormat = new PhoneFormat(Locale.getDefault().getCountry().toLowerCase(), this);
    cellphoneText.addTextChangedListener(new PhoneTextChangeListener(phoneFormat, cellphoneText));
    workphoneText.addTextChangedListener(new PhoneTextChangeListener(phoneFormat, workphoneText));
  }

  @Override public void hideNonBlockingProgressDialog() {

  }

  @Override public void updateUI(final PersonDetail personDetail) {
    Picasso.with(this)
           .load(personDetail.getProfilePicture())
           .placeholder(R.drawable.ic_android_black_24dp)
           .into(profileImage);
    setTitle(personDetail.getName());
    nameText.setText(getString(R.string.name_last_name, personDetail.getName(), personDetail.getLastName()));
    birthdateText.setText(VISIBLE_DATE_FORMAT.format(personDetail.getBirthDate()));
    cellphoneText.setText(phoneFormat.format(personDetail.getCellNo()));
    workphoneText.setText(phoneFormat.format(personDetail.getWorkNo()));
    addressText.setText(personDetail.getAddress());
  }

}

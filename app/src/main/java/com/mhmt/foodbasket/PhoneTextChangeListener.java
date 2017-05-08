package com.mhmt.foodbasket;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.terrakok.phonematter.PhoneFormat;


public class PhoneTextChangeListener implements TextWatcher {

  private final PhoneFormat phoneFormat;
  private final EditText editText;

  public PhoneTextChangeListener(final PhoneFormat phoneFormat, final EditText editText) {
    this.phoneFormat = phoneFormat;
    this.editText = editText;
  }

  @Override public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

  }

  @Override public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {

  }

  @Override public void afterTextChanged(final Editable s) {
    if (phoneFormat != null) {
      editText.removeTextChangedListener(this);
      final String formattedString = phoneFormat.format(s.toString());
      editText.setText(formattedString);
      editText.setSelection(formattedString.length());
      editText.addTextChangedListener(this);
    }
  }
}

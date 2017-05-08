package com.mhmt.foodbasket;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;

import com.mhmt.foodbasket.ui.login.LoginActivity;
import com.mhmt.foodbasket.ui.personlist.PersonListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(AndroidJUnit4.class)
public class LoginActivityInstrumentedTest {

  @Rule public final ActivityTestRule<LoginActivity> loginActivity = new ActivityTestRule<>(LoginActivity.class);

  @Test
  public void testUI() {
    onView(withId(R.id.button_login)).check(matches(notNullValue()));
    onView(withId(R.id.edit_text_password)).check(matches(notNullValue()));
    onView(withId(R.id.edit_text_username)).check(matches(notNullValue()));
  }

  @Test
  public void testEmptyCredentialError() {
    onView(withId(R.id.button_login)).perform(click());
    onView(withText(R.string.credentials_error)).inRoot(withDecorView(not(loginActivity.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
  }

  @Test
  public void testOpenNextActivity() {
    Instrumentation.ActivityMonitor
        activityMonitor = getInstrumentation().addMonitor(PersonListActivity.class.getName(), null, false);
    LoginActivity myActivity = loginActivity.getActivity();
    onView(withId(R.id.edit_text_username)).perform(typeText("username"));
    onView(withId(R.id.edit_text_password)).perform(typeText("password"));
    final Button button = (Button) myActivity.findViewById(R.id.button_login);
    myActivity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        button.performClick();
      }
    });
    Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
    assertNotNull(nextActivity);
    nextActivity.finish();
  }

}

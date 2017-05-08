package com.mhmt.foodbasket;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mhmt.foodbasket.ui.personlist.PersonListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(AndroidJUnit4.class)
public class PersonDetailActivityInstrumentedTest {

  @Rule public final ActivityTestRule<PersonListActivity> personListActivity = new ActivityTestRule<>(PersonListActivity.class);


  @Test
  public void testUI() {
    onView(withId(R.id.profile_image)).check(matches(notNullValue()));
    onView(withId(R.id.text_view_name)).check(matches(notNullValue()));
    onView(withId(R.id.text_view_birthdate)).check(matches(notNullValue()));
    onView(withId(R.id.text_view_cellphone)).check(matches(notNullValue()));
    onView(withId(R.id.text_view_workphone)).check(matches(notNullValue()));
    onView(withId(R.id.text_view_address)).check(matches(notNullValue()));
  }

}

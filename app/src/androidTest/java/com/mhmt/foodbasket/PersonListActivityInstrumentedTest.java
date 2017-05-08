package com.mhmt.foodbasket;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.mhmt.foodbasket.ui.persondetail.PersonDetailActivity;
import com.mhmt.foodbasket.ui.personlist.PersonListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(AndroidJUnit4.class)
public class PersonListActivityInstrumentedTest {

  @Rule public final ActivityTestRule<PersonListActivity> personListActivity = new ActivityTestRule<>(PersonListActivity.class);

  @Test
  public void testUI() {
    onView(withId(R.id.text_view_error)).check(matches(notNullValue()));
    onView(withId(R.id.list_recycler_view)).check(matches(notNullValue()));
    onView(withId(R.id.progress_loading_bar)).check(matches(notNullValue()));
  }

  @Test
  public void testItemClickOpensDetailActivity() {
    Instrumentation.ActivityMonitor
        activityMonitor = getInstrumentation().addMonitor(PersonDetailActivity.class.getName(), null, false);
    PersonListActivity activity = personListActivity.getActivity();
    final RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.list_recycler_view);
    while (recyclerView.getChildAt(0) == null) {}
    activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        recyclerView.getChildAt(0).performClick();
      }
    });
    Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
    assertNotNull(nextActivity);
    nextActivity.finish();
  }
}

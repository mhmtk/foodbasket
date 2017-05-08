package com.mhmt.foodbasket;

import com.mhmt.foodbasket.domain.Person;
import com.mhmt.foodbasket.ui.personlist.PersonListAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UnitTests {

  @Test
  public void testEmptyRecyclerView() {
    PersonListAdapter adapter = new PersonListAdapter(new ArrayList<Person>());
    assertEquals(0, adapter.getItemCount());
  }

  @Test
  public void testFilledRecyclerView() {
    final ArrayList<Person> persons = new ArrayList<>();
    final int count = 7;
    for (int i = 0; i < 7; i++) {
      persons.add(new Person());
    }
    PersonListAdapter adapter = new PersonListAdapter(persons);
    assertEquals(count, adapter.getItemCount());
  }

}
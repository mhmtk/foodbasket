package com.mhmt.foodbasket.ui.personlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mhmt.foodbasket.R;
import com.mhmt.foodbasket.domain.Person;

import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonViewHolder> {

  private List<Person> personList;

  public PersonListAdapter(final List<Person> personList) {
    this.personList = personList;
  }

  @Override public PersonViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
    return new PersonViewHolder(LayoutInflater.from(parent.getContext())
                                                  .inflate(R.layout.list_item_person,
                                                           parent,
                                                           false));
  }

  @Override public void onBindViewHolder(final PersonViewHolder holder, final int position) {
    holder.bind(personList.get(position));
  }

  @Override public int getItemCount() {
    return personList.size();
  }

  public void updateData(final List<Person> persons) {
    this.personList = persons;
    notifyDataSetChanged();
  }
}

package com.mhmt.foodbasket.ui.personlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mhmt.foodbasket.Injector;
import com.mhmt.foodbasket.R;
import com.mhmt.foodbasket.domain.Person;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  @BindView(R.id.text_view_name)
  protected TextView nameText;
  @BindView(R.id.profile_image)
  protected CircleImageView profileImageView;
  private int personId;

  @Inject protected EventBus eventbus;

  public PersonViewHolder(final View itemView) {
    super(itemView);
    Injector.getViewHolderComponent().inject(this);
    ButterKnife.bind(this, itemView);
    itemView.setOnClickListener(this);
  }

  public void bind(final Person person) {
    this.personId = person.getId();
    nameText.setText(itemView.getContext().getString(R.string.name_last_name, person.getName(), person.getLastName()));
    Picasso.with(itemView.getContext()).load(person.getProfilePicture()).placeholder(R.drawable.ic_android_black_24dp).into(profileImageView);
  }

  @Override public void onClick(final View v) {
    eventbus.post(new PersonItemClicked(personId));
  }
}

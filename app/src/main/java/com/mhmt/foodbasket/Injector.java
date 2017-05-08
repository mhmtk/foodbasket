package com.mhmt.foodbasket;

import com.mhmt.foodbasket.network.DaggerNetworkJobComponent;
import com.mhmt.foodbasket.network.NetworkJobComponent;
import com.mhmt.foodbasket.ui.DaggerPresenterComponent;
import com.mhmt.foodbasket.ui.DaggerViewHolderComponent;
import com.mhmt.foodbasket.ui.PresenterComponent;
import com.mhmt.foodbasket.ui.ViewHolderComponent;

public final class Injector {

  private static PresenterComponent presenterComponent;
  private static NetworkJobComponent networkJobComponent;
  private static ViewHolderComponent viewHolderComponent;

  public static PresenterComponent getPresenterComponent() {
    if (presenterComponent == null) {
      presenterComponent = DaggerPresenterComponent.create();
    }
    return presenterComponent;
  }

  public static NetworkJobComponent getNetworkJobComponent() {
    if (networkJobComponent == null) {
      networkJobComponent = DaggerNetworkJobComponent.create();
    }
    return networkJobComponent;
  }

  public static ViewHolderComponent getViewHolderComponent() {
    if (viewHolderComponent == null) {
      viewHolderComponent = DaggerViewHolderComponent.create();
    }
    return viewHolderComponent;
  }
}

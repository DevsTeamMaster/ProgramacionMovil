// Generated by view binder compiler. Do not edit!
package com.devsteammaster.poi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.devsteammaster.poi.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ExtendedFloatingActionButton floatGotoListButton;

  @NonNull
  public final FloatingActionButton floatSettingsButton;

  @NonNull
  public final ImageView imageViewPrincipalView;

  private FragmentHomeBinding(@NonNull ConstraintLayout rootView,
      @NonNull ExtendedFloatingActionButton floatGotoListButton,
      @NonNull FloatingActionButton floatSettingsButton,
      @NonNull ImageView imageViewPrincipalView) {
    this.rootView = rootView;
    this.floatGotoListButton = floatGotoListButton;
    this.floatSettingsButton = floatSettingsButton;
    this.imageViewPrincipalView = imageViewPrincipalView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.float_goto_list_button;
      ExtendedFloatingActionButton floatGotoListButton = ViewBindings.findChildViewById(rootView, id);
      if (floatGotoListButton == null) {
        break missingId;
      }

      id = R.id.float_settings_button;
      FloatingActionButton floatSettingsButton = ViewBindings.findChildViewById(rootView, id);
      if (floatSettingsButton == null) {
        break missingId;
      }

      id = R.id.imageView_principal_view;
      ImageView imageViewPrincipalView = ViewBindings.findChildViewById(rootView, id);
      if (imageViewPrincipalView == null) {
        break missingId;
      }

      return new FragmentHomeBinding((ConstraintLayout) rootView, floatGotoListButton,
          floatSettingsButton, imageViewPrincipalView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

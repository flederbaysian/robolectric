package org.robolectric.shadows;

import android.widget.NumberPicker;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;

import static org.robolectric.shadow.api.Shadow.directlyOn;

@Implements(value = NumberPicker.class)
public class ShadowNumberPicker extends ShadowLinearLayout {
  @RealObject
  private NumberPicker realObject;
  private int value;
  private int minValue;
  private int maxValue;
  private boolean wrapSelectorWheel;
  private String[] displayedValues;
  private NumberPicker.OnValueChangeListener onValueChangeListener;

  @Implementation
  protected void setValue(int value) {
    this.value = value;
  }

  @Implementation
  protected int getValue() {
    return value;
  }

  @Implementation
  protected void setDisplayedValues(String[] displayedValues) {
    this.displayedValues = displayedValues;
  }

  @Implementation
  protected String[] getDisplayedValues() {
    return displayedValues;
  }

  @Implementation
  protected void setMinValue(int minValue) {
    this.minValue = minValue;
  }

  @Implementation
  protected void setMaxValue(int maxValue) {
    this.maxValue = maxValue;
  }

  @Implementation
  protected int getMinValue() {
    return this.minValue;
  }

  @Implementation
  protected int getMaxValue() {
    return this.maxValue;
  }

  @Implementation
  protected void setWrapSelectorWheel(boolean wrapSelectorWheel) {
    this.wrapSelectorWheel = wrapSelectorWheel;
  }

  @Implementation
  protected boolean getWrapSelectorWheel() {
    return wrapSelectorWheel;
  }

  @Implementation
  protected void setOnValueChangedListener(NumberPicker.OnValueChangeListener listener) {
    directlyOn(realObject, NumberPicker.class).setOnValueChangedListener(listener);
    this.onValueChangeListener = listener;
  }

  public NumberPicker.OnValueChangeListener getOnValueChangeListener() {
    return onValueChangeListener;
  }
}

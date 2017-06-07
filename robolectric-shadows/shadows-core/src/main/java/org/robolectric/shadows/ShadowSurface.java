package org.robolectric.shadows;

import android.view.Surface;
import android.graphics.SurfaceTexture;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

@Implements(Surface.class)
public class ShadowSurface {
  private SurfaceTexture surfaceTexture;

  @Implementation
  protected void __constructor__(SurfaceTexture surfaceTexture) {
    this.surfaceTexture = surfaceTexture;
  }

  public SurfaceTexture getSurfaceTexture() {
    return surfaceTexture;
  }
}

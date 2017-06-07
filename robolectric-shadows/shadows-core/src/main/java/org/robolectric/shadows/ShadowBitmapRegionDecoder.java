package org.robolectric.shadows;

import android.graphics.*;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.util.ReflectionHelpers;

import java.io.*;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.robolectric.RuntimeEnvironment.getApiLevel;
import static org.robolectric.Shadows.shadowOf;

@Implements(BitmapRegionDecoder.class)
public class ShadowBitmapRegionDecoder {
  private int width;
  private int height;

  @Implementation
  protected static BitmapRegionDecoder newInstance(byte[] data, int offset, int length, boolean isShareable) throws IOException {
    return fillWidthAndHeight(newInstance(), new ByteArrayInputStream(data));
  }

  @Implementation
  protected static BitmapRegionDecoder newInstance(FileDescriptor fd, boolean isShareable) throws IOException {
    return fillWidthAndHeight(newInstance(), new FileInputStream(fd));
  }

  @Implementation
  protected static BitmapRegionDecoder newInstance(InputStream is, boolean isShareable) throws IOException {
    return fillWidthAndHeight(newInstance(), is);
  }

  @Implementation
  protected static BitmapRegionDecoder newInstance(String pathName, boolean isShareable) throws IOException {
    return fillWidthAndHeight(newInstance(), new FileInputStream(pathName));
  }

  private static BitmapRegionDecoder fillWidthAndHeight(BitmapRegionDecoder bitmapRegionDecoder, InputStream is) {
    ShadowBitmapRegionDecoder shadowDecoder = shadowOf(bitmapRegionDecoder);
    Point imageSize = ImageUtil.getImageSizeFromStream(is);
    if (imageSize != null) {
      shadowDecoder.width = imageSize.x;
      shadowDecoder.height = imageSize.y;
    }
    return bitmapRegionDecoder;
  }

  @Implementation
  protected int getWidth() {
    return width;
  }

  @Implementation
  protected int getHeight() {
    return height;
  }

  @Implementation
  protected Bitmap decodeRegion(Rect rect, BitmapFactory.Options options) {
    return Bitmap.createBitmap(rect.width(), rect.height(),
        options.inPreferredConfig != null ? options.inPreferredConfig : Bitmap.Config.ARGB_8888);
  }

  private static BitmapRegionDecoder newInstance() {
    if (getApiLevel() >= LOLLIPOP) {
      return ReflectionHelpers.callConstructor(BitmapRegionDecoder.class,
          new ReflectionHelpers.ClassParameter<>(long.class, 0L));
    } else {
      return ReflectionHelpers.callConstructor(BitmapRegionDecoder.class,
          new ReflectionHelpers.ClassParameter<>(int.class, 0));
    }
  }
}

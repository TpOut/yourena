High Efficiency Image File (HEIF) , HEIC  



AdobeRGB, Pro Photo RGB, DCI-P3,  

ImageDecoder   替代BitmapFactory.Option  



`ImageDecoder` lets you create a [`Drawable`](https://developer.android.google.cn/reference/android/graphics/drawable/Drawable) or a [`Bitmap`](https://developer.android.google.cn/reference/android/graphics/Bitmap) from a byte buffer, a file, or a URI. To decode an image, first call [`createSource()`](https://developer.android.google.cn/reference/android/graphics/ImageDecoder#createSource(java.nio.ByteBuffer)) with the source of the encoded image. Then, call [`decodeDrawable()`](https://developer.android.google.cn/reference/android/graphics/ImageDecoder#decodeDrawable(android.graphics.ImageDecoder.Source)) or [`decodeBitmap()`](https://developer.android.google.cn/reference/android/graphics/ImageDecoder#decodeBitmap(android.graphics.ImageDecoder.Source)) by passing the [`ImageDecoder.Source`](https://developer.android.google.cn/reference/android/graphics/ImageDecoder.Source) object to create a [`Drawable`](https://developer.android.google.cn/reference/android/graphics/drawable/Drawable) or a [`Bitmap`](https://developer.android.google.cn/reference/android/graphics/Bitmap). To change the default settings, pass [`OnHeaderDecodedListener`](https://developer.android.google.cn/reference/android/graphics/ImageDecoder.OnHeaderDecodedListener) to `decodeDrawable()` or `decodeBitmap()`. `ImageDecoder` calls [`onHeaderDecoded()`](https://developer.android.google.cn/reference/android/graphics/ImageDecoder.OnHeaderDecodedListener#onHeaderDecoded(android.graphics.ImageDecoder, android.graphics.ImageDecoder.ImageInfo, android.graphics.ImageDecoder.Source)) with the image's default width and height, once they are known. If the encoded image is an animated GIF or WebP, `decodeDrawable()` returns a `Drawable` that is an instance of the [`AnimatedImageDrawable`](https://developer.android.google.cn/reference/android/graphics/drawable/AnimatedImageDrawable) class.

[`setTargetSize()`](https://developer.android.google.cn/reference/android/graphics/ImageDecoder#setTargetSize(int, int)). `setTargetSampleSize()`.`setCrop`  `setMutableRequired`  



[`AnimatedImageDrawable`](https://developer.android.google.cn/reference/android/graphics/drawable/AnimatedImageDrawable)




OpenGL ES 3.1 adds compute shaders, stencil textures, accelerated visual effects, high quality ETC2/EAC texture compression, advanced texture rendering, standardized texture size and render-buffer formats, and more.  



- Compute shaders
- Separate shader objects
- Indirect draw commands
- Multisample and stencil textures
- Shading language improvements
- Extensions for advanced blend modes and debugging
- Backward compatibility with OpenGL ES 2.0 and 3.0



Android Extension Pack (AEP)  

- Guaranteed fragment shader support for shader storage buffers, images, and atomics (Fragment shader support is optional in OpenGL ES 3.1.)
- Tessellation and geometry shaders
- ASTC (LDR) texture compression format
- Per-sample interpolation and shading
- Different blend modes for each color attachment in a frame buffer



```shell
<manifest>
    <uses-feature android:glEsVersion="0x00030001" />
     <uses-feature android:name=“android.hardware.opengles.aep”
        android:required="true" />
    ...
</manifest>
```



- All extensions from the [Android Extension Pack](https://www.khronos.org/registry/gles/extensions/ANDROID/ANDROID_extension_pack_es31a.txt) (AEP) except for `EXT_texture_sRGB_decode`.
- Floating-point framebuffers for HDR and deferred shading.
- BaseVertex draw calls to enable better batching and streaming.
- Robust buffer access control to reduce WebGL overhead.


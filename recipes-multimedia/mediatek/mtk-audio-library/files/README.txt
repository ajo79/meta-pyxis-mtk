Place the vendor-provided MediaTek audio library archive in this directory.

Expected name/layout:
  - File: mtk-audio-library-1.0.tar.gz (adjust PV/SRC_URI in the recipe if you use a different version)
  - Contents: lib/ with shared objects, include/ with headers, optional LICENSE file

The recipe installs libraries into /usr/lib and headers into /usr/include/mediatek/audio.
If your archive layout differs, update do_install() in the recipe accordingly.

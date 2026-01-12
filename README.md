# meta-pyxis-mtk

Custom Yocto layer for the MediaTek Genio 350 EVK with Chromium, Wayland VNC access, and a WebRTC/RTSP-ready GStreamer stack that can integrate the MediaTek audio library.

## Contents
- Distro config `conf/distro/pn-mtk.conf` (systemd, Wayland, PulseAudio, OpenGL, commercial codecs enabled).
- Chromium tuning for Ozone/Wayland + VAAPI via `recipes-browser/chromium/chromium-ozone-wayland_%.bbappend`.
- Wayland VNC server configuration + systemd template in `recipes-graphics/vnc/wayvnc-config`.
- GStreamer WebRTC/RTSP options enabled via bbappends and packagegroups for media + browser + remote access.
- Drop-in recipe for the proprietary MediaTek audio library (`recipes-multimedia/mediatek/mtk-audio-library`).
- Image recipe `recipes-core/images/pyxis-genio350-image.bb` that pulls everything together.

## Layer dependencies
Add these layers before adding this one (tested with scarthgap):
- `poky` (meta, meta-poky)
- `meta-openembedded/meta-oe`
- `meta-browser`
- MediaTek Genio BSP layer that provides the Genio 350 EVK machine (e.g., `meta-mediatek` or vendor BSP)

## Quick start
```
$ source oe-init-build-env build-genio350
$ bitbake-layers add-layer ../meta-openembedded/meta-oe ../meta-browser ../meta-mediatek ../meta-pyxis-mtk
$ echo 'DISTRO = "pn-mtk"' >> conf/local.conf
$ echo 'MACHINE = "genio350-evk"' >> conf/local.conf   # or vendor machine name
# Drop your MediaTek audio tarball: recipes-multimedia/mediatek/mtk-audio-library/files/mtk-audio-library-1.0.tar.gz
$ bitbake pyxis-genio350-image
```

## MediaTek audio library drop-in
- Place the vendor-provided archive at `recipes-multimedia/mediatek/mtk-audio-library/files/mtk-audio-library-1.0.tar.gz` (or adjust `PV`/`SRC_URI` in the recipe to match your filename).
- The recipe installs `lib*` from the tarball into `/usr/lib` and headers from `include/` into `/usr/include/mediatek/audio`. Update the recipe if your archive layout differs.

## Runtime notes
- VNC: edit `/etc/wayvnc/wayvnc.ini` as needed, then `systemctl enable --now wayvnc@root.service` (or another user). Create a password file and point to it in the config before enabling on untrusted networks.
- Chromium: use Wayland/VAAPI flags from `${PYXIS_CHROMIUM_FLAGS}` (set in `pn-mtk.conf`). Example: `chromium --ozone-platform=wayland --enable-features=VaapiVideoDecoder`.
- GStreamer WebRTC/RTSP examples (adjust devices/payloads):
  - Publish RTSP: `gst-launch-1.0 v4l2src ! videoconvert ! x264enc tune=zerolatency ! rtph264pay name=pay0 pt=96 ! rtspclientsink location=rtsp://<server>/stream`
  - WebRTC send/recv building block: `gst-launch-1.0 webrtcbin bundle-policy=max-bundle name=pc v4l2src ! videoconvert ! vp8enc deadline=1 ! rtpvp8pay pt=96 ! pc. autoaudiosrc ! audioconvert ! opusenc ! rtpopuspay pt=97 ! pc.`

## Image contents
`pyxis-genio350-image` pulls:
- Chromium Ozone/Wayland (from meta-browser)
- WayVNC + config/systemd template
- GStreamer base/good/bad/ugly/libav, RTSP server, libnice, libsrtp2, WebRTC audio processing
- MediaTek audio library package placeholder
- Weston/Wayland session, SSH server, and package management

## Licensing
Layer files use MIT (see `COPYING.MIT`). MediaTek audio artifacts remain under their vendor license.

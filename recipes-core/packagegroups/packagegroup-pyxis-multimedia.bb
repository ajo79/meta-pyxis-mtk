# SPDX-License-Identifier: MIT

SUMMARY = "Pyxis multimedia stack with WebRTC/RTSP"
DESCRIPTION = "GStreamer with WebRTC/RTSP, media codecs, and MediaTek audio integration."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS:${PN} = " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-ugly \
    gstreamer1.0-libav \
    gstreamer1.0-rtsp-server \
    libnice \
    libsrtp2 \
    webrtc-audio-processing \
    pulseaudio-server \
    alsa-utils-alsactl \
    alsa-utils-amixer \
    mtk-audio-library \
"

RRECOMMENDS:${PN} = " \
    usrsctp \
"

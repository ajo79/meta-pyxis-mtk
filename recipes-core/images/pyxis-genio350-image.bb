# SPDX-License-Identifier: MIT

SUMMARY = "Pyxis Genio 350 EVK image with Chromium, VNC, and WebRTC/RTSP"
DESCRIPTION = "Wayland system image that includes Chromium Ozone/Wayland, WayVNC, and a WebRTC/RTSP-enabled GStreamer stack with MediaTek audio support."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit core-image

REQUIRED_DISTRO_FEATURES = "wayland systemd"

IMAGE_FEATURES:append = " package-management ssh-server-openssh"

IMAGE_INSTALL:append = " \
    packagegroup-pyxis-browser \
    packagegroup-pyxis-multimedia \
    packagegroup-pyxis-remote \
"

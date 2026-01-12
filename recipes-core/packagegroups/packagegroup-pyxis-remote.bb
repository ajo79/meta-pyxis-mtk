# SPDX-License-Identifier: MIT

SUMMARY = "Pyxis remote GUI access (Wayland VNC)"
DESCRIPTION = "WayVNC configuration and Weston runtime pieces."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS:${PN} = " \
    wayvnc \
    wayvnc-config \
    weston \
    weston-init \
    xwayland \
"

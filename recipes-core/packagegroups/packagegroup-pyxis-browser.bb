# SPDX-License-Identifier: MIT

SUMMARY = "Pyxis browser stack for Genio 350"
DESCRIPTION = "Chromium Ozone/Wayland and supporting runtime pieces."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS:${PN} = " \
    chromium-ozone-wayland \
    ca-certificates \
    fontconfig \
"

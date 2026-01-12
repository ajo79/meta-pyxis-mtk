# SPDX-License-Identifier: MIT

DESCRIPTION = "Configuration and systemd template for WayVNC on Wayland/Weston"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://wayvnc.ini \
    file://wayvnc@.service \
"

S = "${WORKDIR}"

inherit allarch systemd

SYSTEMD_SERVICE:${PN} = "wayvnc@.service"
SYSTEMD_AUTO_ENABLE = "disable"

RDEPENDS:${PN} = "wayvnc"

do_install() {
    install -d ${D}${sysconfdir}/wayvnc
    install -d ${D}${systemd_system_unitdir}

    install -m 0644 ${WORKDIR}/wayvnc.ini ${D}${sysconfdir}/wayvnc/wayvnc.ini
    install -m 0644 ${WORKDIR}/wayvnc@.service ${D}${systemd_system_unitdir}/wayvnc@.service
}

FILES:${PN} = " \
    ${sysconfdir}/wayvnc/wayvnc.ini \
    ${systemd_system_unitdir}/wayvnc@.service \
"

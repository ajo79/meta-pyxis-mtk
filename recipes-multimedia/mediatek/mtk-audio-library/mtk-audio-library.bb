# SPDX-License-Identifier: MIT

SUMMARY = "MediaTek proprietary audio library drop-in for Genio 350"
DESCRIPTION = "Packs the vendor-provided MediaTek audio library tarball for use with GStreamer and Chromium on Genio 350 EVK."
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://README.txt;md5=CED861DA27C0892DBBBE41DB155835D3"

SRC_URI = "file://mtk-audio-library-${PV}.tar.gz \
           file://README.txt"

S = "${WORKDIR}/mtk-audio-library-${PV}"

inherit pkgconfig

COMPATIBLE_MACHINE = "(genio350|mt8365)"

ALLOW_EMPTY:${PN} = "1"
INSANE_SKIP:${PN} += "ldflags textrel already-stripped"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${libdir}
    install -d ${D}${includedir}/mediatek/audio
    install -d ${D}${datadir}/licenses/${PN}

    if [ -d ${S}/lib ]; then
        cp -a ${S}/lib/* ${D}${libdir}/
    fi

    if [ -d ${S}/include ]; then
        cp -a ${S}/include/* ${D}${includedir}/mediatek/audio/
    fi

    if [ -f ${S}/LICENSE ]; then
        install -m 0644 ${S}/LICENSE ${D}${datadir}/licenses/${PN}/LICENSE
    fi
}

FILES:${PN} += " \
    ${libdir}/* \
    ${datadir}/licenses/${PN}/LICENSE \
"

FILES:${PN}-dev += " \
    ${includedir}/mediatek/audio \
"

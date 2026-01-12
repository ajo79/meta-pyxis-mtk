# SPDX-License-Identifier: MIT

# Ensure Chromium is built for Wayland with hardware video decode.
PACKAGECONFIG:append = " ozone-wayland proprietary-codecs vaapi"

# Align with gstreamer/WebRTC usage; drop X11 if it was selected elsewhere.
PACKAGECONFIG:remove = " x11 X11"

# Default runtime flags are provided via ${PYXIS_CHROMIUM_FLAGS} in pn-mtk.conf.

# SPDX-License-Identifier: MIT

# Enable WebRTC/RTSP-friendly options.
PACKAGECONFIG:append = " webrtc srtp dtls openh264 uvch264 wayland opus"

# Prefer Wayland over X11 where both are present.
PACKAGECONFIG:remove = " x11 X11"

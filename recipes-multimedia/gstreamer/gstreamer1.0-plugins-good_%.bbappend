# SPDX-License-Identifier: MIT

# Soup enables rtspsrc/rtspclientsink; PulseAudio ensures working audio path.
PACKAGECONFIG:append = " pulseaudio soup"

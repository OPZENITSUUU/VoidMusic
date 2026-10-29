<div align="center">

# VOID MUSIC

### Find your sound. Own your listening.

A customizable music client with flexible sources, detailed playback controls, synced lyrics, an artwork-rich player and a library that stays yours.

<p>
  <a href="https://github.com/OPZENITSUUU/VoidMusic/releases/latest/download/Void.Music.apk"><img src="https://img.shields.io/badge/GET%20VOID%20MUSIC-Latest%20Android%20APK-8B5CF6?style=for-the-badge&logo=android&logoColor=white" alt="Download the latest Android APK" /></a>
</p>

<p>
  <a href="https://github.com/OPZENITSUUU/VoidMusic/releases"><img src="https://img.shields.io/github/v/release/OPZENITSUUU/VoidMusic?style=for-the-badge&label=LATEST%20RELEASE&labelColor=151521" alt="Latest release" /></a>
  <a href="https://github.com/OPZENITSUUU/VoidMusic/releases"><img src="https://img.shields.io/github/downloads/OPZENITSUUU/VoidMusic/total?style=for-the-badge&label=DOWNLOADS&labelColor=151521" alt="Total downloads" /></a>
  <a href="https://github.com/OPZENITSUUU/VoidMusic/blob/main/LICENSE"><img src="https://img.shields.io/github/license/OPZENITSUUU/VoidMusic?style=for-the-badge&labelColor=151521" alt="License" /></a>
</p>

[Features](#-feature-tour) · [Download](#-download) · [Audio quality](#-playback--audio) · [Build](#-build-from-source) · [Contribute](#-support--contributing)

<br/>

<img src="Banner.png" alt="Void Music banner" width="100%" />

</div>

---

## What is Void Music?

Void Music is an independent music-client distribution built around a simple idea: **your player should give you control, not hide how it works.** Discover music through supported catalogues, configure compatible sources, shape the sound, follow lyrics, manage a personal library and understand the stream the player actually receives.

The Android app is the primary published build. The repository also contains source for additional targets, including Android TV, Wear OS, desktop and a web player with a companion browser extension. These targets have different build and release states; see [Platform availability](#-platform-availability).

> **Important:** a feature being present in the code does not make every third-party service or source permanently available. Login, catalogue access, formats, downloads and quality depend on the selected provider, its authorization rules, your device and your output route.

## 🎧 Playback & audio

Void Music combines a media player with a source-resolution layer. The selected source determines what can actually be played; playback settings control what the app requests and how it handles the resulting stream.

### Playback controls

- **Background listening:** persistent media session and system playback controls.
- **Queue control:** add, remove, clear and reorder queued tracks, plus shuffle and repeat modes.
- **Crossfade:** configurable transition length from **0 to 12 seconds**.
- **Automix (Beta):** beat/downbeat analysis and tempo-aware transitions where the track and analysis data allow.
- **Playback speed:** **0.5×–2.0×**.
- **Skip silence** and a **sleep timer**, including stop-after-current-track behaviour.
- **Playback fallback and rendition upgrades:** use compatible alternative streams when a configured source can resolve them.
- **Per-network quality ceilings:** independent preferences for Wi-Fi and mobile data.

### Audio formats, output & diagnostics

- **Stats for Nerds:** inspect the selected input format, including available codec, sample-rate and bit-depth information.
- **Audio pipeline/output views:** review format and output-route information exposed by the current device and player.
- **Quality-aware source choice:** an optional best-source preference compares available source metadata; it is not a guarantee that every song has a better or lossless alternative.
- **System equalizer integration** plus app playback-processing controls.
- **USB/direct-output paths** where supported by the hardware, Android version and connected device.
- **Extended codec decoding:** the project includes native decoding support for additional formats, including ALAC and selected Dolby/DTS-family formats. Actual decoding and output depend on the build, input stream, licensing/device support and route.
- **Spatial-audio processing** and output capability checks.
- **Bluetooth capability awareness:** the app distinguishes reported codec labels from proof that a particular route can deliver the requested fidelity.

> [!IMPORTANT]
> **Lossless, Hi-Res and Dolby Atmos are conditional capabilities, not universal promises.** Void Music cannot turn a lossy stream into genuine lossless audio. A source badge, file extension or reported bitrate alone does not prove bit-perfect output. The source must deliver the format, and the decoder, device and output route must support it.

## 🔌 Sources & service integrations

### Source choices

- **YouTube Music integration** for supported search, browsing and playback.
- **JioSaavn source integration** where its service endpoints are available.
- **Wikimedia Commons** for supported public media.
- **Add-on sources:** configure, enable/disable, reorder, test and health-check compatible source endpoints.
- **JavaScript modules:** load compatible modules through the project's QuickJS-based module runtime.
- **Source matching:** compare candidate tracks and renditions using available metadata before selecting an alternative.
- **Provider-aware permissions:** source manifests and service rules may limit quality options, playback or downloads.

A configured source is not automatically a verified working provider. It may require private credentials, account authorization or service-side changes; some integrations may be unavailable. Use only sources and content you are authorized to access.

### Connected services

- **YouTube Music / Google sign-in:** access supported account and profile features.
- **Spotify integration:** supported library, playlist, Liked Songs and Canvas-related features when the account connection and integration are available. Audio playback still depends on a supported playable source.
- **Discord Rich Presence:** share current track, artist and progress when connected and enabled.
- **Last.fm:** scrobbling and now-playing updates, with configurable behaviour.
- **ListenBrainz:** scrobbling when configured.
- **Google Cast:** send playback to supported Cast targets.
- **Listen Together:** party codes/invites, shared listening state and queue coordination through a reachable party server; a custom server can be configured.
- **SMB and WebDAV:** connect compatible network shares or remote libraries.

All account integrations are optional. Third-party sign-in, API access and server availability are controlled partly by the respective service, not solely by Void Music.

## 🎼 Lyrics, Canvas & personal touches

### Lyrics

- Select between available lyric providers.
- Display timed lyrics with playback-aware line highlighting and word-level highlighting when provider timing supports it.
- Adjust lyric timing offset for a track.
- Translate displayed lyrics on demand into the language selected in the app.
- Switch between original and translated lyrics without intentionally replacing the original text or its timeline.
- Create shareable lyric cards where the current lyrics support sharing.

Translation uses an online provider and needs connectivity. Translation timing is an approximate projection from the source lyrics; word boundaries cannot always map perfectly between languages. If a translation request fails, the original lyrics remain the reference.

### Artwork and visual design

- Artwork-colour-driven palettes and mesh-gradient backgrounds.
- Animated artwork/Canvas sources when available, including supported Spotify, Apple Music and community artwork paths.
- Artwork-aware player surfaces, frosted-glass styling and Material 3 components.
- Full-screen and landscape player layouts where the window/device supports them.
- Artist signature artwork where configured.
- System, light and dark appearance choices, plus animation and player-display preferences.

## 📥 Downloads, cache & storage

### Download quality

The download quality preference includes these rungs:

| Mode | Behaviour |
| --- | --- |
| **Standard** | A smaller, standard-quality download where the selected source supports it. |
| **High** | Prefer the best available source rendition within the supported source path. |
| **Lossless** | Keep a lossless source file when one is genuinely available; otherwise use the best supported fallback. |

Actual file format and quality depend on the provider and the specific track. Some add-ons explicitly disable downloads; Void Music should respect the source's permission declaration.

Downloads support media metadata/tagging paths for compatible formats, including lyric and FLAC-tag handling where available. Downloaded files and source caches are separate: clearing playback cache should not be confused with deleting the download library.

### Add-on prefetch modes

| Mode | Upcoming add-on tracks | Best for |
| --- | ---: | --- |
| **Standard** | 1 | Lower data use and a smaller prefetch footprint. |
| **Aggressive** | Up to 5 | Preparing more of the queue ahead of transitions. |

Prefetch relies on queue contents, source response, storage and network conditions. It can warm up eligible tracks and resolve work earlier, but it cannot promise gap-free playback when a provider is down or a track is unavailable.

### Cache and personal libraries

- Configurable audio-cache storage budget, with automatic least-recently-used eviction.
- Cached-song view and cache clearing.
- Local device music with song/artist/album grouping.
- Supported SMB and WebDAV library access.
- Download collections, multi-select actions and compatible transfer actions for configured remote storage.

## 📊 Replay, history & data ownership

- **Replay/listening summaries:** browse local listening aggregates across available periods.
- Listening time and play counts, with track, artist and album summaries and day-level patterns where recorded.
- Listening history stored on the device by the Replay subsystem.
- **JSON backup/restore:** export settings and listening data into a file you choose, then restore it on a compatible build.
- **CSV history export/import:** move listening-history aggregates through a spreadsheet-friendly format.

For safety, authentication credentials are not included in the ordinary JSON settings backup. CSV is intended for listening history, not account credentials or every app setting. Backups do not package downloaded audio or playback cache, so move those separately if you need them.

## 🤝 Listen Together

Listen Together is designed for shared sessions rather than just passing someone a song link.

- Join or create a session using a party code or invitation.
- Coordinate playback state and queue actions with other participants when the session server is reachable.
- See supported session/member information in the player.
- Configure a custom party-server URL and check server connectivity.
- Share or copy the invite information from the session screen.

The feature depends on a compatible, reachable party server and supported account/session state. Connection failure can interrupt synchronization even if local playback still works.

## 🎨 Settings & customization

Void Music exposes settings so the experience can be tuned to the device and the listener:

- System/light/dark appearance and supported app-language preferences.
- Home-shelf and home-content visibility options.
- Playback speed, shuffle/repeat, silence skipping, crossfade and sleep timer.
- Separate Wi-Fi/mobile audio-quality preferences.
- Standard/Aggressive add-on prefetch and audio-cache budget.
- Source ordering, enable/disable state and source health checks.
- Lyrics provider selection, translation and timing offset.
- Equalizer, output-route and audio-pipeline options.
- Player display preferences, including supported artwork and animation choices.
- Account connections, scrobbling, Rich Presence and Listen Together server settings.
- Data backup, history export/import and cache management.

Setting availability can vary with app version, operating system, hardware and configured integrations.

## 📱 Platform availability

The repository contains more than one target, but **the current Void Music Releases page publishes the Android APK**. The presence of source code for another platform is not the same thing as a ready-to-download Void Music build.

| Target | What the source includes | Current distribution note |
| --- | --- | --- |
| **Android phone/tablet** | Main music app, player, sources, lyrics, library, integrations and downloads. | **Published:** install the APK from the Releases page. |
| **Android TV / Google TV** | Remote-first home, library, search, settings, full-screen player, synced lyrics, display-refresh-rate options, spatial audio and QR sign-in. | Source target; build instructions below. |
| **Wear OS companion** | Watch-facing now-playing and playback components connected to the phone app. | Companion source is included; compatibility depends on the paired phone/watch builds. |
| **Desktop** | Compose Multiplatform desktop code for Windows, Linux and macOS, with desktop playback, local music, lyrics, downloads, equalizer and desktop integrations. | Source target; installers are not attached to the current Void Music Android release. Check branding and updater configuration before distributing a custom desktop build. |
| **Web player + browser extension** | React/TypeScript web client and Chromium/Firefox extension source, with a page-to-extension bridge. | Development source is included. It is not the Android APK and should be built/deployed separately. |

## ⬇️ Download

<div align="center">

<a href="https://github.com/OPZENITSUUU/VoidMusic/releases/latest/download/Void.Music.apk"><img src="https://img.shields.io/badge/Download-Void.Music.apk-8B5CF6?style=for-the-badge&logo=android&logoColor=white" alt="Download Void.Music.apk" /></a>

**[Open all releases](https://github.com/OPZENITSUUU/VoidMusic/releases)** · **[Report a problem](https://github.com/OPZENITSUUU/VoidMusic/issues)**

</div>

### Install on Android

1. Open the [latest release](https://github.com/OPZENITSUUU/VoidMusic/releases/latest).
2. Download **Void.Music.apk** from the release assets.
3. Open the APK. If prompted, allow your browser or file manager to install apps from that source.
4. Install and open Void Music, then configure the sources and optional services you use.

**Minimum Android version:** Android 8.0 (API 26) or newer.

Android only accepts an in-place update when the package identity and signing certificate are compatible. Back up important data before changing signing tracks or uninstalling an older build. Some older beta packages signed with a different key cannot be upgraded in place.

## 🧑‍💻 Build from source

### Android app

**Requirements:** JDK 17+, Android SDK and the native build components requested by the Gradle project.

<pre><code>git clone https://github.com/OPZENITSUUU/VoidMusic.git
cd VoidMusic
./gradlew :app:assemblePersonalDebug</code></pre>

The personal debug APK is created under <code>app/build/outputs/apk/personal/debug/</code>.

Run the corresponding unit tests:

<pre><code>./gradlew :app:testPersonalDebugUnitTest :sharedUi:jvmTest</code></pre>

For an Android TV development build:

<pre><code>./gradlew :tv:assembleProdDebug</code></pre>

The TV module is included when a compatible Android SDK is available.

### Desktop target

The desktop build uses **JDK 21**. With the required platform libraries installed:

<pre><code>./gradlew :desktopApp:run</code></pre>

Desktop packaging uses native tooling for the platform being built. The current Void Music release page should not be assumed to contain desktop installers.

### Web player and browser extension

The web source requires **Node.js 22 or newer**. <code>pnpm</code> is provided via Corepack.

<pre><code>corepack pnpm --dir web install
corepack pnpm --dir web/app dev</code></pre>

Build the browser extension targets:

<pre><code>corepack pnpm --dir web/extension build</code></pre>

For the full extension setup, browser loading instructions, environment configuration and deployment details, see [web/README.md](web/README.md).

### Keep signing material private

Release builds require a release key and suitable configuration. Signing keystores, passwords, service tokens and private addon URLs must never be committed to the repository. A successful debug build is not proof that a production release has been signed, published or tested on a real device.

## 🧱 Project map

| Directory | Purpose |
| --- | --- |
| <code>app/</code> | Android app, playback service, source registry, downloads, settings and platform integrations |
| <code>shared/</code> | Shared data and supporting logic |
| <code>sharedUi/</code> | Shared player, lyrics and discovery/library UI |
| <code>wearos/</code> | Wear OS companion and phone/watch protocol |
| <code>tv/</code> | Android TV-specific UI and resources |
| <code>desktopApp/</code> | Desktop application and native platform integration |
| <code>desktopUi/</code> | UI shared with the desktop target |
| <code>web/app/</code> | Web player application |
| <code>web/extension/</code> | Chromium/Firefox companion extension |
| <code>backend/</code> | Listen Together server implementation and deployment notes |
| <code>docs/</code> | Design, implementation and platform documentation |

### Built with

- **Kotlin** and **Jetpack Compose / Compose Multiplatform**
- **AndroidX Media3** for the Android playback/session foundation
- **Ktor** for supported networking flows
- **QuickJS** for compatible JavaScript module sources
- **Native audio components** for extended decoding and audio processing
- **ONNX Runtime** for on-device Automix analysis paths
- **React, TypeScript and Vite** for the web source

## 🐛 Support & contributing

Found a bug or have a feature idea? Open a [GitHub Issue](https://github.com/OPZENITSUUU/VoidMusic/issues). Please include:

- App version and device/OS version.
- What you expected and what actually happened.
- The selected source and relevant Stats for Nerds details for playback issues.
- Steps to reproduce, plus a minimal log or screenshot where useful.

Remove passwords, OAuth tokens, private source URLs and other account secrets before posting logs.

Before making a pull request, read the [Contributing Guide](CONTRIBUTING.md), [Code of Conduct](CODE_OF_CONDUCT.md), [Maintainers](MAINTAINERS.md) and [Additional Documentation](ADDITIONAL.md).

## ⚖️ Legal, copyright & licensing

Void Music is an independent community distribution. It is **not affiliated with, endorsed by or sponsored by Google, YouTube, YouTube Music, Spotify, Discord, TIDAL, Qobuz or their parent companies**. Product names and trademarks belong to their respective owners.

Void Music does not claim ownership of third-party catalogues or user media. You are responsible for ensuring that your use of any source or content is authorized and complies with local law and the relevant service terms. Availability, stream quality and permissions may change when an external service changes.

This repository builds on the open-source BitChord project. Applicable upstream notices, copyright information and license obligations must be retained when redistributing the software. See [LICENSE](LICENSE) for the repository's **GNU General Public License v3.0 (GPL-3.0)** terms.

---

<div align="center">

**Void Music** · A player built for your music, your setup and your way of listening.

<sub>Browse the code · Test the source · Verify the stream · Keep the music yours. 🎧</sub>

</div>
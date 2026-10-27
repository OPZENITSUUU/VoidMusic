package com.music.bitchord.data.sources.addon

/**
 * Normalizes the documented-in-practice LastWave JSON dialect into the shared
 * addon protocol. This is intentionally a data adapter only: it executes no
 * remote code and never follows or downloads the URL returned by the server.
 */
internal object LastWaveAdapter {
    const val MANIFEST_ID = "com.lastwave.addon"

    fun supports(manifest: AddonManifest?): Boolean = manifest?.id == MANIFEST_ID

    /** LastWave may return a synthetic `swap_…` fallback for a no-match query. */
    fun normalizeTracks(tracks: List<AddonTrack>): List<AddonTrack> = tracks.mapNotNull { track ->
        if (track.id.startsWith("swap_", ignoreCase = true) &&
            track.artist.isBlank() &&
            track.album.equals("Single", ignoreCase = true)
        ) {
            null
        } else {
            track.copy(audioQuality = track.audioQuality.ifBlank { track.quality })
        }
    }

    /** LastWave uses several names for the resolved media URL. */
    fun normalizeStream(stream: AddonStream): AddonStream {
        val resolvedUrl = sequenceOf(
            stream.url,
            stream.streamUrl,
            stream.directUrl,
            stream.downloadUrl,
            stream.mediaUrl,
        ).firstNotNullOfOrNull { candidate -> candidate?.takeIf { it.isNotBlank() } }.orEmpty()
        return stream.copy(url = resolvedUrl)
    }
}

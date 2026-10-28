package com.music.bitchord

import com.music.bitchord.data.settings.AddonCacheMode
import org.junit.Assert.assertEquals
import org.junit.Test

class AddonCacheModeTest {
    @Test
    fun standardModePreparesOneUpcomingTrack() {
        assertEquals(1, AddonCacheMode.STANDARD.upcomingTrackLimit)
    }

    @Test
    fun aggressiveModePreparesAtMostFiveUpcomingTracks() {
        assertEquals(5, AddonCacheMode.AGGRESSIVE.upcomingTrackLimit)
    }

    @Test
    fun unknownOrMissingStoredModeFallsBackToStandard() {
        assertEquals(AddonCacheMode.STANDARD, AddonCacheMode.fromPreference(null))
        assertEquals(AddonCacheMode.STANDARD, AddonCacheMode.fromPreference("future-mode"))
    }

    @Test
    fun storedModeIsReadCaseInsensitively() {
        assertEquals(AddonCacheMode.AGGRESSIVE, AddonCacheMode.fromPreference(" aggressive "))
    }
}

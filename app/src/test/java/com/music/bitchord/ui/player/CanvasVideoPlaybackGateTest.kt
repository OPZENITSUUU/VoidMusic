package com.music.bitchord.ui.player

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CanvasVideoPlaybackGateTest {
    @Test
    fun canvasRunsOnlyWhileMusicIsPlayingInForegroundOutsideTransitions() {
        assertTrue(shouldPlayCanvasVideo(foreground = true, isPlaying = true, pausedForTransition = false))
        assertFalse(shouldPlayCanvasVideo(foreground = false, isPlaying = true, pausedForTransition = false))
        assertFalse(shouldPlayCanvasVideo(foreground = true, isPlaying = false, pausedForTransition = false))
        assertFalse(shouldPlayCanvasVideo(foreground = true, isPlaying = true, pausedForTransition = true))
        assertFalse(shouldPlayCanvasVideo(foreground = false, isPlaying = false, pausedForTransition = true))
    }
}

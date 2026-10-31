package com.music.bitchord

import android.content.pm.PackageManager
import android.os.Process
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.music.bitchord.data.settings.LauncherIcon
import com.music.bitchord.data.settings.LauncherIconManager
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LauncherIconManagerTest {
    @Test
    fun everyLauncherIconSwitchesWithoutRestartingTheApp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val packageManager = context.packageManager
        val processId = Process.myPid()
        val choices = listOf(
            LauncherIcon.PURPLE,
            LauncherIcon.GALAXY,
            LauncherIcon.NEON,
            LauncherIcon.SILVER,
            LauncherIcon.DEFAULT,
        )

        try {
            choices.forEach { selected ->
                LauncherIconManager.apply(context, selected)

                assertEquals("Changing the app icon must not restart the app", processId, Process.myPid())
                val activeIcons = LauncherIcon.entries.filter { icon ->
                    when (packageManager.getComponentEnabledSetting(LauncherIconManager.componentFor(context, icon))) {
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED -> true
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER,
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED -> false
                        else -> icon == LauncherIcon.DEFAULT
                    }
                }
                assertEquals("Exactly the selected launcher alias must be active", listOf(selected), activeIcons)

                LauncherIcon.entries.forEach { icon ->
                    val component = LauncherIconManager.componentFor(context, icon)
                    val info = packageManager.getActivityInfo(
                        component,
                        PackageManager.MATCH_DISABLED_COMPONENTS,
                    )
                    assertEquals(component.className, info.name)
                    assertEquals(context.packageName, info.packageName)
                }
            }
        } finally {
            LauncherIconManager.apply(context, LauncherIcon.DEFAULT)
        }
    }
}

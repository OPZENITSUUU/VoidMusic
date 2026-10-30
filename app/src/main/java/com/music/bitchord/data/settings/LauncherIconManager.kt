package com.music.bitchord.data.settings

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager

/** Switches the one active launcher alias without killing the running app. */
object LauncherIconManager {
    private val aliases = mapOf(
        LauncherIcon.DEFAULT to ".LauncherDefault",
        LauncherIcon.PURPLE to ".LauncherPurple",
        LauncherIcon.GALAXY to ".LauncherGalaxy",
        LauncherIcon.NEON to ".LauncherNeon",
        LauncherIcon.SILVER to ".LauncherSilver",
    )

    fun apply(context: Context, icon: LauncherIcon) {
        val appContext = context.applicationContext
        val packageManager = appContext.packageManager
        val components = aliases.mapValues { (_, suffix) ->
            ComponentName(appContext, appContext.packageName + suffix)
        }

        // Check every alias before changing any of them. The phone app declares
        // these launchers; a different app variant must fail without leaving the
        // package with no launcher entry.
        components.values.forEach { component ->
            packageManager.getActivityInfo(component, PackageManager.MATCH_DISABLED_COMPONENTS)
        }

        val selected = requireNotNull(components[icon])
        packageManager.setComponentEnabledSetting(
            selected,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP,
        )
        components.values.filterNot { it == selected }.forEach { component ->
            packageManager.setComponentEnabledSetting(
                component,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP,
            )
        }
        AppSettings.setLauncherIcon(icon)
    }
}

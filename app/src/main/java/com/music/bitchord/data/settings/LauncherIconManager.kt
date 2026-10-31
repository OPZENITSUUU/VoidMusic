package com.music.bitchord.data.settings

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.music.bitchord.MainActivity

/** Switches the one active launcher alias without killing the running app. */
object LauncherIconManager {
    /**
     * The application ID (for example, `com.voidmusic.player`) is not the
     * manifest namespace. Relative activity-alias names are expanded against
     * MainActivity's namespace (`com.music.bitchord`), while ComponentName's
     * package part must still be the installed application ID.
     */
    internal fun componentFor(context: Context, icon: LauncherIcon): ComponentName =
        ComponentName(
            context.packageName,
            "${MainActivity::class.java.name.substringBeforeLast('.')}.${icon.aliasClassName}",
        )

    internal fun components(context: Context): Map<LauncherIcon, ComponentName> =
        LauncherIcon.entries.associateWith { icon -> componentFor(context, icon) }

    fun apply(context: Context, icon: LauncherIcon) {
        val appContext = context.applicationContext
        val packageManager = appContext.packageManager
        val components = components(appContext)

        // Validate all manifest declarations before changing any state. If an
        // app variant omits these aliases, preserve the currently working icon
        // and let the caller show the actual PackageManager error.
        components.values.forEach { component ->
            packageManager.getActivityInfo(component, PackageManager.MATCH_DISABLED_COMPONENTS)
        }

        val enabledIcons = components.filter { (candidate, component) ->
            when (packageManager.getComponentEnabledSetting(component)) {
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED -> true
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED -> false
                // Only the Default alias is enabled by default in the manifest.
                else -> candidate == LauncherIcon.DEFAULT
            }
        }.keys

        // Avoid notifying the launcher again when the requested icon is already
        // the sole active alias; keep the persisted preference in sync regardless.
        if (enabledIcons.singleOrNull() == icon) {
            AppSettings.setLauncherIcon(icon)
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+ applies the alias changes as one package-manager
            // operation, preventing a transient duplicate or missing home icon.
            packageManager.setComponentEnabledSettings(
                components.map { (candidate, component) ->
                    PackageManager.ComponentEnabledSetting(
                        component,
                        if (candidate == icon) {
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                        } else {
                            PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                        },
                        PackageManager.DONT_KILL_APP,
                    )
                },
            )
        } else {
            // Android 11–12 have no batch API. Enable the replacement first so
            // the package never has zero launcher entries, then disable the rest.
            packageManager.setComponentEnabledSetting(
                requireNotNull(components[icon]),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP,
            )
            components.filterKeys { it != icon }.values.forEach { component ->
                packageManager.setComponentEnabledSetting(
                    component,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP,
                )
            }
        }

        AppSettings.setLauncherIcon(icon)
    }
}

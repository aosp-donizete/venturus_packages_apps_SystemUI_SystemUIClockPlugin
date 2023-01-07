package com.venturus.plugin

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.android.systemui.plugins.*
import com.android.systemui.plugins.annotations.Requires

private const val TAG = "Venturus"

@Requires(
        target = ClockProviderPlugin::class,
        version = ClockProviderPlugin.VERSION
)
class ClockPlugin : ClockProviderPlugin {
    private lateinit var mSysuiContext: Context
    private lateinit var mPluginContext: Context

    override fun onCreate(sysuiContext: Context, pluginContext: Context) {
        mSysuiContext = sysuiContext
        mPluginContext = pluginContext
        Log.d(TAG, "Plugin created")
    }

    override fun getClocks() = arrayListOf(
            ClockMetadata(
                    "my_custom_venturus_systemui_clock",
                    "my_custom_venturus_systemui_clock"
            )
    )

    override fun createClock(id: ClockId) = MyClock(mSysuiContext, mPluginContext, id)

    override fun getClockThumbnail(id: ClockId): Drawable? = null
}

class MyClock(
        private val mSysuiContext: Context,
        private val mPluginContext: Context,
        private val clockId: String
) : Clock {
    private val inflater = LayoutInflater.from(mPluginContext)

    override val smallClock: View = inflater.inflate(R.layout.my_clock_view, null)

    override val largeClock: View = inflater.inflate(R.layout.my_clock_view, null)

    override val events: ClockEvents = object : ClockEvents {
        override fun onTimeTick() {
            Log.d(TAG, "onTimeTick")
        }
    }

    override val animations: ClockAnimations = object : ClockAnimations {

    }
}
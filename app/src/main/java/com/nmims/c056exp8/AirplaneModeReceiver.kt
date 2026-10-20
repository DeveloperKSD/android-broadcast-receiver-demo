package com.nmims.c056exp8

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class AirplaneModeReceiver(
    private val onChanged: (Boolean) -> Unit
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val isOn = intent.getBooleanExtra("state", false)
            onChanged(isOn)
        }
    }
}

package com.nmims.c056exp8

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class DynamicCustomReceiver(
    private val onMessage: (String) -> Unit
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == CustomBroadcastReceiver.ACTION_CUSTOM) {
            onMessage(intent.getStringExtra(CustomBroadcastReceiver.EXTRA_MESSAGE) ?: "(no message)")
        }
    }
}

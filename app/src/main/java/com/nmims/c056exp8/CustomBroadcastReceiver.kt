package com.nmims.c056exp8

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast


class CustomBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ACTION_CUSTOM) {
            val msg = intent.getStringExtra(EXTRA_MESSAGE) ?: "(no message)"
            Log.d("Exp8", "Static receiver got: $msg")
            Toast.makeText(context, "Static receiver: $msg", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val ACTION_CUSTOM = "com.nmims.c056exp8.CUSTOM_ACTION"
        const val EXTRA_MESSAGE = "message"
    }
}

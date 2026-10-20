package com.nmims.c056exp8

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {

    private var airplaneOn by mutableStateOf(false)
    private val logs = mutableStateListOf<String>()
    private var counter = 0

    private val airplaneReceiver = AirplaneModeReceiver { isOn ->
        airplaneOn = isOn
        addLog("Airplane mode turned " + if (isOn) "ON" else "OFF")
    }

    private val customReceiver = DynamicCustomReceiver { msg ->
        addLog("Dynamic receiver got: $msg")
    }

    private fun addLog(text: String) {
        val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        logs.add(0, "[$time] $text")
    }

    private fun sendCustomBroadcast() {
        counter++
        val intent = Intent(CustomBroadcastReceiver.ACTION_CUSTOM).apply {
            putExtra(CustomBroadcastReceiver.EXTRA_MESSAGE, "Hello from sender #$counter")
            setPackage(packageName) // deliver only inside this app
        }
        sendBroadcast(intent)
        addLog("Sender: broadcast #$counter sent")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        airplaneOn = Settings.Global.getInt(contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .statusBarsPadding()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("Broadcast Receiver Demo", style = MaterialTheme.typography.headlineSmall)
                        Text(
                            "Airplane mode: " + if (airplaneOn) "ON" else "OFF",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text("Toggle Airplane mode from the quick settings / emulator panel.")
                        Button(onClick = { sendCustomBroadcast() }, modifier = Modifier.fillMaxWidth()) {
                            Text("Send Custom Broadcast")
                        }
                        Spacer(Modifier.height(4.dp))
                        Text("Event log", style = MaterialTheme.typography.titleMedium)
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            items(logs) { line ->
                                Card(modifier = Modifier.fillMaxWidth()) {
                                    Text(line, modifier = Modifier.padding(10.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // DYNAMIC registration: active only while the activity is visible
    override fun onStart() {
        super.onStart()
        ContextCompat.registerReceiver(
            this, airplaneReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED),
            ContextCompat.RECEIVER_EXPORTED
        )
        ContextCompat.registerReceiver(
            this, customReceiver,
            IntentFilter(CustomBroadcastReceiver.ACTION_CUSTOM),
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(airplaneReceiver)
        unregisterReceiver(customReceiver)
    }
}

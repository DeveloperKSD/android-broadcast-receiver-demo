# Android Broadcast Receiver Demo

A small Android app (Kotlin + Jetpack Compose) that demonstrates how **Broadcast Receivers** work: listening for a system event (**Airplane Mode on/off**) and sending and receiving a **custom broadcast** inside the app. It shows both **static** (manifest) and **dynamic** (code) registration.

Built for the *Mobile Application Development* lab (Experiment 8) at SVKM's NMIMS, Navi Mumbai.

## Features

- Detects Airplane Mode being switched on or off in real time (dynamic receiver)
- Sends a custom broadcast from a button and receives it with two receivers:
  - a **statically registered** receiver (declared in `AndroidManifest.xml`) that shows a Toast
  - a **dynamically registered** receiver that adds a line to the on-screen event log
- Timestamped event log inside the app

## How it works

| Component | Registration | Purpose |
|---|---|---|
| `AirplaneModeReceiver` | Dynamic (`onStart` / `onStop`) | Listens for `ACTION_AIRPLANE_MODE_CHANGED` |
| `CustomBroadcastReceiver` | Static (manifest) | Handles `com.nmims.c056exp8.CUSTOM_ACTION`, shows a Toast |
| `DynamicCustomReceiver` | Dynamic (`onStart` / `onStop`) | Handles the same custom action and updates the UI log |

Airplane Mode is registered dynamically because, from Android 8.0 (API 26), manifest-declared receivers can no longer receive most implicit system broadcasts.

## Project structure

```
app/src/main/
├── AndroidManifest.xml
└── java/com/nmims/c056exp8/
    ├── MainActivity.kt
    ├── AirplaneModeReceiver.kt
    ├── CustomBroadcastReceiver.kt
    └── DynamicCustomReceiver.kt
```

## Requirements

- Android Studio (Koala or newer recommended) with JDK 17
- Android 7.0 (API 24) or higher device or emulator
- Internet connection for the first Gradle sync

## Getting started

1. Clone the repository:
   ```bash
   git clone https://github.com/DevelopeprKSD/android-broadcast-receiver-demo.git
   ```
2. Open the folder in Android Studio (**File → Open**) and wait for Gradle sync to finish.
3. Select an emulator or connected device and click **Run**.

## Usage

1. Tap **Send Custom Broadcast**. A Toast appears from the static receiver and a log entry appears from the dynamic receiver.
2. Toggle **Airplane Mode** from the quick settings panel (or the emulator's controls). The status text and event log update immediately.

## Tech stack

- Kotlin
- Jetpack Compose (Material 3)
- Android SDK (`BroadcastReceiver`, `IntentFilter`, `ContextCompat.registerReceiver`)

## Key concepts demonstrated

- Creating a `BroadcastReceiver` and overriding `onReceive()`
- Static vs. dynamic registration
- Receiving system broadcasts and sending custom broadcasts with `sendBroadcast()`
- Restricting a broadcast to your own app with `setPackage()` and `RECEIVER_NOT_EXPORTED`
- Unregistering receivers to avoid leaks

## License

This project is for educational purposes. Add a license of your choice (for example MIT) if you plan to share it publicly.

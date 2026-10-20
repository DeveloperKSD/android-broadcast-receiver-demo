C056-exp8 : Broadcast Sender-Receiver (Airplane Mode + Custom Broadcast)
Kotlin + Jetpack Compose, minSdk 24, compileSdk 34

HOW TO RUN
1. Extract the zip.
2. Android Studio -> File > Open -> select the C056-exp8 folder -> wait for Gradle sync.
   (Needs internet for the first sync. Use the bundled JDK 17: Settings > Build Tools > Gradle > Gradle JDK.)
3. Pick an emulator / real phone (API 24+), click Run.
4. Test:
   - Tap "Send Custom Broadcast": a Toast (static receiver) and a log line (dynamic receiver) appear.
   - Toggle Airplane mode: swipe down quick settings on the device, or on the emulator use
     Extended controls (...) or the emulator's quick settings. The screen and log update.

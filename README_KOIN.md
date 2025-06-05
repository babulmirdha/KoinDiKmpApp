Great! Here's how to **integrate Koin with iOS** in your **Kotlin Multiplatform (KMM)** app so that you can use shared logic with dependency injection on iOS too.

---

## ✅ Goal

Use Koin DI in **shared KMM module**, call `GreetingUseCase` from Swift code, and display greeting text on iOS UI.

---

## ✅ 1. Add Koin Dependencies

You already have this in `shared/build.gradle.kts`:

```kotlin
kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.insert-koin:koin-core:3.5.0")
            }
        }
    }
}
```

---

## ✅ 2. Create Koin Initialization for iOS

### 📄 `shared/src/iosMain/kotlin/com/example/shared/PlatformKoin.kt`

```kotlin
package com.example.shared

import com.example.shared.di.sharedModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(sharedModule)
    }
}
```

✅ Now both Android and iOS have the same `initKoin()` function with platform-specific location.

---

## ✅ 3. Create a Swift-Friendly Class

You can't use `GreetingUseCase : KoinComponent` directly in Swift. So expose a wrapper.

### 📄 `shared/src/commonMain/kotlin/com/example/shared/GreetingBridge.kt`

```kotlin
package com.example.shared

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GreetingBridge : KoinComponent {
    private val useCase: GreetingUseCase by inject()

    fun greet(): String = useCase.greet()
}
```

✅ `GreetingBridge` is now a Swift-friendly wrapper.

---

## ✅ 4. Expose Shared Code to iOS

The `shared` module already builds a framework for iOS (via CocoaPods or manual export).

If you're using CocoaPods, the iOS code will import `shared`.

---

## ✅ 5. iOS Swift Code

Assuming you're using SwiftUI in iOS:

### 📄 `iosApp/iosApp/ContentView.swift`

```swift
import SwiftUI
import shared // ← import your KMM shared module

struct ContentView: View {
    let greetingBridge = GreetingBridge()

    var body: some View {
        Text(greetingBridge.greet())
            .padding()
    }
}
```

---

### 📄 `iosApp/iosApp/iOSApp.swift`

You need to initialize Koin here.

```swift
import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        KoinKt.doInitKoin() // this is the Kotlin function exposed to Swift
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
```

But `doInitKoin()` doesn’t exist yet! So…

---

## ✅ 6. Add Helper for Swift Init

Add this function in Kotlin (commonMain):

### 📄 `shared/src/commonMain/kotlin/com/example/shared/KoinInitForSwift.kt`

```kotlin
package com.example.shared

fun doInitKoin() {
    initKoin()
}
```

✅ This function is easily callable from Swift.

---

## ✅ Result

On iOS, your app displays:

```
Hello from KMM + Koin!
```

---

## ✅ Recap

| Step | Task                                    |
| ---- | --------------------------------------- |
| 1    | Add `koin-core` to `shared` module      |
| 2    | Define `sharedModule` with services     |
| 3    | Create platform-specific `initKoin()`   |
| 4    | Create `GreetingBridge` for Swift       |
| 5    | Call `GreetingBridge.greet()` in iOS UI |
| 6    | Expose `doInitKoin()` to Swift          |

---

Would you like a downloadable GitHub example too?

package com.babulmirdha.koin_kmp

import android.app.Application
import com.babulmirdha.shared.initKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}

package com.sign.googlein

import android.app.Application
import com.sign.googlein.di.appModule
import io.ktor.client.plugins.logging.LogLevel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AuthApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AuthApp)
            androidLogger(Level.DEBUG)
            modules(listOf(appModule))
        }
    }
}
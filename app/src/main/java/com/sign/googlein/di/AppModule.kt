package com.sign.googlein.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import java.net.CookieManager

val appModule = module {
    single<CookieManager>{
        CookieManager()
    }

    factory {
        HttpClient(Android){
            install(ContentNegotiation){
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(HttpTimeout){
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 15000
            }

            install(HttpCookies){

            }
        }
    }
}
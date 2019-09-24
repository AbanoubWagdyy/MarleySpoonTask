package net.marleyspoon.di

import com.contentful.java.cda.CDAClient
import net.marleyspoon.BuildConfig
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { provideContentfulClient() }
}

fun provideContentfulClient(): CDAClient {

    return CDAClient.builder()
        .setSpace(BuildConfig.Space_ID)
        .setToken(BuildConfig.ACCESS_TOKEN)
        .build()
}

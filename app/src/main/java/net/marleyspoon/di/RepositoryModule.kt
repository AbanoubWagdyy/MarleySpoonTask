package net.marleyspoon.di

import com.contentful.java.cda.CDAClient
import net.marleyspoon.BuildConfig
import net.marleyspoon.data.DataRepository
import net.marleyspoon.data.RepositorySource
import org.koin.dsl.module

val repositoryModule = module {
    factory { provideCDAClient() }
    single { provideRepositoryInstance(get()) }
}

fun provideCDAClient(): CDAClient {
    return CDAClient.builder()
        .setSpace(BuildConfig.Space_ID)
        .setToken(BuildConfig.ACCESS_TOKEN)
        .build()
}

fun provideRepositoryInstance(client: CDAClient): RepositorySource {
    return DataRepository(client)
}

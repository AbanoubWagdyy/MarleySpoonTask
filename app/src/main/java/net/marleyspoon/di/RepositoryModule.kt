package net.marleyspoon.di

import net.marleyspoon.data.DataRepository
import net.marleyspoon.data.RepositorySource
import org.koin.dsl.module

val repositoryModule = module { 
    single { provideRepositoryInstance() }
}

fun provideRepositoryInstance(): RepositorySource {
    return DataRepository.getInstance()
}

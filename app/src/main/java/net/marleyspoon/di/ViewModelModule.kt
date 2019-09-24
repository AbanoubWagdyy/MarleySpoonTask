package net.marleyspoon.di

import net.marleyspoon.ui.detailsContent.DetailsContentViewModel
import net.marleyspoon.ui.listContent.ListContentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListContentViewModel(get()) }
    viewModel { DetailsContentViewModel(get()) }
}
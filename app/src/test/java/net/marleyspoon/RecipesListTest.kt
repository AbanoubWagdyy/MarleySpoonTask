package net.marleyspoon

import net.marleyspoon.data.RepositorySource
import net.marleyspoon.di.repositoryModule
import net.marleyspoon.di.viewModelModule
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given

class RecipesListTest : KoinTest {

    @Before
    fun before() {
        startKoin { modules(listOf(repositoryModule, viewModelModule)) }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `declareMock with RecipeList`() {
        declareMock<RepositorySource> {
            given(this.getContent())
        }
    }
}
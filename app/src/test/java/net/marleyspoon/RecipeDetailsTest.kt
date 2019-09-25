package net.marleyspoon

import net.marleyspoon.data.RepositorySource
import net.marleyspoon.data.model.Recipe
import net.marleyspoon.di.repositoryModule
import net.marleyspoon.di.viewModelModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given

class RecipeDetailsTest : KoinTest {

    @Before
    fun before() {
        startKoin { modules(listOf(repositoryModule, viewModelModule)) }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `declareMock with RecipeDetails`() {
        declareMock<RepositorySource> {
            val recipe = Recipe("437eO3ORCME46i02SeCW46","http://images.ctfassets.net/kk2bw5ojx476/5mFyTozvSoyE0Mqseoos86/fb88f4302cfd184492e548cde11a2555/SKU1479_Hero_077-71d8a07ff8e79abcb0e6c0ebf0f3b69c.jpg",
                "Crispy Chicken and Rice with Peas & Arugula Salad")
            this.saveRecipe(recipe)
            given(this.getDetailsContent())
        }
    }
}
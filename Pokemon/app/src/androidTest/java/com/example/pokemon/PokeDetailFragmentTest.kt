package com.example.pokemon

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pokemon.api.IPokeRepository
import com.example.pokemon.di.FakePokeRepository
import com.example.pokemon.di.RepositoryModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Singleton

@UninstallModules(RepositoryModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class PokeDetailFragmentTest {

//    @get:Rule
//    val ruleChain: RuleChain = RuleChain.outerRule(HiltAndroidRule(this))
//        .around(ActivityScenarioRule(MainActivity::class.java))

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

//    @Inject
//    lateinit var repository: IPokeRepository
//
//    @Before
//    fun init(){
//        hiltRule.inject()
//    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class FakePokeModule {

        @Singleton
        @Binds
        abstract fun bindFakePokeRepository(pokeRepository: FakePokeRepository):IPokeRepository
    }

    @Test
    fun pokeList_isShow(){
        onView(withId(R.id.search_poke)).perform(typeText("1"))
        onView(withId(R.id.search_button)).perform(click())

        onView(withId(R.id.poke_number)).check(matches(withText(containsString("pikachu"))))
    }
}
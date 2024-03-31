package github.user.sdk.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import github.user.sdk.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
class MainActivityTest {

    @get : Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)
    private lateinit var activity: ComponentActivity

    @Before
    fun setUp() {
        activity = composeTestRule.activity
    }

    @Test
    fun uiIsDisplayedTest() {
        composeTestRule
            .onNodeWithTag(activity.getString(R.string.search_github_users))
            .isDisplayed()
        composeTestRule
            .onNodeWithTag(activity.getString(R.string.search_input))
            .isDisplayed()
        composeTestRule
            .onNodeWithTag(activity.getString(R.string.no_users_found))
            .isDisplayed()
    }

    @Test
    fun clickUserItemTest() {
        composeTestRule
            .onNodeWithTag(activity.getString(R.string.user_item) + "0")
            .performClick()
    }
}
package com.pratiksha.admin.rojgarihub

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pratiksha.rojgarihub.MainActivity
import com.pratiksha.rojgarihub.presentation.auth.UserType
import com.pratiksha.rojgarihub.presentation.auth.login.LoginAction
import com.pratiksha.rojgarihub.presentation.auth.login.LoginScreen
import com.pratiksha.rojgarihub.presentation.auth.login.LoginState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>() // or any launcher activity

    @Test
    fun test_login_ui_components_and_input() {
        // Set content
        composeRule.setContent {
            LoginScreen(
                state = LoginState(),
                onAction = {}
            )
        }

        // Check presence of radio buttons
        composeRule.onNodeWithText("Employer").assertIsDisplayed()
        composeRule.onNodeWithText("Job Seeker").assertIsDisplayed()

        // Check email and password fields
        composeRule.onNodeWithText("Email Address").assertIsDisplayed()
        composeRule.onNodeWithText("Password").assertIsDisplayed()

        // Check login button
        composeRule.onNodeWithText("Login").assertIsDisplayed()
    }

    @Test
    fun test_input_and_role_selection() {
        var loginAs: UserType? = null
        var email = ""
        var password = ""

        composeRule.setContent {
            LoginScreen(
                state = LoginState(),
                onAction = {
                    when (it) {
                        is LoginAction.EmailChanged -> email = it.value
                        is LoginAction.PasswordChanged -> password = it.value
                        is LoginAction.LoginAsChanged -> loginAs = it.value
                        else -> {}
                    }
                }
            )
        }

        // Interact with email field
        composeRule.onNodeWithText("Email Address").performTextInput("cow@gmail.com")
        composeRule.onNodeWithText("Password").performTextInput("123456")

        // Select Job Seeker
        composeRule.onNodeWithText("Job Seeker").performClick()

        // Assert values updated
        composeRule.runOnIdle {
            assert(email == "cow@gmail.com")
            assert(password == "123456")
            assert(loginAs == UserType.JOB_SEEKER)
        }
    }
}

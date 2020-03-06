package ca326.petwatch.petwatch;
import android.app.Activity;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ForgotPasswordTest
{
    @Test
    public void testIfActivityIsDisplayed()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(ForgotPassword.class);

        onView(withId(R.id.forgotPasswordRelative)).check(matches(isDisplayed()));

        onView(withId(R.id.sendEmailButton)).check(matches(isDisplayed()));

        onView(withId(R.id.enterEmailLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void testIfEditTextWorks()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(ForgotPassword.class);

        String email = "test@mail.com";

        onView(withId(R.id.enterEmail)).perform(typeText(email));
    }

    @Test
    public void testIfButtonIsClickable()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(ForgotPassword.class);

        onView(withId(R.id.sendForgotEmail)).check(matches(isClickable()));
    }

    @Test
    public void testIfButtonAndEditTextIsVisible()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(ForgotPassword.class);

        onView(withId(R.id.enterEmail)).check(matches(isDisplayed()));

        onView(withId(R.id.sendForgotEmail)).check(matches(isDisplayed()));
    }
}
package ca326.petwatch.petwatch;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class StartUpScreenTest
{

    // Test if the activity is in view for the user
    @Test
    public void testActivityInView()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(StartUpScreen.class);

        onView(withId(R.id.startUpScreenRelative)).check(matches(isDisplayed()));

        onView(withId(R.id.signInBackground)).check(matches(isDisplayed()));

        onView(withId(R.id.signInLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.signInStringLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.signUpBackground)).check(matches(isDisplayed()));

        onView(withId(R.id.signUpLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.signUpButtonLayout)).check(matches(isDisplayed()));

    }

    // To test the visibility of text and buttons
    @Test
    public void testVisibilityOfTextAndButtons()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(StartUpScreen.class);

        //sign in text
        onView(withId(R.id.textView2)).check(matches(isDisplayed()));
        //sign in button
        onView(withId(R.id.signInButton)).check(matches(isDisplayed()));

        //sign up text
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
        //sign up button
        onView(withId(R.id.signUpButton)).check(matches(isDisplayed()));

    }


    // To test if all Strings upon the screen are displayed correctly
    @Test
    public void testIsTextDisplayedCorrectly()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(StartUpScreen.class);

        // sign up text
        onView(withId(R.id.textView)).check(matches(withText(R.string.don_t_have_an_account)));

        //sign in text
        onView(withId(R.id.textView2)).check(matches(withText(R.string.already_have_an_account)));
    }

    // To test if the sign in button works
    @Test
    public void testSignInButtonWorks()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(StartUpScreen.class);

        onView(withId(R.id.signInButton)).perform(click());

        onView(withId(R.id.signInScreenRelative)).check(matches(isDisplayed()));
    }

    // To test if the sign up button works
    @Test
    public void testSignUpButtonWorks()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(StartUpScreen.class);

        onView(withId(R.id.signUpButton)).perform(click());

        onView(withId(R.id.signUpScreenRelative)).check(matches(isDisplayed()));
    }


}
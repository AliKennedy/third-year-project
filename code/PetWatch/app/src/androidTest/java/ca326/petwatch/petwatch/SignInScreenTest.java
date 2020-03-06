package ca326.petwatch.petwatch;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class SignInScreenTest
{

    @Test
    public void testActivityInView()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(SignInScreen.class);

        onView(withId(R.id.signInScreenRelative)).check(matches(isDisplayed()));

        onView(withId(R.id.emailAddresslayoutSignIn)).check(matches(isDisplayed()));

        onView(withId(R.id.enterPasswordLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.buttonSigninLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.errorMessageLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.forgotPasswordButtonLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void testVisibilityOfButtonsAndText()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(SignInScreen.class);

        onView(withId(R.id.emailAddress)).check(matches(isDisplayed()));

        onView(withId(R.id.passwordText)).check(matches(isDisplayed()));

        //onView(withId(R.id.warningText)).check(matches(isDisplayed()));

        onView(withId(R.id.buttonSignin)).check(matches(isDisplayed()));

        onView(withId(R.id.forgotPasswordButton)).check(matches(isDisplayed()));

    }

    @Test
    public void testIsTextDisplayedCorrectly()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(SignInScreen.class);
    }

    @Test
    public void testSignInButtonWorkCorrectly()
    {
        String email = "shark.ethan@gmail.com";
        String password = "123456";

        ActivityScenario activityScenario = ActivityScenario.launch(SignInScreen.class);

        onView(withId(R.id.emailAddress)).perform(ViewActions.typeText(email));

        onView(withId(R.id.passwordText)).perform(ViewActions.typeText(password));

        onView(withId(R.id.buttonSignin)).check(matches(isDisplayed())).check(matches(isClickable()));
        //onView(withId(R.id.buttonSignin)).check(matches(isDisplayed())).check(matches(isClickable())).perform(doubleClick());

       // onView(withId(R.id.mapFragmentLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void testForgotPasswordButtonWorks()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(SignInScreen.class);

        onView(withId(R.id.forgotPasswordButton)).perform(click());

        onView(withId(R.id.forgotPasswordRelative)).check(matches(isDisplayed()));
    }


}
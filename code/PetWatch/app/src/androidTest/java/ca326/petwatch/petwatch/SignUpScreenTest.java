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
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class SignUpScreenTest
{

    // To test if the activity is displayed correctly
    @Test
    public void testIfActivitydisplayed()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(SignUpScreen.class);

        onView(withId(R.id.signUpScreenRelative)).check(matches(isDisplayed()));

        onView(withId(R.id.firstNameLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.surnameLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.emailAddresslayout)).check(matches(isDisplayed()));

        onView(withId(R.id.petsNameLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.passwordCreateLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.confirmPasswordLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.passwordNotMatchLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.emailAddressTakenWarningLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.buttonSignUpLayout)).check(matches(isDisplayed()));
    }

    // To test if the Edit Texts work
    @Test
    public void testIfEditTextsWork()
    {

        ActivityScenario activityScenario = ActivityScenario.launch(SignUpScreen.class);

        String fName = "test";
        String sName = "test2";
        String pName = "test3";
        String email = "test@mail.com";
        String pass = "ALi123";

        onView(withId(R.id.firstName)).perform(ViewActions.typeText(fName));

        onView(withId(R.id.Surname)).perform(ViewActions.typeText(sName));

        onView(withId(R.id.emailAddress)).perform(ViewActions.typeText(email));

        onView(withId(R.id.petsName)).perform(ViewActions.typeText(pName));

        //onView(withId(R.id.passwordCreate)).perform(ViewActions.typeText(pass));

        //onView(withId(R.id.confirmPassword)).perform(ViewActions.typeText(pass)).perform(closeSoftKeyboard());
    }

    // To test if the buttons are clickable
    @Test
    public void testIfButtonClicksAndIsDisplayed()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(SignUpScreen.class);

        onView(withId(R.id.buttonSignUp)).check(matches(isDisplayed()));

        onView(withId(R.id.buttonSignUp)).check(matches(isClickable()));
    }

    // To test if all text that is displayed is displayed correctly
    @Test
    public void testIfTextIsCorrect()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(SignUpScreen.class);

        onView(withId(R.id.passwordNotMatch)).check(matches(withText("Passwords Do Not Match")));

        onView(withId(R.id.emailAddressTakenWarning)).check(matches(withText("Account with this E-mail address is already taken")));
    }

}
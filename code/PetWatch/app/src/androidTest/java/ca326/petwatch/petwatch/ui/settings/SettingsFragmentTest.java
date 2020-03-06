package ca326.petwatch.petwatch.ui.settings;

import android.app.Activity;

import static androidx.test.espresso.action.ViewActions.pressBack;
import static org.junit.Assert.*;
import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca326.petwatch.petwatch.ActivityTestSuite;
import ca326.petwatch.petwatch.Main2Activity;
import ca326.petwatch.petwatch.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class SettingsFragmentTest
{

    @Test
    public void testIfFragmentDisplayed()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(Main2Activity.class);

        onView(withId(R.id.navigation_settings)).perform(click());


        //To check if all layouts display on screen
        onView(withId(R.id.settingsLayoutRelative)).check(matches(isDisplayed()));

        onView(withId(R.id.AccountLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.NotificationsLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.PrivacySecurityLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.SupportLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.AboutLayout)).check(matches(isDisplayed()));

        onView(withId(R.id.logOutLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void testIfButtonsDisplayed()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(Main2Activity.class);

        onView(withId(R.id.navigation_settings)).perform(click());

        //To check if all buttons displayed correctly
        onView(withId(R.id.Account)).check(matches(isDisplayed()));

        onView(withId(R.id.Notifications)).check(matches(isDisplayed()));

        onView(withId(R.id.PrivacySecurity)).check(matches(isDisplayed()));

        onView(withId(R.id.Contact)).check(matches(isDisplayed()));

        onView(withId(R.id.About)).check(matches(isDisplayed()));

        onView(withId(R.id.logOut)).check(matches(isDisplayed()));
    }

    @Test
    public void testIfButtonsWork()
    {

        ActivityScenario activityScenario = ActivityScenario.launch(Main2Activity.class);

        onView(withId(R.id.navigation_settings)).perform(click());

        //Account button working correctly
        onView(withId(R.id.Account)).perform(click());

        onView(withId(R.id.accountRelative)).check(matches(isDisplayed()));

        pressBack();


    }

    @Test
    public void testIfChangeTrackerWorks()
    {

        ActivityScenario activityScenario = ActivityScenario.launch(Main2Activity.class);

        onView(withId(R.id.navigation_settings)).perform(click());
        //Change Tracker button working correctly
        onView(withId(R.id.Notifications)).perform(click());

        onView(withId(R.id.changeTrackerRelative)).check(matches(isDisplayed()));

        pressBack();
    }

    @Test
    public void testIfPrivacySecurityWorks()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(Main2Activity.class);

        onView(withId(R.id.navigation_settings)).perform(click());
        //PrivacySecurity button working correctly
        onView(withId(R.id.PrivacySecurity)).perform(click());

        onView(withId(R.id.privacySecurityRelative)).check(matches(isDisplayed()));

        pressBack();
    }

    @Test
    public void testIfContactWorks()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(Main2Activity.class);

        onView(withId(R.id.navigation_settings)).perform(click());

        //Contact Us button working correctly
        onView(withId(R.id.Contact)).perform(click());

        onView(withId(R.id.contactRelative)).check(matches(isDisplayed()));

        pressBack();
    }

    @Test
    public void testIfAboutWorks()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(Main2Activity.class);

        onView(withId(R.id.navigation_settings)).perform(click());

        //About button working correctly
        onView(withId(R.id.About)).perform(click());

        onView(withId(R.id.aboutRelative)).check(matches(isDisplayed()));

        pressBack();

    }

    public void testIfLogOutWorks()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(Main2Activity.class);

        onView(withId(R.id.navigation_settings)).perform(click());
        //logout button working correctly
        onView(withId(R.id.logOut)).perform(click());

        onView(withId(R.id.startUpScreenRelative)).check(matches(isDisplayed()));

        pressBack();
    }
}
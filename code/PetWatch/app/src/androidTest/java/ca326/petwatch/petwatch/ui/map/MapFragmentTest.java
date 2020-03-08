package ca326.petwatch.petwatch.ui.map;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca326.petwatch.petwatch.Main2Activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MapFragmentTest
{
    // A test to ensure if nav bar is working correctly
    @Test
    public void testIfNavBarWorks()
    {
        ActivityScenario activityScenario = ActivityScenario.launch(Main2Activity.class);


    }
}
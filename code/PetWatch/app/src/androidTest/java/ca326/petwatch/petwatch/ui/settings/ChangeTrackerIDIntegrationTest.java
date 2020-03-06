package ca326.petwatch.petwatch.ui.settings;


import androidx.test.espresso.action.TypeTextAction;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ca326.petwatch.petwatch.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
//checks that the database update is successful

public class ChangeTrackerIDIntegrationTest {

    @Rule
    public ActivityTestRule<ChangeTrackerID> mChangeTrackerIDtestResult = new ActivityTestRule<ChangeTrackerID>(ChangeTrackerID.class);

    @Before
    public void setUp() throws Exception
    {

    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void onCreate() throws Exception
    {

        String newTrackerID = "khdvT";

        onView(withId(R.id.updateArdID)).perform(new TypeTextAction(newTrackerID)).perform(closeSoftKeyboard());
        Thread.sleep(2000);
        onView(withId(R.id.updateArdIDButton)).perform(click());;
        Thread.sleep(3000);
        onView(withId(R.id.accountRelative)).check(matches(isDisplayed()));
        onView(withId(R.id.trackerIDFromDatabase)).check(matches(withText(newTrackerID)));


    }

}

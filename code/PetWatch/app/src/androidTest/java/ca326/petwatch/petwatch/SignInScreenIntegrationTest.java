package ca326.petwatch.petwatch;


import androidx.test.espresso.action.TypeTextAction;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


public class SignInScreenIntegrationTest
{

    @Rule
    public ActivityTestRule<SignInScreen> mSignInScreenTestResult = new ActivityTestRule<SignInScreen>(SignInScreen.class);

    @Before
    public void setUp() throws Exception
    {

    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void testSignIn() throws Exception
    {
        String email = "alison.kennedy98@gmail.com";
        String password = "pippers";

        onView(withId(R.id.emailAddress)).perform(new TypeTextAction(email));

        onView(withId(R.id.passwordText)).perform(new TypeTextAction(password)).perform(closeSoftKeyboard());;
        Thread.sleep(1000);
        onView(withId(R.id.buttonSignin)).perform(click());
        Thread.sleep(10000);
        onView(withId(R.id.mapFragmentLayout)).check(matches(isDisplayed()));


    }
}
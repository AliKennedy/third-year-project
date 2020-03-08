package ca326.petwatch.petwatch;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ca326.petwatch.petwatch.ui.settings.ChangeTrackerIDIntegrationTest;
import ca326.petwatch.petwatch.ui.settings.SettingsFragmentTest;

// A Unit test suite to test all unit tests and integration tests at once
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                SignInScreenTest.class,
                StartUpScreenTest.class,
                SettingsFragmentTest.class,
                SignUpScreenTest.class,
                ForgotPasswordTest.class,
                SignInScreenIntegrationTest.class,
                ChangeTrackerIDIntegrationTest.class,
        })

public class ActivityTestSuite
{
}

package ca326.petwatch.petwatch;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ca326.petwatch.petwatch.ui.settings.SettingsFragmentTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                SignInScreenTest.class,
                StartUpScreenTest.class,
                SettingsFragmentTest.class,
                SignUpScreenTest.class,
                ForgotPasswordTest.class,
        })

public class ActivityTestSuite
{
}

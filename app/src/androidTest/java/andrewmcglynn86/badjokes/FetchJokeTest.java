package andrewmcglynn86.badjokes;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import andrewmcglynn86.badjokes.activities.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by amcglynn on 10/12/2017.
 */
@RunWith(AndroidJUnit4.class)
public class FetchJokeTest {
    @Rule   //automates test activity lifetime
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test() throws Exception {
//        ViewInteraction fetchNewJoke = onView(withId(R.id.refreshButton));
//        fetchNewJoke.perform(click());

        onView(withId(R.id.refreshButton)).perform(click());
        onView(withId(R.id.refreshButton)).perform(click());
        onView(withId(R.id.refreshButton)).perform(click());
        onView(withId(R.id.favouritesButton)).perform(click());
        pressBack();
//        onView(withId(R.id.joke_text)).check(matches(withText(containsString())));
    }
}

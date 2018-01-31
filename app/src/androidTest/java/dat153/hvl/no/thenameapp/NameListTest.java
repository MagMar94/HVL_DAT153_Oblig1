package dat153.hvl.no.thenameapp;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Magnus on 31.01.2018.
 */
@RunWith(AndroidJUnit4.class)
public class NameListTest {
    private int numberOfDefaultUsers = 3;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void theInitialNamesAreOnTheListWhenLaunched(){
        onView(withId(R.id.listOfNamesButton3)).perform(click());
        onView (withId (R.id.list_View)).check (ViewAssertions.matches (ListViewMatcher.withListSize (numberOfDefaultUsers)));
    }
}

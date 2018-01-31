package dat153.hvl.no.thenameapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Magnus on 31.01.2018.
 */
@RunWith(AndroidJUnit4.class)
public class LearningModeTest {
    private String adrian = "Adrian Storm-Johannessen";
    private String stephanie = "Stephanie Marthinussen";
    private String magnus = "Magnus Marthinsen";

    @Rule
    public ActivityTestRule<LearningModeActivity> mActivityRule = new ActivityTestRule<LearningModeActivity>(LearningModeActivity.class);

    @Before
    public void setRandomSeedAndFirstImage(){
        //Set up the test so we know what order the images will appear in
        mActivityRule.getActivity().setRandomSeed(1337);

        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mActivityRule.getActivity().setNewRandomPicture();
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void correctGuessGivesOnePoint(){
        //The test
        onView(withId(R.id.score)).check(matches(withText("0/0")));
        onView(withId(R.id.guessText)).perform(typeText(stephanie));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.score)).check(matches(withText("1/1")));
    }

    @Test
    public void wrongGuessGivesNoPoints(){
        //The test
        onView(withId(R.id.score)).check(matches(withText("0/0")));
        onView(withId(R.id.guessText)).perform(typeText(magnus));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.score)).check(matches(withText("0/1")));
    }

    @Test
    public void correctScoreWithThreeCorrectGuesses(){
        //The test
        onView(withId(R.id.score)).check(matches(withText("0/0")));
        onView(withId(R.id.guessText)).perform(typeText(stephanie));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.score)).check(matches(withText("1/1")));
        onView(withId(R.id.guessText)).perform(typeText(magnus));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.score)).check(matches(withText("2/2")));
        onView(withId(R.id.guessText)).perform(typeText(adrian));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.score)).check(matches(withText("3/3")));
    }

    @Test
    public void correctScoreWithThreeGuessesOneMiss(){
        //The test
        onView(withId(R.id.score)).check(matches(withText("0/0")));
        onView(withId(R.id.guessText)).perform(typeText(stephanie));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.score)).check(matches(withText("1/1")));
        onView(withId(R.id.guessText)).perform(typeText(adrian));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.score)).check(matches(withText("1/2")));
        onView(withId(R.id.guessText)).perform(typeText(adrian));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.score)).check(matches(withText("2/3")));
    }
}

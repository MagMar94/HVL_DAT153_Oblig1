package dat153.hvl.no.thenameapp;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Magnus on 31.01.2018.
 */
@RunWith(AndroidJUnit4.class)
public class MainMenuTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp(){
        Intents.init();
    }

    @After
    public void tearDown(){
        Intents.release();
    }

    @Test
    public void theListOfNamesButton(){
        onView(withId(R.id.listOfNamesButton3)).perform(click());
        intended(hasComponent(NameListActivity.class.getName()));
    }

    @Test
    public void theImageGalleryButton(){
        onView(withId(R.id.listOfNamesButton)).perform(click());
        intended(hasComponent(ImageGalleryActivity.class.getName()));
    }

    @Test
    public void theLearningModeButton(){
        onView(withId(R.id.listOfNamesButton2)).perform(click());
        intended(hasComponent(LearningModeActivity.class.getName()));
    }

}

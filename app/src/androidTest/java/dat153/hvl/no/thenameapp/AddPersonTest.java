package dat153.hvl.no.thenameapp;

import android.app.Activity;
import android.app.Instrumentation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;
import android.widget.ImageView;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Magnus on 30.01.2018.
 */
@RunWith(AndroidJUnit4.class)
public class AddPersonTest {

    //@Rule
    //public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testAddPerson() throws Exception{
        onView(withId(R.id.listOfNamesButton3)).perform(click());
        onView(withId(R.id.button)).perform(click());

        mockImage();

        onView(withId(R.id.nameInputText)).perform(typeText("Mr. Android"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click());
    }

    /**
     * mocks the image, so the test does not open the camera.
     */

    private void mockImage(){
        Bitmap icon = BitmapFactory.decodeResource(
                InstrumentationRegistry.getTargetContext().getResources(),
                R.mipmap.ic_launcher);

        // Build a result to return from the Camera app
        Intent resultData = new Intent();
        resultData.putExtra("data", icon);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        // Stub out the Camera. When an intent is sent to the Camera, this tells Espresso to respond
        // with the ActivityResult we just created
        intending(toPackage("com.android.camera")).respondWith(result);

        // Now that we have the stub in place, click on the button in our app that launches into the Camera
        onView(withId(R.id.takePictureButton)).perform(click());

        // We can also validate that an intent resolving to the "camera" activity has been sent out by our app
        intended(toPackage("com.android.camera"));
    }
}

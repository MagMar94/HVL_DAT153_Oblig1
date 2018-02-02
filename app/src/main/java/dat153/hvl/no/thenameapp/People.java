package dat153.hvl.no.thenameapp;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Magnus on 18.01.2018.
 */

class People extends Application {
    /**
     * Creates the singleton.
     */
    public static final People mInstance = new People();

    /**
     * The HashMap containing the names and image
     */
    public Map<Drawable, String> mPeopleMap;

    private People() {
        mPeopleMap = new HashMap<>();
    }


}

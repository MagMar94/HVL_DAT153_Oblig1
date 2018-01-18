package dat153.hvl.no.thenameapp;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    public HashMap<Integer, String> mPeopleMap;

    /**
     * @return the instance
     */
    static People getInstance() {
        return mInstance;
    }

    private People() {
        mPeopleMap = new HashMap<>();
        initiateStudentList();
    }

    private void initiateStudentList() {
        mPeopleMap.put(R.drawable.rabbit1, "Stephanie Marthinussen");
        mPeopleMap.put(R.drawable.rabbit4, "Magnus Marthinsen");
        mPeopleMap.put(R.drawable.rabbit3, "Adrian Storm-Johannessen");
    }
}

package dat153.hvl.no.thenameapp;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;

/**
 * Created by Magnus on 31.01.2018.
 */

public class NameAppContext extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        if(People.mInstance.mPeopleMap.isEmpty())
            initiateStudentList();
    }

    private void initiateStudentList() {
        Drawable imageStephanie = getResources().getDrawable(R.drawable.rabbit1);
        Drawable imageMagnus = getResources().getDrawable(R.drawable.rabbit4);
        Drawable imageAdrian = getResources().getDrawable(R.drawable.rabbit3);
        People.mInstance.mPeopleMap.put(imageStephanie, "Stephanie Marthinussen");
        People.mInstance.mPeopleMap.put(imageMagnus, "Magnus Marthinsen");
        People.mInstance.mPeopleMap.put(imageAdrian, "Adrian Storm-Johannessen");
    }
}

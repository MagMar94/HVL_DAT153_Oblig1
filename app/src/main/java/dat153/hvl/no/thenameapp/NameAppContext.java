package dat153.hvl.no.thenameapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.util.Base64;

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
        checkUser();
    }



    private void initiateStudentList() {
        Drawable imageStephanie = getResources().getDrawable(R.drawable.rabbit1);
        Drawable imageMagnus = getResources().getDrawable(R.drawable.rabbit4);
        Drawable imageAdrian = getResources().getDrawable(R.drawable.rabbit3);
        People.mInstance.mPeopleMap.put(imageStephanie, "Stephanie Marthinussen");
        People.mInstance.mPeopleMap.put(imageMagnus, "Magnus Marthinsen");
        People.mInstance.mPeopleMap.put(imageAdrian, "Adrian Storm-Johannessen");
    }


    // masse rot, kan gjøres mye enklere vis det blir tid
    public void checkUser()
    {
        if(sjekkSharedPref())
            legIHash();
        else
            {
                Intent startNewActivity = new Intent(this, UserSettingsActivity.class);
                startActivity(startNewActivity);
            }


    }


    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }



    public String henteBildeStringFraSharedPref()
    {
        SharedPreferences sP = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String bildeString = sP.getString("userPic", "");

        return bildeString;
    }



    public String hentNavnFraSharedPref()
    {
        SharedPreferences sP = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String navnString = sP.getString("userName","");

        return navnString;

    }


    public void legIHash()
    {
        String navn = hentNavnFraSharedPref();

        // gjør bildeStringen om til en Drawable
        String bildeString = henteBildeStringFraSharedPref();
        Bitmap bildeBitmap = StringToBitMap(bildeString);
        Drawable bildeDrawable = new BitmapDrawable(getResources(), bildeBitmap);

        People.mInstance.mPeopleMap.put(bildeDrawable, navn);
    }

    public boolean sjekkSharedPref()
    {
        String tomString = "";
        boolean ikkeRegistrert = true;
        if(henteBildeStringFraSharedPref().equals(tomString) && hentNavnFraSharedPref().equals(tomString))
            ikkeRegistrert = false;

        return ikkeRegistrert;
    }

}

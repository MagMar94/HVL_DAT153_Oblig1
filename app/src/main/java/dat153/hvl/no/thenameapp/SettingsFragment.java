package dat153.hvl.no.thenameapp;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * The fragment that allows the user to change the settings
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
package dat153.hvl.no.thenameapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    UserSettingsActivity userSettingsActivity = new UserSettingsActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void loadNameListMode(View v){
        Intent startNewActivity = new Intent(this, NameListActivity.class);
        startActivity(startNewActivity);
    }

    public void loadImageGalleryMode(View v){
        Intent startNewActivity = new Intent(this, ImageGalleryActivity.class);
        startActivity(startNewActivity);
    }

    public void loadLearningMode(View v){
        Intent startNewActivity = new Intent(this, LearningModeActivity.class);
        startActivity(startNewActivity);
    }
    public void loadUserSettings(View v){
        Intent startNewActivity = new Intent(this, UserSettingsActivity.class);
        startActivity(startNewActivity);
    }
}

package dat153.hvl.no.thenameapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

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
}

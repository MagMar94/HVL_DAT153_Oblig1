package dat153.hvl.no.thenameapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static HashMap<String, Integer> mStudentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStudentList = new HashMap<>();
        initiateStudentList();
    }

    public void initiateStudentList() {
        mStudentList.put("Stephanie Marthinussen", R.drawable.rabbit1);
        mStudentList.put("Magnus Marthinsen", R.drawable.rabbit4);
        mStudentList.put("Adrian Storm-Johannessen", R.drawable.rabbit3);
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

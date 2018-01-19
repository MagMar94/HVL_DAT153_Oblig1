package dat153.hvl.no.thenameapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NameListActivity extends AppCompatActivity {
    private List<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);

        fillList();
    }

    public void loadAddPersonAcitivity(View v) {
        Intent startNewActivity = new Intent(this, AddPersonActivity.class);
        startActivity(startNewActivity);
    }

    private void fillList(){
        list = new ArrayList<String>();
        ListView listView = (ListView) findViewById(R.id.list_View);
        HashMap<Drawable, String> studentList = People.mInstance.mPeopleMap;
        final String[] names = new String[studentList.size()];
        int i = 0;
        for(String s : studentList.values()) {
            names[i] = s;
            i++;
        }

        final List<String> listNames = new ArrayList<>(Arrays.asList(names));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listNames);

        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        fillList();
    }

}

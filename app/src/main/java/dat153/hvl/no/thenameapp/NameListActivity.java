package dat153.hvl.no.thenameapp;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class NameListActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);
    }

    @Override
    protected void onResume(){
        super.onResume();
        fillList();
    }

    public void loadAddPersonAcitivity(View v) {
        Intent startNewActivity = new Intent(this, AddPersonActivity.class);
        startActivity(startNewActivity);
    }

    private void fillList() {
        ListView listView = (ListView) findViewById(R.id.list_View);
        final List<String> listNames = new ArrayList<String>(People.mInstance.mPeopleMap.values());
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listNames);

        AdapterView.OnItemClickListener nameClickedHandler = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setTitle(People.mInstance.mPeopleMap.values().toArray()[i].toString());
                setContentView(R.layout.activity_show_picture);
                ImageView iw = findViewById(R.id.displayPersonImage);
                BitmapDrawable image = (BitmapDrawable) People.mInstance.mPeopleMap.keySet().toArray()[i];
                iw.setImageDrawable(image);

            }
        };

        listView.setOnItemClickListener(nameClickedHandler);
        listView.setAdapter(adapter);
    }
}

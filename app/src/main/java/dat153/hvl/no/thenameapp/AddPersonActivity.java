package dat153.hvl.no.thenameapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by stephanie on 18.01.2018.
 *
 * @see <a href="https://developer.android.com/training/camera/photobasics.html#TaskPath">developer.android.com/...</a>
 */
public class AddPersonActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        //takePictureOfPerson();
    }

    /**
     * Fixes so an image is captured if another application on the device can handle the event.
     */
    public void takePictureOfPerson(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ImageView pictureOfPersonView = findViewById(R.id.imageView);
            pictureOfPersonView.setImageBitmap(imageBitmap);
        }
    }

    /**
     * Saves the person.
     *
     * @param v
     */
    public void savePerson(View v) {
        TextView inputTextView = findViewById(R.id.nameInputText);
        String inputName = inputTextView.getText().toString();
        BitmapDrawable image = new BitmapDrawable(getResources(), imageBitmap);
        People.mInstance.mPeopleMap.put(image,inputName);
        finish();
    }


}

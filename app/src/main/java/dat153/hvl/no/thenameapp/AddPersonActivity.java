package dat153.hvl.no.thenameapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
        if(userInformationIsValid(inputName,image)){
            People.mInstance.mPeopleMap.put(image,inputName);
            finish(); //returns to previous activity
        } else {
            TextView errorText = (TextView)findViewById(R.id.addPersonErrorTextView);
            if(!isValidImage(image) && !isValidName(inputName)){ //both name and image is invalid
                errorText.setText(getResources().getText(R.string.invalid_name_and_image_error));
            } else if (!isValidImage(image)){ //only image is invalid
                errorText.setText(getResources().getText(R.string.invalid_image_error));
            } else { //only name is invalid
                errorText.setText(getResources().getText(R.string.invalid_name_error));
            }
        }
    }

    /**
     * Checks that the user information is valid
     * @param name
     * @param image
     * @return true if all the information is valid
     */
    private boolean userInformationIsValid(String name, Drawable image){
        return isValidName(name) && isValidImage(image);
    }

    /**
     * Checks that the name is valid.
     *
     * @param name
     * @return true if tha name is valid
     */
    private boolean isValidName(String name){
        Log.d("is name valid", name.toString());
        return name != null && !name.equals("") && !name.equals(getResources().getText(R.string.add_person_name_default_text));
    }

    /**
     * Checks that the image is valid
     *
     * @param image
     * @return true if the image is valid
     */
    private boolean isValidImage(Drawable image){
        Log.d("is image valid", image.toString());
        if(Drawable.class.isAssignableFrom(BitmapDrawable.class)){
            BitmapDrawable bitmapImage = (BitmapDrawable)image;
            return bitmapImage.getBitmap() != null;
        } else {
            return  image != null;
        }

    }
}

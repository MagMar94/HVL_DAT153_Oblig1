package dat153.hvl.no.thenameapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Adrian on 02.02.2018.
 */

public class UserSettingsActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    Bitmap brukerBilde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        Button bildeKnapp = (Button) findViewById(R.id.takeUserPic);
        imageView = (ImageView) findViewById(R.id.userPic);
        final EditText userName = (EditText) findViewById(R.id.userName);
        Button saveButton = (Button) findViewById(R.id.saveUserBotton);

        // Avbryt knapp om maksinen ikke har et kamera
        if (!harKamera())
            bildeKnapp.setEnabled(false);
    }

    //sjekk om maskin har kamera
    private boolean harKamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    // starter kamera
    public void startKamera(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //ta bildet så flytt det til onActivutyResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }


    //gjør noe med bildet
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle ekstra = data.getExtras();
            Bitmap bilde = (Bitmap) ekstra.get("data");
            brukerBilde = bilde;
            imageView.setImageBitmap(bilde);

        }
    }

    // gjør Bitmap om til en String (returnerer String)
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    // gjør String om til Bitmap (returnerer bitmap)
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    // legger brukernavn og bilde i databasen på en eller anen måte
    public void saveUser(View view) throws IOException {

        //String userName = getSharedPreferences("user_name");

        String brukerBildeString = BitMapToString(brukerBilde);
        EditText userName = findViewById(R.id.userName);
        String userNameString = userName.getText().toString();

        SharedPreferences sP = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sP.edit();
        edit.putString("userPic", brukerBildeString);
        edit.putString("userName", userNameString);
        edit.apply();

        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();

        legIHash();
    }

    // returnerer bilde fra sharedPref som string
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

    //legger brukeren i hashen
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
        if(henteBildeStringFraSharedPref()==null && hentNavnFraSharedPref()==null)
            ikkeRegistrert = false;

        return ikkeRegistrert;
    }

}
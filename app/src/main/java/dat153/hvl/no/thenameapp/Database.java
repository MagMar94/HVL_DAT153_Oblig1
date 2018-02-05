package dat153.hvl.no.thenameapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Magnus on 05.02.2018.
 */

public class Database {
    String FILENAME = "my_img";

    /**
     * Writes an image to the internal storage
     *
     * @param context
     * @param image
     */
    public void writeToStorage(Context context, Drawable image){
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            byte[] imageAsBytes = convertImageToBytes(image);
            fos.write(imageAsBytes);
            fos.close();
        } catch (java.io.FileNotFoundException e){
            Log.d("DATABASE", "Could not find file.");
        } catch (java.io.IOException e){
            Log.d("DATABASE", "Error saving the image");
        }
    }

    /**
     * Converts a drawable image to a byte-array.
     *
     * @param image to be converted
     * @return a byte-array with the image
     */
    private byte[] convertImageToBytes(Drawable image){
        Bitmap bitmap = ((BitmapDrawable)image).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    /**
     * Reads an image from storage and returns it as a drawable.
     *
     * @param context
     * @param filename
     * @return the image as a drawable
     */
    public Drawable readImageFromStorage(Context context, String filename){
        try {
            FileInputStream fis = context.openFileInput(filename);
            BitmapDrawable image = new BitmapDrawable(BitmapFactory.decodeStream(fis));
            fis.close();
            return image;
        } catch (java.io.FileNotFoundException e){
            Log.d("DATABASE", "Could not find file.");
        } catch (java.io.IOException e){
            Log.d("DATABASE", "Error saving the image");
        }
        return null;
    }
}

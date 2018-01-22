package dat153.hvl.no.thenameapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class LearningModeActivity extends AppCompatActivity {
    private String correctName;
    private int score;
    private int totaleNumberOfGuesses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode);
        score = 0;
        totaleNumberOfGuesses = 0;
        updateScore();
        setNewRandomPicture();
    }

    public void submit(View v) {
        String guessedName = getTheGuess(v);
        Boolean ressult = compareTo(correctName, guessedName);
        totaleNumberOfGuesses = totaleNumberOfGuesses+1;
        if(ressult)
            score = score+1;

        updateScore();
        setNewRandomPicture();
    }

    // generer tilfeldig tall
    public static int randomNumber()
    {
        Random randomGenerator = new Random();
        int max = People.mInstance.mPeopleMap.size();
        int randomNum = randomGenerator.nextInt(max);

        return randomNum;
    }


    public void setNewRandomPicture()
    {
        int ranNum = randomNumber();

        Drawable imageFromHashMap = (Drawable) People.mInstance.mPeopleMap.keySet().toArray()[ranNum];
        correctName = (String) People.mInstance.mPeopleMap.values().toArray()[ranNum];

        BitmapDrawable bitmapImage = (BitmapDrawable) imageFromHashMap;

        ImageView pictureOfPersonView = findViewById(R.id.randomPicture);
        pictureOfPersonView.setImageBitmap(bitmapImage.getBitmap());
    }


    // samelingn to tekster
    public boolean compareTo(String one, String two)
    {
        return (one.equals(two));
    }


    // tar imot innput
    public String getTheGuess(View view)
    {
        EditText inputTextView = findViewById(R.id.guessText);
        String inputGuess = inputTextView.getText().toString();

        return inputGuess;
    }


    // update score
    public void updateScore()
    {
        TextView scoreBord = findViewById(R.id.score);
        String scoreText = "" + score + "/" + totaleNumberOfGuesses;
        scoreBord.setText(scoreText);
    }

    // quit
    public void quitButton(View view)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String scoreText = "" + score + "/" + totaleNumberOfGuesses;

        builder.setMessage(scoreText).setTitle(R.string.quit_dialog_title);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}

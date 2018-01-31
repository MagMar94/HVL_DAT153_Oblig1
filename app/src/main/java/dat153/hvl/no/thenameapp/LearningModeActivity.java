package dat153.hvl.no.thenameapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class LearningModeActivity extends AppCompatActivity {
    private String correctName;
    private int score;
    private int totalNumberOfGuesses;
    private Random randomGenerator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        randomGenerator = new Random();
        setContentView(R.layout.activity_learning_mode);
        score = 0;
        totalNumberOfGuesses = 0;
        updateScore();
        setNewRandomPicture();
    }

    /**
     * Submits the answer
     *
     * @param v current view
     */
    public void submit(View v) {
        String guessedName = getTheGuess(v);
        totalNumberOfGuesses = totalNumberOfGuesses + 1;
        if (correctName.equals(guessedName))
            score = score + 1;

        updateScore();
        ImageView iv = findViewById(R.id.randomPicture);
        fadeOut(iv);
        setNewRandomPicture();
        EditText inputField = findViewById(R.id.guessText);
        inputField.setText("");
    }

    /**
     * Updates the displayed score
     */
    private void updateScore() {
        TextView scoreBord = findViewById(R.id.score);
        String scoreText = "" + score + "/" + totalNumberOfGuesses;
        scoreBord.setText(scoreText);
    }

    /**
     * Gets the guessed name
     *
     * @param view
     * @return the guessed name
     */
    private String getTheGuess(View view) {
        EditText inputTextView = findViewById(R.id.guessText);
        String inputGuess = inputTextView.getText().toString();

        return inputGuess;
    }

    /**
     * Sets a random piture on the screen
     */
    public void setNewRandomPicture() {
        int ranNum = randomNumber();

        Drawable imageFromHashMap = (Drawable) People.mInstance.mPeopleMap.keySet().toArray()[ranNum];
        correctName = (String) People.mInstance.mPeopleMap.values().toArray()[ranNum];

        BitmapDrawable bitmapImage = (BitmapDrawable) imageFromHashMap;

        ImageView pictureOfPersonView = findViewById(R.id.randomPicture);
        pictureOfPersonView.setImageBitmap(bitmapImage.getBitmap());
        fadeIn(pictureOfPersonView);
    }

    /**
     * Genererates a random number
     *
     * @return a random number
     */
    private int randomNumber() {
        int max = People.mInstance.mPeopleMap.size();
        int randomNum = randomGenerator.nextInt(max);

        return randomNum;
    }

    // quit
    public void quitButton(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String scoreText = "" + score + "/" + totalNumberOfGuesses;

        builder.setMessage(scoreText).setTitle(R.string.quit_dialog_title);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Used for testing. Sets the seed that the random generator uses.
     *
     * Sets a new random picture so the first
     *
     * @param seed
     */
    public void setRandomSeed(long seed){
        randomGenerator.setSeed(seed);
    }

    private void fadeIn(final ImageView img) {
        int fadeInDuration = 1000;
        Animation fadeIn = new AlphaAnimation(0,1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(fadeInDuration);
        img.setAnimation(fadeIn);
        img.startAnimation(fadeIn);

    }

    private void fadeOut(final ImageView img) {
        int fadeOutDuration = 1000;
        Animation fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(fadeOutDuration);
        img.setAnimation(fadeOut);
        img.startAnimation(fadeOut);

    }
}

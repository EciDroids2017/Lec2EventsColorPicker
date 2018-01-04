package temerbu.edu.eventscolorpicker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

//Context
public class ColorPickerActivity extends AppCompatActivity {
    public static final String EXTRA_COLOR = "color";
    public static final String EXTRA_USER_NAME = "user name";
    //Properties:
    private EditText etRed, etGreen, etBlue;
    private EditText etTotal;
    private SeekBar sbRed, sbGreen, sbBlue;
    private Button btnNext;
    private int rgb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice);

        findViews();
        initEvents(); //alt + enter
    }

    private void findViews() {
        etRed = (EditText) findViewById(R.id.etRed);
        etGreen = (EditText) findViewById(R.id.etGreen);
        etBlue = (EditText) findViewById(R.id.etBlue);
        etTotal = (EditText) findViewById(R.id.etTotal);
        sbRed = (SeekBar) findViewById(R.id.sbRed);
        sbGreen = (SeekBar) findViewById(R.id.sbGreen);
        sbBlue = (SeekBar) findViewById(R.id.sbBlue);
        btnNext = (Button) findViewById(R.id.btnNext);
    }

    private void initEvents() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Goto next Activity
                //What is context?!?
                //Intent -> intention                 //Who,                    Where
                Intent nextActivityIntent = new Intent(ColorPickerActivity.this, NextActivity.class);

                //Intent is also a message
                //ViewBag -> Push some data into the intent
                nextActivityIntent.putExtra(EXTRA_COLOR, rgb);
                nextActivityIntent.putExtra(EXTRA_USER_NAME, "Moshe :)");

                startActivity(nextActivityIntent);
                //Pass some data
                //Read the data in the target
            }
        });

        sbRed.setOnSeekBarChangeListener(new OnSeekbarProgressListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (!fromUser) return;
                etRed.setText(String.valueOf(progress));
                updateColor();//alt enter
            }
        });
        sbGreen.setOnSeekBarChangeListener(new OnSeekbarProgressListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (!fromUser) return;
                etGreen.setText(String.valueOf(progress));
                updateColor();
            }
        });

        sbBlue.setOnSeekBarChangeListener(new OnSeekbarProgressListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (!fromUser) return;
                etBlue.setText(String.valueOf(progress));
                updateColor();
            }
        });

        etRed.addTextChangedListener(new OnTextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateProgress(s, sbRed);
            }
        });
        etGreen.addTextChangedListener(new OnTextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateProgress(s, sbGreen);
            }
        });
        etBlue.addTextChangedListener(new OnTextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateProgress(s, sbBlue);
            }
        });
    }

    private void updateProgress(CharSequence s, SeekBar seekbar) {
        String text = s.toString();

        //convert to Integer, set the red progress:
        try {
            Integer progress = Integer.valueOf(text);
            seekbar.setProgress(progress);
            updateColor();
        } catch (NumberFormatException ignored) {}
    }

    private void updateColor() {
        //calc the color:
        rgb = Color.rgb(sbRed.getProgress(), sbGreen.getProgress(), sbBlue.getProgress());
        //int example = 0xdd00ff;
        etTotal.setBackgroundColor(rgb);

        etTotal.setText("#" + Integer.toHexString(rgb).toUpperCase());
    }

}

//Mute the unnecessary methods:
abstract class OnSeekbarProgressListener implements SeekBar.OnSeekBarChangeListener {
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //mute
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //mute
    }
}

/**
 * On Text Changed listener without before and after.
 * mute the before after
 * */
abstract class OnTextChangedListener implements TextWatcher{
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //mute
    }

    @Override
    public void afterTextChanged(Editable editable) {
        //mute
    }
}






















package temerbu.edu.eventscolorpicker;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

//ViewController:?
public class NextActivity extends AppCompatActivity {

    //Properties?
    TextView tvHexColor; // View
    ConstraintLayout layout; //ViewGroup

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        findViews(); //alt + enter
        //this activity was instantiated via intent:
        //read the data = get the intent
        Intent intent = getIntent();

        //int can't be null -> set a default
        int color = intent.getIntExtra(ColorPickerActivity.EXTRA_COLOR, 0xFFFFFFFF);

        layout.setBackgroundColor(color); //change the background of the layout
        //replace the textView text:
        tvHexColor.setText("#" + Integer.toHexString(color).toUpperCase());
    }

    private void findViews() {
        tvHexColor = (TextView) findViewById(R.id.tvHexColor);
        layout = (ConstraintLayout) findViewById(R.id.layout);
    }
}

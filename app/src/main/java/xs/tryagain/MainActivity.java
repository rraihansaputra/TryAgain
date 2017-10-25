package xs.tryagain;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import org.w3c.dom.Text;

import static java.lang.Math.random;
import static java.lang.Math.round;

// This code is from https://www.androidcode.ninja/android-compass-code-example/, with modifications
// by me commented by sections

public class MainActivity extends Activity implements SensorEventListener {

    // define the display assembly compass picture
    private ImageView image;

    // record the compass picture angle turned
    private float currentDegree = 0f;

    // device sensor manager
    private SensorManager mSensorManager;

    // this section is modifications made by me
    // lightbar assignments
    private Button bar0;
    private Button bar1;
    private Button bar2;
    private Button bar3;
    private Button bar4;
    private Button bar5;
    private Button bar6;
    private Button bar7;
    private Button bar8;

    private int Cblock;
    private int F0block;
    private int F1block;

    private int ColdBlock = -1;
    private int F0oldBlock = -1;
    private int F1oldBlock = -1;

    private int Ctarget = 280;
    private int F0target = 280;
    private int F1target = 120;

    private int status = 0;

    TextView Cval;
    TextView F0val;
    TextView F1val;
    //end of this section


    TextView tvHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // our compass image
        image = (ImageView) findViewById(R.id.imageViewCompass);

        // TextView that will tell the user what degree is he heading
        tvHeading = (TextView) findViewById(R.id.tvHeading);

        // initialize your android device sensor capabilities
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // My modification: insert button instances to control
        bar0 = (Button) findViewById(R.id.bar0);
        bar1 = (Button) findViewById(R.id.bar1);
        bar2 = (Button) findViewById(R.id.bar2);
        bar3 = (Button) findViewById(R.id.bar3);
        bar4 = (Button) findViewById(R.id.bar4);
        bar5 = (Button) findViewById(R.id.bar5);
        bar6 = (Button) findViewById(R.id.bar6);
        bar7 = (Button) findViewById(R.id.bar7);
        bar8 = (Button) findViewById(R.id.bar8);
        bar8 = (Button) findViewById(R.id.bar8);

        // Label instances to control
        Cval = (TextView) findViewById(R.id.compassVal);
        F0val = (TextView) findViewById(R.id.friend0Val);
        F1val = (TextView) findViewById(R.id.friend1Val);

        // end of modification
    }

    @Override
    protected void onResume() {
        super.onResume();

        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // get the angle around the z-axis rotated
        float degree = round(event.values[0]);

        // This section is my work

        // heading in int
        int heading = Math.round(degree);

        // calculate and update blocks

        if (status == 0) { // status 0 is compass mode
            Cblock = blockCal(heading, Ctarget);

            if (ColdBlock != Cblock) {
                barUpdate(ColdBlock, 0xFF000000);
                ColdBlock = barUpdate(Cblock, 0xFFFF0000);
            }

            // label updates for diagnostics
            tvHeading.setText("Heading: " + Float.toString(degree) + " degrees \n"
                    + Integer.toString((Ctarget - (heading - 45)) / 10) + "\n" +
                    Integer.toString(Ctarget - heading) + "\n" +
                    Integer.toString(Cblock));
        }
        else if (status == 1) { // status 1 is friends mode
            F0block = blockCal(heading, F0target);
            F1block = blockCal(heading, F1target);

            barUpdate(F0oldBlock, 0xFF000000);
            F0oldBlock = barUpdate(F0block, 0xFF00FF00);

            barUpdate(F1oldBlock, 0xFF000000);
            F1oldBlock = barUpdate(F1block, 0xFF0000FF);

            // label updates for diagnostics
            tvHeading.setText("Heading: " + Float.toString(degree) + " degrees \n"
                    + Integer.toString(F0oldBlock) + "\n" + Integer.toString(F0block) + "\n" +
                    Integer.toString(F1oldBlock) + "\n" + Integer.toString(F1block) + "\n"
                    );
        }
        // end of my section

        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        // how long the animation will take place
        ra.setDuration(210);

        // set the animation after the end of the reservation status
        ra.setFillAfter(true);

        // Start the animation
        image.startAnimation(ra);
        currentDegree = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not in use
    }
    // Functions after this line is my own work

    // change targets from inside the app
    public void setupTarget(View view) {
        Ctarget = (int) (Math.random()*360);
        F0target = (int) (Math.random()*360);
        F1target = (int) (Math.random()*360);

        Cval.setText(Integer.toString(Ctarget));
        F0val.setText(Integer.toString(F0target));
        F1val.setText(Integer.toString(F1target));
    }

    //Calculate which block is needed to be updated
    public int blockCal(int heading, int target) {
        int block = 0;

        if ((target <= heading + 45) && (target > heading - 45)) {
            block = (target-(heading-45))/10;
        }
        else if ((target - heading < 0 && target - heading > -180) || target - heading > 180) {
            block = 0;
        }
        else if (target - heading > 0 || target - heading < -180) {
            block = 8;
        }

        else {

        }
        return block;
    }
    // updating the actual blocks
    public int barUpdate(int block, int color) {
        switch (block) {
            case 0:
                bar0.setBackgroundColor(color);
                break;
            case 1:
                bar1.setBackgroundColor(color);
                break;
            case 2:
                bar2.setBackgroundColor(color);
                break;
            case 3:
                bar3.setBackgroundColor(color);
                break;
            case 4:
                bar4.setBackgroundColor(color);
                break;
            case 5:
                bar5.setBackgroundColor(color);
                break;
            case 6:
                bar6.setBackgroundColor(color);
                break;
            case 7:
                bar7.setBackgroundColor(color);
                break;
            case 8:
                bar8.setBackgroundColor(color);
                break;
        }
        return block;
    }

    // switch between the compass and friends mode
    public void switchStatus (View view) {
        switch (status) {
            case 0: status = 1;
                break;
            case 1: status = 0;
                break;
        }
    }
}

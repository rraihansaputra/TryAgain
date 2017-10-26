package xs.tryagain;

import android.os.CountDownTimer;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

// This code is from https://www.androidcode.ninja/android-compass-code-example/, with modifications
// by me commented by sections
// now this is pretty much my work, all compass component has been removed

public class MainActivity extends Activity {

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

    TextView Cval;

    private long blinkInterval = 125;

    //end of this section


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Cval = (TextView) findViewById(R.id.compassVal);

        // end of modification
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        barUpdate(keyCode, 0xFF00FF00);
        Cval.setText(Integer.toString(keyCode));
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return true;
    }

    // updating the actual blocks
    public int barUpdate(int block, int color) {
        final int blinkcolor = color;
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
            case 19:
                new CountDownTimer(1250, blinkInterval) {
                    private boolean on = true;
                    public void onTick (long millisUntilFinished) {
                        if (on){
                            bar3.setBackgroundColor(blinkcolor);
                            bar4.setBackgroundColor(blinkcolor);
                            bar5.setBackgroundColor(blinkcolor);
                            on = false;
                        }
                        else {
                            bar3.setBackgroundColor(0xFF000000);
                            bar4.setBackgroundColor(0xFF000000);
                            bar5.setBackgroundColor(0xFF000000);
                            on = true;
                        }
                    }
                    public void onFinish() {
                        bar3.setBackgroundColor(0xFF000000);
                        bar4.setBackgroundColor(0xFF000000);
                        bar5.setBackgroundColor(0xFF000000);
                    }
                }.start();
                break;
            case 21:
                new CountDownTimer(1250, blinkInterval) {
                    private boolean on = true;
                    public void onTick (long millisUntilFinished) {
                        if (on){
                            bar0.setBackgroundColor(blinkcolor);
                            bar1.setBackgroundColor(blinkcolor);
                            bar2.setBackgroundColor(blinkcolor);
                            bar3.setBackgroundColor(blinkcolor);
                            on = false;
                        }
                        else {
                            bar0.setBackgroundColor(0xFF000000);
                            bar1.setBackgroundColor(0xFF000000);
                            bar2.setBackgroundColor(0xFF000000);
                            bar3.setBackgroundColor(0xFF000000);
                            on = true;
                        }
                    }
                    public void onFinish() {
                        bar0.setBackgroundColor(0xFF000000);
                        bar1.setBackgroundColor(0xFF000000);
                        bar2.setBackgroundColor(0xFF000000);
                        bar3.setBackgroundColor(0xFF000000);
                    }
                }.start();
                break;
            case 22:
                new CountDownTimer(1250,blinkInterval) {
                    private boolean on = true;
                    public void onTick (long millisUntilFinished) {
                        if (on){
                            bar5.setBackgroundColor(blinkcolor);
                            bar6.setBackgroundColor(blinkcolor);
                            bar7.setBackgroundColor(blinkcolor);
                            bar8.setBackgroundColor(blinkcolor);
                            on = false;
                        }
                        else {
                            bar5.setBackgroundColor(0xFF000000);
                            bar6.setBackgroundColor(0xFF000000);
                            bar7.setBackgroundColor(0xFF000000);
                            bar8.setBackgroundColor(0xFF000000);
                            on = true;
                        }
                    }
                    public void onFinish() {
                        bar5.setBackgroundColor(0xFF000000);
                        bar6.setBackgroundColor(0xFF000000);
                        bar7.setBackgroundColor(0xFF000000);
                        bar8.setBackgroundColor(0xFF000000);
                    }
                }.start();
                break;
            case 98:
                blinkInterval = 250;
                break;
            case 97:
                blinkInterval = 125;
                break;
            case 100;
                break;
            case 101;
                break;
        }
        return block;
    }
}

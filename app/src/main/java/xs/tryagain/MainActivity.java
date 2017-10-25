package xs.tryagain;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;

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
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        barUpdate(keyCode, 0xFF000000);
        return true;
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
            case 19:
                bar3.setBackgroundColor(color);
                bar4.setBackgroundColor(color);
                bar5.setBackgroundColor(color);
                break;
            case 21:
                bar0.setBackgroundColor(color);
                bar1.setBackgroundColor(color);
                bar2.setBackgroundColor(color);
                bar3.setBackgroundColor(color);
                break;
            case 22:
                bar5.setBackgroundColor(color);
                bar6.setBackgroundColor(color);
                bar7.setBackgroundColor(color);
                bar8.setBackgroundColor(color);
                break;
            case 61:
                bar3.setBackgroundColor(color);
                bar2.setBackgroundColor(color);
                bar3.setBackgroundColor(0xFF000000);
                bar1.setBackgroundColor(color);
                bar2.setBackgroundColor(0xFF000000);
                bar0.setBackgroundColor(color);
                bar1.setBackgroundColor(0xFF000000);
                bar0.setBackgroundColor(0xFF000000);
                break;
            case 62:
                bar5.setBackgroundColor(color);
                bar6.setBackgroundColor(color);
                bar5.setBackgroundColor(0xFF000000);
                bar7.setBackgroundColor(color);
                bar6.setBackgroundColor(0xFF000000);
                bar8.setBackgroundColor(color);
                bar7.setBackgroundColor(0xFF000000);
                bar8.setBackgroundColor(0xFF000000);
                break;
        }
        return block;
    }
}

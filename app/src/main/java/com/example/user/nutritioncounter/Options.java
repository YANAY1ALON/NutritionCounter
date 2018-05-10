package com.example.user.nutritioncounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Options extends AppCompatActivity {

    boolean main = true, db = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    public void buttonchk(View view) {
        RadioButton r = (RadioButton) view;
        if (main) {
            int choices = 0;
            if (r.isChecked()){
                r.clearFocus();
                choices--;
            }
            if (choices > 3) {
                r.clearFocus();
            }
        }

    }

}

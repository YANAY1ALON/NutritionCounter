package com.example.user.nutritioncounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class Main extends AppCompatActivity {

    Intent t;
    ProgressBar pba, pbb, pbc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pba = (ProgressBar) findViewById(R.id.pba);
        pbb = (ProgressBar) findViewById(R.id.pbb);
        pbc = (ProgressBar) findViewById(R.id.pbc);

        pba.setProgress(Math.round((float)(Math.random()*101)));
        pbb.setProgress(Math.round((float)(Math.random()*101)));
        pbc.setProgress(Math.round((float)(Math.random()*101)));
    }

    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.main, menu);
        menu.add(0,0,4,"הוסף מוצר");

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        String st = item.getTitle().toString();
        if(st.equals("הוסף מוצר")) {
            t = new Intent(this, Add_FB.class);
            startActivity(t);

        }
        if(st.equals("הגדרות")){
            t = new Intent(this, Options.class);
            startActivity(t);
        }
        return true;
    }
}

package com.example.user.nutritioncounter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Add_FB extends AppCompatActivity {
    DatabaseReference mProducts = FirebaseDatabase.getInstance().getReference().child("products");
    Spinner cat;
    EditText pro;
    Button snd, bck;
    ArrayList categories;
    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    ArrayAdapter<ArrayList> adp;
    SimpleAdapter adp1;
    FoodProduct product;
    ListView nuts;
    HashMap<String, String> item;
    AlertDialog.Builder ret,sndalt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__fb);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        cat = (Spinner) findViewById(R.id.cat);
        pro = (EditText) findViewById(R.id.pro);
        nuts = (ListView) findViewById(R.id.nuts);
        snd = (Button) findViewById(R.id.snd);
    }


    @Override
    protected void onStart() {
        super.onStart();

        final String[][] nutritions = {{"סוכר", ""}, {"כולסטרול", ""}, {"קלוריות", ""}, {"פרוטאין", ""}, {"ויטמין A", ""}, {"ויטמין B1", ""},
                {"ויטמין B2", ""}, {"ויטמין B3", ""}, {"ויטמין B6", ""}, {"ויטמין B9", ""}, {"ויטמין B12", ""}, {"ויטמין C", ""},
                {"ויטמין D", ""}, {"ויטמין E", ""}, {"ויטמין K", ""}, {"סידן", ""}, {"סיבים תזונתיים", ""}, {"ברזל", ""}, {"אשלגן", ""},
                {"מגנזיום", ""}, {"נתרן", ""}, {"זרחן", ""}, {"אבץ", ""}, {"שומן רב בלתי רווי", ""}, {"שומן טראנס", ""}, {"שומן רווי", ""}, {"שומן חד בלתי רווי", ""}};

        for (int i = 0; i < nutritions.length; i++) {
            item = new HashMap<String, String>();
            item.put("Name", nutritions[i][0]);
            item.put("Value", nutritions[i][1]);
            list.add(item);
        }

        adp1 = new SimpleAdapter(this, list, R.layout.nut_list, new String[]{"Name", "Value"}, new int[]{R.id.nut_name, R.id.nut_value});
        nuts.setAdapter(adp1);

        mProducts.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                categories = new ArrayList();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    categories.add(ds.getKey());
                }
                adp = new ArrayAdapter<ArrayList>(Add_FB.this, R.layout.support_simple_spinner_dropdown_item, categories);
                cat.setAdapter(adp);
                cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        nuts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int k, long l) {

                                LinearLayout nutal = (LinearLayout) getLayoutInflater().inflate(R.layout.nut_alart, null);
                                final EditText inp = (EditText) nutal.findViewById(R.id.inp);
                                final AlertDialog.Builder adb = new AlertDialog.Builder(Add_FB.this);
                                adb.setView(nutal);
                                adb.setTitle(nutritions[k][0]);
                                inp.setText(nutritions[k][1]);
                                final int nk = k;
                                double nd = 0;
                                if (!nutritions[nk][1].equals("")){
                                    nd = Double.valueOf(nutritions[nk][1]);
                                }
                                final double fd = nd;
                                DialogInterface.OnClickListener adbclk = new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int j) {
                                        double d = 0;
                                        if (!inp.getText().toString().isEmpty()) {
                                            d = Double.valueOf(inp.getText().toString());
                                        }

                                        if ((j == dialogInterface.BUTTON_POSITIVE) && (d != 0)){
                                            nutritions[nk][1] = d + "";
                                        }else if(fd != 0){
                                            nutritions[nk][1] = fd + "";
                                        }else{
                                            nutritions[nk][1] = "";
                                        }
                                        list.remove(nk);
                                        item = new HashMap<String, String>();
                                        item.put("Name", nutritions[nk][0]);
                                        item.put("Value", nutritions[nk][1]);
                                        list.add(nk, item);

                                        nuts.setAdapter(adp1);

                                        snd.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                sndalt = new AlertDialog.Builder(Add_FB.this);
                                                sndalt.setTitle("אשרו שליחה");
                                                sndalt.setMessage("לא תוכלו לערוך אחר כך");
                                                sndalt.setPositiveButton("שלח", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                        if (!nutritions[0][1].equals("")){
                                                            product.setSugar(Double.valueOf(nutritions[0][1]));
                                                        }
                                                        if (!nutritions[1][1].equals("")){
                                                            product.setCholesterol(Double.valueOf(nutritions[1][1]));
                                                        }
                                                        if (!nutritions[2][1].equals("")){
                                                            product.setCalories(Double.valueOf(nutritions[2][1]));
                                                        }
                                                        if (!nutritions[3][1].equals("")){
                                                            product.setProteins(Double.valueOf(nutritions[3][1]));
                                                        }
                                                        if (!nutritions[4][1].equals("")){
                                                            product.setVit_A(Double.valueOf(nutritions[4][1]));
                                                        }
                                                        if (!nutritions[5][1].equals("")){
                                                            product.setVit_B1(Double.valueOf(nutritions[5][1]));
                                                        }
                                                        if (!nutritions[6][1].equals("")){
                                                            product.setVit_B2(Double.valueOf(nutritions[6][1]));
                                                        }
                                                        if (!nutritions[7][1].equals("")){
                                                            product.setVit_B3(Double.valueOf(nutritions[7][1]));
                                                        }
                                                        if (!nutritions[8][1].equals("")){
                                                            product.setVit_B6(Double.valueOf(nutritions[8][1]));
                                                        }
                                                        if (!nutritions[9][1].equals("")){
                                                            product.setVit_B9(Double.valueOf(nutritions[9][1]));
                                                        }
                                                        if (!nutritions[10][1].equals("")){
                                                            product.setVit_B12(Double.valueOf(nutritions[10][1]));
                                                        }
                                                        if (!nutritions[11][1].equals("")){
                                                            product.setVit_C(Double.valueOf(nutritions[11][1]));
                                                        }
                                                        if (!nutritions[12][1].equals("")){
                                                            product.setVit_D(Double.valueOf(nutritions[12][1]));
                                                        }
                                                        if (!nutritions[13][1].equals("")){
                                                            product.setVit_E(Double.valueOf(nutritions[13][1]));
                                                        }
                                                        if (!nutritions[14][1].equals("")){
                                                            product.setVit_K(Double.valueOf(nutritions[14][1]));
                                                        }
                                                        if (!nutritions[15][1].equals("")){
                                                            product.setCalcium(Double.valueOf(nutritions[15][1]));
                                                        }
                                                        if (!nutritions[16][1].equals("")){
                                                            product.setFiber(Double.valueOf(nutritions[16][1]));
                                                        }
                                                        if (!nutritions[17][1].equals("")){
                                                            product.setIron(Double.valueOf(nutritions[17][1]));
                                                        }
                                                        if (!nutritions[18][1].equals("")){
                                                            product.setPotassium(Double.valueOf(nutritions[18][1]));
                                                        }
                                                        if (!nutritions[19][1].equals("")){
                                                            product.setMagnesium(Double.valueOf(nutritions[19][1]));
                                                        }
                                                        if (!nutritions[20][1].equals("")){
                                                            product.setSodium(Double.valueOf(nutritions[20][1]));
                                                        }
                                                        if (!nutritions[21][1].equals("")){
                                                            product.setPhosphorus(Double.valueOf(nutritions[21][1]));
                                                        }
                                                        if (!nutritions[22][1].equals("")){
                                                            product.setZinc(Double.valueOf(nutritions[22][1]));
                                                        }
                                                        if (!nutritions[23][1].equals("")){
                                                            product.setPoly_fat(Double.valueOf(nutritions[23][1]));
                                                        }
                                                        if (!nutritions[24][1].equals("")){
                                                            product.setTrans_fat(Double.valueOf(nutritions[24][1]));
                                                        }
                                                        if (!nutritions[25][1].equals("")){
                                                            product.setSatu_fat(Double.valueOf(nutritions[25][1]));
                                                        }
                                                        if (!nutritions[26][1].equals("")){
                                                            product.setMono_fat(Double.valueOf(nutritions[26][1]));
                                                        }
                                                        product.send();
                                                        Add_FB.super.onBackPressed();
                                                    }
                                                });
                                                sndalt.setNegativeButton("חזור למסך", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                    }
                                                });
                                                AlertDialog ad = sndalt.create();
                                                ad.show();
                                            }
                                        });
                                    }
                                };
                                adb.setPositiveButton("אשר", adbclk);
                                adb.setNegativeButton("בטל", adbclk);
                                adb.show();

                            }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void Return(View view) {
        ret = new AlertDialog.Builder(this);
        ret.setTitle("אשרו יציאה");
        ret.setMessage("כל המידע ששינית ימחק");
        ret.setPositiveButton("צא", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Add_FB.super.onBackPressed();
            }
        });
        ret.setNegativeButton("חזור למסך", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog ad = ret.create();
        ad.show();
    }
}

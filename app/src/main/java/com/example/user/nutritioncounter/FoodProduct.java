package com.example.user.nutritioncounter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FoodProduct {
    private String category;
    private String name;
    private double Phosphorus, Potassium, Vit_A, Vit_B1, Vit_B2, Vit_B3,
            Vit_B6,  Vit_B9, Vit_B12, Vit_C, Vit_D, Vit_E, Vit_K,
            Calcium, Calories, Cholesterol, Fiber, Iron, Magnesium, Mono_fat,
            Poly_fat, Proteins, Satu_fat, Sodium, Sugar, Trans_fat, Zinc;
    private DatabaseReference pro = FirebaseDatabase.getInstance().getReference().child("products");
    private DatabaseReference nut = FirebaseDatabase.getInstance().getReference().child("nutrient");

    public FoodProduct (String category, String product){
        this.category = category;
        this.name = product;
        Phosphorus = 0; Potassium = 0; Vit_A = 0; Vit_B1 = 0; Vit_B2 = 0; Vit_B3 = 0; Vit_B6 = 0;
        Vit_B9 = 0; Vit_B12 = 0; Vit_C = 0; Vit_D = 0; Vit_E = 0; Vit_K = 0; Calcium = 0;
        Calories = 0; Cholesterol = 0; Fiber = 0; Iron = 0; Magnesium = 0; Mono_fat = 0; Poly_fat = 0;
        Proteins = 0; Satu_fat = 0; Sodium = 0; Sugar = 0; Trans_fat = 0; Zinc = 0;
    }

    public void setCalcium(double calcium) {
        this.Calcium = calcium;
    }

    public void setPhosphorus(double phosphorus) {
        this.Phosphorus = phosphorus;
    }

    public void setPotassium(double potassium) {
        this.Potassium = potassium;
    }

    public void setCalories(double calories) {
        this.Calories = calories;
    }

    public void setCholesterol(double cholesterol) {
        this.Cholesterol = cholesterol;
    }

    public void setVit_A(double vit_A) {
        this.Vit_A = vit_A;
    }

    public void setVit_B1(double vit_B1) {
        this.Vit_B1 = vit_B1;
    }

    public void setFiber(double fiber) {
        this.Fiber = fiber;
    }

    public void setVit_B2(double vit_B2) {
        this.Vit_B2 = vit_B2;
    }

    public void setVit_B3(double vit_B3) {
        this.Vit_B3 = vit_B3;
    }

    public void setIron(double iron) {
        this.Iron = iron;
    }

    public void setMagnesium(double magnesium) {
        this.Magnesium = magnesium;
    }

    public void setVit_B6(double vit_B6) {
        this.Vit_B6 = vit_B6;
    }

    public void setVit_B9(double vit_B9) {
        this.Vit_B9 = vit_B9;
    }

    public void setMono_fat(double mono_fat) {
        this.Mono_fat = mono_fat;
    }

    public void setPoly_fat(double poly_fat) {
        this.Poly_fat = poly_fat;
    }

    public void setVit_B12(double vit_B12) {
        this.Vit_B12 = vit_B12;
    }

    public void setProteins(double proteins) {
        this.Proteins = proteins;
    }

    public void setSatu_fat(double satu_fat) {
        this.Satu_fat = satu_fat;
    }

    public void setVit_C(double vit_C) {
        this.Vit_C = vit_C;
    }

    public void setSodium(double sodium) {
        this.Sodium = sodium;
    }

    public void setSugar(double sugar) {
        this.Sugar = sugar;
    }

    public void setTrans_fat(double trans_fat) {
        this.Trans_fat = trans_fat;
    }

    public void setVit_D(double vit_D) {
        this.Vit_D = vit_D;
    }

    public void setVit_E(double vit_E) {
        this.Vit_E = vit_E;
    }

    public void setVit_K(double vit_K) {
        this.Vit_K = vit_K;
    }

    public void setZinc(double zinc) {
        this.Zinc = zinc;
    }

    public void send(){

        final  DatabaseReference tmp = pro.child(category).child(name);
        FirebaseDatabase.getInstance().getReference().child("key").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int Key = (int) dataSnapshot.getValue();
                tmp.setValue(Key);
                FirebaseDatabase.getInstance().getReference().child("key").setValue(Key + 1);

                if(Calcium != 0){
                    DatabaseReference n = nut.child("Calcium(mg)").child("" + Key);
                    n.setValue(Calcium);
                }
                if(Phosphorus != 0){
                    DatabaseReference n = nut.child("Phosphorus(mg)").child("" + Key);
                    n.setValue(Phosphorus);
                }
                if(Vit_C != 0){
                    DatabaseReference n = nut.child("C(mg)").child("" + Key);
                    n.setValue(Vit_C);
                }
                if(Cholesterol != 0){
                    DatabaseReference n = nut.child("Cholesterol(mg)").child("" + Key);
                    n.setValue(Cholesterol);
                }
                if(Vit_B2 != 0){
                    DatabaseReference n = nut.child("B2(mg)").child("" + Key);
                    n.setValue(Vit_B2);
                }
                if(Satu_fat != 0){
                    DatabaseReference n = nut.child("Saturated fat(g)").child("" + Key);
                    n.setValue(Satu_fat);
                }
                if(Vit_E != 0){
                    DatabaseReference n = nut.child("E(mg)").child("" + Key);
                    n.setValue(Vit_E);
                }
                if(Zinc != 0){
                    DatabaseReference n = nut.child("Zinc(mg)").child("" + Key);
                    n.setValue(Zinc);
                }
                if(Iron != 0){
                    DatabaseReference n = nut.child("Iron(mg)").child("" + Key);
                    n.setValue(Iron);
                }
                if(Sugar != 0){
                    DatabaseReference n = nut.child("Sugar(g)").child("" + Key);
                    n.setValue(Sugar);
                }
                if(Vit_K != 0){
                    DatabaseReference n = nut.child("K(ug)").child("" + Key);
                    n.setValue(Vit_K);
                }
                if(Mono_fat != 0){
                    DatabaseReference n = nut.child("Monounsaturated fat(g)").child("" + Key);
                    n.setValue(Mono_fat);
                }
                if(Vit_A != 0){
                    DatabaseReference n = nut.child("A(iu)").child("" + Key);
                    n.setValue(Vit_A);
                }
                if(Potassium != 0){
                    DatabaseReference n = nut.child("Potassium(mg)").child("" + Key);
                    n.setValue(Potassium);
                }
                if(Vit_B9 != 0){
                    DatabaseReference n = nut.child("B9(ug)").child("" + Key);
                    n.setValue(Vit_B9);
                }
                if(Poly_fat != 0){
                    DatabaseReference n = nut.child("Polyunsaturated fat(g))").child("" + Key);
                    n.setValue(Poly_fat);
                }
                if(Fiber != 0){
                    DatabaseReference n = nut.child("Fiber(g)").child("" + Key);
                    n.setValue(Fiber);
                }
                if(Magnesium != 0){
                    DatabaseReference n = nut.child("Magnesium(mg)").child("" + Key);
                    n.setValue(Magnesium);
                }
                if(Proteins != 0){
                    DatabaseReference n = nut.child("Proteins(g)").child("" + Key);
                    n.setValue(Proteins);
                }
                if(Vit_D != 0){
                    DatabaseReference n = nut.child("D(iu)").child("" + Key);
                    n.setValue(Vit_D);
                }
                if(Calories != 0){
                    DatabaseReference n = nut.child("Calories(kcal)").child("" + Key);
                    n.setValue(Calories);
                }
                if(Vit_B12 != 0){
                    DatabaseReference n = nut.child("B12(ug)").child("" + Key);
                    n.setValue(Vit_B12);
                }
                if(Vit_B3 != 0){
                    DatabaseReference n = nut.child("B3(mg)").child("" + Key);
                    n.setValue(Vit_B3);
                }
                if(Sodium != 0){
                    DatabaseReference n = nut.child("Sodium(mg)").child("" + Key);
                    n.setValue(Sodium);
                }
                if(Vit_B1 != 0){
                    DatabaseReference n = nut.child("B1(mg)").child("" + Key);
                    n.setValue(Vit_B1);
                }
                if(Vit_B6 != 0){
                    DatabaseReference n = nut.child("B6(mg)").child("" + Key);
                    n.setValue(Vit_B6);
                }
                if(Trans_fat != 0){
                    DatabaseReference n = nut.child("Trans fat(g)").child("" + Key);
                    n.setValue(Trans_fat);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

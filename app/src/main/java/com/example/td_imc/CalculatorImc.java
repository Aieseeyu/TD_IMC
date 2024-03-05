package com.example.td_imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class CalculatorImc extends AppCompatActivity {

    private EditText tailleInput;
    private EditText kgInput;
    private Button calculer;
    private Handler handler;
    private ProgressBar progressBar;
    private Float imc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_imc);

        tailleInput = findViewById(R.id.tailleInput);
        kgInput = findViewById(R.id.kgInput);
        calculer = findViewById(R.id.calculer);
        progressBar = findViewById(R.id.progressBar);
        handler = new Handler(Looper.getMainLooper());

        progressBar.setVisibility(View.INVISIBLE);

        calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tailleInput.getText().toString().equals("")) {
                    tailleInput.setText("");
                    Toast monToast = Toast.makeText(getApplicationContext(), "Veuillez renseigner une taille", Toast.LENGTH_SHORT);
                    monToast.show();

                } else if (kgInput.getText().toString().equals("")) {
                    kgInput.setText("");
                    Toast monToast = Toast.makeText(getApplicationContext(), "Veuillez renseigner un poids", Toast.LENGTH_SHORT);
                    monToast.show();

                } else {
                    progressBar.setVisibility(View.VISIBLE);

                    Float taille = Float.parseFloat(tailleInput.getText().toString().trim()) / 100;
                    Float kg = Float.parseFloat(kgInput.getText().toString().trim());

                    if (taille <= 2.50 && taille >= 1 && kg <= 250 && kg >= 40) {
                        imc = kg / (taille * taille);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent p = new Intent(getApplicationContext(), Resultat.class);
                                p.putExtra("imc", imc);
                                startActivity(p);
                                finish();
                            }
                        }, 3000);
                    } else if (!(taille <= 2.50 && taille >= 1)) {
                        Toast monToast = Toast.makeText(getApplicationContext(), "La taille doit etre comprise entre 100cm et 250cm", Toast.LENGTH_SHORT);
                        monToast.show();
                    } else if (!(kg <= 250 && kg >= 40)) {
                        Toast monToast = Toast.makeText(getApplicationContext(), "Les kilogrames doivent etre compris entre 40 et 250", Toast.LENGTH_SHORT);
                        monToast.show();
                    }
                }
            }
        });


    }
}
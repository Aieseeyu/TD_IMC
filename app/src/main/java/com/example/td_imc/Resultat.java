package com.example.td_imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Resultat extends AppCompatActivity {

    private TextView resultatInput;
    private Float imc;
    private Button recalculer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        resultatInput=findViewById(R.id.resultat);
        recalculer=findViewById(R.id.recalculer);

        imc = getIntent().getExtras().getFloat("imc");

        DecimalFormat dfrmt =  new DecimalFormat();
        dfrmt.setMaximumFractionDigits(1);

        resultatInput.setText(String.format("Votre IMC est: " + dfrmt.format(imc)));

        recalculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), CalculatorImc.class);
                startActivity(p);
                finish();
            }
        });
    }
}
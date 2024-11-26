package com.example.miappintegradora.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miappintegradora.R;

public class HomeActivity extends AppCompatActivity {

    private Button btnReportProblem;
    private Button btnViewIncidents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Enlazar botones
        btnReportProblem = findViewById(R.id.btnReportProblem);
        btnViewIncidents = findViewById(R.id.btnViewIncidents);

        // Navegar a Reportar Problema
        btnReportProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ReportProblemActivity.class);
                startActivity(intent);
            }
        });

        // Navegar a Ver Problemas Reportados
        btnViewIncidents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ViewIncidentsActivity.class);
                startActivity(intent);
            }
        });
    }
}


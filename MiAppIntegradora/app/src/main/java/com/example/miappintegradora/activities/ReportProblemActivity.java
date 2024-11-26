package com.example.miappintegradora.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miappintegradora.R;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReportProblemActivity extends AppCompatActivity {

    private EditText editTextDescription;
    private Button btnSubmitProblem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_problem);

        editTextDescription = findViewById(R.id.editTextDescription);
        btnSubmitProblem = findViewById(R.id.btnSubmitProblem);

        btnSubmitProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = editTextDescription.getText().toString().trim();

                if (description.isEmpty()) {
                    Toast.makeText(ReportProblemActivity.this, "Por favor, ingrese una descripción", Toast.LENGTH_SHORT).show();
                } else {
                    enviarProblema(description);
                }
            }
        });
    }

    private void enviarProblema(String description) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL("http://tu-servidor.com/ruta-al-endpoint-reportar"); // Cambiar por la URL real
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setDoOutput(true);

                    String params = "description=" + description;

                    OutputStream os = connection.getOutputStream();
                    os.write(params.getBytes());
                    os.flush();
                    os.close();

                    return "Problema enviado exitosamente";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Error al enviar problema";
                }
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(ReportProblemActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}
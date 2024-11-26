package com.example.miappintegradora.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miappintegradora.adapters.IncidentsAdapter;
import com.example.miappintegradora.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ViewIncidentsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewIncidents;
    private ArrayList<String> incidentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_incidents);

        recyclerViewIncidents = findViewById(R.id.recyclerViewIncidents);
        incidentsList = new ArrayList<>();

        recyclerViewIncidents.setLayoutManager(new LinearLayoutManager(this));

        // Llamada al servicio para obtener las incidencias
        obtenerIncidencias();
    }

    private void obtenerIncidencias() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL("http://tu-servidor.com/ruta-al-endpoint-incidencias"); // Cambia esta URL
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Recibir la respuesta
                        return connection.getInputStream().toString(); // Aquí sería necesario leer el InputStream y convertirlo en String
                    } else {
                        return "Error al obtener los datos";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Error al obtener los datos";
                }
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if (!result.contains("Error")) {
                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String description = jsonObject.getString("description");  // Cambiar al campo correcto
                            incidentsList.add(description);
                        }

                        // Configurar el RecyclerView
                        IncidentsAdapter adapter = new IncidentsAdapter(incidentsList);
                        recyclerViewIncidents.setAdapter(adapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ViewIncidentsActivity.this, result, Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}


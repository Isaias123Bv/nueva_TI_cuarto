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

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Enlazar elementos del diseño
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        // Configurar acción del botón
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    realizarLogin(username, password);
                }
            }
        });
    }

    private void realizarLogin(String username, String password) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL("http://tu-servidor.com/login-endpoint"); // Cambia esta URL
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);

                    String params = "username=" + username + "&password=" + password;
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(params.getBytes());
                    outputStream.flush();
                    outputStream.close();

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        return "Login exitoso";
                    } else {
                        return "Error en las credenciales";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Error al conectar con el servidor";
                }
            }

            @Override
            protected void onPostExecute(String result) {
                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}

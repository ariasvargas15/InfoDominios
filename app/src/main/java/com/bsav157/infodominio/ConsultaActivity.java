package com.bsav157.infodominio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConsultaActivity extends AppCompatActivity {

    private TextView consulta;
    private EditText host;
    private RequestQueue requestQueue;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        consulta = findViewById(R.id.consulta_textview);
        host = findViewById(R.id.host);
        requestQueue = Volley.newRequestQueue(this);
    }

    public void consultarDominio(View view) {

        url = "http://192.168.1.15:7000/search/" + host.getText().toString();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray lista = response.getJSONArray("servers");
                    String respuesta = "";
                    for (int i=0; i < lista.length(); i++) {
                        JSONObject o = lista.getJSONObject(i);
                        respuesta += "Server " + (i+1) + "\n";
                        respuesta += "Adress: " + o.getString("address") + "\n";
                        respuesta += "Ssl grade: " + o.getString("ssl_grade") + "\n";
                        respuesta += "Country: " + o.getString("country") + "\n";
                        respuesta += "Owner: " + o.getString("owner") + "\n\n";
                    }

                    respuesta += "Servers changed: " + response.getBoolean("servers_changed") + "\n";
                    respuesta += "Ssl grade: " + response.getString("ssl_grade") + "\n";
                    respuesta += "Previous ssl grade: " + response.getString("previous_ssl_grade") + "\n";
                    respuesta += "Logo: " + response.getString("logo") + "\n";
                    respuesta += "Title: " + response.getString("title") + "\n";
                    respuesta += "Is down: " + response.getBoolean("is_down") + "\n";
                    consulta.setText(respuesta);
                } catch(Exception e){
                    Log.e("responseError", "res");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);
    }
}

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

public class HistorialActivity extends AppCompatActivity {

    private TextView historial;
    private RequestQueue requestQueue;
    private String url = "http://192.168.1.15:7000/history";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        historial = findViewById(R.id.history_textview);
        requestQueue = Volley.newRequestQueue(this);
    }

    public void consultarHistorial(View view) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray lista = response.getJSONArray("items");
                    String respuesta = "Dominios: \n\n";
                    for (int i=0; i < lista.length(); i++) {
                        JSONObject o = lista.getJSONObject(i);
                        respuesta += o.getString("domain") + "\n";
                    }
                    historial.setText(respuesta);
                }catch(Exception e){
                    Log.e("responseError", "res");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {}
        });
        requestQueue.add(request);
    }
}

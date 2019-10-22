package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button button;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text1);
        button=findViewById(R.id.button1);
        editText=findViewById(R.id.edit1);
        requestQueue= Volley.newRequestQueue(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata();
            }
        });
    }
    
    
    public void getdata()
    { 
        String place= editText.getText().toString();
        
        //url to get weather of a particular place in india 
        //passing a query parameter (place) selected by user in text field
        
        String url="http://api.openweathermap.org/data/2.5/weather?q="+place+",india&appid=90ebb1d21627a4cdb6b3117daa901f14";
        
        //sending get request to the url and taking response as JsonObject
        
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject jsonArray=response.getJSONObject("main");
                    String temp=jsonArray.getString("temp");
                    textView.setText(temp);
                } catch (JSONException e) {
                    
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}

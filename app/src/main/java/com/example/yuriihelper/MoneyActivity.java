package com.example.yuriihelper;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MoneyActivity extends AppCompatActivity {

    public EditText editTextCurrency;
    public EditText editTextPriceOnMarket;
    public EditText editTextSpend;
    public EditText editTextDate;
    public EditText editTextAmount;
    public Button buttonInsert;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        getSupportActionBar().setTitle("Money");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#A95698"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        initView();
    }

    private void initView() {
        editTextCurrency = findViewById(R.id.edit_text_currency);
        editTextPriceOnMarket = findViewById(R.id.edit_text_price_on_market);
        editTextSpend = findViewById(R.id.edit_text_spend);
        editTextDate = findViewById(R.id.edit_text_date);
        editTextAmount = findViewById(R.id.edit_text_amount);

        progressDialog = new ProgressDialog(MoneyActivity.this);
        progressDialog.setMessage("Loading...");

        buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoneyData();
                progressDialog.show();
            }
        });

    }

    private void addMoneyData() {
        String currency = editTextCurrency.getText().toString();
        String priceInMarket = editTextPriceOnMarket.getText().toString();
        String spend = editTextSpend.getText().toString();
        String date = editTextDate.getText().toString();
        String amount = editTextAmount.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbwbtSrohh6DYCY7I32fKbN0eHIijMrmL36lHJU0-5Hq68RC-TV3R9gx677EqcXysGX8EA/exec", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MoneyActivity.this, "Success", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> params = new HashMap<>();
                params.put("action", "addMoneyData");
                params.put("currency", currency);
                params.put("priceInMarket", priceInMarket);
                params.put("spend", spend);
                params.put("date", date);
                params.put("amount", amount);

                return params;
            }
        };
        int socketTimeOut = 50000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
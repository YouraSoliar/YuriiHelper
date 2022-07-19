package com.example.yuriihelper;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    public EditText editTextType;
    public EditText editTextTicker;
    public EditText editTextActualPrice;
    public EditText editTextSpentUSD;
    public EditText editTextAmount;
    public EditText editTextDate;
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
        editTextType = findViewById(R.id.edit_text_type);
        editTextTicker = findViewById(R.id.edit_text_ticker);
        editTextActualPrice = findViewById(R.id.edit_text_actual_price);
        editTextSpentUSD = findViewById(R.id.edit_text_spent_usd);
        editTextAmount = findViewById(R.id.edit_text_amount);
        editTextDate = findViewById(R.id.edit_text_date);

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
        String id = String.valueOf(0);
        String type = editTextType.getText().toString();
        String ticker = editTextTicker.getText().toString();
        String actualPrice = editTextActualPrice.getText().toString();
        String spentUSD = editTextSpentUSD.getText().toString();
        String amount = editTextAmount.getText().toString();
        String date = editTextDate.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbwra-n2G55HCCX0HQsc-eRSUADgXC7YCEIK9U26pnF7BQs1tS-aBQdpVePklue9PPc5gg/exec", new Response.Listener<String>() {
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
                params.put("id", id);
                params.put("type", type);
                params.put("ticker", ticker);
                params.put("actualPrice", actualPrice);
                params.put("spentUSD", spentUSD);
                params.put("amount", amount);
                params.put("date", date);

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
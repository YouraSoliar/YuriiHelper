package com.example.yuriihelper;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MoneyActivity extends AppCompatActivity {

    public Spinner spinnerType;
    public Spinner spinnerOperation;
    public EditText editTextTicker;
    public EditText editTextActualPrice;
    public EditText editTextSpentUSD;
    public EditText editTextAmount;
    public EditText editTextDate;
    public Button buttonInsert;

    private SharedPreferences sharedPreferences;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        initView();
    }

    private void initView() {
        spinnerType = findViewById(R.id.spinnerType);
        spinnerOperation = findViewById(R.id.spinnerOperation);
        editTextTicker = findViewById(R.id.edit_text_ticker);
        editTextActualPrice = findViewById(R.id.edit_text_actual_price);
        editTextSpentUSD = findViewById(R.id.edit_text_spent_usd);
        editTextAmount = findViewById(R.id.edit_text_amount);
        editTextDate = findViewById(R.id.edit_text_date);

        progressDialog = new ProgressDialog(MoneyActivity.this);
        progressDialog.setMessage("Loading...");

        getSupportActionBar().setTitle("Money");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#A95698"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = sdf.format(c.getTime());

        editTextDate.setText(strDate);

        buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmptyText(editTextTicker) || isEmptyText(editTextActualPrice) || isEmptyText(editTextSpentUSD)
                        || isEmptyText(editTextAmount)) {
                    Toast.makeText(MoneyActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isDigitsOnly((CharSequence) editTextSpentUSD.getText())){
                    addMoneyData();
                    progressDialog.show();
                } else {
                    Toast.makeText(MoneyActivity.this, "Spent USD filed should`n contain letters", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isEmptyText(EditText editText) {
        return editText.getText().toString().trim().matches("");
    }

    private String getID() {

        sharedPreferences = getSharedPreferences("id", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int id = sharedPreferences.getInt("id_key", 0);

        editor.putInt("id_key", ++id);
        editor.apply();

        return String.valueOf(id);
    }

    private void addMoneyData() {
        String id = getID();
        String type = spinnerType.getSelectedItem().toString().trim();
        String operation = spinnerOperation.getSelectedItem().toString().trim();
        String ticker = editTextTicker.getText().toString().trim();
        String actualPrice = editTextActualPrice.getText().toString().trim();
        int usd = Integer.parseInt(editTextSpentUSD.getText().toString().trim());
        if (spinnerOperation.getSelectedItem().toString().trim().equals("Buy")) {
            usd *= (-1);
        }
        String spentUSD = String.valueOf(usd);
        String amount = editTextAmount.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbwhaUGiRTlWWvr6ETsK190YiMdbRlzjQjPlA6YqziH-1VvcXTDT_pRLlGliFNJ1vRWjvg/exec", new Response.Listener<String>() {
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
                params.put("operation", operation);
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
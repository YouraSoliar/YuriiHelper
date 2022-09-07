package com.example.yuriihelper.activity;

import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuriihelper.AppCompat;
import com.example.yuriihelper.ItemCurrency;
import com.example.yuriihelper.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BankActivity extends AppCompat {

    private TextView textViewDollar;
    private TextView textViewOldDollar;
    private TextView textViewEuro;
    private TextView textViewOldEuro;
    private Spinner spinnerCurrency;

    private ItemCurrency itemDollar;
    private ItemCurrency itemEuro;

    private Document document;
    private Thread secondThread;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        initView();
        initThread();
        initAction();
    }

    private void initView() {
        getSupportActionBar().setTitle(R.string.button_bank);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.bg_button6_2));

        textViewDollar = findViewById(R.id.textViewDollar);
        textViewOldDollar = findViewById(R.id.textViewOldDollar);
        textViewEuro = findViewById(R.id.textViewEuro);
        textViewOldEuro = findViewById(R.id.textViewOldEuro);

        spinnerCurrency = findViewById(R.id.spinnerCurrency);

        itemDollar = new ItemCurrency();
        itemEuro = new ItemCurrency();
    }

    private void initAction() {
        double dollar = 39.0;
        double oldDollar = 38.0;
        double euro = 43.0;
        double oldEuro = 44.0;

        spinnerCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View selectedItemView, int position, long id) {

                switch (position) {
                    case 0:
                        textViewDollar.setText(itemDollar.getAverage());
                        textViewEuro.setText(itemEuro.getAverage());
                        break;
                    case 1:
                        textViewDollar.setText(itemDollar.getNbu());
                        textViewEuro.setText(itemEuro.getNbu());
                        break;
                    case 2:
                        textViewDollar.setText(itemDollar.getBlackMarket());
                        textViewEuro.setText(itemEuro.getBlackMarket());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (dollar > oldDollar) {
            textViewDollar.setTextColor(ContextCompat.getColor(this, R.color.red));
        } else {
            textViewDollar.setTextColor(ContextCompat.getColor(this, R.color.green));
        }
        if (euro > oldEuro) {
            textViewEuro.setTextColor(ContextCompat.getColor(this, R.color.red));
        } else {
            textViewEuro.setTextColor(ContextCompat.getColor(this, R.color.green));
        }
    }

    private void initThread() {
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secondThread = new Thread(runnable);
        secondThread.start();
    }

    public void getWeb() {
        try {
            document = Jsoup.connect("https://minfin.com.ua/ua/currency/").get();
            Elements table = document.getElementsByTag("tbody");
            Element tableCurrency = table.get(0);
            Elements elementsFromTable = tableCurrency.children();

            Element dollar = elementsFromTable.get(0);
            Element euro = elementsFromTable.get(1);

            Elements dollar_elements = dollar.children();
            Elements euro_elements = euro.children();

            String averageDollar = dollar_elements.get(1).text().substring(0, 6) + " / " + dollar_elements.get(1).text().substring(23, 29);
            String averageEuro = euro_elements.get(1).text().substring(0, 6) + " / " + euro_elements.get(1).text().substring(23, 29);

            itemDollar.setName(dollar_elements.get(0).text());
            itemDollar.setAverage(averageDollar);
            itemDollar.setNbu(dollar_elements.get(2).text().substring(0, 6));
            itemDollar.setBlackMarket(dollar_elements.get(3).text());

            itemEuro.setName(euro_elements.get(0).text());
            itemEuro.setAverage(averageEuro);
            itemEuro.setNbu(euro_elements.get(2).text().substring(0, 6));
            itemEuro.setBlackMarket(euro_elements.get(3).text());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
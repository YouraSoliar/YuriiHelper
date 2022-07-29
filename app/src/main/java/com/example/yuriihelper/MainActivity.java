package com.example.yuriihelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    
    private TextView textViewCar;
    private TextView textViewMoney;
    private TextView textViewStatistic;
    private TextView textViewNotification;

    private TextView textViewDollar;
    private TextView textViewOldDollar;
    private TextView textViewEuro;
    private TextView textViewOldEuro;

    private Document document;
    private Thread secondThread;
    private Runnable runnable;

    private Spinner spinnerCurrency;

    private ItemCurrency itemDollar;
    private ItemCurrency itemEuro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Main menu");

        initView();
        initThread();
        initAction();
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

    private void initView() {
        textViewCar = findViewById(R.id.text_view_car);
        textViewMoney = findViewById(R.id.text_view_money);
        textViewStatistic = findViewById(R.id.text_view_statistic);
        textViewNotification = findViewById(R.id.text_view_notification);

        textViewDollar = findViewById(R.id.textViewDollar);
        textViewOldDollar = findViewById(R.id.textViewOldDollar);
        textViewEuro = findViewById(R.id.textViewEuro);
        textViewOldEuro = findViewById(R.id.textViewOldEuro);

        spinnerCurrency = findViewById(R.id.spinnerCurrency);

        itemDollar = new ItemCurrency();
        itemEuro = new ItemCurrency();
    }


    public void onClickTextView(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.text_view_car:
                intent = new Intent(getApplicationContext(), CarActivity.class);
                startActivity(intent);
                break;
            case R.id.text_view_money:
                intent = new Intent(getApplicationContext(), MoneyActivity.class);
                startActivity(intent);
                break;
            case R.id.text_view_statistic:
                intent = new Intent(getApplicationContext(), StatisticActivity.class);
                startActivity(intent);
                break;
            case R.id.text_view_notification:
                intent = new Intent(getApplicationContext(), NotificationActivity.class);
                startActivity(intent);
                break;
            default:
                break;
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

}
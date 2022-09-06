package com.example.yuriihelper.activity;

import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuriihelper.AppCompat;
import com.example.yuriihelper.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class WeatherActivity extends AppCompat {

    private EditText editTextWeather;
    private TextView textViewFind;
    private TextView textViewWeather;
    private ImageView imageViewWeather;
    private ProgressBar progress;

    private final String API_KEY = "d7029b257960a0104d2e3d60432ad40b";
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=" + API_KEY + "&units=metric";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        initView();
        initAction();
    }

    private void initView() {
        getSupportActionBar().setTitle(R.string.button_weather);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.bg_button8_2));

        this.editTextWeather = findViewById(R.id.editTextWeather);
        this.textViewFind = findViewById(R.id.textViewFind);
        this.textViewWeather = findViewById(R.id.textViewWeather);
        this.imageViewWeather = findViewById(R.id.imageViewWeather);
        this.progress = findViewById(R.id.progress);
    }

    private void initAction() {
        textViewFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = editTextWeather.getText().toString().trim();
                if (city.equals("")) {
                    Toast.makeText(WeatherActivity.this, "Fill the field", Toast.LENGTH_SHORT).show();
                } else {
                    findCityWeather(city);
                }
            }
        });
    }

    private void findCityWeather(String city) {
        // api site https://openweathermap.org/
        String finalURl = String.format(BASE_URL, city);
        new GetUrlData().execute(finalURl);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private class GetUrlData extends AsyncTask<String, String, String> {


        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progress.setVisibility(View.GONE);

            try {
                JSONObject jsonObject = new JSONObject(result);
                textViewWeather.setText(jsonObject.getJSONObject("main").getDouble("temp") + "°");
                String weather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
                String weatherDescription = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
                String sunset = String.valueOf(jsonObject.getJSONObject("sys").getLong("sunset")).substring(0, 10);
                String sunrise = String.valueOf(jsonObject.getJSONObject("sys").getLong("sunrise")).substring(0, 10);
                long unixSunset = Long.parseLong(sunset);
                long unixSunrise = Long.parseLong(sunrise);
                setImageWeather(weather, weatherDescription, unixSunset, unixSunrise);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void setImageWeather(String weather, String weatherDescription, long unixSunset, long unixSunrise) {

        Calendar cal = Calendar.getInstance();
        TimeZone timeZone =  cal.getTimeZone();
        Date cals = Calendar.getInstance(TimeZone.getDefault()).getTime();
        long milliseconds =   cals.getTime();
        milliseconds = milliseconds + timeZone.getOffset(milliseconds);
        long unixCurrent = milliseconds / 1000L;

        boolean isDay = unixCurrent < unixSunset;
        boolean isNight = unixCurrent < unixSunrise;

        switch (weather) {
            case "Clouds":
                if (isDay) {
                    imageViewWeather.setImageResource(R.drawable.cloudy_sun);
                } else {
                    imageViewWeather.setImageResource(R.drawable.cloudy_moon);
                }
                break;
            case "Clear":
                if (isDay) {
                    imageViewWeather.setImageResource(R.drawable.sunny);
                } else {
                    imageViewWeather.setImageResource(R.drawable.clean_moon);
                }
                break;
            case "Rain":
                if (weatherDescription.equals("light intensity shower rain")) {
                    imageViewWeather.setImageResource(R.drawable.rainy);
                } else if (weatherDescription.equals("light rain")) {
                    if (isDay) {
                        imageViewWeather.setImageResource(R.drawable.rain_sun);
                    } else {
                        imageViewWeather.setImageResource(R.drawable.rain_moon);
                    }
                }
                break;
            case "Snow":
                imageViewWeather.setImageResource(R.drawable.snow);
                break;
            default:
                imageViewWeather.setImageResource(R.drawable.no_pictures);
                break;
        }
    }
}
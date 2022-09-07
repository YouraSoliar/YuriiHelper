package com.example.yuriihelper.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import java.util.Locale;
import java.util.TimeZone;

public class WeatherActivity extends AppCompat {

    private EditText editTextWeather;
    private TextView textViewFind;
    private TextView textViewWeather;
    private TextView textViewHold1;
    private TextView textViewHold2;
    private TextView textViewHold3;
    private ImageView imageViewWeather;
    private ProgressBar progress;
    SharedPreferences sharedPreferences;

    private final String API_KEY = "d7029b257960a0104d2e3d60432ad40b";
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=" + API_KEY + "&units=metric";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        initView();

        sharedPreferences = getSharedPreferences("Holds", MODE_PRIVATE);
        String hold1 = sharedPreferences.getString("hold1", getString(R.string.text_view_hold));
        String hold2 = sharedPreferences.getString("hold2", getString(R.string.text_view_hold));
        String hold3 = sharedPreferences.getString("hold3", getString(R.string.text_view_hold));
        if (hold1.equals("none")) {
            textViewHold1.setText(R.string.text_view_hold);
        } else {
            textViewHold1.setText(hold1);
        }
        if (hold2.equals("none")) {
            textViewHold2.setText(R.string.text_view_hold);
        } else {
            textViewHold2.setText(hold2);
        }
        if (hold3.equals("none")) {
            textViewHold3.setText(R.string.text_view_hold);
        } else {
            textViewHold3.setText(hold3);
        }

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
        this.textViewHold3 = findViewById(R.id.textViewHold3);
        this.textViewHold2 = findViewById(R.id.textViewHold2);
        this.textViewHold1 = findViewById(R.id.textViewHold1);
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

        textViewHold1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findCityWeather(textViewHold1.getText().toString());
            }
        });

        textViewHold2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findCityWeather(textViewHold2.getText().toString());
            }
        });

        textViewHold3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findCityWeather(textViewHold3.getText().toString());
            }
        });

        textViewHold3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showEditTextDialog(textViewHold3);
                return false;
            }
        });

        textViewHold2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showEditTextDialog(textViewHold2);
                return true;
            }
        });

        textViewHold1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showEditTextDialog(textViewHold1);
                return false;
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

    private void showEditTextDialog (TextView textView) {

        LayoutInflater inflater = LayoutInflater.from(this);
        final View viewInflater = inflater.inflate(R.layout.edit_text_layout, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(viewInflater);

        final EditText editTextFastPanel = (EditText) viewInflater.findViewById(R.id.editTextFastPanel);
        editTextFastPanel.requestFocus();
        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        alert.setPositiveButton(R.string.dialog_set, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                if (editTextFastPanel.getText().toString().equals("")) {
                    textView.setText(R.string.text_view_hold);
                } else {
                    textView.setText(editTextFastPanel.getText().toString().trim());
                }
                imm.hideSoftInputFromWindow(editTextFastPanel.getWindowToken(), 0);
            }
        });

        alert.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                imm.hideSoftInputFromWindow(editTextFastPanel.getWindowToken(), 0);
                dialog.cancel();
            }
        });

        alert.show();
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
            if (result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    textViewWeather.setText(jsonObject.getJSONObject("main").getDouble("temp") + "°");
                    String weather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
                    String weatherDescription = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
                    double windSpeed = jsonObject.getJSONObject("wind").getDouble("speed");
                    String sunset = String.valueOf(jsonObject.getJSONObject("sys").getLong("sunset")).substring(0, 10);
                    String sunrise = String.valueOf(jsonObject.getJSONObject("sys").getLong("sunrise")).substring(0, 10);
                    long unixSunset = Long.parseLong(sunset);
                    long unixSunrise = Long.parseLong(sunrise);
                    setImageWeather(weather, weatherDescription, windSpeed, unixSunset, unixSunrise);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(WeatherActivity.this, R.string.toast_incorrect_city, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setImageWeather(String weather, String weatherDescription, double windSpeed, long unixSunset, long unixSunrise) {

        Calendar cal = Calendar.getInstance();
        TimeZone timeZone =  cal.getTimeZone();
        Date cals = Calendar.getInstance(TimeZone.getDefault()).getTime();
        long milliseconds =   cals.getTime();
        milliseconds = milliseconds + timeZone.getOffset(milliseconds);
        long unixCurrent = (milliseconds / 1000L) - 10800;

        boolean isDay = unixCurrent < unixSunset;
        boolean isNight = unixCurrent < unixSunrise;

        switch (weather) {
            case "Clouds":
                if (windSpeed > 3.4) {
                    imageViewWeather.setImageResource(R.drawable.wind);
                } else {
                    if (isDay) {
                        imageViewWeather.setImageResource(R.drawable.cloudy_sun);
                    } else {
                        imageViewWeather.setImageResource(R.drawable.cloudy_moon);
                    }
                }
                break;
            case "Clear":
                if (isDay) {
                    imageViewWeather.setImageResource(R.drawable.sunny);
                } else {
                    imageViewWeather.setImageResource(R.drawable.clean_moon);
                }
                break;
            case "Drizzle":
                if (isDay) {
                    imageViewWeather.setImageResource(R.drawable.rain_sun);
                } else {
                    imageViewWeather.setImageResource(R.drawable.rain_moon);
                }
                break;
            case "Snow":
                imageViewWeather.setImageResource(R.drawable.snow);
                break;
            case "Rain":
                if (weatherDescription.contains("heavy")) {
                    imageViewWeather.setImageResource(R.drawable.heavy_rain);
                } else {
                    imageViewWeather.setImageResource(R.drawable.rainy);
                }
                break;
            case "Thunderstorm":
                imageViewWeather.setImageResource(R.drawable.storm);
                break;
            default:
                imageViewWeather.setImageResource(R.drawable.no_pictures);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (textViewHold1.getText().toString().equals(getText(R.string.text_view_hold))) {
            editor.putString("hold1", "none");
        } else {
            editor.putString("hold1", textViewHold1.getText().toString());
        }
        if (textViewHold2.getText().toString().equals(getText(R.string.text_view_hold))) {
            editor.putString("hold2", "none");
        } else {
            editor.putString("hold2", textViewHold2.getText().toString());
        }
        if (textViewHold3.getText().toString().equals(getText(R.string.text_view_hold))) {
            editor.putString("hold3", "none");
        } else {
            editor.putString("hold3", textViewHold3.getText().toString());
        }
        editor.apply();
    }
}
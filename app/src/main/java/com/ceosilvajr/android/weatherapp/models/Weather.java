package com.ceosilvajr.android.weatherapp.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ceosilvajr on 20/02/16.
 */
public class Weather {

    private String icon;

    private float windSpeed;

    private float pressure;

    private long sunRise;

    private long sunSet;

    private String countryCode;

    private String city;

    private Date foreCastDate;

    private float temperature;

    private float temperatureMin;

    private float temperatureMax;

    private String description;

    public Weather(JsonObject jsonObject) {
        JsonArray w = jsonObject.get("weather").getAsJsonArray();
        this.icon = w.get(0).getAsJsonObject().get("icon").getAsString();
        this.description = w.get(0).getAsJsonObject().get("description").getAsString();
        this.windSpeed = jsonObject.get("wind").getAsJsonObject().get("speed").getAsFloat();
        JsonObject m = jsonObject.get("main").getAsJsonObject();
        this.pressure = m.get("pressure").getAsFloat();
        this.temperature = m.get("temp").getAsFloat();
        this.temperatureMin = m.get("temp_min").getAsFloat();
        this.temperatureMax = m.get("temp_max").getAsFloat();
        JsonObject s = jsonObject.get("sys").getAsJsonObject();
        try {
            this.sunRise = s.get("sunrise").getAsLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.sunSet = s.get("sunset").getAsLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.countryCode = s.get("country").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.city = jsonObject.get("name").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-mm-dd hh:mm:ss", Locale.US);
        try {
            this.foreCastDate = simpleDateFormat.parse(jsonObject.get("dt_txt").getAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public long getSunRise() {
        return sunRise;
    }

    public void setSunRise(long sunRise) {
        this.sunRise = sunRise;
    }

    public long getSunSet() {
        return sunSet;
    }

    public void setSunSet(long sunSet) {
        this.sunSet = sunSet;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(float temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public float getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(float temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconUrl() {
        return "http://openweathermap.org/img/w/" + getIcon();
    }

    public Date getForeCastDate() {
        return foreCastDate;
    }

    public void setForeCastDate(Date foreCastDate) {
        this.foreCastDate = foreCastDate;
    }

    public String getSunSetTime() {
        Date date = new Date(getSunSet());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm", Locale.US);
        return simpleDateFormat.format(date);
    }

    public String getSunRiseTime() {
        Date date = new Date(getSunRise());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm", Locale.US);
        return simpleDateFormat.format(date);
    }

    public String getTodayDate() {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        return "TODAY: " + simpleDateFormat.format(date);
    }

    public String getFormattedWindSpeed() {
        return getWindSpeed() + " m/s";
    }

    public String getFormattedTemperature() {
        return getTemperature() + " °C";
    }

    public String getFormattedTemperatureMin() {
        return getTemperatureMin() + " °C";
    }

    public String getFormattedTemperatureMax() {
        return getTemperatureMax() + " °C";
    }

    public String getFormattedCityCountry() {
        return getCity() + "," + getCountryCode();
    }

    public String getFormattedForecastDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM", Locale.US);
        return simpleDateFormat.format(getForeCastDate());
    }

    @Override
    public String toString() {
        return "Weather{" +
                "icon='" + icon + '\'' +
                ", windSpeed=" + windSpeed +
                ", pressure=" + pressure +
                ", sunRise=" + sunRise +
                ", sunSet=" + sunSet +
                ", countryCode='" + countryCode + '\'' +
                ", city='" + city + '\'' +
                ", temperature=" + temperature +
                ", temperatureMin=" + temperatureMin +
                ", temperatureMax=" + temperatureMax +
                ", description='" + description + '\'' +
                '}';
    }
}

package com.example.myapplication;

public class Weather {
    public String getIconPhrase() {
        return iconPhrase;
    }

    public void setIconPhrase(String iconPhrase) {
        this.iconPhrase = iconPhrase;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    private  String iconPhrase;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    private  String dateTime;
    private Temperature temperature;
    private  int WeatherIcon;

    public int getWeatherIcon() {
        return WeatherIcon;
    }

    public void setWeatherIcon(int icon) {
        this.WeatherIcon = icon;
    }
}

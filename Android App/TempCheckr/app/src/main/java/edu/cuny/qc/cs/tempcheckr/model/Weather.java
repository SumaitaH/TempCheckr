package edu.cuny.qc.cs.tempcheckr.model;
import java.util.ArrayList;
import java.util.List;

public class Weather {

    private String forecast;
    private String dayOfWeek;
    private String weekday;
    private String dateTime;
    private String iconLink;
    private String description;
    private String highTemp;
    private String lowTemp;
    private String windSpeed;
    private String rainProb;

    public Weather() {
        this.forecast = "";
        this.dayOfWeek = "";
        this.weekday = "";
        this.dateTime = "";
        this.iconLink = "";
        this.description = "";
        this.highTemp = "";
        this.lowTemp = "";
        this.windSpeed = "";
        this.rainProb = "";
    }

    public Weather(String forecast, String dayOfWeek, String weekday, String dateTime, String iconLink, String description, String highTemp, String lowTemp, String windSpeed, String rainProb) {
        this.forecast = forecast;
        this.dayOfWeek = dayOfWeek;
        this.weekday = weekday;
        this.dateTime = dateTime;
        this.iconLink = iconLink;
        this.description = description;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.windSpeed = windSpeed;
        this.rainProb = rainProb;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getRainProb() {
        return rainProb;
    }

    public void setRainProb(String rainProb) {
        this.rainProb = rainProb;
    }


}

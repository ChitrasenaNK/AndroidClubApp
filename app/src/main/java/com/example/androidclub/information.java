package com.example.androidclub;

public class information {
    private String name,des,venue,date;
    public information()
    {

    }


    public information(String name, String des, String venue, String date) {
        this.name=name;
        this.des = des;
        this.venue=venue;
        this.date=date;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

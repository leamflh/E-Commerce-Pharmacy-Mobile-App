package com.example.mysqldemo;

public class Reminder {
    private String medname;
    private int nbdays,nbtimes;

    public Reminder(String name,int nbdays,int numtimes){
        this.setMedname(name);
        this.setNbdays(nbdays);
        this.setNbtimes(numtimes);
    }

    public int getNbtimes() {
        return nbtimes;
    }

    public void setNbtimes(int nbtimes) {
        this.nbtimes = nbtimes;
    }

    public String getMedname() {
        return medname;
    }

    public void setMedname(String medname) {
        this.medname = medname;
    }

    public int getNbdays() {
        return nbdays;
    }

    public void setNbdays(int nbdays) {
        this.nbdays = nbdays;
    }
}
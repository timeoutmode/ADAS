package com.example.adas.Model;

import java.util.ArrayList;

public class SpeechRecogntionQuestion {

    String phrase;

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public ArrayList<Item> getListofitems() {
        return listofitems;
    }

    public void setListofitems(ArrayList<Item> listofitems) {
        this.listofitems = listofitems;
    }

    ArrayList<Item> listofitems;

}

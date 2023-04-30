package com.kurs;

import java.io.Serializable;
import java.util.ArrayList;

public class Info implements Serializable{
    private String name;
    private int ID;
    private ArrayList<Integer> ocenka = new ArrayList<Integer>(3);
    private boolean metka = false;

    Info(int ID, String name, ArrayList<Integer> ocenka){
        this.name = name;
        this.ocenka = ocenka;
        this.ID = ID;
    }

    public void invertMetka(){
        metka = !metka;
    }

    public float meanOcenka(){
        float sum = 0;
        for (int i=0; i<ocenka.size(); i++) sum+=ocenka.get(i);
        return sum/ocenka.size();
    }

    public String getName(){
        return name;
    }

    public ArrayList<Integer> getOcenka(){
        return ocenka;
    }

    public boolean getMetka(){
        return metka;
    }

    public int getID(){
        return ID;
    }

    public void setMetka(boolean metka){
        this.metka = metka;
    }

    @Override
    public boolean equals(Object o){
        Info i = (Info) o;
        return super.equals(o)&&(i.getID()==ID);
    }

}

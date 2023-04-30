package com.kurs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class DBWorker implements Serializable{
    
    private class Data implements Serializable
    {
        LinkedList<Info> entries = new LinkedList<Info>();
        int lastID = 0;
    }

    File file;
    Data data = new Data();
    

    DBWorker(File file){
        this.file = file;
    }

    public void read() throws IOException, ClassNotFoundException{
        if (!file.exists()) throw new IOException("Файл не существует");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        data = (Data) ois.readObject();
        ois.close();

    }
    

    public void write() throws IOException{
        if (!file.exists()) throw new IOException("Файл не существует");
        FileOutputStream fos = new FileOutputStream(file, false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data);
        oos.flush();
        oos.close();
    }

    public void addEntry(String name, ArrayList<Integer> ocenka){
        data.entries.add(new Info(data.lastID++,name,ocenka));
    }


    public Info getEntryByID(int ID){
        if (ID>data.lastID){ throw new IndexOutOfBoundsException("Worker ID out of bounds");}
        for (Info entry: data.entries){
            if (entry.getID()==ID)
                return entry;
        }
        throw new IndexOutOfBoundsException("Entry with the given ID doesn't exist");
    }

    public LinkedList<Info> getMarkedEntries(boolean mark){
        LinkedList<Info> markedList = new LinkedList<Info>();
        for (Info entry: data.entries){
            if (entry.getMetka()==mark)
                markedList.add(entry);
        }
        return markedList;
    }

    public void removeMarkedEntries(boolean mark){
        for (int i =0;i<data.entries.size();i++){
            if (data.entries.get(i).getMetka()==mark)
                data.entries.remove(data.entries.get(i));
        }
    }

    public LinkedList<Info> getList(){
        return data.entries;
    }
}

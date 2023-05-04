package com.kurs;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.util.Arrays.asList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class DBWorkerTest {
    private DBWorker dbw;
    private File file;
    private ArrayList<Integer> marks;
    @Rule
    public TemporaryFolder folder= new TemporaryFolder();
    

    @Before 
    public void setUp() throws Exception{
        file = folder.newFile("test.bin");
        dbw = new DBWorker(file);
        marks = new ArrayList<Integer>(asList(2,3,4));
        dbw.addEntry("abcd", marks);
    }
    

    @Test 
    public void testAddEntry(){
        dbw.addEntry("abcd", marks);
        Info entry = dbw.getEntryByID(0);
        assertEquals(entry.getID(),0);
        assertEquals(entry.getName(),"abcd");
        assertEquals(entry.getMetka(),false);
        assertTrue(entry.getOcenka().equals(marks));
        dbw.addEntry("abcd", marks);
        assertEquals(dbw.getEntryByID(1).getID(),1);
    }
    @Test
    public void testIO() throws IOException, ClassNotFoundException{
        LinkedList<Info> list = dbw.getList();
        dbw.write();
        dbw = new DBWorker(file);
        dbw.read();
        assertArrayEquals(list.toArray(),dbw.getList().toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDelete(){
        dbw.addEntry("test", marks);
        dbw.getList().get(0).setMetka(true);
        dbw.removeMarkedEntries(true);
        dbw.getEntryByID(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetEntryByID(){
        dbw.getEntryByID(5);
    }
    
}

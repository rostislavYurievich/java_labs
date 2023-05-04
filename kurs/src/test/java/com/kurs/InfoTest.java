package com.kurs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static java.util.Arrays.asList;


public class InfoTest {
    private Info info;
    private ArrayList<Integer> marks;
    @Before
    public void setUp() throws Exception{
        marks = new ArrayList<Integer>(asList(2,3,4));
        info = new Info(0,"abcd", marks);
    }

    @Test
    public void testMean(){
        assertEquals(info.meanOcenka(), 3.0, 0);
    }

    @Test
    public void testInvertMetka(){
        boolean temp = info.getMetka();
        info.invertMetka();
        assertEquals(temp, !info.getMetka());
    }

    @Test
    public void testEquals() {
        Info info2 = new Info(1,"abc",marks);
        assertTrue(!info.equals(info2));
        info2 = new Info(0,"abc",marks);
        assertTrue(info.equals(info2));
    }
}

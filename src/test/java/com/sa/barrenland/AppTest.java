package com.sa.barrenland;

import com.sa.barrenland.App;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for Barren Land Analysis.
 * @author shubhaviarya
 */
public class AppTest 
{
    App bla = new App();

    public AppTest(){
    }

    @BeforeClass
    public static void setUpClass(){
    }

    @AfterClass
    public static void tearDownClass(){
    }

    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
    }

    @Test
    public void testMain(){
    }

    @Test
    public void testFindFertile(){
        String[] strSTDIN ={"0 292 399 307"};
        String STDOUT = "116800 116800 ";

        assertEquals(STDOUT, bla.findFertile(strSTDIN));
    }

    @Test
    public void testFindFertile2(){
        String[] strSTDIN = {"48 192 351 207","48 392 351 407", "120 52 135 547", "260 52 275 547"};
        String STDOUT = "22816 192608 ";

        assertEquals(STDOUT, bla.findFertile(strSTDIN));
    }

    @Test
    public void testFindFertile3(){
        String[] strSTDIN = {"0 0 399 599"};
        String STDOUT = "No fertile land available";

        assertEquals(STDOUT, bla.findFertile(strSTDIN));
    }

    @Test
    public void testFindFertile4(){
        String[] strSTDIN = {"0 0 1 1"};
        String STDOUT = "239996 ";

        assertEquals(STDOUT, bla.findFertile(strSTDIN));
    }

}

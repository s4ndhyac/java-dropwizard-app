package com.test.dropwizard.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloWorldTest {

    private HelloWorld helloWorld;

    @Before
    public void setUp()
    {
        helloWorld = new HelloWorld();
    }

    @Test
    public void test_Constructor()
    {
        final long id = 10;
        final String name = "sandhya";

        final HelloWorld helloWorld = new HelloWorld("sandhya");

        assertEquals(name, helloWorld.getName());
    }



}

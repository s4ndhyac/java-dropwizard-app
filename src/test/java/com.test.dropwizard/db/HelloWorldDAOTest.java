package com.test.dropwizard.db;

import com.test.dropwizard.core.HelloWorld;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

public class HelloWorldDAOTest {

    private  HelloWorldDAO helloWorldDAO = mock(HelloWorldDAO.class);

    private final HelloWorld hello1 = new HelloWorld("sandhya1");
    private final HelloWorld hello2 = new HelloWorld("sandhya2");
    private final List<HelloWorld> hellos = new ArrayList<>();

    @Before
    public void setup()
    {
        hellos.add(hello1);
        hellos.add(hello2);
        when(helloWorldDAO.findAll()).thenReturn(hellos);
        when(helloWorldDAO.findById((long)1)).thenReturn(hello1);
        when(helloWorldDAO.findById((long)2)).thenReturn(hello2);
    }

    @After
    public void tearDown()
    {
        reset(helloWorldDAO);
    }


}

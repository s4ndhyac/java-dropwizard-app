package com.test.dropwizard.db;

import com.test.dropwizard.core.HelloWorld;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class HelloWorldDAO extends AbstractDAO<HelloWorld> {

    public HelloWorldDAO(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }

    public HelloWorld findById(Long id)
    {
        return get(id);
    }

    public List<HelloWorld> findAll()
    {
        return list(criteria());
    }

    public HelloWorld create(HelloWorld helloWorld)
    {
        return persist(helloWorld);
    }

    public void delete(Long id)
    {
        HelloWorld helloWorld = findById(id);
        currentSession().delete(helloWorld);
    }


}

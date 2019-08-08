package com.test.dropwizard;

import com.test.dropwizard.core.HelloWorld;
import com.test.dropwizard.db.HelloWorldDAO;

import com.test.dropwizard.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class DropwizardTestAppApplication extends Application<DropwizardTestAppConfiguration> {

	public static final String APPLICATION_NAME = "DropwizardTestApp";

	// Hibernate
	private final HibernateBundle<DropwizardTestAppConfiguration> hibernate = new HibernateBundle<DropwizardTestAppConfiguration>(HelloWorld.class) {
		@Override
		public DataSourceFactory getDataSourceFactory(DropwizardTestAppConfiguration configuration) {
			return configuration.getDatabase();
		}
	};
	
	private final MigrationsBundle<DropwizardTestAppConfiguration> migrations = new MigrationsBundle<DropwizardTestAppConfiguration>() {
		@Override
		public DataSourceFactory getDataSourceFactory(DropwizardTestAppConfiguration configuration) {
			return configuration.getDatabase();
		}
	};

	public static void main(final String[] args) throws Exception {
		new DropwizardTestAppApplication().run(args);
	}

	@Override
	public String getName() {
		return APPLICATION_NAME;
	}

	@Override
	public void initialize(final Bootstrap<DropwizardTestAppConfiguration> bootstrap) {
		bootstrap.addBundle(hibernate);
		bootstrap.addBundle(new ViewBundle<DropwizardTestAppConfiguration>());
		bootstrap.addBundle(migrations);
		bootstrap.addBundle(new AssetsBundle("/assets/"));
	}

	@Override
	public void run(final DropwizardTestAppConfiguration configuration, final Environment environment) {
		final HelloWorldDAO helloWorldDAO = new HelloWorldDAO(hibernate.getSessionFactory());
		environment.jersey().register(new HelloWorldResource(helloWorldDAO));
	}

}

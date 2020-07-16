package org.example.dropwizard;

import org.example.dropwizard.base.AuthenticatorFeature;
import org.example.dropwizard.base.JustInTimeServiceResolver;
import org.example.dropwizard.database.DemoUserDAO;
import org.example.dropwizard.resources.AuthDemoResource;
import org.glassfish.hk2.api.JustInTimeInjectionResolver;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.StatementBuilderFactory;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class AuthDemoApplication extends Application<AuthDemoConfiguration> {

	public static void main(final String[] args) throws Exception {
		new AuthDemoApplication().run(args);
	}

	@Override
	public String getName() {
		return "AuthDemo";
	}

	@Override
	public void initialize(final Bootstrap<AuthDemoConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle());
		bootstrap.addBundle(new ViewBundle<AuthDemoConfiguration>());
	}

	@Override
	public void run(final AuthDemoConfiguration configuration, final Environment environment) {

		final JdbiFactory factory = new JdbiFactory();
		final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "database");

		DemoUserDAO demoUserDAO = jdbi.onDemand(DemoUserDAO.class);
		
		environment.jersey().register(new AbstractBinder() {
			@Override
			protected void configure() {
				// have to bind DAO explicitly
				bind(demoUserDAO).to(DemoUserDAO.class);
				
				bind(JustInTimeServiceResolver.class).to(JustInTimeInjectionResolver.class);
			}
		});


		environment.jersey().register(new AuthenticatorFeature());
//		environment.jersey().register(new ResponseServerFilter());
		
		environment.jersey().register(AuthDemoResource.class);

	}

}

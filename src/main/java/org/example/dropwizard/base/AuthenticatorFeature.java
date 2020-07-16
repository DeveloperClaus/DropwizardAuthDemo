package org.example.dropwizard.base;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.jersey.InjectionManagerProvider;
import org.glassfish.jersey.internal.inject.InjectionManager;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;

public class AuthenticatorFeature implements Feature {

    @Override
    public boolean configure(FeatureContext ctx) {
        InjectionManager locator = InjectionManagerProvider.getInjectionManager(ctx);
        
        AuthDemoAuthenticator authenticator = new AuthDemoAuthenticator();
        locator.inject(authenticator);
        ctx.register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<StructDemoUserPrincipal>()
                        .setAuthenticator(authenticator)
                        .setRealm("DemoBasicAuth")
                        .buildAuthFilter()));
        ctx.register(new AuthValueFactoryProvider.Binder<>(StructDemoUserPrincipal.class));
        return true;
    }
}

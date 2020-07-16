package org.example.dropwizard.base;

import java.util.Optional;

import javax.inject.Inject;

import org.example.dropwizard.database.DemoUserRepository;
import org.example.dropwizard.database.TupleDemoUser;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;


public class AuthDemoAuthenticator implements Authenticator<BasicCredentials, StructDemoUserPrincipal> {

	@Inject
    private DemoUserRepository demoUserRepository;
	
    @Override
    public Optional<StructDemoUserPrincipal> authenticate(BasicCredentials credentials) throws AuthenticationException {
    	
    	TupleDemoUser member = demoUserRepository.findNameById(credentials.getUsername());
    	
        if (member != null) {
            return Optional.of(new StructDemoUserPrincipal(member));
        }
        return Optional.empty();
    }
}
package org.example.dropwizard.base;

import java.security.Principal;

import org.example.dropwizard.database.TupleDemoUser;

public class StructDemoUserPrincipal implements Principal {

	private final TupleDemoUser demoUser;
	
	public StructDemoUserPrincipal(TupleDemoUser pDemoUser) {

		if(pDemoUser == null) {
			throw new IllegalArgumentException("no null values allowed for user as principal");
		}
		demoUser = pDemoUser;
	}
	
	@Override
	public String getName() {
		return demoUser.getUsername();
	}
	
	public TupleDemoUser getTuple() {
		return demoUser;
	}
}

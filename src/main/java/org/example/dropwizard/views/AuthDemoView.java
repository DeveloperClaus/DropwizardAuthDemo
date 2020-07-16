package org.example.dropwizard.views;

import java.util.List;
import java.util.Optional;

import org.example.dropwizard.base.StructDemoUserPrincipal;
import org.example.dropwizard.database.TupleDemoUser;

import io.dropwizard.auth.Auth;
import io.dropwizard.views.View;

public class AuthDemoView extends View {

	private final List<TupleDemoUser> allUsers;
	private final Optional<StructDemoUserPrincipal> authorizedUser;
	private final Optional<StructDemoUserPrincipal> persistedAuthorizedUser;
	private final Optional<String> message;
	
	public AuthDemoView(List<TupleDemoUser> pAllUsers, Optional<StructDemoUserPrincipal> pAuthorizedUser, Optional<StructDemoUserPrincipal> pPersistedAuthorizedUser, Optional<String> pMessage) {
		super("authdemo.ftl");
		allUsers = pAllUsers;
		authorizedUser = pAuthorizedUser;
		persistedAuthorizedUser = pPersistedAuthorizedUser;
		message = pMessage;
	}

	public List<TupleDemoUser> getAllUsers() {
		
		return allUsers;
	}
	
	public boolean isAuthorized() {
		return authorizedUser.isPresent();
	}
	
	public String getAuthorizedUserName() {
		if(isAuthorized()) {
			return authorizedUser.get().getTuple().getUsername();
		}
		
		return "";
	}
	
	public boolean isPersistedAuthorized(){
		return persistedAuthorizedUser.isPresent();
	}
	
	public boolean isWithMessage() {
		return message.isPresent();
	}
	
	public String getMessage() {
		return message.orElse(null);
	}
}

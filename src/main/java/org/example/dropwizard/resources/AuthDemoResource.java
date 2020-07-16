package org.example.dropwizard.resources;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.example.dropwizard.base.StructDemoUserPrincipal;
import org.example.dropwizard.database.DemoUserRepository;
import org.example.dropwizard.database.TupleDemoUser;
import org.example.dropwizard.views.AuthDemoView;

import com.codahale.metrics.annotation.Timed;

import io.dropwizard.auth.Auth;


@Path("/")
@Produces("text/html; charset=utf-8")
public class AuthDemoResource {
    
	@Inject
    private DemoUserRepository demoUserRepository;

	@GET
	@Timed
    public AuthDemoView initialPage(@Auth Optional<StructDemoUserPrincipal> pAuthorizedUser) {

		return createView(pAuthorizedUser.orElse(null), pAuthorizedUser, null);
	}
	
    @POST
	@Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Timed
    public AuthDemoView login(@FormParam("userName") String pUserId, @Auth Optional<StructDemoUserPrincipal> pAuthorizedUser /*, @Context HttpServletRequest request, @Context ContainerRequestContext requestContext*/) {
	
    	TupleDemoUser userFromDB = demoUserRepository.findNameById(pUserId);

    	// TODO: keep the user, e.g. in the requestContext
    	
    	if(userFromDB != null) {
    		return createView(new StructDemoUserPrincipal(userFromDB), pAuthorizedUser, null);
    	} else {
    		return createView(null, pAuthorizedUser, "Sorry, user '"+pUserId+"' unknown");
    	}
	}

	@GET
	@Path("/restrictedlogin")
	@Timed
    public AuthDemoView restrictedLogin(@Auth StructDemoUserPrincipal pAuthorizedUser) {

		return createView(pAuthorizedUser, Optional.of(pAuthorizedUser), null);
	}
	
	private AuthDemoView createView(StructDemoUserPrincipal pAuthorizedUser, Optional<StructDemoUserPrincipal> pPersistedAuthorizedUser, String pMessage){
		
		List<TupleDemoUser> allUsers = demoUserRepository.list();
		Optional<String> message = StringUtils.isAllBlank(pMessage) ? Optional.empty() : Optional.of(pMessage);
		
		return new AuthDemoView(allUsers, Optional.ofNullable(pAuthorizedUser), pPersistedAuthorizedUser, message);
	}
	
    @GET
	@Path("/logout")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Timed
    public AuthDemoView logout(@Auth Optional<StructDemoUserPrincipal> pAuthorizedUser /*, @Context HttpServletRequest request, @Context ContainerRequestContext requestContext*/) {
	
    	// TODO: remove the user from the requestContect
    	
    	return createView(null, pAuthorizedUser, null);
	}


}

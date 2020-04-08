package sec;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JSONRequired;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resources.AuthenticationService;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component(service = LoginResource.class)
@JaxrsResource
@Produces(MediaType.APPLICATION_JSON)
@JSONRequired
@Path("login")
public class LoginResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginResource.class);

    private static final String tokenHeader = "be-token";

    AuthenticationService authService = new AuthenticationService();
    SecurityManager securityManager = new SecurityManager();

    @Context
    UriInfo uri;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(final UsernamePasswordToken credentials) {
        if(credentials == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        assert credentials != null && credentials.getUsername().isEmpty()
                && credentials.getPassword().length > 0;
        LOGGER.info("Performing authentication for username [{}]",
                credentials.getUsername());
        try {
            final String token = authService.login(credentials);
            return Response.ok().header(tokenHeader, token).build();
        } catch (UnauthenticatedException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}

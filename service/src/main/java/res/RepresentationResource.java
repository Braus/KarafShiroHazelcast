package res;

import model.Behemoth;
import model.BehemothService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JSONRequired;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resources.AuthenticationService;
import org.apache.shiro.mgt.SecurityManager;
import resources.BehemothServiceImpl;


import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component(service = RepresentationResource.class)
@JaxrsResource
@Produces(MediaType.APPLICATION_JSON)
@JSONRequired
@Path("apis/beh")
public class RepresentationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepresentationResource.class);


    private static final String tokenHeader = "be-token";

    SecurityManager securityManager = SecurityFactory.getSecurityManager();

    SecurityFactory securityFactory = new SecurityFactory();
    AuthenticationService authService = new AuthenticationService();

//    @Reference
    BehemothService behemothService = new BehemothServiceImpl();

    @Context
    UriInfo uri;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getHero(@PathParam("id") Integer id) {
        Behemoth hero = behemothService.getById(id);
        return hero == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.ok(hero).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getHeroes(@HeaderParam("be-token") final String token) {
//        Subject currentUser = SecurityUtils.getSubject();
//        if(!currentUser.isAuthenticated()) {
//            return Response.status(Response.Status.UNAUTHORIZED).build();
//        }
        boolean authenticated = authService.isAuthenticated(token);
        return authenticated ?  Response.ok(behemothService.getAll()).build() : Response.status(Response.Status.UNAUTHORIZED).build();
//        return Response.ok(behemothService.getAll()).build();
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response login(final UsernamePasswordToken credentials) {
//        if(credentials == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }
//        assert credentials != null && credentials.getUsername().isEmpty()
//                && credentials.getPassword().length > 0;
//        LOGGER.info("Performing authentication for username [{}]",
//                credentials.getUsername());
//        try {
//            final String token = authService.login(credentials);
//            return Response.ok().header(tokenHeader, token).build();
//        } catch (UnauthenticatedException e) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }
//    }

}

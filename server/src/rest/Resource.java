package rest;

import app.LoginService;
import com.google.gson.Gson;
import util.LoginMessage;
import util.LoginValidationMessage;
import util.SignInMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by mihfilip on 14/10/2015.
 */

@Path("/user")
public class Resource {

    public static Gson gson = new Gson();


    /*
    login example
     */

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Object login(String resource) {
        LoginMessage loginMessage = gson.fromJson(resource, LoginMessage.class);

        LoginService.handleLoginRequest(loginMessage);

        return null;
    }

    @POST
    @Path("/loginValidation")
    @Produces(MediaType.APPLICATION_JSON)
    public Object loginValidation(String resource) {
        LoginValidationMessage loginValidationMessage = gson.fromJson(resource, LoginValidationMessage.class);
        return null;
    }

    @POST
    @Path("/signin")
    @Produces(MediaType.APPLICATION_JSON)
    public Object signIn(String resource) {
        SignInMessage signInMessage = gson.fromJson(resource, SignInMessage.class);
        LoginService.handleSignInRequest(signInMessage);

        return null;
    }



}

package rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import util.LoginMessage;
import util.LoginValidation;
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
        return null;
    }

    @POST
    @Path("/loginValidation")
    @Produces(MediaType.APPLICATION_JSON)
    public Object loginValidation(String resource) {
        LoginValidation loginValidationMessage = gson.fromJson(resource, LoginValidation.class);
        return null;
    }

    @POST
    @Path("/signin")
    @Produces(MediaType.APPLICATION_JSON)
    public Object signIn(String resource) {
        SignInMessage signInMessage = gson.fromJson(resource, SignInMessage.class);

        return null;
    }



}

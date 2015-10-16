package rest;

import app.GameRequestService;
import com.google.gson.Gson;
import util.GameRequestMessage;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by mihfilip on 16/10/2015.
 */
@Path("/game")
public class GameResource {

    public static Gson gson = new Gson();

    @POST
    @Path("/begin")
    @Produces(MediaType.APPLICATION_JSON)
    public Object begin(String resource) {
        GameRequestMessage gameRequestMessage = gson.fromJson(resource, GameRequestMessage.class);
        return gson.toJson(GameRequestService.handleGameRequest(gameRequestMessage));
    }

    @GET
    @Path("/image/{imageName}")
    @Produces("image/jpg")
    public Response getImage(@PathParam("imageName") String imageName) {
        try {
            BufferedImage image = ImageIO.read(new FileInputStream("resources/" + imageName));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            byte[] imageData = baos.toByteArray();
            return Response.ok(imageData).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

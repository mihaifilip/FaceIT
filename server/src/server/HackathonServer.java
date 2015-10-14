package server;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mihfilip on 14/10/2015.
 */

public class HackathonServer {

    private Server restServer = null;
    private Server server = null;

    private List<HackathonWebSocket> sockets = new ArrayList<HackathonWebSocket>();

    public void initializeRestServer() {
        restServer = new Server(9081);
        // register all servlets
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setResourceBase("./webapp");
        context.setServer(restServer);

        // initialize resource handler to serve static resources
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(false);
        resourceHandler.setResourceBase("./webapp");

        // jersey servlet
        ServletHolder sh = context.addServlet(ServletContainer.class, "/*");
        sh.setInitParameter("com.sun.jersey.config.property.packages", "rest");
        sh.setInitOrder(0);

        // add all handlers to server
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{context, resourceHandler, new DefaultHandler()});
        restServer.setHandler(handlers);

        try {
            restServer.start();
            restServer.join();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void initializeServer() {
        server = new Server(9082);
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath("/");
        servletContextHandler.setResourceBase("./webapp");
        servletContextHandler.setServer(server);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(false);
        resourceHandler.setBaseResource(Resource.newClassPathResource("./webapp"));

        DefaultHandler defaultHandler = new DefaultHandler();

        WebSocketHandler wsHandler = new WebSocketHandler() {
            public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
                return new HackathonWebSocket(HackathonServer.this, request.getRemoteAddr(), request.getRemotePort());
            }
        };

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {servletContextHandler, resourceHandler, wsHandler, defaultHandler});
        server.setHandler(handlers);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleIncomingClientConnection(HackathonWebSocket socket) {
        System.out.println("Client " + socket.ipAddress +" attempting connection on port " + socket.port);
        sockets.add(socket);
    }

    public List<HackathonWebSocket> getClientSockets() {
        return sockets;
    }

    public void removeSocket(HackathonWebSocket socket) {
        sockets.remove(socket);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object retrieveReportList() {
        List<String> passwords = new ArrayList<String>();
        return passwords;
    }

}

package server;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.WebSocket;

/**
 * Created by mihfilip on 14/10/2015.
 */
public class HackathonWebSocket implements WebSocket.OnTextMessage {

    protected WebSocket.Connection connection;
    protected final HackathonServer server;
    protected String ipAddress;
    protected int port;
    protected final Gson gson;

    public HackathonWebSocket(HackathonServer server, String ipAddress, int port) {
        super();
        this.server = server;
        this.ipAddress = ipAddress;
        this.port = port;
        gson = new Gson();

    }

    @Override
    public void onOpen(WebSocket.Connection connection) {
        this.connection = connection;
        connection.setMaxIdleTime(Integer.MAX_VALUE);
        server.handleIncomingClientConnection(this);

        System.out.println("new connection");
    }

    @Override
    public void onClose(int code, String message) {
        server.removeSocket(this);
        connection.close();
        System.out.println("Closed");
    }

    @Override
    public void onMessage(final String data) {
        System.out.println("Message " + data);
    }

    private void handleSignInMessage() {
    }

    private void handleLoginMessage() {
    }

}

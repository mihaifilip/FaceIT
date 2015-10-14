import server.HackathonServer;

/**
 * Created by mihfilip on 14/10/2015.
 */
public class Initializer {

    public static HackathonServer hs = new HackathonServer();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                hs.initializeRestServer();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                hs.initializeServer();
            }
        });

        t1.start();

        t2.start();


    }
}

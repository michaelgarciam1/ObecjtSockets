import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/* Conector para actuar como servidor*/

public class ServerConnector implements Runnable{
    private int PORT;
    private ServerSocket SOCKET;
    private Socket clsock;

    public ServerConnector(int port) {
        this.PORT = port;
        try {
            this.SOCKET = new ServerSocket(PORT);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Socket getClsock() {
        return clsock;
    }

    @Override
    public void run() {
        try {
            System.out.println("Conectando como servidor...");
            this.clsock = SOCKET.accept();
        } catch (Exception e) {
            System.out.println("ServerConnector error: " + e);
        }
    }

    public void killSocket(){
        try{
            SOCKET.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isSocketClosed(){
        return SOCKET.isClosed();
    }
}

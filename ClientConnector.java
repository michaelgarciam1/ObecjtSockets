import java.net.Socket;

/*Conector para actuar como cliente*/

public class ClientConnector implements Runnable{
    private Socket SOCKET;
    private int PORT;
    private String ADDRESS;

    private boolean conexionEstablecida = false;

    private volatile boolean intentarReconectar = true;
    public ClientConnector(int port, String address) {
        this.PORT = port;
        this.ADDRESS = address;
    }
    @Override
    public void run() {
        try {
            System.out.println("Conectando como cliente...");
            while (this.SOCKET == null) {
                this.SOCKET = new Socket(this.ADDRESS, this.PORT);
            }
            conexionEstablecida = true;
        } catch (Exception e) {
            System.out.println("Esperando conexión...");
            try {
                Thread.sleep(5000);
                if (intentarReconectar) {
                    run();
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    public Socket getSOCKET() {
        return SOCKET;
    }

    public boolean isConexionEstablecida() {
        return conexionEstablecida;
    }

    public void setIntentarReconectar(boolean intentarReconectar) {
        this.intentarReconectar = intentarReconectar;
    }
}

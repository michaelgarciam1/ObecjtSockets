
public class HealthCareConnection implements Runnable {

    private Connection connection;

    private long timeOut;

    public boolean estaEncendido;

    public HealthCareConnection(Connection connection, long timeOut) {
        this.connection = connection;
        this.timeOut = timeOut;
        estaEncendido = true;
    }

    public void pararEjecucion() {
        System.err.println("Deteniendo Health Care Connection...");
        estaEncendido = false;
    }

    public void run() {
        while(estaEncendido) {
            System.out.println("Health Care: Checking...");
            // Verificar si ha pasado más tiempo que timeOut desde que se recibió el último mensaje
            long currentTime = System.currentTimeMillis();
            long timeLastMessage = connection.getTimeReceivedMessage();
            long diferencia = currentTime - timeLastMessage;
            if(diferencia > timeOut) {
                System.out.println("Health Care: last recieved message was " + diferencia + " ago");
                if(connection.ping()){
                    System.out.println("Health Care: Ping sent successfully");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("HealthCareConnection: Cerrando conexion.");
                    connection.killSocket();
                    break;
                }
            }
        }
        System.err.println(this.getClass().getSimpleName() + " detenido.");
    }


}


public class Main {
    Connection connection;
    HealthCareConnection healthCareConnection;
    Vista vista;

    public Main() {
        this.vista = new Vista(this);
        this.connection = new Connection("localhost", this);
        this.healthCareConnection = new HealthCareConnection(connection, 10000);
        new Thread(connection).start();
        new Thread(healthCareConnection).start();
        connection.setHCC(healthCareConnection);
        System.out.println("Main: " + this.connection.toString());
    }

    public static void main(String[] args) {
        new Main();
    }

    public void enviarMensaje(Message m) {
        System.out.println(connection.toString());
        connection.enviarMensanje(m);
    }

    public void recibirMensaje(Message m) {
        vista.recibirMensaje(m);
    }

}
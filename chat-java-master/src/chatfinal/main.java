package chatfinal;

import java.io.IOException;

public class main {
    public static void main(String[] argumentos) throws IOException {
        ventana vent = new ventana();

        Cliente cliente = new Cliente(vent);

        enciende server = new enciende(vent);
        vent.abreventana(cliente);
        server.start();



    }
}

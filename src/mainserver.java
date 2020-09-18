import java.io.IOException;
import serversocket.Servidor;

//Clase principal que hará uso del servidor
public class mainserver {
    public static void main(String[] args) throws IOException
    {
        Servidor serv = new Servidor(); //Se crea el servidor

        System.out.println("Iniciando servidor\n");
        serv.startServer(); //Se inicia el servidor
    }
}

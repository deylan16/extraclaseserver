import java.io.IOException;
import serversocket.Cliente;
import serversocket.Servidor;

public class main {
    public static void main(String[] args) throws IOException
    {
        hilos ch = new hilos("servidor",2);
        ch.start();
        hilos ch2 = new hilos("cliente",6);
        ch2.start();
//        hilos prueba = new hilos("cliente");
//        prueba.run();
//        hilos prueba2 = new hilos("cliente");
//        prueba2.run();
//        Servidor serv = new Servidor(); //Se crea el servidor
//        Cliente cli = new Cliente(); //Se crea el cliente
//
//        System.out.println("Iniciando cliente\n");
//        System.out.println("Iniciando servidor\n");
//        serv.startServer(); //Se inicia el servidor
//
//
//        cli.startClient(); //Se inicia el cliente


    }
}

import serversocket.Cliente;
import serversocket.Servidor;

import java.io.IOException;

import static java.lang.Thread.sleep;
import javax.swing.*;

public class hilos extends Thread {

        String tipo;

        public hilos(String tipo,int seg)
        {
            this.tipo = tipo;
        }



        public void run()
        {
            if(this.tipo == "cliente") {
                Cliente cli = null; //Se crea el cliente
                try {
                    cli = new Cliente();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Iniciando cliente\n");
                cli.startClient(); //Se inicia el cliente
                ventana chat = new ventana();
                chat.abreventana();
            }
            else{
                Servidor serv = null; //Se crea el servidor;
                try {
                    serv = new Servidor();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Iniciando servidor\n");
                serv.startServer(); //Se inicia el servidor
            }
        }
    }


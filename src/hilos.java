import serversocket.Cliente;
import serversocket.Servidor;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class hilos extends Thread {

        String tipo;
        int seg;

        public hilos(String tipo,int seg)
        {
            this.tipo = tipo;
            this.seg = seg;
        }

//        public static void main(String[] args)
//        {
//            hilos ch = new hilos(tipo);
//            ch.start();
//        }

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

//    private String tipo;
//
//    public hilos(String tipo) {
//        this.tipo = tipo;
//    }
//    public static void main(String[] args)
//    {
//        for (int i = 0; i < 4; i++)
//        {
//            hilos ch = new hilos("cliente");
//            ch.start();
//        }
//    }
//
//    public void run() {
//        if(this.tipo == "cliente") {
//            System.out.println("hola cliente");
//            try {
//                sleep( 3*1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("hola cliente");
//
//
//        }
//        else{
//            System.out.println("hola servidor");
//        }
//    }
//}
//System.out.println("hola cliente");
//        try {
//        sleep( this.seg*1000);
//        } catch (InterruptedException e) {
//        e.printStackTrace();
//        }
//        System.out.println("hola cliente");
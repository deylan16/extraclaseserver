package chatfinal;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class HiloParaTratarElCliente extends Thread {
    Socket cs;
    String puerto;
    Cliente client;
    private DataInputStream bufferDeEntrada = null;
    private DataOutputStream bufferDeSalida = null;
    final String COMANDO_TERMINACION = "salir()";
    public HiloParaTratarElCliente (Socket cs,int port, Cliente client) {

        this.cs = cs;
        this.puerto = String.valueOf(port);
        this.client = client;
    }

    public void flujos() throws IOException {
        bufferDeEntrada = new DataInputStream(cs.getInputStream());
        bufferDeSalida = new DataOutputStream(cs.getOutputStream());
        bufferDeSalida.flush();
    }

    public void recibirDatos() throws IOException {
        String st = "";
        try {
            do {
                st = (String) bufferDeEntrada.readUTF();
                mostrarTexto("\n[Cliente] => " + st);
                System.out.print("\n[Usted] => ");
                String textonuevo,textoviejo;
                textoviejo = client.tchat.getText();
                textonuevo ="\n" + "Servidor:" + st;
                client.tchat.setText(textoviejo +  textonuevo );
            } while (!st.equals(COMANDO_TERMINACION));
        } catch (IOException e) {
            cerrarConexion();
        }
    }


    public void enviar(String s) {
        try {
            if(s == "[Usted] => adios"){
                cerrarConexion();
            }
            else {
                bufferDeSalida.writeUTF(s);
                bufferDeSalida.flush();
            }
        } catch (IOException e) {
            mostrarTexto("Error en enviar(): " + e.getMessage());
        }
    }

    public static void mostrarTexto(String s) {
        System.out.print(s);
    }

    public void cerrarConexion() throws IOException {
        bufferDeEntrada.close();
        bufferDeSalida.close();
        cs.close();

    }
    public void run() {
        try {
                try {
                    flujos();
                    recibirDatos();
                } finally {
                    cerrarConexion();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}

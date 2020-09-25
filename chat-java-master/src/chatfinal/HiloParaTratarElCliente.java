package chatfinal;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HiloParaTratarElCliente extends Thread {
    Socket cs;
    String puerto;
    ventana vent;
    private DataInputStream bufferDeEntrada = null;
    private DataOutputStream bufferDeSalida = null;
    final String COMANDO_TERMINACION = "salir()";

    public HiloParaTratarElCliente (Socket cs,int port, ventana vent) {

        this.cs = cs;
        this.puerto = String.valueOf(port);
        this.vent = vent;
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
                String textonuevo,textoviejo;
                textoviejo = vent.tchat.getText();
                String[] Divido = procesamensaje(st);
                textonuevo ="\n" + Divido[0]+ ":" + Divido[1];
                vent.tchat.setText(textoviejo +  textonuevo );
                vent.agregamensaje(Divido[0], Divido[0]+ ":"+Divido[1]);

            } while (!st.equals(COMANDO_TERMINACION));
        } catch (IOException e) {
            cerrarConexion();
        }
    }
    public String[] procesamensaje(String men){
        String menArray[] = men.split("\\@");
        return menArray;
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

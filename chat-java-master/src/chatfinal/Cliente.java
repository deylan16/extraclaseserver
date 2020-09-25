package chatfinal;

import java.net.*;
import java.io.*;

public class Cliente {
    ventana vent;
    private Socket socket;
    private DataInputStream bufferDeEntrada = null;
    private DataOutputStream bufferDeSalida = null;
    final String COMANDO_TERMINACION = "salir()";

    public Cliente(ventana vent) {
        this.vent = vent;
    }


    public void levantarConexion(String ip, int puertot) {
            try {
                socket = new Socket(ip, puertot);
                mostrarTexto("Conectado a :" + socket.getInetAddress().getHostName());

            } catch (Exception e) {
                mostrarTexto("Excepción al levantar conexión: " + e.getMessage());
                System.exit(0);
            }
        }

        public static void mostrarTexto(String s) {
            System.out.println(s);
        }

        public void abrirFlujos() {
            try {
                bufferDeEntrada = new DataInputStream(socket.getInputStream());
                bufferDeSalida = new DataOutputStream(socket.getOutputStream());
                bufferDeSalida.flush();
            } catch (IOException e) {
                mostrarTexto("Error en la apertura de flujos");
            }
        }

        public void enviar(String str) throws IOException {
                String textonuevo,textoviejo;
                textoviejo = vent.tchat.getText();
                textonuevo ="\n" + "tu:" + str;
                vent.tchat.setText(textoviejo +  textonuevo );
                Socket s = new Socket("127.0.0.1", vent.puerto);
                bufferDeSalida.writeUTF(vent.frame.getTitle()+"@"+str);
                bufferDeSalida.flush();
                s.close();
                vent.entradamensaje.setText(null);

        }

        public void cerrarConexion() {
            try {
                bufferDeEntrada.close();
                bufferDeSalida.close();
                socket.close();
                mostrarTexto("Conexión terminada");
            } catch (IOException e) {
                mostrarTexto("IOException on cerrarConexion()");
            }finally{
                System.exit(0);
            }
        }

        public void ejecutarConexion(String ip, int puertof) {
            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        levantarConexion(ip, puertof);
                        abrirFlujos();
                        recibirDatos();
                    } finally {
                        cerrarConexion();
                    }
                }
            });
            hilo.start();
        }
        public void recibirDatos() {
            String st = "";
            try {
                do {
                    st = (String) bufferDeEntrada.readUTF();
                    String textonuevo,textoviejo;
                    textoviejo = vent.tchat.getText();
                    textonuevo ="\n" + "Servidor:" + st;
                    vent.tchat.setText(textoviejo +  textonuevo );
                } while (!st.equals(COMANDO_TERMINACION));
            } catch (IOException e) {}
        }



    }


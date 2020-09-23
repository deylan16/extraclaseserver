package chatfinal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;

public class Cliente extends ventana{

    private Socket socket;
    private DataInputStream bufferDeEntrada = null;
    private DataOutputStream bufferDeSalida = null;
    final String COMANDO_TERMINACION = "salir()";

    public Cliente(
            ){super();}

        public void levantarConexion(String ip, int puerto) {
            try {
                socket = new Socket(ip, puerto);
                mostrarTexto("Conectado a :" + socket.getInetAddress().getHostName());
                btenviar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enviar(entradamensaje.getText());
                        entradamensaje.setText(null);


                    }
                });
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

        public void enviar(String s) {
            try {
                bufferDeSalida.writeUTF(s);
                bufferDeSalida.flush();
                String textonuevo,textoviejo;
                textoviejo = tchat.getText();
                textonuevo ="\n" + "Tu:" + s;
                tchat.setText(textoviejo +  textonuevo );
            } catch (IOException e) {
                mostrarTexto("IOException on enviar");
            }
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

        public void ejecutarConexion(String ip, int puerto) {
            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        levantarConexion(ip, puerto);
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
                    textoviejo = tchat.getText();
                    textonuevo ="\n" + "Servidor:" + st;
                    tchat.setText(textoviejo +  textonuevo );
                } while (!st.equals(COMANDO_TERMINACION));
            } catch (IOException e) {}
        }


        public static void main(String[] argumentos) throws IOException {
            Cliente cliente = new Cliente();

            enciende server = new enciende(cliente);
            server.start();
            cliente.abreventana();
            cliente.frame.setTitle("deylan");
            cliente.ejecutarConexion("127.0.0.1", 4001
            );

        }
    }


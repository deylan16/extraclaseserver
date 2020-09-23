/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatfinal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;


public class Servidor {

    private Socket socket;
    private ServerSocket serverSocket;
    private DataInputStream bufferDeEntrada = null;
    private DataOutputStream bufferDeSalida = null;
    final String COMANDO_TERMINACION = "salir()";

    public void levantarConexion(int puerto,Cliente clien) {
        try {
            serverSocket = new ServerSocket(puerto);
            System.out.println( "Esperando conexión entrante en el puerto " + String.valueOf(puerto) + "...");
            while (true) {
                socket = serverSocket.accept();
                Thread hiloParaTratarElCliente = new HiloParaTratarElCliente(socket, puerto,clien);
                hiloParaTratarElCliente.start();
            }
        } catch (Exception e) {
            puerto += 1;
            levantarConexion(puerto,clien);
        }
    }
//    public static void main(String[] args) throws IOException {
//        Servidor s = new Servidor();
//
//
//        s.levantarConexion(4000);
//    }
}

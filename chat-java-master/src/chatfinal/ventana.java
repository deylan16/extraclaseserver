package chatfinal;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ventana {
    int puerto = 0;

    JFrame frame,frame2;
    String nombre;
    JPanel construyePanelRedactaryChat, construyePanelCoversaciones,chat,Redactar,vredactar;
    JButton btenviar,btredactar,btconfirmar;
    JTextField entradamensaje,cpuerto;
    JTextArea tchat;
    JScrollPane conver;
    JLabel etiqueta,epuerto;
    Cliente client;


    public void abreventana(Cliente client){
        this.client = client;
        construyePanelRedactaryChat();
        construyePanelCoversaciones();
        construyeVentana();}

    void construyePanelRedactaryChat(){
        btconfirmar = new JButton("Confirmar");
        construyePanelRedactaryChat = new JPanel ();
        construyePanelRedactaryChat.setLayout(new GridLayout(2,1,8,8));
        //panel para mostrar el chat
        chat = new JPanel ();
        chat.setLayout(new GridLayout(1,1,8,8));
        tchat = new JTextArea();
        tchat.setEnabled(false);
        conver=new JScrollPane(tchat);
        conver.setBounds(10,50,4,3);
        chat.add(conver);
        construyePanelRedactaryChat.add(chat);
        //panel para redactar
        Redactar = new JPanel ();
        Redactar.setLayout(new GridLayout(1,2,8,8));
        entradamensaje = new JTextField(10);
        Redactar.add(entradamensaje);
        btenviar = new JButton("Enviar");
        Redactar.add(btenviar);
        btenviar.addActionListener(e -> {
            try {
                client.enviar(entradamensaje.getText());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });
        construyePanelRedactaryChat.add(Redactar);

    }


    void construyePanelCoversaciones() {
        construyePanelCoversaciones = new JPanel();
        construyePanelCoversaciones.setLayout(new GridLayout(7, 1, 8, 8));
        etiqueta = new JLabel();
        etiqueta.setText("Conversaciones");
        construyePanelCoversaciones.add(etiqueta);
        btredactar = new JButton("redactar");
        construyePanelCoversaciones.add(btredactar);
        btredactar.addActionListener(e -> {
            frame2 = new JFrame("REDACTAR");
            frame2.setLayout(new GridLayout(1,1,8,8));
            vredactar = new JPanel();
            vredactar.setLayout(new GridLayout(2, 2, 8, 8));
            epuerto = new JLabel();
            epuerto.setText("Para(puerto):");
            vredactar.add(epuerto);
            cpuerto = new JTextField(2);
            vredactar.add(cpuerto);

            vredactar.add(btconfirmar);
            frame2.add(vredactar);
            frame2.pack();
            frame2.setVisible(true);

        });
    }

    void construyeVentana(){
        nombre = "";
        frame =new JFrame(nombre);
        frame.setLayout(new GridLayout(1,2,8,8));
        frame.add(construyePanelCoversaciones);
        frame.add(construyePanelRedactaryChat);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);}


}
package chatfinal;

import chatfinal.Cliente;
import chatfinal.Servidor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class ventana {
    JFrame frame;
    JPanel construyePanelRedactaryChat, construyePanelCoversaciones,chat,Redactar;
    JButton btenviar;
    JTextField entradamensaje;
    JTextArea tchat;
    JScrollPane conver;
    JLabel etiqueta;

    public void abreventana(){
        construyePanelRedactaryChat();
        construyePanelCoversaciones();
        construyeVentana();}

    void construyePanelRedactaryChat(){
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
        construyePanelRedactaryChat.add(Redactar);

    }


    void construyePanelCoversaciones() {
        construyePanelCoversaciones = new JPanel();
        construyePanelCoversaciones.setLayout(new GridLayout(7, 1, 8, 8));
        etiqueta = new JLabel();
        etiqueta.setText("Conversaciones");
        construyePanelCoversaciones.add(etiqueta);
    }

    void construyeVentana(){
        frame =new JFrame("Cliente ");
        frame.setLayout(new GridLayout(1,2,8,8));
        frame.add(construyePanelCoversaciones);
        frame.add(construyePanelRedactaryChat);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);}


}
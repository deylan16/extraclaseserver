import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventana {
    JFrame frame;
    JPanel construyePanelRedactaryChat, construyePanelCoversaciones,chat,Redactar;
    JButton bt1,btenviar;
    JTextField entradamensaje;
    JTextArea tchat;
    JScrollPane conver;

    public void abreventana(){
        construyePanelRedactaryChat();
        construyePanelCoversaciones();
        construyeVentana();}

    void construyePanelRedactaryChat(){
        construyePanelRedactaryChat = new JPanel ();
        construyePanelRedactaryChat.setLayout(new GridLayout(2,1,8,8));
        Redactar = new JPanel ();
        chat = new JPanel ();
        chat.setLayout(new GridLayout(1,1,8,8));
        tchat = new JTextArea();
        tchat.setEnabled(false);
        conver=new JScrollPane(tchat);
        conver.setBounds(10,50,400,300);
        chat.add(conver);
        construyePanelRedactaryChat.add(chat);
        Redactar.setLayout(new GridLayout(1,2,8,8));
        entradamensaje = new JTextField(10);
        Redactar.add(entradamensaje);
        btenviar = new JButton("Enviar");
        Redactar.add(btenviar);
        construyePanelRedactaryChat.add(Redactar);
        btenviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textonuevo,textoviejo;
                textoviejo = tchat.getText();
                textonuevo ="\n" +entradamensaje.getText();
                tchat.setText(textoviejo +  textonuevo );
                JLabel etiqueta;

            }
        });
    }

    void construyePanelCoversaciones() {
        construyePanelCoversaciones = new JPanel();
        construyePanelCoversaciones.setLayout(new GridLayout(7, 1, 8, 8));
        bt1 = new JButton("hola");
        construyePanelCoversaciones.add(bt1);
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
package chatfinal;




import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ventana {
    int puerto = 0;
    int puertoactual = 0;

    JFrame frame,frame2;
    String nombre;
    JPanel construyePanelRedactaryChat, construyePanelCoversaciones,chat,Redactar,vredactar,construyePanelCoversaciones1;
    JButton btenviar,btredactar,btconfirmar;
    JTextField entradamensaje,cpuerto;
    JTextArea tchat;
    JScrollPane conver;
    JLabel etiqueta,epuerto;
    Cliente client;
    JList messageList;
    Map<String, List<String>> messageDB = new HashMap<>();

    java.util.List<String> messages = new ArrayList<String>();


    public void abreventana(Cliente client){
        this.client = client;
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
        construyePanelCoversaciones.setLayout(new GridLayout(2, 1, 8, 8));
        construyePanelCoversaciones1 = new JPanel();
        construyePanelCoversaciones1.setLayout(new GridLayout(3, 1, 8, 8));
        etiqueta = new JLabel();
        etiqueta.setText("Conversaciones");
        construyePanelCoversaciones1.add(etiqueta);
        btredactar = new JButton("redactar");
        construyePanelCoversaciones1.add(btredactar);
        construyePanelCoversaciones.add(construyePanelCoversaciones1);
        messageList = new JList();
        messageList.setFont(new Font("Arial", Font.ITALIC, 10));
        messageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        messageList.addListSelectionListener(e -> {
            puertoactual = Integer.parseInt(String.valueOf(messageList.getSelectedValue()));
            tchat.setText(messageDB.get(messageList.getSelectedValue()).get(0));

        });
        construyePanelCoversaciones.add(messageList);
        btredactar.addActionListener(e -> {
            btconfirmar = new JButton("Confirmar");
            btconfirmar.addActionListener(r -> { ;
                puertoactual = Integer.parseInt(cpuerto.getText());
                frame2.setVisible(false);

            });
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
    void agregamensaje(String puert,String mensaje){

        if (messageDB.containsKey(puert)) {
            messageDB.get(puert).get(0);
            messageDB.get(puert).add(0,messageDB.get(puert).get(0) +"\n"+ mensaje);

        } else {
            java.util.List<String> messageForSender = new ArrayList<>();
            messageForSender.add(mensaje);
            messageDB.put(puert, messageForSender);
        }
        messageList.setListData(messageDB.keySet().toArray());

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

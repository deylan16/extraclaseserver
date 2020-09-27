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

    public void enviar(String str) throws IOException {
        String textonuevo,textoviejo;
        textoviejo = vent.tchat.getText();
        textonuevo ="\n" + "Tu:" + str;
        vent.tchat.setText(textoviejo +  textonuevo );
        Socket s = new Socket("127.0.0.1", vent.puertoactual);
        bufferDeSalida = new DataOutputStream(s.getOutputStream());
        bufferDeSalida.writeUTF(vent.frame.getTitle()+"@"+str);
        bufferDeSalida.flush();
        s.close();
        vent.entradamensaje.setText(null);
        vent.agregamensaje(String.valueOf(vent.puertoactual),  "Tu:"+str);


    }
    }


package chatfinal;

public class enciende  extends Thread{
        ventana vent;
    public enciende(ventana vent){
        this.vent = vent;

    }

    public void run() {
        Servidor s = new Servidor();
        s.levantarConexion(4000,vent);

    }
}

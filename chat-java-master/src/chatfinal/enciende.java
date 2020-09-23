package chatfinal;

public class enciende  extends Thread{
        Cliente cli;
    public enciende(Cliente cli){
        this.cli = cli;

    }

    public void run() {
        Servidor s = new Servidor();
        s.levantarConexion(4000,cli);

    }
}

import java.io.IOException;


public class main {
    public static void main(String[] args) throws IOException
    {
        hilos ch = new hilos("servidor",2);
        ch.start();
        hilos ch2 = new hilos("cliente",6);
        ch2.start();



    }
}

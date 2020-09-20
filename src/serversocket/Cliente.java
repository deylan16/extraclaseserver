package serversocket;

import java.io.DataOutputStream;
import java.io.IOException;


public class Cliente extends conexion
{
    public Cliente() throws IOException{super("cliente");} //Se usa el constructor para cliente de Conexion

    public void startClient() //Método para iniciar el cliente
    {
        try
        {
            //Flujo de datos hacia el servidor
            salidaServidor = new DataOutputStream(cs.getOutputStream());


            //Se escribe en el servidor usando su flujo de datos
            salidaServidor.writeUTF("Este es el mensaje número " );

            cs.close();//Fin de la conexión

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

import java.net.*;
import java.io.*;
public class ClientImpl {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 6666);

        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        Thread.sleep(3000);
        dos.writeUTF("hello server");
        dos.flush();
        dos.close();
        socket.close();
        }
}

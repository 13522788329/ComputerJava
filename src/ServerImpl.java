//TCP Server
import java.net.*;
import java.io.*;

public class ServerImpl {
    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(6666);
        boolean threadLock = true;
        while (threadLock) {
            Socket s = socket.accept();
            System.out.println("a client connect");
            DataInputStream dis = new DataInputStream(s.getInputStream());
            System.out.println(dis.readUTF());
            dis.close();
            s.close();
            threadLock = false;
        }
    }
}

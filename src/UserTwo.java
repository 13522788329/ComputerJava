import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UserTwo {
    public static void main(String[] args) throws SocketException {
        DatagramSocket ds = new DatagramSocket(7777);
        Scanner scanner = new Scanner(System.in);

        //receive data
        new Thread() {
            @Override
            public void run() {
                boolean threadLock = true;
                while (threadLock) {
                    String next = scanner.next();
                    try {
                        DatagramPacket dp = new DatagramPacket(next.getBytes(), next.getBytes().length, InetAddress.getByName("127.0.0.1"), 8888);
                        ds.send(dp);
                        threadLock = false;
                    }
                    catch (Exception exception) {
                        exception.fillInStackTrace();
                    }
                }
            }
        }.start();

        //send data
        new Thread() {
            @Override
            public void run() {
                boolean threadLock = true;
                while (threadLock) {
                    try {
                        DatagramPacket dp = new DatagramPacket(new byte[1024], 1024, InetAddress.getByName("127.0.0.1"), 8888);
                        ds.receive(dp);
                        byte[] data = dp.getData();
                        int length = data.length;
                        System.out.println("length is:");
                        System.out.println(length);
                        threadLock = false;
                    }
                    catch (Exception e) {
                        e.fillInStackTrace();
                    }
                }
            }
        }.start();
    }
}

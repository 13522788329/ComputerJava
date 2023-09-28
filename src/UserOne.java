import java.net.*;
import java.util.Date;
import java.util.Scanner;

public class UserOne {
    public static void main(String[] args) throws SocketException {
        DatagramSocket ds = new DatagramSocket(8888);
        Scanner scanner = new Scanner(System.in);
        new Thread() {
            //接受数据的线程
            @Override
            public void run() {
                boolean threadLock = true;
                while (threadLock) {
                    String next = scanner.next();
                    try {
                        DatagramPacket dp = new DatagramPacket(next.getBytes(), next.getBytes().length,InetAddress.getByName("127.0.0.1"), 7777);
                        ds.send(dp);
                        threadLock = false;
                    }
                    catch (Exception exception) {
                        exception.fillInStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            //发送数据的线程
            @Override
            public void run() {
                boolean threadLock = true;
                while (threadLock) {
                    try {
                        DatagramPacket dp = new DatagramPacket(new byte[1024], 1024, InetAddress.getByName("127.0.0.1"), 7777);
                        ds.receive(dp);
                        byte[] data = dp.getData();
                        int length = data.length;
                        System.out.println(new Date().toString());
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


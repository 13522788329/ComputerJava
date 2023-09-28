import java.net.*;
public class UdpServerImpl {
    public static void main(String[] args) throws Exception {
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        DatagramSocket ds = new DatagramSocket(5678);
        boolean threadLock = true;
        while (threadLock) {
            ds.receive(dp);
            System.out.println(new String(buf, 0, dp.getLength()));
            threadLock = false;
        }

    }
}

package serverAdmin;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;



public class serverAdmin {


    public static void main(String[] args) throws IOException {

        DatagramSocket serverSocket = new DatagramSocket(8000);
        //boolean bye=false;

        //int c=5;
        while(true) //instead of c i want to use true
        {
            byte[] receivebuffer = new byte[1024];

            byte[] sendbuffer;

            DatagramPacket recvdpkt = new DatagramPacket(receivebuffer, receivebuffer.length);

            serverSocket.receive(recvdpkt);


            InetAddress IP = recvdpkt.getAddress();

            int portno = recvdpkt.getPort();

            String clientdata = new String(recvdpkt.getData());

            System.out.println("\nClient : "+ clientdata);

            System.out.print("\nServerAdmin : ");


            BufferedReader serverRead = new BufferedReader(new InputStreamReader (System.in) );
            String serverdata = serverRead.readLine();

            sendbuffer = serverdata.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, IP,portno);
            serverSocket.send(sendPacket);
            //here the check condition for serverdata which must be bye

            if(serverdata.equalsIgnoreCase("quit"))
            {
                System.out.println("connection ended by serverAdmin");
                break;
            }


        }
        serverSocket.close();
    }

}


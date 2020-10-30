package segmentedfilesystem;
import java.net.DatagramPacket;

/* Decides whether a packet is a header or a data packet,
constructs the packet, and adds it to the correct ReceivedFile object.
*/
public class PacketManager {

    //Take in a packet from FileRetriever and convert it into
    //the custom data type 'Packet'
    Packet packet;
    public PacketManager(DatagramPacket inputPacket){
        byte[] data = inputPacket.getData();
        int length = inputPacket.getLength();
        Packet packet = new Packet(data,length);
        this.packet = packet;
    }



    public static boolean allPacketsReceived(){
        return false;
    }
}

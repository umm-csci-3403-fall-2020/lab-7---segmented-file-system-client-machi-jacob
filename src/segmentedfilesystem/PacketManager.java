package segmentedfilesystem;
import java.net.DatagramPacket;

/* Decides whether a packet is a header or a data packet,
constructs the packet, and adds it to the correct ReceivedFile object.
*/
public class PacketManager {

    //Take in a packet from FileRetriever and convert it into
    //the custom data type 'Packet'
    Packet packet;
    boolean header;
    boolean lastPacket;
    byte[] fileIDs = {-1,-1,-1}; //Change to ints
    public PacketManager(){

    }

    //Only for normal data packets
    public void checkFileIDs(){
        for (int i=0; i<fileIDs.length; i++){
            if (packet.getFileID() == fileIDs[i]){
                //put in existing file
            } else {
                //create new file
                //update fileIDs to hold the fileID
            }
        }
    }

    public void sortPacket(DatagramPacket inputPacket){
        byte[] data = inputPacket.getData();
        int length = inputPacket.getLength();
        Packet packet = new Packet(data,length);

        if (header){
            //do header stuff
            //Get the name of the file
        } else if (lastPacket) {
            //do lastPacket stuff
            //Set the maximum number of packets
        } else {
            checkFileIDs();
        }
    }


    public static boolean allPacketsReceived(){
        return false;
    }
}

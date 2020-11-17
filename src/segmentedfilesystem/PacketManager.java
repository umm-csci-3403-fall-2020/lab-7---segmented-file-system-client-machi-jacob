package segmentedfilesystem;
import java.net.DatagramPacket;


/* Decides whether a packet is a header or a data packet,
constructs the packet, and adds it to the correct ReceivedFile object.
*/
public class PacketManager {

    int[] fileIDs = {-1,-1,-1};
    int fileIDLocation = 0;
    static ReceivedFile file1 = new ReceivedFile();
    static ReceivedFile file2 = new ReceivedFile();
    static ReceivedFile file3 = new ReceivedFile();

    //This takes a packet and assigns it to the correct file
    //based on the packet's fileID
    public void checkFileIDs(Packet packet){
        int fileID = packet.getFileID();

        for (int i=0; i<fileIDs.length; i++) {
            if (fileID == fileIDs[0]) {
                file1.collectPackets(packet);
            } else if (fileID == fileIDs[1]) {
                file2.collectPackets(packet);
            } else if (fileID == fileIDs[2]) {
                file3.collectPackets(packet);
            } else {
                checkFiles(packet);
                fileIDs[fileIDLocation] = fileID;
                fileIDLocation++;
            }
        }
    }

    //Checks to see if an initialized ReceivedFile file has been used
    public void checkFiles(Packet packet){
        if (file1 == null){
            file1 = new ReceivedFile();
            System.out.println("Initialized File1.");
        }
        if(fileIDs[0] == -1){
            file1.newFile(packet);
        } else if (fileIDs[1] == -1){
            file2.newFile(packet);
        } else if (fileIDs[2] == -1) {
            file3.newFile(packet);
        } else {
            System.out.println("No more files!");
        }
    }

    //Sorts the packets by converting the Datagram packet to
    //the custom 'Packet' class
    public void sortPacket(DatagramPacket inputPacket){
        byte[] data = inputPacket.getData();
        int length = inputPacket.getLength();
        Packet packet = new Packet(data,length);

        checkFileIDs(packet);
    }

    public static boolean allPacketsReceived(){
        //if file1 & file2 & file3 are all done,
        //then return true. otherwise return false
        if (file1.allPacketsReceived() && file2.allPacketsReceived() && file3.allPacketsReceived()){
            return true;
        } else {
            return false;
        }
    }
}

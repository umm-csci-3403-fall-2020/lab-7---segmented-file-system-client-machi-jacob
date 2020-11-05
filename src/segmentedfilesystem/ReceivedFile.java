package segmentedfilesystem;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeMap;

//Collects all the packets for a single file.
public class ReceivedFile {

    TreeMap<Integer, byte[]> tree;
    String fileName;
    int lastPacketNum;
    byte[] finalByteArray;

    //Creates a new 'file' that's actually a TreeMap
    public void newFile(Packet packet){
        TreeMap<Integer, byte[]> tree = new TreeMap<>();
        if (!packet.isHeader()) {
            tree.put(packet.getPacketNum(), packet.getPacketData());
        } else {
            fileName = packet.getFileName();
        }

        if (packet.isLastPacket()){
            lastPacketNum = packet.getPacketNum();
        }

        this.tree = tree;
    }

    //Collects the packets and puts them in the TreeMap
    //or collects the filename if the packet is a header
    public void collectPackets(Packet packet){
        if (!packet.isHeader()) {
            this.tree.put(packet.getPacketNum(), packet.getPacketData());
        } else {
            fileName = packet.getFileName();
        }

        if (packet.isLastPacket()){
            lastPacketNum = packet.getPacketNum();
        }

        if(allPacketsReceived()){
            writeFile();
        }
    }


    //Writes all of the values in the TreeMap to a byte array
    public void writeToByteArray(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            for (int i = 0; i < lastPacketNum; i++) {
                outputStream.write(tree.get(i));
            }
            finalByteArray = outputStream.toByteArray();
        } catch (IOException ioe){
            System.out.println("IOException in ReceivedFile.writeToByteArray");
            System.out.println(ioe);
        }
    }

    //Writes the final byte array to a file
    public void writeFile(){
        writeToByteArray();
        try (FileOutputStream fos = new FileOutputStream(fileName)){
            fos.write(finalByteArray);
        } catch (IOException ioe){
            System.out.println("IOException in ReceivedFile.writeFile");
            System.out.println(ioe);
        }
    }

    public boolean allPacketsReceived(){
        //Checks if all of the packets in the file have been received
        return false;
    }
}

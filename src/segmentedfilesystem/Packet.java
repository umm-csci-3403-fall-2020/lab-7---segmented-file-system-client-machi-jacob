package segmentedfilesystem;

//Custom data type that implements useful functions of a packet
public class Packet {

    byte[] data;
    int length;
    boolean header;

    public Packet(byte[] data, int length){
        this.length = length;
        this.data = data;
    }

    //Gets the status byte of the packet
    public int getStatusByte(){
        byte statusByte = data[0];//First byte
        int status = Byte.toUnsignedInt(statusByte);
        return status;
    }

    //Gets the file ID of the packet
    public int getFileID(){
        byte fileIDByte = data[1];//Second byte
        int fileID = Byte.toUnsignedInt(fileIDByte);
        return fileID;
    }

    //Gets the packet number of the packet
    //Only call if the packet is a data packet
    public int getPacketNum(){
        byte first = data[2];//Third byte
        byte second = data[3];//Fourth byte
        int firstInt = Byte.toUnsignedInt(first);
        int secondInt = Byte.toUnsignedInt(second);
        int packetNum = firstInt*256 + secondInt;
        return packetNum;
    }

    //Checks if the packet is a header packet or not
    //Sets the boolean 'header' to true if true, and false if not
    public boolean isHeader(){
        int statusByte = Byte.toUnsignedInt(data[0]);
        if (statusByte % 2 == 0){
            this.header = true;
            return true;
        } else {
            this.header = false;
            return false;
        }
    }

    //Checks if the packet is the last packet for the file
    public boolean isLastPacket(){
        int statusByte = Byte.toUnsignedInt(data[0]);
        return (statusByte % 4  == 3 );
    }

    //Gets the file name from the header packet
    public String getFileName(){
        if (header){ //Will not work if it isn't a header packet
            byte[] fileName = new byte[length-2];
            for (int i = 2; i<length;i++)
                fileName[i-2] = data[i];
            String name = new String(fileName);
            return name;
        } else
            return null;
    }

    //Gets the packet data from the data packet
    public byte[] getPacketData(){
        if (!header){
            byte[] packetData = new byte[length-4];
            for (int i = 4; i<length; i++)
                packetData[i-4] = data[i];
            return packetData;
        } else
            return null;
    }

}

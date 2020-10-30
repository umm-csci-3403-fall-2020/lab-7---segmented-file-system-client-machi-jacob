package segmentedfilesystem;

//Custom data type that implements useful functions of a packet
public class Packet {

    byte[] data;
    int length;
    public Packet(byte[] data, int length){
        this.length = length;
        this.data = data;
    }

    public byte getStatusByte(){
        byte status = data[0];//First byte
        return status;
    }

    public byte getFileID(){
        byte fileID = data[1];//Second byte
        return fileID;
    }

    //Only call if the packet is a data packet
    public int getPacketNum(){
        byte first = data[2];//Third byte
        byte second = data[3];//Fourth byte
        int firstInt = Byte.toUnsignedInt(first);
        int secondInt = Byte.toUnsignedInt(second);
        int packetNum = firstInt + secondInt;//I assume that we add these together
        return packetNum;
    }

    public boolean isHeader(){
        int statusByte = Byte.toUnsignedInt(data[0]);
        if (statusByte % 2 == 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean isLastPacket(){
        int statusByte = Byte.toUnsignedInt(data[0]);
        if (statusByte  == 3%4 ){
            return true;
        } else {
            return false;
        }
    }

    public byte getFileName(){
        byte fileName = 0;
        return fileName;
    }

    public byte getPacketData(){
        byte packetData = 0;
        return packetData;
    }

}

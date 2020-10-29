package segmentedfilesystem;

public class Packet {

    public byte getFileID(){
        byte fileID = 0;
        return fileID;
    }

    public byte getStatusByte(){
        byte status = 0;
        return status;
    }

    public byte getPacketNum(){
        //This deals with two bytes, so we must convert into
        //a non-negative packet num
        byte packetNum = 0;
        return packetNum;
    }

    public byte getFileName(){
        byte fileName = 0;
        return fileName;
    }

    public byte getPacketData(){
        byte packetData = 0;
        return packetData;
    }

    public boolean isLastPacket(){
        //If status byte is 3 % 4 then it's the last packet
        return false;
    }
}

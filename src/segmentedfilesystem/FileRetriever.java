package segmentedfilesystem;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class FileRetriever {

	InetAddress server;
	int port;

	public FileRetriever(String server, int port) throws java.net.UnknownHostException {
        // Save the server and port for use in `downloadFiles()`
        //...
		if (server.equals("localhost")){
			this.server = InetAddress.getLocalHost();
		} else {
			this.server = InetAddress.getByName(server);
		}
		this.port = port;
	}

	public void downloadFiles() throws java.io.IOException{
        // Do all the heavy lifting here.
        // This should
        //   * Connect to the server
        //   * Download packets in some sort of loop
        //   * Handle the packets as they come in by, e.g.,
        //     handing them to some PacketManager class
        // Your loop will need to be able to ask someone
        // if you've received all the packets, and can thus
        // terminate. You might have a method like
        // PacketManager.allPacketsReceived() that you could
        // call for that, but there are a bunch of possible
        // ways.

		// Send an empty packet to the server.
		byte[] sendBuf = new byte[256];
		DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, server, port);
		DatagramSocket socket = new DatagramSocket(port, server);
		socket.send(packet);


	}

}


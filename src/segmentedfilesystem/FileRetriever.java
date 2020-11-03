package segmentedfilesystem;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class FileRetriever {
	InetAddress server;
	int port;

	public FileRetriever(String server, int port) throws java.net.UnknownHostException {
        // Save the server and port for use in `downloadFiles()`
		try {
			if (server.equals("localhost")) {
				this.server = InetAddress.getLocalHost();
			} else {
				this.server = InetAddress.getByName(server);
			}
			this.port = port;
		} catch (UnknownHostException uhe) {
			System.out.println("UnknownHostException in FileRetriever.");
			System.out.println(uhe);
		}
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

		try {
			// Send an empty packet to the server.
			byte[] packetBuf = new byte[1028];
			boolean notDone = true;
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(packetBuf, packetBuf.length, server, port);
			socket.send(packet);
			PacketManager packetManager = new PacketManager();

			//Wait for incoming packets to arrive and send them to PacketManager
			//Will terminate once all packets have been received
			while (notDone) {
				packet = new DatagramPacket(packetBuf, packetBuf.length);
				socket.receive(packet);

				packetManager.sortPacket(packet);

				if (PacketManager.allPacketsReceived()) {
					notDone = false;
					socket.close();
				}
			}
		} catch (IOException ioe) {
			System.out.println("IOException in FileRetriever.");
			System.out.println(ioe);
		}
	}
}


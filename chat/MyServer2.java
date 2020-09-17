import java.net.ServerSocket;
import java.io.IOException;

public class MyServer2 {
	public static int portNum;

	public static void main(String[] args) {
		ServerSocket serv = null;

		portNum = Integer.parseInt( args[0] );

		try {
			serv = new ServerSocket( portNum, 30 );
		} catch ( IOException e ) {
			System.err.println( "Server ERROR!" );
			System.exit(1);
		}
	}
}
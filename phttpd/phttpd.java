import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Phttpd {
	public static void main(String[] args) {
		ServerSocket servSock = null;
		Socket clientSock = null;

		try {
			servSock = new ServerSocket( 8080, 30 );
			clientSock = servSock.accept();
			// debug
			System.out.println( "client connected." );


		} catch ( IOException e ) {
			System.err.println( "server ERROR." );
			System.exit(1);
		}
	}
}
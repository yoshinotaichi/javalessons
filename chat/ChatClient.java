import java.net.Socket;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.io.InputStream;
import java.io.OutputStream;

public class ChatClient {
	public static void main(String[] args) {
		try {
			T1 t = new T1();

			t.openConnection( args[0], Integer.parseInt( args[1] ) );
			t.main_proc();
		} catch ( Exception e ) {
			System.err.println( e );
			System.exit( 1 );
		}
	}
}



class T1 {
	protected Socket sock;
	public OutputStream outST;
	public BufferedInputStream inBIS;

	public void openConnection( String host, int port ) throws IOException, UnknownHostException {
		sock = new Socket( host, port );
		outST = sock.getOutputStream();
		inBIS = new BufferedInputStream( sock.getInputStream() );
	}


	public void main_proc() throws IOException {
		try {
			StreamConnector stdin_to_socket = new StreamConnector( System.in, outST );
			StreamConnector socket_to_stdout = new StreamConnector( inBIS, System.out );

			Thread input_thread = new Thread( stdin_to_socket );
			Thread output_thread = new Thread( socket_to_stdout );

			input_thread.start();
			output_thread.start();
		} catch ( Exception e ) {
			System.err.println( e );
			System.exit( 1 );
		}
	}
}


class StreamConnector implements Runnable {
	InputStream src = null;
	OutputStream dist = null;

	public StreamConnector( InputStream in, OutputStream out ) {
		src = in;
		dist = out;
	}

	public void run() {
		byte[] buff = new byte[1024];

		while ( true ) {
			try {
				int n = src.read( buff );
				if ( n > 0 ) {
					dist.write( buff, 0, n );
				}
			} catch ( Exception e ) {
				System.err.println( e );
			}
		}
	}
}
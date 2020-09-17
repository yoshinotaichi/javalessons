import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class MyServer {
	static int port = 7777;
	static ServerSocket servSock = null;
	static ArrayList<Socket> connections = null;

	public static void main(String[] args) {

		try {
			// サーバソケットを生成する
			servSock = new ServerSocket( port );
			System.out.println( "ALOHA" );

			// クライアントを待ち受ける
			while ( true ) {
				Socket clientSock = servSock.accept();
				System.out.println( "client connected." );

				if ( connections == null ) {
					connections = new ArrayList<Socket>();
				}

				// クライアントを登録する
				connections.add( clientSock );
				System.out.println( "add socket...done." );
				System.out.println( "num connections: " + connections.size() );

				// クライアントをスレッドに渡す
				ConnectionHandler cHandler = new ConnectionHandler( clientSock );
				Thread th = new Thread( cHandler );
				th.start();
			}
		} catch ( IOException e ) {
			System.err.println( e );
			System.exit( 1 );
		}
	}
}




class ConnectionHandler implements Runnable {
	Socket sock;
	BufferedReader inBR;
	PrintWriter outPW;
	Scanner sc;
	String name;

	public ConnectionHandler( Socket socket ) {
		System.out.println( "ConnectionHandler class born." );

		sock = socket;
		sc = new Scanner( System.in );
		try {
			inBR = new BufferedReader( new InputStreamReader( sock.getInputStream() ) );
			outPW = new PrintWriter( sock.getOutputStream() );
		} catch ( IOException e ) {
			System.err.println( "ERROR at constructor_ConnectionHandler." );
			System.err.println( e );
			System.exit( 1 );
		}
	}

	public void run() {
		try {
			// 動作確認
			System.out.println( "running start." );
			System.out.println( sock.getInetAddress().toString() );

			// ユーザ名を要求する & 取得する
			String line = "input name: ";
			outPW.println( line );
			outPW.flush();
			line = inBR.readLine();
			System.out.println( line );

			// ユーザ名を変数に格納する
			name = line;


			// メッセージを送受信し続ける
			line = inBR.readLine();
			while ( !"quit".equals( line )) {
				MyServer.sendAll( name + "> " + line );
				line = inBR.readLine();
			}
		} catch ( Exception e ) {
			System.err.println( e );
			System.exit( 1 );
		}
	}
}
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Vector;
import java.util.Enumeration;

public class ChatServer {
	static final int DEFAULT_PORT = 6000;
	static ServerSocket servSock;
	static Vector connections;

	public static void main(String[] args) {
		int port = DEFAULT_PORT;

		try {
			// サーバソケットを生成する
			servSock = new ServerSocket( port );

			// 無限ループ
			while ( true ) {
				// クライアントの接続を受け付ける
				Socket clientSock = servSock.accept();

				// クライアントの接続を配列に保存する
				addConnection( clientSock );

				// クライアント処理のスレッドを走らせる
				Thread clientTh = new Thread( new ClientProc( clientSock ) );
				clientTh.start();
			}
		} catch ( IOException e ) {
			System.err.println( e );
			System.exit( 1 );
		}
	}

	// 新しい接続を追加するメソッド
	public static void addConnection( Socket sock ) {
		if ( connections == null ) {
			connections = new Vector();
		}

		connections.addElement( sock );
	}

	// 接続を削除する
	public static void deleteConnection( Socket sock ) {
		if ( connections != null ) {
			connections.removeElement( sock );
		}
	}

	// 各クライアントにメッセージを送信する
	public static void sendAll( String msg ) {
		if ( connections != null ) {
			for ( Enumeration en = connections.elements(); en.hasMoreElements(); ) {
				try {
					PrintWriter pw = new PrintWriter( ( ( Socket ) en.nextElement() ).getOutputStream() );
					pw.println( msg );
					pw.flush();
				} catch ( IOException e ) {
					System.err.println( e );
				}
			}
		}
	}
}



class ClientProc implements Runnable {
	Socket sock;
	BufferedReader inBR;
	PrintWriter outPW;
	String name = null;

	// コンストラクタ
	public ClientProc( Socket socket ) throws IOException {
		sock = socket;
		inBR = new BufferedReader( new InputStreamReader( sock.getInputStream() ) );
		outPW = new PrintWriter( sock.getOutputStream() );
	}

	// スレッド処理
	public void run() {
		try {
			// ユーザ名を要求・入力する
			while ( name == null ) {
				outPW.print( "put your name: " );
				outPW.flush();
				name = inBR.readLine();
			}

			// クライアントからのメッセージを1行読み取る
			String line = inBR.readLine();

			// メッセージが "quit" でない限り、ループ
			while ( !"quit".equals( line ) ) {
				ChatServer.sendAll( name + ">" + line );
				line = inBR.readLine();
			}

			// "quit"でループを脱出した後、接続する処理
			ChatServer.deleteConnection( sock );
			sock.close();
		} catch ( IOException e ) {
			System.err.println( e );
		}
	}
}

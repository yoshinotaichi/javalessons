import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class MyClient {
	static int port = 7777;
	static BufferedReader inBR = null;
	static PrintWriter outPW = null;

	public static void main(String[] args) {
		try {
			// サーバに接続する
			Socket sock = new Socket( "localhost", port );

			// 入出力を準備する
			inBR = new BufferedReader( new InputStreamReader( sock.getInputStream() ) );
			outPW = new PrintWriter( sock.getOutputStream() );
			Scanner sc = new Scanner( System.in );

			// ユーザ名の要求メッセージを受信する & ユーザ名を取得・送信する
			String line = inBR.readLine();
			System.out.print( line );
			line = sc.nextLine();
			outPW.println( line );
			outPW.flush();

			// メッセージを送信する
		} catch ( IOException e ) {
			System.err.println( e );
			System.exit( 1 );
		}
	}
}
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class TestFile {
	public static void main(String[] args) {
		File jfile = new File( "./test.txt" );

		try ( FileReader fr = new FileReader( jfile ); BufferedReader br = new BufferedReader( fr ) ) {
			String line;

			// ファイルを1行ずつ処理する無限ループ
			while ( ( line = br.readLine() ) != null ) {
				String[] row = line.split( "," );
			}
		} catch ( IOException e ) {
			System.out.println( "io exception." );
		}
	}
}
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class PrintFile {
	public static void main(String[] args) {
		File inputFile = new File( "test.txt" );

		try ( FileReader fr = new FileReader( inputFile ); BufferedReader br = new BufferedReader( fr ); ) {
			String line = "";

			while ( ( line = br.readLine() ) != null ) {
				String[] data = line.split( "," );

				System.out.println( "id: " + data[0] );
				System.out.println( "name: " + data[1] );
			}

		} catch ( IOException e ) {
			System.out.println( "io exception ..." );
		}
	}
}
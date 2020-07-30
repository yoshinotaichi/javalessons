import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;

public class CountLines {
	public static void main(String[] args) {
		Path inputPath = Paths.get("test.txt");

		try {
			int numLines = ( int )Files.lines( inputPath ).count();

			System.out.println( numLines );
		} catch ( IOException e ) {
			System.out.println( "io exception" );
		}
	}
}

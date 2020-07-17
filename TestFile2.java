import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.NoSuchFileException;
import java.io.IOException;

public class TestFile2 {
	public static void main(String[] args) throws IOException {
		Path file = Paths.get( "./test.txt" );
		int numStudents = (int) Files.lines( file ).count();

		System.out.println( numStudents );
	}
}
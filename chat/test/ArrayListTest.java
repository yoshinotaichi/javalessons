// 要素を追加でする配列のテスト
// 20200917
import java.util.Scanner;
import java.util.ArrayList;

public class ArrayListTest {
	public static ArrayList<String> names;

	public static void main(String[] args) {
		while ( true ) {
			System.out.print( "input name: " );
			Scanner sc = new Scanner( System.in );
			String line = sc.nextLine();

			if ( "quit".equals( line ) ) {
				for ( String name : names ) {
					System.out.println( name );
				}
				break;
			}

			if ( names == null ) {
				names = new ArrayList<String>();
			}

			names.add( line );
		}
	}
}
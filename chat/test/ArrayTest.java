// 配列でデータを管理する
// 要素数を途中で増やしたい場合のテスト...扱いが面倒なので、.add()できる方が良い
// 20200917
import java.util.Scanner;

public class ArrayTest {
	private static String[] names;

	public static void main(String[] args) {
		int numArray = 0;

		while ( true ) {
			System.out.print( "input name: " );
			Scanner sc = new Scanner( System.in );
			String line = sc.nextLine();

			if ( "quit".equals( line ) ) {
				for ( int i=0; i<names.length; i++ ) {
					System.out.println( names[i] );
				}
				break;
			}

			String[] tmp = new String[ numArray + 1 ];
			if ( names != null ) {
				System.arraycopy( names, 0, tmp, 0, numArray);
			}
			tmp[ numArray ] = line;
			names = tmp;
			numArray++;
		}
	}
}
import java.util.Scanner;

public class Test {
  	public static void main(String[] args) {

  		Students studentsAll = new Students( 3 );
  		studentsAll.showData();

  	}
  }


  class Students {
  	Student[] members;

  	Students( int num ) {
  		this.members = new Student[ num ];

  		Scanner sc = new Scanner( System.in );

  		for ( int i=0; i<num; i++ ) {
  			System.out.print( "input ID: " );
  			int id = Integer.parseInt( sc.nextLine() );

  			System.out.print( "input Name: " );
  			String name = sc.nextLine();

  			this.members[i] = new Student( id, name );
  		}
  	}

  	public void showData() {
  		int numMembers = this.members.length;

  		for ( int i=0; i<numMembers; i++ ) {
  			this.members[i].showData();
  		}
  	}
  }
  
  
  class Student {
  	private String id;
  	private String name;
  
  	Student( int id, String name ) {
  		setId( id );
  		setName( name );
  	}
  
  	public void showData() {
  		System.out.println( "ID: " + getId() );
  		System.out.println( "Name: " + getName() );
  	}
  
  	private void setId( int num ) {
  		if ( num > 0 && num < 999 ) {
  			String header = "000";
  
  			if ( num < 10 ) {
  				header = "00";
  			} else if ( num < 100 ) {
  				header = "0";
  			} else {
  				header = "";
  			}
  			this.id = header + num;
  		} else {
  			System.out.println( "id is wrong." );
  		}
  	}
  
  	public String getId() {
  		return this.id;
  	}
  
  	private void setName( String text ) {
  		if( text.length() > 1 ) {
  			this.name = text;
  		} else {
  			System.out.println( "Name needs more than 1 char." );
  		}
  	}
  
  	public String getName() {
  		return this.name;
  	}
  }
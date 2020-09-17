package Fase2;

import static org.junit.Assert.assertEquals;

import java.util.Random;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		

		UsersClass tree = new UsersClass();

	
		DList list=new DList();

		DList remove_successor=new DList();
		DList remove_predecessor=new DList();

		Random rn=new Random();
		UsersClass empty=new UsersClass();

		tree.insertUser(new User("jj","Alberto",30,"H"));
		tree.insertUser(new User("rr","Alberto",35,"H",2));
		tree.insertUser(new User("cc","Alberto",25,"H"));
		tree.insertUser(new User("ss","Javer",32,"H"));
		tree.insertUser(new User("nn","Ana",22,"M"));
		tree.insertUser(new User("dd","Juan",18,"H"));

		User user = new User("dd","Juan",18,"H");


		list.addLast("cc");
		list.addLast("dd");
		list.addLast("jj");
		list.addLast("nn");
		list.addLast("rr");
		list.addLast("ss");

	

		System.out.println();

		System.out.println();

		

		tree.removeUser("ss");

		tree.showLevel();

		System.out.println();

		tree.show();

		System.out.println();

		tree.complaint("rr");

		System.out.println();

		tree.show();

		System.out.println();

		list.removeAll("ss");
		
		DList[] listas = new DList[2];
		listas=tree.extremeUsers();
		System.out.println(listas[0] + " " + listas[1]);
	}
}



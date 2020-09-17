package Fase2;

import java.util.Random;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class UsersTest {

	//auxiliary methods 
	public static boolean equals(DList list1, DList list2) {
		if (list1==list2) return true;
		
		if (list1.getSize()!=list2.getSize()) return false;
		
		for (int i=0; i<list1.getSize(); i++) {
			String s1=list1.getAt(i);
			String s2=list2.getAt(i);
			try {
				if (s1!=null || s2!=null) {
					if (!s1.equals(s2)) 
						return false;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
	
	
	
	//Objects
	UsersClass tree=new UsersClass();
	DList list=new DList();

	DList remove_successor=new DList();
	DList remove_predecessor=new DList();
	
	Random rn=new Random();
	UsersClass empty=new UsersClass();
	
	@Before
	public void setUp() throws Exception {
		//input tree
		//					jj
		//		cc						rr
		//			dd			nn				ss
		
		tree.insertUser(new User("jj","Alberto",30,"H"));
		tree.insertUser(new User("rr","Alberto",35,"H",2));
		tree.insertUser(new User("cc","Alberto",25,"H"));
		tree.insertUser(new User("ss","Javer",32,"H"));
		tree.insertUser(new User("nn","Ana",22,"M"));
		tree.insertUser(new User("dd","Juan",18,"H"));
	
		
		list.addLast("cc");
		list.addLast("dd");
		list.addLast("jj");
		list.addLast("nn");
		list.addLast("rr");
		list.addLast("ss");


		//list after remove root - succesor
		remove_successor.addLast("ss");
		remove_successor.addLast("cc");
		remove_successor.addLast("dd");
	
		//list after remove root - predecessor
		remove_predecessor.addLast("dd");
		remove_predecessor.addLast("cc");
		remove_predecessor.addLast("ss");



	}
	

	@Test
	public void testInsertUser() {
		UsersClass aux=new UsersClass();
		aux.insertUser(new User("dd","Juan",18,"H"));
		
		DList list=new DList();
		list.addLast("dd");
		
		assertEquals("Check insert an user in empty tree.", true, 
				UsersTest.equals(aux.getSortedList(),list) );		
		
		aux.insertUser(new User("ee","Loli",38,"M"));
		list.addLast("ee");

		assertEquals("Check insert an user in empty tree.", true, 
				UsersTest.equals(aux.getSortedList(),list) );	

		
	}

	@Test
	public void testFind() {
		
		assertEquals("Check find, empty tree.", true, 
				empty.findUser("ff")==null);
		
		User user=new User("dd","Juan",18,"H");
		
		assertEquals("Check find, tree.", true, 
				tree.findUser("dd").equals(user));
		
		assertEquals("Check find, non empty tree.", true, 
				tree.findUser("a")==null);
	
	}

	

	@Test
	public void testRemove() {
	
		tree.removeUser("nn");
		list.removeAll("nn");
		
		assertEquals("Check remove a leaf node (nn).", true, 
				UsersTest.equals(tree.getSortedList(),list) );

		tree.removeUser("rr");
		list.removeAll("rr");
		
		assertEquals("Check remove a node (rr) with only a child.", true, 
				UsersTest.equals(tree.getSortedList(),list) );

		tree.removeUser("jj");
		list.removeAll("jj");

		assertEquals("Check remove the root (jj) with two children.", true, 
				UsersTest.equals(tree.getSortedList(),list));

	}

	@Test
	public void testComplaint() {
		User u1=tree.findUser("ss");
		int num1=u1.complaints;
		tree.complaint("ss");
		User u2=tree.findUser("ss");
		int num2=u2.complaints;

		assertEquals("Check complaint < 3.", true, (num1+1)==num2);
		for (int i=1; i<=4; i++) {
			tree.complaint("ss");
		}
	
		list.removeAll("ss");

		assertEquals("Check complaint == 5.", true, 
				UsersTest.equals(tree.getSortedList(),list));
	}

	@Test
	public void testExtremUsers() {
		System.out.println(tree.getSortedList());
		for (int i=1; i<=4; i++) {
			tree.complaint("jj");
			tree.complaint("cc");
		}
		tree.showLevel();
		System.out.println(tree.getSortedList());

		DList[] aList=tree.extremeUsers();
		System.out.println(aList[0].toString());
		System.out.println(aList[1].toString());	
	}

}